/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entities.AccountType;
import entities.Accounts;
import entities.Movies;
import entities.MovieType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author StormNs
 */
public class Ultilities {

    public static void transformDOMToStream(Node node, String xmlOutputFile)
            throws TransformerException {
        Source src = new DOMSource(node);
        File file = new File(xmlOutputFile);
        Result result = new StreamResult(file);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        transformer.transform(src, result);
    }

    public static XMLStreamReader parseFileToStAXCursor(InputStream is)
            throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(is);
        return reader;
    }

    public static void UnMarshallAccounts(String realPath) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Accounts.class);
        Unmarshaller u = jc.createUnmarshaller();
        File f = new File(realPath+"WEB-INF/accounts.xml");
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
            File f = new File(realPath+"WEB-INF/accounts1.xml");
//            File f2 = new File("WEB-INF/accounts.xml");
//            Boolean b  = f2.exists();
            

            mar.marshal(accs, f);

        } catch (JAXBException ex) {
            Logger.getLogger(Ultilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void MarshallMovies(String realPath) {
        try {
            DAO dao = new DAO();
            List<MovieType> list = dao.getAllMovie();
            Movies movies = new Movies();
            movies.setMovies(list);

            JAXBContext jc = JAXBContext.newInstance(Movies.class);
            Marshaller mar = jc.createMarshaller();
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File f = new File(realPath+"WEB-INF/movies.xml");
            f.createNewFile();
            mar.marshal(movies, f);

        } catch (JAXBException ex) {
            Logger.getLogger(Ultilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ultilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void validateBeforeSavetoDB(String xmlPath, Movies movies, String contextPath){
        try {
            File f = new File(xmlPath);
            if(f.exists()){
                System.out.println("exist!");
            }
            JAXBContext jc = JAXBContext.newInstance(Movies.class);
            
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(xmlPath));
            
            Marshaller mar = jc.createMarshaller();
            mar.setSchema(schema);
            mar.marshal(schema, new DefaultHandler());
           
            
            System.out.println("yeee");
            
        } catch (SAXException ex) {
            Logger.getLogger(Ultilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Ultilities.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

}
