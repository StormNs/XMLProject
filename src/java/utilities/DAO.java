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

    public int moviesIsExisted(MovieType movie) {
        Query query = em.createNativeQuery("SELECT * FROM Movies WHERE"
                + " Name = '" + movie.getName() + "' AND ReleaseDate = '" + movie.getReleaseDate() + "'", MovieType.class);

        List<MovieType> list = (List<MovieType>) query.getResultList();
        if (list.isEmpty()) {
            return -1;
        } else {
            MovieType test = list.get(0);
            return test.getId();
        }
    }

    public int genresIsExisted(Genres genre) {
        Query query = em.createNamedQuery("Genres.findByName");
        query.setParameter("name", genre.getName());
        List<Genres> list = (List<Genres>) query.getResultList();
        if (list.isEmpty()) {
            return -1;
        } else {
            return list.get(0).getId();
        }
    }

    public int personIsExisted(PersonType person) {
        Query query = em.createNamedQuery("PersonType.findByName");
        query.setParameter("name", person.getName());
        List<PersonType> list = (List<PersonType>) query.getResultList();
        if (list.isEmpty()) {
            return -1;
        } else {
            return list.get(0).getId();
        }
    }

    public int movieGenreExisted(int movieId, int genreId) {
        Query query = em.createNativeQuery("SELECT * FROM MovieGenres WHERE"
                + " MovieId = '" + movieId + "' AND GenreId = '" + genreId + "'");

        List<MovieType> list = (List<MovieType>) query.getResultList();
        if (list.isEmpty()) {
            return -1;
        } else {
            return list.get(0).getId();
        }
    }

    public List getAllAccounts() {
        Query query = em.createNamedQuery("AccountType.findAll");
        return query.getResultList();
    }

    public List getAllMovie() {
        Query query = em.createNamedQuery("MovieType.findAll");
        return query.getResultList();
    }

    public List getMovieForSearch() {
        Query query = em.createNativeQuery("SELECT TOP(50) Name,Id,AlternateName,[Description],"
                + "ImageCover FROM Movies ORDER BY ReleaseDate DESC", MovieType.class);
        return (List<MovieType>) query.getResultList();
    }

    public int createMovie(MovieType movie) {
//        List<MovieType> list = movielist;
//       for (MovieType object : list) {
//           Query query =em.createQuery("INSERT INTO Movies (Name, AlternateName, Description, Country, Runtime, CategoryId, Language, ReleaseDate, Rating, ImageCover, TrailerUrl, Director)"
//                   + " VALUES ("+object.getName()+", "+object.getAlternateName()+", "+object.getDescription()+", "+object.getCountry()+", "+object.getRuntime()+", NULL, "+object.getLanguage()+", "+object.getReleaseDate()+", "+object.getRating()+", "+object.getImageCover()+", NULL, "+object.getDirector()+");");
//           query.getResultList();
//       }
//        for (MovieType object : list) {
        if (moviesIsExisted(movie) != -1) {
            return moviesIsExisted(movie);
        }
        try {
            //format date
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            Date date;
            try {
                date = sdf.parse(movie.getReleaseDate());
                sdf = new SimpleDateFormat("yyyy/MM/dd");
                movie.setReleaseDate(sdf.format(date));
            } catch (ParseException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            em.getTransaction().begin();
            em.persist(movie);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movie.getId();
//        }

    }

    public int createGenre(Genres genre) {
        if (genresIsExisted(genre) != -1) {
            return genresIsExisted(genre);
        }
        em.getTransaction().begin();
        em.persist(genre);
        em.flush();
        em.getTransaction().commit();
        return genre.getId();
    }

    public int createPerson(PersonType person) {
        if (personIsExisted(person) != -1) {
            return personIsExisted(person);
        }
        em.getTransaction().begin();
        em.persist(person);
        em.flush();
        em.getTransaction().commit();
        return person.getId();
    }

    public void createMappingMoiveGenre(MovieType movie, Genres genre) {
        int movieIdExist = moviesIsExisted(movie);
        int genreIdExist = genresIsExisted(genre);
        if (movieIdExist != -1 && genreIdExist != -1) {
            if (movieGenreExisted(movieIdExist, genreIdExist) == -1) {
                MovieGenres mapping = new MovieGenres();
                Query query = em.createNamedQuery("Genres.findById");
                query.setParameter("id", genreIdExist);
                List<Genres> list = query.getResultList();
                Query query2 = em.createNamedQuery("MovieType.findById");
                query2.setParameter("id", movieIdExist);
                List<MovieType> list2 = query2.getResultList();
                mapping.setGenreId(list.get(0));
                mapping.setMovieId(list2.get(0));
                em.getTransaction().begin();
                em.persist(mapping);
                em.flush();
                em.getTransaction().commit();
            }
        } else {

        }

    }

    public int createCast(Cast cast) {
        em.getTransaction().begin();
        em.persist(cast);
        em.flush();
        em.getTransaction().commit();
        return cast.getId();
    }

}
