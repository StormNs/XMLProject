/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entities.MovieType;
import java.awt.Image;
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

    static ArrayList<MovieType> list = new ArrayList<>();

    public Crawler() {

    }

    public static void DownloadImage(String path) {
        try {

            String uri = "https://images-na.ssl-images-amazon.com/images/M/MV5BMTk3OTI3MDk4N15BMl5BanBnXkFtZTgwNDg2ODIyMjI@._V1_SX261_CR0,0,261,383_AL_.jpg";
            String filePath = path + "/web/movieImg/t.jpg";
            URL url = new URL(uri);
            InputStream in = url.openStream();
            Files.copy(in, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        } catch (MalformedURLException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void crawlData() {
        String uri3 = "http://www.imdb.com";
        String uri4 = "http://www.imdb.com/chart/moviemeter?ref_=nv_mv_mpm_8";

//        String t = "http://www.imdb.com/title/tt4116284/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2240084082&pf_rd_r=15T546FJTADCGX5CGTPW&pf_rd_s=center-1&pf_rd_t=15506&pf_rd_i=moviemeter&ref_=chtmvm_tt_26";
//        String document = parseMovieHTML(t);
        List<String> link = getLinkMovie(uri4);

        System.out.println(link.size());
//        String document = parseMovieHTML("http://www.imdb.com" + link.get(0));
        for (int i = 0; i < 10; i++) {
            System.out.println(i + 1 + ". ");
            String document = parseMovieHTML("http://www.imdb.com" + link.get(i));
            StAXParserMovie(document);
        }

    }

    public static BufferedReader getUrlBufferReader(String uri) {
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

    public static String parseMovieHTML(String uri) {
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

    public static void StAXParserMovie(String document) {

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
//            boolean inImageCover = false; //no need
            while (reader.hasNext()) {
                try {

                    XMLEvent event = reader.nextEvent();
                    if (event.getLocation().getLineNumber() == 27) {
                        System.out.println("heheh");
                    }
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
                                    System.out.println("ImageCover: " + attr.getValue().trim());
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
                        if (element.getName().toString().equals("span")) { //CharacterName
                            Attribute attr = element.getAttributeByName(new QName("itemprop"));
                            if (attr != null) {
                                if (attr.getValue().trim().equals("name")) {
                                    inDirector = true;
                                }
                            }
                        }

                    }//end event

                    if (inMovieName) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Name: " + t);
                            inMovieName = false;
                        }
                    }
                    if (inMovieDate) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Date: " + t);
                            inMovieDate = false;
                        }
                    }
                    if (inDescription) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Description: " + t.trim());
                            inDescription = false;
                        }
                    }
                    if (inRuntime) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Runtime: " + t);
                            inRuntime = false;
                        }
                    }
                    if (inGenre) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Genre: " + t);
                            inGenre = false;
                        }
                    }
                    if (inCountry) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Country: " + t);
                            inCountry = false;
                        }
                    }
                    if (inLanguage) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Language: " + t);
                            inLanguage = false;
                        }
                    }
                    if (inRating) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Rating: " + t);
                            inRating = false;
                        }
                    }
                    if (inOriginalName) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Orignal Title: " + t);
                            inOriginalName = false;
                        }
                    }
                    if (inActorName) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Actor: " + t);
                            inActorName = false;
                            inActor = false;
                        }
                    }
                    if (inCharacterName) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Chracter: " + t);
                            inCharacterName = false;
                            System.out.println("");
                        }
                    }
                    if (inDirector) {
                        if (event.isCharacters()) {
                            String t = event.asCharacters().getData();
                            System.out.println("Director: " + t);
                            inDirector = false;
                            System.out.println("");
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
            reader.close();
//        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException | IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<String> getLinkMovie(String uri) {
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

    public static Map<String, String> getLinkCategoryMovie(String uri) {
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
