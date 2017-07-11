<%-- 
    Document   : movie
    Created on : Jun 29, 2017, 2:36:31 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>movie title</title>
    </head>
    <style>
        .movie-watching-container{
            padding: 20px 0;
            width:1015px;
            margin: 0 auto;
        }
        .movie-container{
            display: block;
            margin-left: 17px;
        }
        .movie-detail{
            background-color: #252424;
            padding: 20px 0;
            border-radius: 5px;
        }
        .video-player{
            background-color: black;
        }
        .movie-text{
            color: lightgray;
            display: inline-block;
            position: relative;
            overflow: hidden;
            width: 75%;
            vertical-align: top;
        }
        .title-desc{
            margin-top: 10px;
        }
    </style>
    <body>
        <jsp:include page="template/header.jsp"/>
        <div>
            <div class="lastest-container">
                <div class="movie-watching-container">
                    <div style="    width: 1015px;
                         margin: 0 auto; padding-bottom: 20px">
                        <video width="1015" class="video-player" controls>
                            <source src="http://localhost:9999/XMLProject/asset/trailer/SPIDER_MAN_HOMECOMING_Official Trailer(HD).mp4" type="video/mp4">
                            Your browser does not support HTML5 video.
                        </video></div>
                    <hr style="border-color: #292626;">
                    <div class="movie-detail">
                        <div class="movie-container">

                            <img class="movie-picture" alt="${requestScope.MovieName} Poster" title="/${requestScope.MovieName} Poster" src="FileServlet/${requestScope.MovieImageCover}">
                            <div class="movie-title-overlay"> 
                                ${requestScope.MovieName}
                            </div>


                        </div>
                        <div class="movie-text">
                            <p class="title-desc">${requestScope.MovieName}<br>${requestScope.MovieAltName}</p>

                            <p>${requestScope.MovieDescription}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="template/footer.jsp"/>
    </body>
</html>
