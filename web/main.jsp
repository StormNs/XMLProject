<%-- 
    Document   : main
    Created on : Jun 22, 2017, 12:42:49 PM
    Author     : StormNs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main</title>
    </head>
    <body style="margin:0px">
        <jsp:include page="template/header.jsp" />
        <!--container-->
        <div class="grid-container">
            <div class="slide-panel">
                <div class="slideshow-contain container">
                    <div class="container Slider">
                        <img src="https://static.slickdealscdn.com/attachment/8/6/6/3/0/5700268.attach"
                             alt="Image1" class="picture slideshow-image-1"/>
                        <div class="slide-description">
                            <c:set var="firstName" value="${requestScope.firstName}"/>
                            <c:if test ="${not empty firstName}">
                                ${firstName}
                            </c:if>
                            <c:if test="${empty firstName}">
                                ${firstName}
                            </c:if>
                        </div>
                    </div>
                    <div class="container Slider">
                        <img src="http://static.srcdn.com/wp-content/uploads/2017/05/Caesar-from-War-for-the-Planet-of-the-Apes.jpg"
                             alt="Image2" class="picture slideshow-image-1"/>
                        <div class="slide-description">
                            <c:set var="firstName" value="${requestScope.firstName}"/>
                            <c:if test ="${not empty firstName}">
                                ${firstName}
                            </c:if>
                            <c:if test="${empty firstName}">
                                ${firstName}
                            </c:if>
                        </div>
                    </div>
                    <div class="container Slider">
                        <img src="http://s3.foxmovies.com/foxmovies/production/films/113/images/gallery/gallery1-gallery-image.jpg"
                             alt="Image3" class="picture slideshow-image-1"/>
                        <div class="slide-description">
                            <c:set var="firstName" value="${requestScope.firstName}"/>
                            <c:if test ="${not empty firstName}">
                                ${firstName}
                            </c:if>
                            <c:if test="${empty firstName}">
                                ${firstName}
                            </c:if>
                        </div>
                    </div>
                    <div class="container Slider">
                        <img src="http://static.srcdn.com/wp-content/uploads/War-of-the-Planet-of-the-Apes-Caesar.jpg"
                             alt="Image4" class="picture slideshow-image-1"/>
                        <div class="slide-description">
                            <c:set var="firstName" value="${requestScope.firstName}"/>
                            <c:if test ="${not empty firstName}">
                                ${firstName}
                            </c:if>
                            <c:if test="${empty firstName}">
                                ${firstName}
                            </c:if>
                        </div>
                    </div>

                    <button class="text-white display-left slide-button button-black" 
                            onclick="nextSlide(-1)"> &#10094;</button>
                    <button class="text-white display-right slide-button button-black" 
                            onclick="nextSlide(1)"> &#10095;</button>
                    <div class="display-bottommiddle">
                        <span class="slide-badge" onclick="currentSlide(0)"></span>
                        <span class="slide-badge" onclick="currentSlide(1)"></span>
                        <span class="slide-badge" onclick="currentSlide(2)"></span>
                        <span class="slide-badge" onclick="currentSlide(3)"></span>
                    </div>
                </div>
                <div class="movie-inform">
                    <div class="m-inform-content">
                        <p class="title-desc"><c:set var="firstName" value="${requestScope.firstName}"/>
                            <c:if test ="${not empty firstName}">
                                ${firstName}
                            </c:if>
                            <c:if test="${empty firstName}">
                                ${firstName}
                            </c:if><br><c:set var="firstAltName" value="${requestScope.firstAltName}"/>
                            <c:if test ="${not empty firstAltName}">
                                ${firstAltName}
                            </c:if>
                            <c:if test="${empty firstName}">
                            </c:if></p>
                        <hr style="border-color: #292626;">
                        <div class="movie-description">${firstDescription}
                        </div>
                        <br>
                        <div><span class="genre-tag">Action</span><span class="genre-tag">Sci-fi</span><span class="genre-tag">Drama</span></div>
                        <form action="Watch">
                            <input name="mo" hidden="" value="${firstId}" />
                            <input class="watch-btn" type="submit" value="Watch" name="btnAction">
                        </form>
                    </div>
                </div>
            </div>
            <x:parse xml = "${requestScope.xmltext}" var = "newest"/>
            <div class="lastest-container">
                <div class="header-row">
                    <span class="title-row">Lastest  ❯ </span>
                </div>
                <div class="lastest-row">
                    <x:forEach select = "$newest/movies/movie" var = "newitem" begin="0" end="5">
                        <div class="movie-container">
                            <a href="Watch?mo=<x:out select = "$newitem/id" />"> 
                                <img class="movie-picture" alt="<x:out select = '$newitem/alternateName' /> Poster" title="<x:out select = "$newitem/name" /> Poster" src="FileServlet/<x:out select = "$newitem/imageCover" />">
                                <div class="movie-title-overlay"><x:out select = "$newitem/name" /></div>
                            </a>

                        </div>
                    </x:forEach>
                </div>
            </div>
            <x:parse xml = "${requestScope.xmltop}" var = "top"/>
            <div class="lastest-container">
                <div class="header-row">
                    <span class="title-row">Top Rated  ❯ </span>
                </div>
                <div class="lastest-row">
                    <x:forEach select = "$top/movies/movie" var = "item" begin="0" end="5">
                        <div class="movie-container">
                            <a href="Watch?mo=<x:out select = "$item/id" />"> 
                                <img class="movie-picture" alt="<x:out select = '$item/alternateName' /> Poster" title="<x:out select = "$item/name" /> Poster" src="FileServlet/<x:out select = "$item/imageCover" />">
                                <div class="movie-title-overlay"><x:out select = "$item/name" /></div>
                            </a>
                        </div>
                    </x:forEach>
                    <!--                    <div class="movie-container">
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
                                        </div>-->
                </div>
            </div>

        </div>
        <jsp:include page="template/footer.jsp"/>
    </body>
    <script>
        var firstTime = true;
        var slideIndex = 0;
        var slideIndex_pre = 0;
        var slideBadge = 0;
        document.addEventListener("DOMContentLoaded", function () {
            slideImage(slideIndex);
//            getTopMovies();
//            getMovieList();
        });


        function nextSlide(n) {
            clearTimeout(slideTime);
            if (isNaN(n)) {
                n = 1;
            }
            slideImage(slideIndex += n);
        }
        function currentSlide(n) {
            clearTimeout(slideTime);
            slideImage(slideIndex = n);
        }

        function slideImage(x) {
            var i;
            var imagesContain = document.getElementsByClassName("Slider");//for fadeout
            var picture = document.getElementsByClassName("picture");//for sliding
            var badges = document.getElementsByClassName("slide-badge");//for badge]

            var tIndex = imagesContain.length - 1;

            if (x > imagesContain.length - 1) {
                slideIndex = 0;
                slideIndex_pre = 0;
                imagesContain[0].style.background = "url('" + picture[tIndex].src + "') no-repeat";
                imagesContain[0].style.backgroundSize = "100% 100%";
            }
            if (x < 0) {
                slideIndex = imagesContain.length - 1;
                slideIndex_pre = slideIndex + 1;
                imagesContain[tIndex].style.background = "url('" + picture[0].src + "') no-repeat";
                imagesContain[tIndex].style.backgroundSize = "100% 100%";
            }

//            if (firstTime !== true && slideIndex === 0) {// set background image 4 -> 1
//                imagesContain[i].style.background = "url('" + picture[imagesContain.length - 1].src + "') no-repeat";
//                imagesContain[i].style.backgroundSize = "100% 100%";
//
//            }
//            if (x < 0 && slideIndex === tIndex) { //set background image 1->4
//
//            }

//            if (x > tIndex)
            //for display none
            for (i = 0; i < imagesContain.length; i++) {
                imagesContain[i].style.display = "none";
//                if (i > 0 && i < imagesContain.length) {
//                    if (x >= slideIndex_pre || x >= slideBadge) {
//
//                    } else {
//
//
//                    }
//                }
            }

            if (x >= slideIndex_pre || x >= slideBadge) { //slide right
                if (x > 0 && x < imagesContain.length) {
                    imagesContain[slideIndex].style.background = "url('" + picture[slideIndex - 1].src + "') no-repeat";
                    imagesContain[slideIndex].style.backgroundSize = "100% 100%";
                    firstTime = false;

                }
                for (i = 0; i < picture.length; i++) {
                    picture[i].className = picture[i].className.replace(" slideshow-image-3", " slideshow-image-1");
                }
            } else {
                if (x >= 0 && x < imagesContain.length) {
                    imagesContain[slideIndex].style.background = "url('" + picture[slideIndex + 1].src + "') no-repeat";
                    imagesContain[slideIndex].style.backgroundSize = "100% 100%";
                    firstTime = false;
                }
                for (i = 0; i < picture.length; i++) { //slide left
                    picture[i].className = picture[i].className.replace(" slideshow-image-1", " slideshow-image-3");
                }

            }

            slideIndex_pre = slideIndex;
            for (i = 0; i < badges.length; i++) {
                badges[i].className = badges[i].className.replace(" slide-badge-selected", "");
            }

            imagesContain[slideIndex].style.display = "block";
            badges[slideIndex].className += " slide-badge-selected";
            slideBadge = slideIndex;
            slideTime = window.setTimeout(nextSlide, 4000);
        }
       
    </script>

</html>
