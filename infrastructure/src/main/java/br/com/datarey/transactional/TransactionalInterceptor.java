
package br.com.datarey.transactional;

import java.io.Serializable;
import java.lang.reflect.Modifier;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.datarey.dao.JpaUtil;
import br.com.datarey.util.MessageType;
import br.com.datarey.util.MessageUtil;

@Interceptor
@Transactional
@Priority(Interceptor.Priority.APPLICATION)
public class TransactionalInterceptor implements Serializable {

    private static final long serialVersionUID = -7994877557421884197L;

    @Inject
    private JpaUtil jpaUtil;

    @Inject
    private MessageUtil messageUtil;

    @AroundInvoke
    public Object auditar(InvocationContext context) throws Exception {
        if (context.getMethod().getModifiers() != Modifier.PUBLIC || jpaUtil.getEntityManager() != null) {
            return context.proceed();
        }
        TransactionalReturn transactionalReturn = new TransactionalReturn();
        TransactionalThread transaction = new TransactionalThread(context, 
                transactionalReturn, jpaUtil);
        
        Thread t = new Thread(transaction);
        t.start();
        synchronized (t) {
            try {
                t.wait();
            } catch (InterruptedException e) {
                lancarMessage(e.getMessage());
            }
        }
        return transactionalReturn.getValue();
    }

    private void lancarMessage(String message) {
        messageUtil.showMessage(message, MessageType.ERROR);
    }
}
