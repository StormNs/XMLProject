/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.AccountType;
import entities.Favourites;
import entities.MovieType;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.DAO;

/**
 *
 * @author StormNs
 */
public class EditBookMarkServlet extends HttpServlet {

    private final String filmSerlvet = "VideoServlet";

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

        PrintWriter out = response.getWriter();
        String movieId = request.getParameter("mId");
        HttpSession session = request.getSession();
        String accountName = (String) session.getAttribute("account_Name");
        String header = request.getHeader("referer");
        String url = filmSerlvet;
        String bkMresult = "";
        try {
            if (accountName != null) {
                DAO dao = new DAO();
                MovieType movie = (MovieType) dao.getSingleMovie(Integer.parseInt(movieId));
                AccountType account = dao.getAccountbyId(accountName);
                Favourites fav = dao.FavouriteExisted(movie.getId(), account.getId());
                if (fav != null) { // delete
                    Boolean result = dao.deleteFavourites(fav.getId());
                    if (result) {
//                        request.setAttribute("bkMkResult", "Remove");
                        bkMresult = "Remove";
                    } else {
//                        request.setAttribute("bkMkResult", "Error");
                        bkMresult = "Error";

                    }

                } else { // create
                    dao.createFavourites(movie, account);
                    bkMresult = "Added";
//                    request.setAttribute("bkMkResult", "Added");
                }

                out.write(bkMresult);
                dao.closeEM();
            }
        } catch (Exception e) {
            Logger.getLogger(EditBookMarkServlet.class.getName()).log(Level.SEVERE, null, e);
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            out.close();
        }

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
