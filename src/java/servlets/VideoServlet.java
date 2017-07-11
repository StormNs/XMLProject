/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.MovieType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilities.DAO;
import utilities.Utilities;

/**
 *
 * @author USER
 */
@WebServlet("/Watch")
public class VideoServlet extends HttpServlet {

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
        String movieID = request.getParameter("mo");
        Utilities uti = new Utilities();
        String mo = uti.MarshallWatchMovie(Integer.parseInt(movieID));
        request.setAttribute("mo", mo);
//        DAO dao = new DAO();
//        MovieType mo = (MovieType)dao.getMovieById(Integer.parseInt(movieID)).get(0);
//        request.setAttribute("MovieName", mo.getName());
//        request.setAttribute("MovieAltName", mo.getAlternateName());
//        request.setAttribute("MovieDescription", mo.getDescription());
//        request.setAttribute("MovieImageCover", mo.getImageCover());


        /* TODO output your page here. You may use following sample code. */

//        String realPath = request.getServletContext().getRealPath("/");
//        String videoPath = "http://localhost:"+request.getLocalPort()+"/XMLProject/asset/trailer/WONDER WOMAN - Official Trailer [HD].mp4";
//        
//        request.setAttribute("videolink", videoPath);
        request.getRequestDispatcher("movie.jsp").forward(request, response);
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
