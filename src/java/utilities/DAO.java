/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entities.AccountType;
import entities.Cast;
import entities.Favourites;
import entities.Genres;
import entities.MovieGenres;
import entities.MovieImages;
import entities.MovieType;
import entities.PersonType;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
//            em.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void closeEM() {
        if (em != null) {
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
        } finally {

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
        } finally {

        }
        return listAccounts;
    }

    //check Exist Function
    //<editor-fold>
    public Boolean personIsExisted(PersonType person) {
        Query query = em.createNamedQuery("PersonType.findByName");
        query.setParameter("name", person.getName());
        List<PersonType> list = (List<PersonType>) query.getResultList();

        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Genres getGenrebyId(int id) {
        Query query = em.createNamedQuery("Genres.findById");
        query.setParameter("id", id);
        Genres genre = (Genres) query.getResultList().get(0);
        return genre;
    }

    public AccountType getAccountbyId(String name) {
        Query query = em.createQuery("SELECT a FROM AccountType a WHERE a.username = ?1");
        query.setParameter(1, name);
        List<AccountType> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
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

        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean moviesExisted(MovieType movie) {
        Query query = em.createNativeQuery("SELECT * FROM Movies WHERE"
                + " Name = ?1 AND ReleaseDate = ?2", MovieType.class);
        query.setParameter(1, movie.getName());
        query.setParameter(2, movie.getReleaseDate());
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

    public Favourites FavouriteExisted(int movieId, int accountId) {
        Query query = em.createQuery("SELECT f FROM Favourites f WHERE f.accountId.id = ?1 AND f.movieId.id = ?2");
        query.setParameter(1, accountId);
        query.setParameter(2, movieId);

        List<Favourites> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
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

    public List getMovieById(int id) {
        Query query4 = em.createNativeQuery("SELECT * FROM Movies WHERE"
                + " Id = " + id);
        em.clear();
        return query4.getResultList();
    }

    public MovieType getSingleMovie(int id) {
        Query query = em.createNamedQuery("MovieType.findById", MovieType.class);
        query.setParameter("id", id);

        MovieType m = (MovieType) query.getSingleResult();
        return m;
    }

    public List getUserFavouriteList(int userId, int starIndex, int endIndex) {
        Query query = em.createNativeQuery("SELECT * FROM "
                + "(SELECT ROW_NUMBER() OVER (ORDER BY Id ASC) AS RowNum , * FROM Favourites WHERE AccountId = ?1 ) AS a"
                + " WHERE RowNum >= ?2 and RowNum <= ?3", Favourites.class);
        query.setParameter(1, userId);
        query.setParameter(2, starIndex);
        query.setParameter(3, endIndex);

        List<Favourites> list = query.getResultList();

        return list;
    }

    public Long getSizeFavouriteMovies(int accountId) {
        Query query = em.createQuery("SELECT COUNT(a) FROM Favourites a WHERE a.accountId.id = ?1");
        query.setParameter(1, accountId);
        Long size = (Long) query.getSingleResult();
        return size;
    }

    public List getMovieForSearch() {
        Query query = em.createNativeQuery("SELECT TOP(50) Id, Name, AlternateName, [Description], "
                + "ImageCover FROM Movies ORDER BY ReleaseDate DESC", MovieType.class);

        List list = query.getResultList();
        em.clear();
        return list;
    }

    public List getTopMovie() {
        Query query2 = em.createNativeQuery("SELECT TOP(30) Id, Name, AlternateName, [Description], "
                + "ImageCover FROM Movies ORDER BY Rating DESC", MovieType.class);
//Query query = em.createQuery("SELECT m FROM MovieType a WHERE a.id, a.alternateName");
        List list2 = query2.getResultList();
        em.clear();
        return list2;
    }

    public MovieType get1stMovie() {
        Query query3 = em.createNativeQuery("SELECT TOP(1) Id, Name, AlternateName, [Description], "
                + "ImageCover FROM Movies ORDER BY Rating DESC", MovieType.class);
//Query query = em.createQuery("SELECT m FROM MovieType a WHERE a.id, a.alternateName");
        MovieType first = (MovieType) query3.getResultList().get(0);
        List<Genres> list2 = new ArrayList<>();
        for (MovieGenres genre : first.getMovieGenresCollection()) {
            list2.add(genre.getGenreId());
        }
        first.setGenreList(list2);
        em.clear();
        return first;
    }

    public MovieType getMovieByName(String name) {
        Query query = em.createQuery("SELECT m FROM MovieType m WHERE m.name = ?1", MovieType.class);
        query.setParameter(1, name);
        em.clear();
        return (MovieType) query.getResultList().get(0);
    }

    public Genres getGenreByName(String name) {
        Query query = em.createQuery("SELECT g FROM Genres g WHERE g.name = ?1", Genres.class);
        query.setParameter(1, name);
        em.clear();
        return (Genres) query.getResultList().get(0);
    }

    public PersonType getActorByName(String name) {
        Query query = em.createQuery("SELECT a FROM PersonType a WHERE a.name = ?1", PersonType.class);
        query.setParameter(1, name);
        return (PersonType) query.getResultList().get(0);
    }

    public AccountType getAccount(String username) {
        Query query = em.createQuery("SELECT a FROM AccountType a WHERE a.username = ?1", AccountType.class);
        query.setParameter(1, username);
        AccountType account = (AccountType) query.getSingleResult();

        return account;
    }

    public MovieType getMovieForFavourite(int movieId) {
        Query query = em.createQuery("SELECT a.id, a.name, a.imageCover FROM MovieType a WHERE a.id = ?1");
        query.setParameter(1, movieId);
        Object[] obj = (Object[]) query.getResultList().get(0);
        MovieType movie = new MovieType();
        movie.setId(Integer.parseInt(obj[0].toString()));
        movie.setName(obj[1].toString());
        movie.setImageCover(obj[2].toString());
        return movie;
    }
    
    public List getCastForMovie(int movieId){
       Query query = em.createQuery("SELECT a FROM Cast a WHERE a.movieId.id = ?1", Cast.class);
       query.setParameter(1, movieId);
       List<Cast> list = query.getResultList();
       return list;
    }
    
    public PersonType getPersonById(int personId){
        PersonType p = em.find(PersonType.class, personId);
        return p;
    }
    //</editor-fold>

    // Create and edit function 
    //<editor-fold>
    public void updateMovieImageCover(String link, MovieType movie) {
        EntityTransaction t = em.getTransaction();
        try {
            MovieType m = em.find(MovieType.class, movie.getId());
            m.setImageCover(link);
            t.begin();
            em.merge(m);
            em.flush();
            t.commit();
        } catch (Exception ex) {
            t.rollback();
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }
    public MovieType updateMovie(int movieId, MovieType movie){
         EntityTransaction t = em.getTransaction();
         MovieType result = null;
        try {
        MovieType m = em.find(MovieType.class, movieId);
            m.setAlternateName(movie.getAlternateName());
            m.setDescription(movie.getDescription());
            m.setCountry(movie.getCountry());
            m.setRuntime(movie.getRuntime());
            m.setLanguage(movie.getLanguage());
            m.setReleaseDate(movie.getReleaseDate());
            m.setRating(movie.getRating());
            m.setImageCover(movie.getImageCover());
            m.setDirector(movie.getDirector());
            t.begin();
            result = em.merge(m);
            em.flush();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
        return result;
    }

    public void updateActorImageCover(String link, PersonType person) {
        EntityTransaction t = em.getTransaction();
        try {
            PersonType p = em.find(PersonType.class, person.getId());
            p.setImageUrl(link);
            t.begin();
            em.merge(p);
            em.flush();
            t.commit();
        } catch (Exception ex) {
            t.rollback();
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    public Boolean createAccounts(String username, String email, String password) {
        EntityTransaction t = em.getTransaction();
        try {
            AccountType acc = new AccountType(username, password, email);
            t.begin();
            em.persist(acc);
            em.flush();
            t.commit();
        } catch (Exception e) {
            t.rollback();
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {

        }
        return true;
    }

    public MovieType createMovie(MovieType movie) {

        if (moviesExisted(movie) == true) {
            return null;
        }
        EntityTransaction t = em.getTransaction();
        try {

            t.begin();
            em.persist(movie);
            em.flush();
            t.commit();
        } catch (Exception ex) {
            t.rollback();
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return movie;

    }

    public Genres createGenre(Genres genre) {
        if (genresIsExisted(genre) == true) {
            return null;
        }
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(genre);
            em.flush();
            t.commit();
        } catch (Exception e) {
            t.rollback();
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, e);
        } finally {

        }
        return genre;
    }

    public PersonType createPerson(PersonType person) {
        if (personIsExisted(person) == true) {
            return null;
        }
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(person);
            em.flush();
            t.commit();
        } catch (Exception e) {
            t.rollback();
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, e);
        } finally {

        }
        return person;
    }

    public Boolean createMappingMoiveGenre(MovieType movie, Genres genre) {
        Integer movieIdExist = movie.getId();
        Integer genreIdExist = genre.getId();
        Boolean result = false;
        EntityTransaction t = em.getTransaction();
        try {

            if (movieIdExist != null && genreIdExist != null) {
                if (movieGenreExisted(movieIdExist, genreIdExist) == false) {
                    MovieGenres mapping = new MovieGenres();

                    //set foreign entities - key
                    mapping.setGenreId(genre);
                    mapping.setMovieId(movie);

                    t.begin();
                    em.persist(mapping);
                    em.flush();
                    t.commit();
                    result = true;
                }
            }
        } catch (Exception e) {
            t.rollback();
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, e);
        } finally {

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
        EntityTransaction t = em.getTransaction();
        try {

            if (movieIdExist != null && personIdExist != null && !character.isEmpty()) {
                if (movieCastingExisted(movieIdExist, personIdExist) == false) {
                    Cast casting = new Cast();

                    casting.setCharacter(character);
                    //set foreign entities - key
                    casting.setActorId(person);
                    casting.setMovieId(movie);

                    t.begin();
                    em.persist(casting);
                    em.flush();
                    t.commit();
                    result = true;
                }
            }
        } catch (Exception e) {
            t.rollback();
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, e);
        } finally {

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
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(cast);
            em.flush();
            t.commit();
        } catch (Exception e) {
            t.rollback();
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, e);
        } finally {

        }
        return cast.getId();
    }

    public Boolean createFavourites(MovieType movie, AccountType account) {
        Boolean result = false;
        EntityTransaction t = em.getTransaction();
        try {
            Favourites favourite = new Favourites();
            favourite.setAccountId(account);
            favourite.setMovieId(movie);
            t.begin();
            em.persist(favourite);
            em.flush();
            t.commit();
            result = true;
        } catch (Exception e) {
            t.rollback();
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public Boolean deleteFavourites(Integer favouriteId) {
        Boolean result = false;
        EntityTransaction t = em.getTransaction();
        try {
            Favourites favourite = em.find(Favourites.class, favouriteId);
            t.begin();
            em.remove(favourite);
            em.flush();
            t.commit();
            result = true;
        } catch (Exception e) {
            t.rollback();
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    //</editor-fold>

    public List searchMoviesByName(String keyword) {
        Query query = em.createNativeQuery("SELECT Name,Id,AlternateName,"
                + "ImageCover FROM Movies WHERE Name Like ?keyword ORDER BY ReleaseDate DESC", MovieType.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return (List<MovieType>) query.getResultList();
    }
}
