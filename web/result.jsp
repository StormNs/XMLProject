<%-- 
   Document   : newmovie
   Created on : Jun 27, 2017, 9:40:48 AM
   Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <style>
        .movie-container{
            animation: slide-right 1.5s;
            margin-bottom: 20px;
            margin-right: 21px;
            .lastest-container{
                margin: 20px 10px;
            }
        </style>
        <body>
            <jsp:include page="template/header.jsp"/>
            <div>
                <div class="lastest-container">
                    <div class="header-row">
                        <span class="title-row">Result for:  ❯ </span>
                    </div>
                    <div class="lastest-row">
                        <div class="movie-container">
                            <a href="#"> 
                                <img class="movie-picture" alt="Wonder Woman Poster" title="Wonder Woman Poster" src="https://images-na.ssl-images-amazon.com/images/M/MV5BNDFmZjgyMTEtYTk5MC00NmY0LWJhZjktOWY2MzI5YjkzODNlXkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_UX182_CR0,0,182,268_AL_.jpg">
                                <div class="movie-title-overlay">Wonder Woman</div>
                            </a>

                        </div>
                        
                        <div class="movie-container">
                            <a href="#"> 
                                <img class="movie-picture" alt="The Mummy Poster" title="The Mummy Poster" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMjM5NzM5NTgxN15BMl5BanBnXkFtZTgwNDEyNTk4MTI@._V1_UX182_CR0,0,182,268_AL_.jpg">
                                <div class="movie-title-overlay">The Mummy</div>
                            </a>
                        </div>
                        <div class="movie-container">
                            <a href="#"> 
                                <img class="movie-picture" alt="Pirates of the Caribbean: Dead Men Tell No Tales Poster" title="Pirates of the Caribbean: Dead Men Tell No Tales Poster" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMTYyMTcxNzc5M15BMl5BanBnXkFtZTgwOTg2ODE2MTI@._V1_UX182_CR0,0,182,268_AL_.jpg">
                                <div class="movie-title-overlay">Pirates of the Caribbean: Dead Men Tell No Tales</div>
                            </a>
                        </div>
                        <div class="movie-container">
                            <a href="#"> 
                                <img class="movie-picture" alt="Guardians of the Galaxy Vol. 2 Poster" title="Guardians of the Galaxy Vol. 2 Poster" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMTg2MzI1MTg3OF5BMl5BanBnXkFtZTgwNTU3NDA2MTI@._V1_UX182_CR0,0,182,268_AL_.jpg">
                                <div class="movie-title-overlay">Guardians of the Galaxy Vol. 2</div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="template/footer.jsp"/>
        </body>
    </html>
