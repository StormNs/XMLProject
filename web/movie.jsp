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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <x:parse xml = "${requestScope.mo}" var = "mo"/>

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
            background-image: url('FileServlet/<x:out select = "$mo/movie/imageCover" />');
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
        .bkMark{
            color: white;
            background: none;
            border: none;
            cursor: pointer;
            outline: none;
            float: right;
            font-size: 30px !important;
        }
        .bkMark:hover{
            color:rgb(180, 197, 194);
        }
        .modal{
            display: none;
            width: 100%;
            height: 100%;
            overflow: auto;
            position: fixed;
            z-index: 1;
            top:0;
            background-color: rgba(0,0,0,0.5);
        }
        .close{
            font-size: 28px;
            color: black;
            font-weight: bold;
            top: 0;
            right: 2%;
            cursor: pointer;
            position: absolute;
        }
        .close:hover{
            color: #f31f1f;   
        }
        .modal_cotent{
            background-color: #fefefe;
            margin:auto;
            padding: 20px;
            width: 40%;
            left: 30%;
            position: absolute;
            top: 200px;
        }
        .caster{
            padding: 5px;
            display: block;
        }
        .cast-img{
            width: 50px;
            height: 75px;
        }
        .cast-name{
            width: 70%;
        }
        .cast-char{
            width: 50%;   
        }
        .casting-table{
         width:80%;
         position: relative;
        }

    </style>
    <body>
        <jsp:include page="template/header.jsp"/>
        <div>
            <div id="myModal" class="modal">
                <div class="modal_cotent">
                    <span class="close">&times;</span>
                    <p id="modalContent" >GG</p>
                </div>
            </div>
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
                                <c:if test="${not empty sessionScope.account_Name}" >
                                    <i title="Add to Favorite List" onclick="postBkMark()" value="" class="fa fa-bookmark fa-4 bkMark"></i>
                                    <input id="movieId" hidden="" type="text" value="<x:out select = "$mo/movie/id" />" />
                                    <!--<button type="button" id="btnModal" value="Click">Hehe</button>-->
                                </c:if>    
                                <p class="title-desc"><x:out select = "$mo/movie/name" /><br><x:out select = "$mo/movie/alternateName" /></p>
                            </div>
                            <div>
                                <p><span style="color: #78a7e1;">IMDB Rating:</span> <x:choose>
                                        <x:when select = "$mo/movie//rating">
                                            <i class="fa fa-star" style="color: yellow"></i>  <x:out select = "$mo/movie/rating" />/10 
                                        </x:when>
                                        <x:otherwise>
                                            N/A
                                        </x:otherwise>
                                    </x:choose></p>
                                <p><span style="color: #78a7e1;">Country:</span> <x:out select = "$mo/movie/country" /></p>
                                <p><span style="color: #78a7e1;">Language:</span> <x:out select = "$mo/movie/language" /></p>
                                <p><span style="color: #78a7e1;">Release date:</span> <x:out select = "$mo/movie/releaseDate" /></p>
                                <p><span style="color: #78a7e1;">Director:</span> <x:out select = "$mo/movie/director" /></p>
                            </div>
                            <p><span style="color: #78a7e1;">Description:</span></p>
                            <p><x:out select = "$mo/movie/description" /></p>
                            <p><span style="color: #78a7e1;">Casting:</span></p>
<!--                            <table class="casting-table">
                                <tbody>
                                    <tr class="caster">
                                        <td><img class="cast-img" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMTY4Mjg0NjIxOV5BMl5BanBnXkFtZTcwMTM2NTI3MQ@@._V1_SY200_CR0,0,134,200_AL_.jpg"></td>
                                        <td class="cast-name">Harrison Ford</td>
                                        <td class="cast-char">Han Solo</td>
                                    </tr>
                                </tbody>
                            </table>-->
                            <c:import var="castXsl" url="WEB-INF/castMovie.xsl" />
                            <c:import var="castXml" url="WEB-INF/test.xml" />
                            <x:transform xml="${requestScope.Cast4Film}" xslt="${castXsl}"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" value="no" id="refresher"/>
        <jsp:include page="template/footer.jsp"/>

        <script type="text/javascript">

            window.onload = function () {
                var e = document.getElementById("refresher");
                if (e.value == "no")
                    e.value = "yes";
                else {
                    e.value = "no";
                    location.reload();
                }
            }
            var http = new XMLHttpRequest();
            var mId = document.getElementById('movieId').value;
            var url = "EditBookMarkServlet";
            var params = "btnAction=BMARK&mId=" + mId;
            var btnBMark = document.getElementsByClassName('bkMark')[0];

            var mModal = document.getElementById('myModal');
            var mContent = document.getElementById('modalContent');
            var close = document.getElementsByClassName('close')[0];
            var btn = document.getElementById('btnModal');

            document.addEventListener("DOMContentLoaded", function (event) {
                checkFavourite();
            });
            function checkFavourite() {
                var t = '${requestScope.isFavourite}';
                if (t == 'true') {
                    btnBMark.style.color = "rgb(12, 187, 164)";
                } else {
                    btnBMark.style.color = "white";
                }
            }

            function postBkMark() {
                http.open("POST", url, true);
                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                http.send(params);
            }

            http.onreadystatechange = function () {
                if (http.readyState == 4 && http.status == 200) {
                    var result = http.responseText;
                    if (result == "Added") {
                        mContent.innerHTML = "Movie has been added to your favourite list";
                        mModal.style.display = "block";
                        btnBMark.style.color = " rgb(12, 187, 164)";
                    }
                    if (result == "Remove") {
                        mContent.innerHTML = "Movie has been removed from your favourite list";
                        mModal.style.display = "block";
                        btnBMark.style.color = "white";
                    }
                    if (result == "Error") {
                        mContent.innerHTML = "Error occurs! Please try again later";
                        mModal.style.display = "block";
                        btnBMark.style.color = "white";
                    }
                }
            }


            close.onclick = function () {
                mModal.style.display = "none";
            }

            btn.onclick = function () {
                mModal.style.display = "block";
            }
            window.onclick = function (event) {
                if (event.target == mModal) {
                    mModal.style.display = "none";
                }
            }



        </script>
    </body>
</html>
