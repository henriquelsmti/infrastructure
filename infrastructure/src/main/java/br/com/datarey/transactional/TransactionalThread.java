package br.com.datarey.transactional;

import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.datarey.dao.JpaUtil;

public class TransactionalThread implements Runnable {

    private InvocationContext context;
    private TransactionalReturn transactionalReturn;
    private JpaUtil jpaUtil;
   

    public TransactionalThread(InvocationContext context, TransactionalReturn transactionalReturn, JpaUtil jpaUtil) {
        super();
        this.context = context;
        this.transactionalReturn = transactionalReturn;
        this.jpaUtil = jpaUtil;
    }



    @Override
    public void run() {
        synchronized(this){
            EntityManager manager = jpaUtil.createEntityManager();
            try {
                manager.getTransaction().begin();
                transactionalReturn.setValue(context.proceed());
                manager.getTransaction().commit();
            } catch (Exception t) {
                if (manager.getTransaction() != null && manager.getTransaction().isActive()) {
                    manager.getTransaction().rollback();
                }
                throw new RuntimeException(t);
            } finally {
                jpaUtil.destroyEntityManager();
                notify();
            }
        }
    }

}
