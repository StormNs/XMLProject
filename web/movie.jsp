<%-- 
    Document   : movie
    Created on : Jun 29, 2017, 2:36:31 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
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
            <x:parse xml = "${requestScope.mo}" var = "mo"/>
            <div class="lastest-container">
                <input hidden type="text" value="<x:out select = "$mo/movie/id" />"/>
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

                            <img class="movie-picture" alt="<x:out select = "$mo/movie/name" /> Poster" title="<x:out select = "$mo/movie/name" /> Poster" src="FileServlet/<x:out select = "$mo/movie/imageCover" />">
                            <div class="movie-title-overlay"> 
                                <x:out select = "$mo/movie/name" />
                            </div>


                        </div>
                        <div class="movie-text">
                            <div>
                            <p class="title-desc"><x:out select = "$mo/movie/name" /><br><x:out select = "$mo/movie/alternateName" /></p>
                            </div>
                            <div>
                                <p><span style="color: #78a7e1;">Country:</span> <x:out select = "$mo/movie/country" /></p>
                            <p><span style="color: #78a7e1;">Language:</span> <x:out select = "$mo/movie/language" /></p>
                            <p><span style="color: #78a7e1;">Release date:</span> <x:out select = "$mo/movie/releaseDate" /></p>
                            <p><span style="color: #78a7e1;">Director:</span> <x:out select = "$mo/movie/director" /></p>
                            </div>
                            <p><span style="color: #78a7e1;">Description:</span></p>
                            <p><x:out select = "$mo/movie/description" /></p>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="template/footer.jsp"/>
    </body>
</html>
