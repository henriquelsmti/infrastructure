package br.com.datarey.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class JpaUtil {

    private static EntityManagerFactory factory;

    private Map<Long, EntityManager> pool = new HashMap<>();

    public JpaUtil() {
        if (factory == null)
            factory = Persistence.createEntityManagerFactory("banco");
    }

    public synchronized EntityManager createEntityManager() {
        EntityManager manager = factory.createEntityManager();
        pool.put(Thread.currentThread().getId(), manager);
        return manager;
    }

    public void destroyEntityManager() {
        EntityManager manager = pool.get(Thread.currentThread().getId());
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
