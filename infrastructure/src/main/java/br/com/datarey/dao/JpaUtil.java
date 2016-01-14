package br.com.datarey.dao;

import org.apache.log4j.Logger;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class JpaUtil {

    private static EntityManagerFactory factory;

    private final Logger LOGGER = Logger.getLogger(JpaUtil.class);

    private Map<Long, EntityManager> pool = new HashMap<>();

    public JpaUtil() {
        try{
            if (factory == null){
                addClassInPersistenceXML();
                factory = Persistence.createEntityManagerFactory("banco");
            }
        }catch (Exception e){
            LOGGER.trace(e);
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

    private void addClassInPersistenceXML(){
    }
}
