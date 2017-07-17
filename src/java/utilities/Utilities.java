/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entities.AccountType;
import entities.Accounts;
import entities.ActorList;
import entities.Genres;
import entities.Movies;
import entities.MovieType;
import entities.PersonType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import servlets.LoginServlet;

/**
 *
 * @author StormNs
 */
public class Utilities implements Runnable {

    private static String rPath;

    public static String getrPath() {
        return rPath;
    }

    public static void setrPath(String rPath) {
        Utilities.rPath = rPath;
    }

    public static void UnMarshallAccounts(String realPath) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Accounts.class);
        Unmarshaller u = jc.createUnmarshaller();
        File f = new File(realPath + "WEB-INF/accounts.xml");
        if (!f.exists()) {
            System.out.println("not exist");
            return;
        }
        Accounts accounts = (Accounts) u.unmarshal(f);
        List<AccountType> accList = accounts.getAccounts();
        for (int i = 0; i < accList.size(); i++) {
            AccountType acc = accList.get(i);
            System.out.println(acc.getEmail());
            System.out.println(acc.getUsername());
            System.out.println(acc.getPassword());
        }

    }

    public static void MarshallAccounts(String realPath) {
        try {
            DAO dao = new DAO();
            List<AccountType> list = dao.getAllAccounts();
            Accounts accs = new Accounts();
            accs.setAccounts(list);

            JAXBContext jc = JAXBContext.newInstance(Accounts.class);
            Marshaller mar = jc.createMarshaller();
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File f = new File(realPath + "WEB-INF/accounts1.xml");
//            File f2 = new File("WEB-INF/accounts.xml");
//            Boolean b  = f2.exists();

            mar.marshal(accs, f);

        } catch (JAXBException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String MarshallMovies() {
        try {
            DAO dao = new DAO();
            List<MovieType> list = dao.getMovieForSearch();
            Movies movies = new Movies();
            movies.setMovies(list);

            JAXBContext jc = JAXBContext.newInstance(Movies.class);
            Marshaller mar = jc.createMarshaller();
//            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            mar.setProperty("com.sun.xml.internal.bind.xmlHeaders",
//                    "<?xml-stylesheet type=\"text/xsl\" href=\"clientMovies.xsl\"?>\n");
//            File file = new File(realPath + "WEB-INF/movies.xml");
//            FileWriter fw = new FileWriter(file);
            StringWriter sw = new StringWriter();

            mar.marshal(movies, sw);
            dao.closeEM();
            return sw.toString();

        } catch (JAXBException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public String MarshallWatchMovie(int id) {
        try {
            DAO dao = new DAO();
            Object[] object = dao.getMovieById(id).toArray();
            MovieType movie = setObjectToMovieType((Object[]) object[0]);

            JAXBContext jc = JAXBContext.newInstance(MovieType.class);
            Marshaller mar = jc.createMarshaller();
//            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            mar.setProperty("com.sun.xml.internal.bind.xmlHeaders",
//                    "<?xml-stylesheet type=\"text/xsl\" href=\"clientMovies.xsl\"?>\n");
//            File file = new File(realPath + "WEB-INF/movies.xml");
//            FileWriter fw = new FileWriter(file);
            StringWriter sw = new StringWriter();

            mar.marshal(movie, sw);
            dao.closeEM();
            return sw.toString();

        } catch (JAXBException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public String MarshallTopMovies() {
        try {
            DAO dao = new DAO();
            List<MovieType> list = dao.getTopMovie();
            Movies movies = new Movies();
            movies.setMovies(list);

            JAXBContext jc = JAXBContext.newInstance(Movies.class);
            Marshaller mar = jc.createMarshaller();
//            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            mar.setProperty("com.sun.xml.internal.bind.xmlHeaders",
//                    "<?xml-stylesheet type=\"text/xsl\" href=\"clientMovies.xsl\"?>\n");
//            File file = new File(realPath + "WEB-INF/movies.xml");
//            FileWriter fw = new FileWriter(file);
            StringWriter sw = new StringWriter();

            mar.marshal(movies, sw);
            dao.closeEM();
            return sw.toString();

        } catch (JAXBException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public MovieType setObjectToMovieType(Object[] object) {
        MovieType movie = new MovieType();
        String test = object[0].toString();
        movie.setId(Integer.parseInt(object[0].toString()));
        movie.setName(object[1].toString());
        if (object[2] != null) {
            movie.setAlternateName(object[2].toString());
        }
        if (object[3] != null) {
            movie.setDescription(object[3].toString());
        }
        if (object[4] != null) {
            movie.setCountry(object[4].toString());
        }
        if (object[5] != null) {
            movie.setRuntime(object[5].toString());
        }
        if (object[6] != null) {
            movie.setLanguage(object[6].toString());
        }
        if (object[7] != null) {
            movie.setReleaseDate(object[7].toString());
        }
        if (object[8] != null) {
            movie.setRating(Double.parseDouble(object[8].toString()));
        }
        if (object[9] != null) {
            movie.setImageCover(object[9].toString());
        }
        if (object[11] != null) {
            movie.setDirector(object[11].toString());
        }
        return movie;
    }

    public String getNewLayout(String realPath) {
        try {
            StreamSource xsltFile = new StreamSource(realPath + "WEB-INF/newMovies.xsl");
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            StringWriter sw = new StringWriter();
            StreamResult outStream = new StreamResult(sw);
            trans.transform(xsltFile, outStream);
//            trans.setOutputProperty(OutputKeys.METHOD, "xml");
//            trans.setOutputProperty(OutputKeys.INDENT, "no");
            return sw.toString();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String TransMoviesForClient(String realPath) {
        try {
//            File f = new File(realPath + "WEB-INF/movies.xml");
            StreamSource xsltFile = new StreamSource(realPath + "WEB-INF/clientMovies.xsl");
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xmlFile = new StreamSource(realPath + "WEB-INF/movies.xml");
            Templates template = tf.newTemplates(xsltFile);
            Transformer trans = template.newTransformer();
            StringWriter sw = new StringWriter();
            StreamResult outStream = new StreamResult(sw);
            trans.transform(xmlFile, outStream);
            return sw.toString();
        } catch (TransformerException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public String marshallFavouriteMovies(Movies movies) {
        JAXBContext jc;
        String result = null;
        try {
            jc = JAXBContext.newInstance(Movies.class);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            StringWriter sw = new StringWriter();
            marshaller.marshal(movies, sw);
            result = sw.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
     public String marshallActors(ActorList actors) {
        JAXBContext jc;
        String result = null;
        try {
            jc = JAXBContext.newInstance(ActorList.class);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            StringWriter sw = new StringWriter();
            marshaller.marshal(actors, sw);
            result = sw.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    

    public Boolean validateBeforeSavetoDB(Class<?> type, String schemaFile, Object obj) {
        Boolean result = false;
        String realPath = rPath;
        if (rPath == null | rPath.isEmpty()) {
            return false;
        }
        try {

            JAXBContext jc = JAXBContext.newInstance(type);

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(realPath + schemaFile));
//            Schema schema = sf.newSchema(new File(test));

            Marshaller mar = jc.createMarshaller();
            mar.setSchema(schema);
            mar.marshal(obj, new DefaultHandler());

//            File xmlFile = new File(contextPath+"/web/schema/movies.xml");
//            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            mar.marshal(movies, new File(realPath+"schema/movies.xml"));
            result = true;

        } catch (SAXException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void CrawlDataAuto() {
        Crawler crawler = new Crawler();
        crawler.crawlData(); // arrayList will exist after crawl
        List<MovieType> listMovie = crawler.getMovieList(); //contain both Actors and Genres
        Movies movies = new Movies();
        movies.setMovies(listMovie);
//        validateBeforeSavetoDB(movies.getClass(), "schema/movies.xsd", movies); // does not work in background thread - still finding way to resolve
        DAO dao = new DAO();
        int i = 0;
        for (MovieType mItem : listMovie) {
            Boolean valFilm = validateBeforeSavetoDB(mItem.getClass(), "schema/movie.xsd", mItem);
            if (valFilm) {
                try {
                    MovieType movie = null;
                    MovieType mValue = dao.createMovie(mItem);
                    if (mValue != null) { // movie insert successed
                        movie = mValue;
                    } else { // find exist movie
                        movie = dao.getMovieByName(mItem.getName());
                        movie = dao.updateMovie(movie.getId(), mItem);
                    }

                    ArrayList<Genres> listGenre = new ArrayList<>(mItem.getGenreList());
                    for (Genres gItem : listGenre) {
                        Boolean valGen = validateBeforeSavetoDB(gItem.getClass(), "schema/genre.xsd", gItem);
                        if (valGen) {
                            Genres genre = null;
                            Genres genValue = dao.createGenre(gItem);
                            if (genValue != null) { // genre insert successed
                                genre = gItem;
                            } else { // find exist genre
                                genre = dao.getGenreByName(gItem.getName());
                            }

                            Boolean check = dao.createMappingMoiveGenre(movie, genre);
                        }
                    }

                    ArrayList<PersonType> listActor = new ArrayList<>(mItem.getPersonTypeList());
                    for (PersonType aItem : listActor) {
                        Boolean valActor = validateBeforeSavetoDB(aItem.getClass(), "schema/actor.xsd", aItem);
                        if (valActor) {
                            PersonType actor = null;
                            PersonType aValue = dao.createPerson(aItem);
                            if (aValue != null) { // actor insert successed
                                actor = aValue;
                            } else { // find exist actor
                                actor = dao.getActorByName(aItem.getName());
                            }

                            Boolean check = dao.createCast(movie, actor, aItem.getCharacterName());

//                            String aImgName = actor.getName();
//                            String aFolder = actor.getName();
//                            String aUri = aItem.getImageUrl();
//                            String aImgUri = crawler.DownloadImage(aImgName, aFolder, aUri, Enum.ACTOR_IMG);
//                            dao.updateActorImageCover(aImgUri, actor);
                        }
                    }

                    String imageName = movie.getName();
                    String folder = movie.getName();
                    String uri = mItem.getImageCover();
                    String imageUri = crawler.DownloadImage(imageName, folder, uri, Enum.MOVIE_IMG);
                    dao.updateMovieImageCover(imageUri, movie);
                    System.out.println("Done ." + (i++));
                } catch (Exception e) {
                    Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, e);
                }
            }
//            break;
        }
        System.out.println("Done Crawling");
        dao.closeEM();
    }

    public static String formatDate(String mDate) {
        //format date
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        Date date;
        String result = null;
        try {
            date = sdf.parse(mDate);
            sdf = new SimpleDateFormat("yyyy/MM/dd");
            result = sdf.format(date);
        } catch (ParseException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Boolean printForFun() {
        System.out.println("test 1");
        return true;
    }

    @Override
    public void run() {
        printForFun();
//        CrawlDataAuto();
    }

}
