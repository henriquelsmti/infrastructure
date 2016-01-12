package br.com.datarey.dao;

import br.com.datarey.context.Context;
import br.com.datarey.util.MessageType;
import br.com.datarey.util.MessageUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JpaUtil {

    private static EntityManagerFactory factory;

    private final Logger LOGGER = Logger.getLogger(JpaUtil.class);

    private Map<Long, EntityManager> pool = new HashMap<>();

    public JpaUtil() {
        try{
            if (factory == null)
                factory = Persistence.createEntityManagerFactory("banco");
        }catch (Exception e){
            LOGGER.error(e);
            throw e;
        }
    }

    public synchronized EntityManager createEntityManager() {
        EntityManager manager = factory.createEntityManager();
        pool.put(Thread.currentThread().getId(), manager);
        return manager;
    }

    public void destroyEntityManager() {
        EntityManager manager = pool.remove(Thread.currentThread().getId());
        manager.close();
    }

    public EntityManager getEntityManager() {
        return pool.get(Thread.currentThread().getId());
    }

    @PreDestroy
    private void closeFactory() {
        factory.close();
    }

}
