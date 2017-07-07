<%-- 
    Document   : main
    Created on : Jun 22, 2017, 12:42:49 PM
    Author     : StormNs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main</title>
    </head>
    <body style="margin:0px" onload="getMovies()">
        <jsp:include page="template/header.jsp" />
        <!--container-->
        <div class="grid-container">
            <div class="slide-panel">
                <div class="slideshow-contain container">
                    <div class="container Slider">
                        <img src="https://www.w3schools.com/w3css/img_fjords_wide.jpg"
                             alt="Image1" class="picture slideshow-image-1"/>
                        <div class="slide-description">
                            Wonder Woman 1
                        </div>
                    </div>
                    <div class="container Slider">
                        <img src="https://www.w3schools.com/w3css/img_lights_wide.jpg"
                             alt="Image2" class="picture slideshow-image-1"/>
                        <div class="slide-description">
                            Wonder Woman 2
                        </div>
                    </div>
                    <div class="container Slider">
                        <img src="https://www.w3schools.com/w3css/img_mountains_wide.jpg"
                             alt="Image3" class="picture slideshow-image-1"/>
                        <div class="slide-description">
                            Wonder Woman 3
                        </div>
                    </div>
                    <div class="container Slider">
                        <img src="https://www.w3schools.com/w3css/img_forest.jpg"
                             alt="Image4" class="picture slideshow-image-1"/>
                        <div class="slide-description">
                            Wonder Woman 4
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
                        <p class="title-desc">Wonder Woman<br>Nguoi dep Amazon</p>
                        <hr style="border-color: #292626;">
                        <div class="movie-description">Amazon princess
                            Diana finds her idyllic life 
                            interrupted when a pilot crash-lands nearby. 
                            After being told of conflict in the outside world, 
                            she decides to leave home to fight 
                            a war that can end all wars.
                        </div><br>
                        <div><span class="genre-tag">Action</span><span class="genre-tag">Action</span><span class="genre-tag">Super Heroes</span><span class="genre-tag">Action</span><span class="genre-tag">Action</span></div>
                        <form>
                            <input class="watch-btn" type="button" value="Watch" name="btnAction">
                        </form>
                    </div>
                </div>
            </div>
            <div class="lastest-container">
                <div class="header-row">
                    <span class="title-row">New  ❯ </span>
                </div>
                <div class="lastest-row">
                    <div class="movie-container">
                        <a href="movie.jsp"> 
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
            <div class="lastest-container">
                <div class="header-row">
                    <span class="title-row">Top Rated  ❯ </span>
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
            <div class="lastest-container">
                <div class="header-row">
                    <span class="title-row">Series  ❯ </span>
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
    <script>
        var firstTime = true;
        var slideIndex = 0;
        var slideIndex_pre = 0;
        var slideBadge = 0;
        document.addEventListener("DOMContentLoaded", function () {
            slideImage(slideIndex);
        });

        function getMovies() {
            localStorage.setItem("movies", ${requestScope.xmlMovies});
        }

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
