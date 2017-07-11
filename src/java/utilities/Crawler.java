/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entities.Genres;
import entities.MovieType;
import entities.PersonType;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author StormNs
 */
public class Crawler {

    private ArrayList<MovieType> movieList = null;

    public Crawler() {
        movieList = new ArrayList<>();

    }

    public ArrayList<MovieType> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<MovieType> movieList) {
        this.movieList = movieList;
    }

    public String DownloadImage(String fileName, String folderName, String uri, Enum e) {
        String filePath = null;
        String path = null;
        if (e.equals(Enum.MOVIE_IMG)) {
            path = "F:/NetBean_Project/XMLProj_Image";
        }
        if (e.equals(Enum.ACTOR_IMG)) {
            path = "F:/NetBean_Project/XMLProj_Actor_Img";
        }
        try {
            if (uri == null) {
                return null;
            }
            int eIndex = uri.lastIndexOf(Enum.TOKEN.getText());
            uri = uri.substring(0, eIndex) + Enum.LARGE_SEQUENCE.getText();
            folderName = folderName.replaceAll("[^\\p{IsAlphabetic}0-9]", "_");
            fileName = fileName.replaceAll("[^\\p{IsAlphabetic}0-9]", "_");
            filePath = path + "/" + folderName + "/" + fileName + "_pic.jpg";
            File f = new File(filePath);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdir();
            }

            URL url = new URL(uri);

//            BufferedImage img = ImageIO.read(url); //Option 2
//            ImageIO.write(img, "jpg", f);
            InputStream in = url.openStream();
            Files.copy(in, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            return filePath;
        } catch (Exception ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            return filePath;
        }
        return filePath;
    }

    public void crawlData() {
//        String uri3 = "http://www.imdb.com";
        String uri4 = "http://www.imdb.com/chart/moviemeter?ref_=nv_mv_mpm_8";

//        String document = parseMovieHTML(t);
        List<String> link = getLinkMovie(uri4);

        System.out.println(link.size());
//        String document = parseMovieHTML("http://www.imdb.com" + link.get(0));
        for (int i = 0; i < 2; i++) {
            System.out.println(i + 1 + ". ");
            String document = parseMovieHTML("http://www.imdb.com" + link.get(i));
            MovieType movie = StAXParserMovie(document);
            movieList.add(movie);
        }
        System.out.println(movieList.size());

    }

    public BufferedReader getUrlBufferReader(String uri) {
        BufferedReader in = null;
        try {
            URL url = new URL(uri);
            URLConnection uc = url.openConnection();
            uc.addRequestProperty("User-Agent", "Mozilla/5.0 "
                    + "(Windows NT 6.3; Win64; x64) AppleWebKit/537.36 "
                    + "(KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

            InputStream is = uc.getInputStream();
            in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }

    public String parseMovieHTML(String uri) {

        String document = "";
        try {
            BufferedReader in = getUrlBufferReader(uri);
            String line;

            boolean inName = false;
//            boolean inDate = false; // release date of Country
//            boolean inRuntime = false;
//            boolean inGenre = false;
            boolean inGeneralInform = false; // contain run time, genre, date
            int inDirector = 0;

            boolean inDescription = false;
            boolean inCountry = false;
            boolean inLanguage = false;
            boolean inRating = false;
            boolean inOriginalName = false;
            boolean inCastList = false;
            int inImageCover = 0;
            boolean isDone = false;
            document += "<root>";
            while ((line = in.readLine()) != null) {
                if (isDone) {
                    break;
                }
                line = line.replaceAll("\"", "\" ");

                //read Director
                //<editor-fold> 
                if (line.contains("credit_summary_item")) {
                    inDirector++;
                }
                if (inDirector == 1) {
                    if (line.contains("</div>")) {
                        document += line;
                        inDirector++;
                    } else {
                        line = line.replaceAll("itemscope", "");
                        document += line;
                    }
                }
                //</editor-fold>

                //read Casting
                //<editor-fold> 
                if (line.contains("cast_list")) {
                    inCastList = true;
                }
                if (inCastList) {
                    if (line.contains("</table>")) {
                        line = line.replaceAll("itemscope", "");
                        document += line;
                        inCastList = false;
                    } else {
                        line = line.replaceAll("itemscope", "");
                        document += line;
                    }
                }
                //</editor-fold>

                //read originalName
                //<editor-fold> 
                if (line.contains("originalTitle")) {
                    inOriginalName = true;
                }
                if (inOriginalName) {
                    if (line.contains("</div>")) {
                        document += line;
                        inOriginalName = false;
                    } else {
                        document += line;
                    }
                }
                //</editor-fold>

                //read ImageCover
                //<editor-fold> 
                if (line.contains("mediaviewer")) {
                    inImageCover++;
                }
                if (inImageCover == 1) {
                    if (line.contains("</a>")) {
                        line = line.replaceAll("</div>", "");
                        document += line;
                        inImageCover++;
                    } else {
                        document += line;
                    }
                }
                //</editor-fold>

                //read Imdb Rating
                //<editor-fold> 
                if (line.contains("class") && line.contains("ratingValue")) {
                    inRating = true;
                }
                if (inRating) {
                    if (line.contains("</div>")) {
                        document += line;
                        inRating = false;
                    } else {
                        document += line;
                    }
                }
                //</editor-fold>

                //read Language ** the last section to read **
                //<editor-fold> 
                if (line.contains("Language:")) {
                    inLanguage = true;
                }
                if (inLanguage) {
                    if (line.contains("</a>")) {
                        isDone = true;
                        line = line.replaceAll("&", "&amp;");
                        document += line;
                        inLanguage = false;
                    } else {
                        line = line.replaceAll("&", "&amp;");
                        document += line;
                    }
                }
                //</editor-fold>

                //read Country
                //<editor-fold> 
                if (line.contains("country")) {
                    inCountry = true;
                }
                if (inCountry) {
                    if (line.contains("</a>")) {
                        line = line.replaceAll("&", "&amp;");
                        document += line;
                        inCountry = false;
                    } else {
                        line = line.replaceAll("&", "&amp;");
                        document += line;
                    }
                }
                //</editor-fold>

                //read Description
                //<editor-fold> 
                if (line.contains("summary_text")) {
                    inDescription = true;
                }
                if (inDescription) {
                    if (line.contains("</div>")) {
                        document += line;
                        inDescription = false;
                    } else {
                        document += line;
                    }
                }
                //</editor-fold>

                //read Name
                // <editor-fold> 
                if (line.contains("<h1")
                        && line.contains("itemprop")
                        && line.contains("name")) {
                    inName = true;
                }
                if (inName) {
                    if (line.contains("</h1>")) {
                        document += line;
                        inName = false;
                    } else {
                        document += line;
                    }
                }
                // </editor-fold>

                //  read General Information
                //<editor-fold> 
                if (line.contains("subtext")) {
                    inGeneralInform = true;
                }
                if (inGeneralInform) {
                    if (line.contains("</div>")) {
                        document += line;
                        inGeneralInform = false;
                    } else {
                        if (!line.contains("contentRating")) {
                            document += line;
                        }
                    }
                }
                //</editor-fold>

                //df
            }
            document += "</root>";

            in.close();

        } catch (MalformedURLException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return document;

    }

    public MovieType StAXParserMovie(String document) {
        MovieType movie = new MovieType();
        List<Genres> listGenre = new ArrayList<>();
        List<PersonType> listPerson = new ArrayList<>();

        XMLInputFactory fact = XMLInputFactory.newInstance();
        fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

        XMLEventReader reader = null;
        try {
            InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));

//            reader = fact.createXMLEventReader(
//                    new InputStreamReader(
//                            new FileInputStream(filepath), "UTF-8"));
            reader = fact.createXMLEventReader(
                    new InputStreamReader(
                            is, "UTF-8"));

            boolean inMovieName = false;
            boolean inMovieDate = false; //release date of Country
            boolean inDirector = false;
            boolean inDescription = false;
            boolean inRuntime = false;
            boolean isDone = false;
            boolean inGenre = false;
            boolean inCountry = false;
            boolean inLanguage = false;
            boolean inRating = false;
            boolean inOriginalName = false;
            boolean inActor = false;
            boolean inActorName = false;
            boolean inCharacterName = false;
            boolean inActorPhoto = false;
            boolean inDirectorSection = false;
//            boolean inImageCover = false; //no need
            PersonType actor = null;
            while (reader.hasNext()) {
                try {

                    XMLEvent event = reader.nextEvent();

                    if (event.isStartElement()) {
                        StartElement element = event.asStartElement();
                        if (element.getName().toString().equals("h1")) { //Name
                            Attribute attr = element.getAttributeByName(new QName("itemprop"));
                            if (attr != null) {
                                if (attr.getValue().trim().equals("name")) {
                                    inMovieName = true;
                                }
                            }
                        }
                        if (element.getName().toString().equals("a")) { //Date
                            Attribute attr = element.getAttributeByName(new QName("title"));
                            if (attr != null) {
                                if (attr.getValue().trim().equals("See more release dates")) {
                                    inMovieDate = true;
                                }
                            }
                        }
                        if (element.getName().toString().equals("div")) { // Description
                            Attribute attr = element.getAttributeByName(new QName("class"));
                            if (attr != null) {
                                if (attr.getValue().trim().equals("summary_text")) {
                                    inDescription = true;
                                }
                            }
                        }
                        if (element.getName().toString().equals("time")) { //Runtime
                            Attribute attr = element.getAttributeByName(new QName("itemprop"));
                            if (attr != null) {
                                if (attr.getValue().trim().equals("duration")) {
                                    inRuntime = true;
                                }
                            }
                        }
                        if (element.getName().toString().equals("span")) { //Genre
                            Attribute attr = element.getAttributeByName(new QName("itemprop"));
                            if (attr != null) {
                                if (attr.getValue().trim().equals("genre")) {
                                    inGenre = true;
                                }
                            }
                        }
                        if (element.getName().toString().equals("a")) { //Country
                            Attribute attr = element.getAttributeByName(new QName("href"));
                            if (attr != null) {
                                if (attr.getValue().trim().contains("country")) {
                                    inCountry = true;
                                }
                            }
                        }
                        if (element.getName().toString().equals("a")) { //Language
                            Attribute attr = element.getAttributeByName(new QName("href"));
                            if (attr != null) {
                                if (attr.getValue().trim().contains("language")) {
                                    inLanguage = true;
                                }
                            }
                        }
                        if (element.getName().toString().equals("span")) { //Rating
                            Attribute attr = element.getAttributeByName(new QName("itemprop"));
                            if (attr != null) {
                                if (attr.getValue().trim().contains("ratingValue")) {
                                    inRating = true;
                                }
                            }
                        }
                        if (element.getName().toString().equals("img")) { //ImageCover
                            Attribute attr = element.getAttributeByName(new QName("src"));
                            if (attr != null) {
                                if (attr.getValue().trim().contains("jpg")) {
//                                    System.out.println("ImageCover: " + attr.getValue().trim());
                                    movie.setImageCover(attr.getValue().trim());
                                }
                            }
                        }
                        if (element.getName().toString().equals("div")) { //OriginalName
                            Attribute attr = element.getAttributeByName(new QName("class"));
                            if (attr != null) {
                                if (attr.getValue().trim().contains("originalTitle")) {
                                    inOriginalName = true;
                                }
                            }
                        }
                        if (element.getName().toString().equals("td")) { //inActorPhoto
                            Attribute attr = element.getAttributeByName(new QName("class"));
                            if (attr != null) {
                                if (attr.getValue().trim().equals("primary_photo")) {
                                    actor = new PersonType();
                                    inActorPhoto = true;
                                }
                            }
                        }
                        if (inActorPhoto) {
                            if (element.getName().toString().equals("img")) { //inActorPhotoLink
                                Attribute attr = element.getAttributeByName(new QName("loadlate"));
                                if (attr != null) {
                                    actor.setImageUrl(attr.getValue().trim());
                                    inActorPhoto = false;
                                }
                            }
                        }
                        if (element.getName().toString().equals("td")) { //inActor
                            Attribute attr = element.getAttributeByName(new QName("itemprop"));
                            if (attr != null) {
                                if (attr.getValue().trim().equals("actor")) {
                                    inActor = true;
                                }
                            }
                        }
                        if (inActor) {
                            if (element.getName().toString().equals("a")) { //ActorLink
                                Attribute attr = element.getAttributeByName(new QName("href"));
                                if (attr != null) {
                                    if (attr.getValue().trim().contains("/name")) {
                                        System.out.println("LinkActor: " + attr.getValue());
                                    }
                                }
                            }
                            if (element.getName().toString().trim().equals("span")) { //ActorName
                                Attribute attr = element.getAttributeByName(new QName("itemprop"));
                                if (attr != null) {
                                    if (attr.getValue().trim().equals("name")) {
                                        inActorName = true;
                                    }
                                }
                            }
                        }
                        if (element.getName().toString().equals("a")) { //CharacterName
                            Attribute attr = element.getAttributeByName(new QName("href"));
                            if (attr != null) {
                                if (attr.getValue().trim().contains("/character")) {
                                    inCharacterName = true;
                                }
                            }
                        }

                        if (element.getName().toString().equals("div")) { //Director
                            Attribute attr = element.getAttributeByName(new QName("class"));
                            if (attr != null) {
                                if (attr.getValue().trim().equals("credit_summary_item")) {
                                    inDirectorSection = true;
                                }
                            }
                        }
                        if (inDirectorSection) {
                            if (element.getName().toString().equals("span")) { //Director
                                Attribute attr = element.getAttributeByName(new QName("itemprop"));
                                if (attr != null) {
                                    if (attr.getValue().trim().equals("name")) {
                                        inDirector = true;
                                        inDirectorSection = false;
                                    }
                                }
                            }
                        }

                    }//end event

                    if (inMovieName) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
                            System.out.println("Name: " + t);
                            movie.setName(t);
                            inMovieName = false;
                        }
                    }
                    if (inMovieDate) { // need to fix date
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
                            String[] list = t.split(" ");
                            String date = list[0] + " " + list[1] + " " + list[2];
//                            System.out.println("Date: " + t);
                            movie.setReleaseDate(Utilities.formatDate(date));
                            inMovieDate = false;
                        }
                    }
                    if (inDescription) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
//                            System.out.println("Description: " + t);
                            movie.setDescription(t);
                            inDescription = false;
                        }
                    }
                    if (inRuntime) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
