/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entities.Accounts;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author StormNs
 */
public class DAO implements Serializable {

    EntityManagerFactory emf = null;
    EntityManager em = null;

    public DAO() {
        try {
            emf = Persistence.createEntityManagerFactory("XMLProjectPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean persist(Object object) {
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.persist(object);
            tran.commit();
        } catch (Exception e) {
            tran.rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List findAccounts(String email, String username) {
        List listAccounts = null;
        Query query = null;
        if (username.isEmpty()) { //user use email to login
            query = em.createNamedQuery("Accounts.findByEmail");
            query.setParameter("email", email);
        } else { // user use username to login
            query = em.createNamedQuery("Accounts.findByUsername");
            query.setParameter("username", username);
        }
        listAccounts = query.getResultList();
        return listAccounts;
    }

    public List getAllAccounts() {
        Query query = em.createNamedQuery("Accounts.findAll");
        return query.getResultList();
    }

    public List getAllGenres() {
        Query query = em.createNamedQuery("Accounts.findAll");
        return query.getResultList();
    }

    public List getAllMovies() {
        Query query = em.createNamedQuery("Accounts.findAll");
        query.setMaxResults(100);
        return query.getResultList();
    }

}
