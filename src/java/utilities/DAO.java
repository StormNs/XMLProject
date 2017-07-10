/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entities.AccountType;
import entities.Cast;
import entities.Genres;
import entities.MovieGenres;
import entities.MovieImages;
import entities.MovieType;
import entities.PersonType;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import servlets.LoginServlet;

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
            em.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void closeEM(){
        if(em != null){
            em.close();
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
            em.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAccounts;
    }

    //check Exist Function
    //<editor-fold>
    public Boolean personIsExisted(PersonType person) {
        Query query = em.createNamedQuery("PersonType.findByName");
        query.setParameter("name", "%" + person.getName() + "%");
        List<PersonType> list = (List<PersonType>) query.getResultList();
        em.clear();
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean accountIsExisted(String username, String email) {
        Query query = em.createQuery("SELECT acc FROM AccountType acc"
                + " WHERE acc.email = ?1 OR acc.username = ?2", AccountType.class);
        query.setParameter(1, email);
        query.setParameter(2, username);
        List<AccountType> list = query.getResultList();
        if (list.isEmpty()) {
            return false;
        }
        return true;
    }

    public Boolean genresIsExisted(Genres genre) {
        Query query = em.createNamedQuery("Genres.findByName");
        query.setParameter("name", "%" + genre.getName() + "%");
        List<Genres> list = (List<Genres>) query.getResultList();
        em.clear();
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean movieGenreExisted(int movieId, int genreId) {
        Query query = em.createNativeQuery("SELECT * FROM MovieGenres WHERE"
                + " MovieId = '" + movieId + "' AND GenreId = '" + genreId + "'", MovieGenres.class);

        List<MovieGenres> list = (List<MovieGenres>) query.getResultList();
        em.clear();
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean moviesExisted(MovieType movie) {
        Query query = em.createNativeQuery("SELECT * FROM Movies WHERE"
                + " Name = ?name AND ReleaseDate = ?date", MovieType.class);
        query.setParameter("name", movie.getName());
        query.setParameter("date", movie.getReleaseDate());
        em.clear();
        List<MovieType> list = (List<MovieType>) query.getResultList();
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean movieCastingExisted(int movieId, int personId) {
        Query query = em.createQuery("SELECT c FROM Cast c WHERE c.movieId.id = ?1 AND c.actorId.id  = ?2", Cast.class);
        query.setParameter(1, movieId);
        query.setParameter(2, personId);
        em.clear();
        List<Cast> list = query.getResultList();
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    //</editor-fold> 

    //get DTO function
    //<editor-fold>
    public List getAllAccounts() {
        Query query = em.createNamedQuery("AccountType.findAll");
        em.clear();
        return query.getResultList();
    }

    public List getAllMovie() {
        Query query = em.createNamedQuery("MovieType.findAll");
        em.clear();
        return query.getResultList();
    }

    public List getMovieForSearch() {
        Query query = em.createNativeQuery("SELECT TOP(50) Id, Name, AlternateName, "
                + "ImageCover FROM Movies ORDER BY ReleaseDate DESC", MovieType.class);
        
        List list = query.getResultList();
        em.close();
        return list;
    }

    public List getTopMovie() {
        Query query2 = em.createNativeQuery("SELECT TOP(20) Id, Name, AlternateName, "
                + "ImageCover FROM Movies ORDER BY Rating DESC", MovieType.class);
//Query query = em.createQuery("SELECT m FROM MovieType a WHERE a.id, a.alternateName");
        List list2 = query2.getResultList();
        em.close();
        return list2;
    }

    public MovieType getMovieByName(String name) {
        Query query = em.createQuery("SELECT m FROM MovieType m WHERE m.name Like ?1", MovieType.class);
        query.setParameter(1, "%" + name + "%");
        em.clear();
        return (MovieType) query.getResultList().get(0);
    }

    public Genres getGenreByName(String name) {
        Query query = em.createQuery("SELECT g FROM Genres g WHERE g.name Like ?1", Genres.class);
        query.setParameter(1, "%" + name + "%");
        em.clear();
        return (Genres) query.getResultList().get(0);
    }

    public PersonType getActorByName(String name) {
        Query query = em.createQuery("SELECT a FROM PersonType a WHERE a.name Like ?1", PersonType.class);
        query.setParameter(1, "%" + name + "%");
        em.clear();
        return (PersonType) query.getResultList().get(0);
    }
    //</editor-fold>

    // Create and edit function 
    //<editor-fold>
    public void updateMovieImageCover(String link, MovieType movie) {
        try {
            movie.setImageCover(link);
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    public void updateActorImageCover(String link, PersonType person) {
        try {
            person.setImageUrl(link);
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    public Boolean createAccounts(String username, String email, String password) {
        try {

            AccountType acc = new AccountType(username, password, email);
            em.getTransaction().begin();
            em.persist(acc);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public MovieType createMovie(MovieType movie) {
//        List<MovieType> list = movielist;
//       for (MovieType object : list) {
//           Query query =em.createQuery("INSERT INTO Movies (Name, AlternateName, Description, Country, Runtime, CategoryId, Language, ReleaseDate, Rating, ImageCover, TrailerUrl, Director)"
//                   + " VALUES ("+object.getName()+", "+object.getAlternateName()+", "+object.getDescription()+", "+object.getCountry()+", "+object.getRuntime()+", NULL, "+object.getLanguage()+", "+object.getReleaseDate()+", "+object.getRating()+", "+object.getImageCover()+", NULL, "+object.getDirector()+");");
//           query.getResultList();
//       }
//        for (MovieType object : list) {

        if (moviesExisted(movie) == true) {
            return null;
        }
        try {

            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movie;
//        }

    }

    public Genres createGenre(Genres genre) {
        if (genresIsExisted(genre) == true) {
            return null;
        }
        em.getTransaction().begin();
        em.persist(genre);
        em.getTransaction().commit();
        return genre;
    }

    public PersonType createPerson(PersonType person) {
        if (personIsExisted(person) == true) {
            return null;
        }
        em.getTransaction().begin();
        em.persist(person);
        em.flush();
        em.getTransaction().commit();
        return person;
    }

    public Boolean createMappingMoiveGenre(MovieType movie, Genres genre) {
        Integer movieIdExist = movie.getId();
        Integer genreIdExist = genre.getId();
        Boolean result = false;
        try {

            if (movieIdExist != null && genreIdExist != null) {
                if (movieGenreExisted(movieIdExist, genreIdExist) == false) {
                    MovieGenres mapping = new MovieGenres();

                    //set foreign entities - key
                    mapping.setGenreId(genre);
                    mapping.setMovieId(movie);

                    em.getTransaction().begin();
                    em.persist(mapping);
                    em.getTransaction().commit();
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean createCast(MovieType movie, PersonType person, String character) {
        if (character == null) {
            return false;
        }
        Integer movieIdExist = movie.getId();
        Integer personIdExist = person.getId();
        Boolean result = false;
        try {

            if (movieIdExist != null && personIdExist != null && !character.isEmpty()) {
                if (movieCastingExisted(movieIdExist, personIdExist) == false) {
                    Cast casting = new Cast();

                    casting.setCharacter(character);
                    //set foreign entities - key
                    casting.setActorId(person);
                    casting.setMovieId(movie);

                    em.getTransaction().begin();
                    em.persist(casting);
                    em.getTransaction().commit();
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//   public Boolean createMovieImage(MovieImages image){
//       
//       //still doing
//       em.getTransaction().begin();
//       em.persist(image);
//       em.getTransaction().commit();
//       return true;
//   }
    public int createCast(Cast cast) {
        em.getTransaction().begin();
        em.persist(cast);
        em.flush();
        em.getTransaction().commit();
        return cast.getId();
    }
    //</editor-fold>

    public List searchMoviesByName(String keyword) {
        Query query = em.createNativeQuery("SELECT Name,Id,AlternateName,"
                + "ImageCover FROM Movies WHERE Name Like ?keyword ORDER BY ReleaseDate DESC", MovieType.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return (List<MovieType>) query.getResultList();
    }
}