//                            System.out.println("Runtime: " + t);
                            movie.setRuntime(t);
                            inRuntime = false;
                        }
                    }
                    if (inGenre) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
//                            System.out.println("Genre: " + t);
                            listGenre.add(new Genres(t));
                            inGenre = false;
                        }
                    }
                    if (inCountry) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
//                            System.out.println("Country: " + t);
                            if (movie.getCountry() == null) {
                                movie.setCountry(t);
                            } else {
                                movie.setCountry(movie.getCountry() + " " + t);
                            }
                            inCountry = false;
                        }
                    }
                    if (inLanguage) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
//                            System.out.println("Language: " + t);
                            movie.setLanguage(t);
                            inLanguage = false;
                            isDone = true;
                        }
                    }
                    if (inRating) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
//                            System.out.println("Rating: " + t);
                            movie.setRating(Double.parseDouble(t));
                            inRating = false;
                        }
                    }
                    if (inOriginalName) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
//                            System.out.println("Orignal Title: " + t);
                            movie.setAlternateName(t);
                            inOriginalName = false;
                        }
                    }
                    if (inActorName) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
//                            System.out.println("Actor: " + t);
                            actor.setName(t);
                            inActorName = false;
                            inActor = false;
                        }
                    }
                    if (inCharacterName) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
