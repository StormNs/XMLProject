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
                            <source src="http://video-http.media-imdb.com/MV5BYjZhZWIzNjctZTM1Ny00ZjgzLThjMzktODlhZWFkNTVlZThiXkExMV5BbXA0XkFpbWRiLWV0cy10cmFuc2NvZGU@.mp4?Expires=1498717835&Signature=Z5mPzrBp09yVkYpk2Eo~kMroO0o-Ga3ZlaHhNHytFcQWMu2qPlmdDVkVIuOEiZqjFvdkJZnP8sht71bgpYCCOz~JI1YwQlxWUd0fjwqgLwlAivTCUgJ2CF2DfbH4GrcIrchlOTREOI2EXf5s2Yn48EOTLS~b6ebxKw2nE4hSj0Q_&Key-Pair-Id=APKAILW5I44IHKUN2DYA" type="video/mp4">
                            Your browser does not support HTML5 video.
                        </video></div>
                    <hr style="border-color: #292626;">
                    <div class="movie-detail">
                        <div class="movie-container">

                            <img class="movie-picture" alt="Wonder Woman Poster" title="Wonder Woman Poster" src="https://images-na.ssl-images-amazon.com/images/M/MV5BNDFmZjgyMTEtYTk5MC00NmY0LWJhZjktOWY2MzI5YjkzODNlXkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_UX182_CR0,0,182,268_AL_.jpg">
                            <div class="movie-title-overlay">Wonder Woman</div>


                        </div>
                        <div class="movie-text">
                            <p class="title-desc">Wonder Woman<br>Nguoi dep Amazon</p>
                            
                            <p>Amazon princess
                                Diana finds her idyllic life 
                                interrupted when a pilot crash-lands nearby. 
                                After being told of conflict in the outside world, 
                                she decides to leave home to fight 
                                a war that can end all wars.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="template/footer.jsp"/>
    </body>
</html>
