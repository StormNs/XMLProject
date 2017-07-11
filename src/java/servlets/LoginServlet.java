/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.AccountType;
import entities.Genres;
import entities.MovieType;
import entities.Movies;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import utilities.DAO;
import utilities.Utilities;

/**
 *
 * @author StormNs
 */
public class LoginServlet extends HttpServlet {

    private final String loginPage = "account.jsp";
    private final String mainServlet = "MainServlet";

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

        String username = request.getParameter("txtUsername").trim();
        String password = request.getParameter("txtPassword").trim();
        String email = "";
        if (username.contains("@")) {
            email = username;
            username = "";
        }

        String url = loginPage;
        HttpSession session = request.getSession();

        String accountExist = (String) session.getAttribute("account_Name");
        try {
            DAO dao = new DAO();
            List<AccountType> list = dao.findAccounts(email, username);
            dao.closeEM();
            if (list.isEmpty()) {
                request.setAttribute("Result", "Wrong_field");
            } else {
                AccountType acc = list.get(0);
                if (acc.getPassword().equals(password)) {;
//                    url = mainServlet;
                    session.setAttribute("account_Name", acc.getUsername());
                } else {
                    request.setAttribute("Result", "Wrong_field");
                }
            }

            String temp = (String) session.getAttribute("index");

//           
//            DAO dao = new DAO();
            Utilities ul = new Utilities();
            ul.CrawlDataAuto();
//           
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