//                            System.out.println("Chracter: " + t);
                            actor.setCharacterName(t);
                            listPerson.add(actor);
                            inCharacterName = false;
                            System.out.println("");
                        }
                    }
                    if (inDirector) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData().trim();
//                            System.out.println("Director: " + t);
                            movie.setDirector(t);
                            inDirector = false;
                        }
                    }
                    if (isDone) {
                        break;
                    }
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                    break;
                }
            }
            movie.setGenreList(listGenre);
            movie.setPersonTypeList(listPerson);
            reader.close();
//        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException | IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movie;
    }

    public List<String> getLinkMovie(String uri) {
        List<String> listLinkMovie = new ArrayList<>();
        List<String> listNameMovie = new ArrayList<>();
        try {
            BufferedReader in = getUrlBufferReader(uri);

            String line;
            String document = "";

            boolean inPopularMovie = false;
            int inMovieLink = 0; //take 
            boolean firstEncounter = false;

            document += "<root>";
//            Writer writer = new BufferedWriter(
//                    new OutputStreamWriter(
//                            new FileOutputStream("src/StaxCrawler/pirates_5.xml"), "UTF-8"));
//            writer.write("<root>");
            while ((line = in.readLine()) != null) {
                if (line.contains("lister-list")) {
                    inPopularMovie = true;
                }
                if (inPopularMovie) {
                    if (line.contains("</tbody>")) {
                        break;
                    }
                    if (line.contains("<a") && line.contains("href") && line.contains("/title/")) {
                        firstEncounter = true;
                        inMovieLink++;
                    }
                    if (inMovieLink % 2 == 0 && firstEncounter) {
                        if (line.contains("</a>")) {
                            line = line.replaceAll("\"", "\" ");
                            line = line.replaceAll("&", "&amp;");
//                            writer.write(line);
                            document += line;
                            firstEncounter = false;
                        } else {
                            line = line.replaceAll("\"", "\" ");
                            line = line.replaceAll("&", "&amp;");
//                            writer.write(line);
                            document += line;
                        }
                    }
                }
            }
//            writer.write("</root>");

            document += "</root>";

//            writer.close();
            in.close();

            XMLInputFactory fact = XMLInputFactory.newInstance();
            fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
            fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

            InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));

            XMLEventReader reader = fact.createXMLEventReader(
                    new InputStreamReader(
                            is, "UTF-8"));

            boolean inLink = false;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement element = event.asStartElement();
                    if (element.getName().toString().equals("a")) {
                        Attribute attr = element.getAttributeByName(new QName("href"));
                        if (attr != null) {
                            if (attr.getValue().trim().contains("title")) {
                                listLinkMovie.add(attr.getValue().trim().replaceAll("&amp;", "&"));
                                inLink = true;
                            }
                        }
                    }

                }
                if (inLink) {
                    if (event.isCharacters()) {
                        String name = event.asCharacters().getData();
                        listNameMovie.add(name);
                        inLink = false;
                    }
                }
            }
            reader.close();

        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLinkMovie;
    }

    public Map<String, String> getLinkCategoryMovie(String uri) {
        Map<String, String> listLinkCategory = new HashMap<String, String>();
        try {
            BufferedReader in = getUrlBufferReader(uri);
            String line;
            String document = "";

            boolean inPopularMovie = false; //take 

            document += "<root>";
            while ((line = in.readLine()) != null) {
                document += " ";
                if (line.contains("chart/movie")) {
                    inPopularMovie = true;
                }
                if (inPopularMovie) {
                    if (line.contains("</li>")) {
                        document += line;
                        inPopularMovie = false;
                    } else {
                        document += line;
                    }
                }
            }
            document += "</root>";
            in.close();

            XMLInputFactory fact = XMLInputFactory.newInstance();
            fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
            fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

            InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));

            XMLEventReader reader = fact.createXMLEventReader(
                    new InputStreamReader(
                            is, "UTF-8"));

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement element = event.asStartElement();
                    if (element.getName().toString().equals("a")) {
                        Attribute attr = element.getAttributeByName(new QName("href"));
                        if (attr != null) {
                            listLinkCategory.put("Popular", attr.getValue());
                            System.out.println(attr.getValue());
                            break;
                        }
                    }

                }

            }
            reader.close();
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLinkCategory;
    }
}
