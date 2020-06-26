package com.fun.committee.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;

/**
 * Created by harshams on 26/06/2020
 */
public class AbstractDao {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public Session openSession(){
        return entityManagerFactory.createEntityManager().unwrap(Session.class);
    }

    public void beginTransaction(Session session){
        session.getTransaction().begin();
    }

    public void commitTransaction(Session session){
        session.getTransaction().commit();
    }

    public void rollbackTransaction(Session session){
        session.getTransaction().rollback();
    }

    public void closeSession(Session session){
        session.close();
    }
}
