/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.AccountType;
import entities.Favourites;
import entities.MovieImages;
import entities.MovieType;
import entities.Movies;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.DAO;
import utilities.Utilities;

/**
 *
 * @author StormNs
 */
public class FavouriteListServlet extends HttpServlet {

    private final String FavouritePage = "favourite.jsp";

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
        String sIndex = (String) request.getParameter("sIndex");
        HttpSession session = request.getSession();
        String accName = (String) session.getAttribute("account_Name");
        Integer startIndex;
        if (sIndex == null) {
            startIndex = 1;
        } else {
            startIndex = Integer.parseInt(sIndex);
        }
        if (startIndex <= 0) {
            startIndex = 1;
        }
        DAO dao = new DAO();

        String url = FavouritePage;
        try {
            if (accName != null) {
                AccountType acc = dao.getAccount(accName);
                List<MovieType> movieList = new ArrayList<>();
                List<Favourites> favList = dao.getUserFavouriteList(acc.getId(), startIndex, startIndex + 29); // get 30 movies

                for (Favourites favItem : favList) {
                    MovieType movie = dao.getMovieForFavourite(favItem.getMovie().getId());
                    if (movie != null) {
                        movieList.add(movie);
                    }
                }
                Movies movies = new Movies();
                movies.setMovies(movieList);
                Utilities ul = new Utilities();
                String moviesXml = ul.marshallFavouriteMovies(movies);
                Long filmSize = dao.getSizeFavouriteMovies(acc.getId());
                Double pageSize = Double.parseDouble(filmSize.toString()) / 30;
                request.setAttribute("sIndex", startIndex);
                request.setAttribute("PageSize", Math.ceil(pageSize));
                request.setAttribute("moviePageSize", movieList.size());
                request.setAttribute("moviesXml", moviesXml);
            }
        } catch (Exception e) {
            Logger.getLogger(FavouriteListServlet.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            dao.closeEM();;
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
