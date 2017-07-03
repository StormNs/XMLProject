/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entities.AccountType;
import entities.Cast;
import entities.Genres;
import entities.MovieType;
import entities.PersonType;
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
            em.flush();
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
        try {
            if (username.isEmpty()) { //user use email to login
                query = em.createNamedQuery("AccountType.findByEmail");
                query.setParameter("email", email);
            } else { // user use username to login
                query = em.createNamedQuery("AccountType.findByUsername");
                query.setParameter("username", username);
            }
            listAccounts = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAccounts;
    }

    public List getAllAccounts() {
        Query query = em.createNamedQuery("AccountType.findAll");
        return query.getResultList();
    }

    public List getAllMovie() {
        Query query = em.createNamedQuery("MovieType.findAll");
        return query.getResultList();
    }

    public int createMovie(MovieType movie) {
//        List<MovieType> list = movielist;
//       for (MovieType object : list) {
//           Query query =em.createQuery("INSERT INTO Movies (Name, AlternateName, Description, Country, Runtime, CategoryId, Language, ReleaseDate, Rating, ImageCover, TrailerUrl, Director)"
//                   + " VALUES ("+object.getName()+", "+object.getAlternateName()+", "+object.getDescription()+", "+object.getCountry()+", "+object.getRuntime()+", NULL, "+object.getLanguage()+", "+object.getReleaseDate()+", "+object.getRating()+", "+object.getImageCover()+", NULL, "+object.getDirector()+");");
//           query.getResultList();
//       }
//        for (MovieType object : list) {
            em.getTransaction().begin();
            em.persist(movie);
            em.flush();
            em.getTransaction().commit();
            em.close();
            return movie.getId();

//        }
        
    }

    public int createGenre(Genres genre) {
        em.getTransaction().begin();
        em.persist(genre);
        em.flush();
        em.getTransaction().commit();
        em.close();
        return genre.getId();
    }

    public int createPerson(PersonType person) {
        em.getTransaction().begin();
        em.persist(person);
        em.flush();
        em.getTransaction().commit();
        em.close();
        return person.getId();
    }

    public int createCast(Cast cast) {
            em.getTransaction().begin();
            em.persist(cast);
            em.flush();
            em.getTransaction().commit();
            em.close();
            return cast.getId();
    }

}
