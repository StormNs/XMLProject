/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.AccountType;
import entities.MovieType;
import entities.Movies;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import utilities.Crawler;
import utilities.DAO;
import utilities.Utilities;

/**
 *
 * @author StormNs
 */
public class LoginServlet extends HttpServlet {

    private final String loginPage = "login.jsp";
    private final String mainPage = "main.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String email = "";
        if (username.contains("@")) {
            email = username;
            username = "";
        }

        String url = loginPage;
        try {
            HttpSession session = request.getSession();
            String temp = (String) session.getAttribute("index");

            Integer index = null; // test session
            if (temp == null) {
                index = 0;
            } else {
                index = Integer.parseInt((temp));
                index++;
            }
//            Utilities ulti = new Utilities();
//            String realPath = request.getServletContext().getRealPath("/");
//            ulti.MarshallMovies(realPath);
            
//            DAO dao = new DAO();
//            dao.persist(new AccountType("Cuong", "123", "cat@gmail.com"));
//            List listAccounts = dao.findAccounts(email, username);
//            AccountType account = (AccountType) listAccounts.get(0);
//            if (account.getPassword().equals(password)) {
//                url = mainPage;
//                session.setAttribute("index", index.toString());
//            }
            Crawler crawl = new Crawler();
//            crawl.DownloadImage(this.getServletContext().getRealPath("") + "/../../");
                    crawl.crawlData();
            List<MovieType> list = crawl.getMovieList();
            Movies movies = new Movies();
            movies.setMovies(list);
            Utilities ul = new Utilities();
            ul.validateBeforeSavetoDB(this.getServletContext().getRealPath("/"),
                    movies);
            
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
