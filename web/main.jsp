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
        <style>
            .Slider{z-index: -1;
            }

            .container{
                position: relative;
                overflow-x: hidden;

            }
            .slideshow-contain{
                width: 76%;
                margin: auto;
                float:left;
                overflow-x: hidden;
            }
            .slideshow-image-1{
                width: 100%;
                height:420px;
                transition-delay: 2s;
                margin-bottom:-4px;
                animation: slide-right 1.5s;
            }
            @keyframes slide-right {
                0% { transform: translateX(100%); }
                100% { transform: translateX(0%); }
            }
            .slideshow-image-3{
                width: 100%;
                height:420px;
                transition-delay: 2s;
                margin-bottom:-4px;
                animation: slide-left 1.5s;
            }
            @keyframes slide-left {
                0% { transform: translateX(-100%); }
                100% { transform: translateX(0%); }
            }
            /*            .slideshow-image-2{
                            z-index: -1;
                            animation:hide 2s;
                        }
                        @keyframes hide {
                            0% { transform: translateX(0%); }
                            100% { transform: translateX(-100%); }
                        }*/


            .display-bottommiddle{
                position: absolute;
                text-align: center;
                left: 47%;
                margin-bottom: 20px;
                bottom: 0;
            }
            .text-white{
                color: white;
            }
            .display-left{
                float:left !important;
                position: absolute;
                top:40%;
                left:0%;
                cursor: pointer;

            }
            .display-right{
                float:right !important;
                position: absolute;
                top:40%;
                right:0%;
                cursor: pointer;
            }
            .button-black{
                color:white;
                background-color: black;
            }
            .slide-button{
                opacity: 0.7;
                height: 80px;
                border: none;
                padding: 8px 16px;
                overflow:hidden;
                text-decoration: none;
                cursor: pointer;
                outline: none;
            }
            .slide-button:hover{
                opacity: 0.7;
                background-color: darkgrey;
                color:black;
            }
            .slide-description{
                opacity: 0.7;
                border-top-right-radius: 8px;
                padding: 16px 10px !important;
                overflow: hidden;
                position:absolute;
                color:lightgray;
                background-color: black;
                bottom:0%;
                left:0%
            }
            .slide-badge{
                opacity: 0.7;
                border-radius: 50%;
                border: white 1px solid;
                display: inline-block;
                height: 10px;
                width: 10px;
                cursor: pointer;
            }
            .slide-badge-selected{
                opacity: 0.7;
                background-color:  white;
            }
            .movie-inform{
                color:lightgray;
                float:right;
                background-color: #191818;
                width: 24%;
                height: 420px;
            }
            .m-inform-content{
                margin: 15px;
                margin-top: 35px;
            }
            .genre-tag{
                margin-top: 5px;
                display: inline-block;
                padding: 6px;
                margin-right: 10px;
                background-color: lightgray;
                color: #1f4d86;
                border-radius: 7px;
                font-size: 14px;
                font-weight: 600;
                white-space: nowrap;
            }
            .watch-btn{
                box-shadow: 3px 3px 8px black;
                cursor: pointer;
                background-color: #1f4d86;
                border: none;
                border-radius: 5px;
                padding: 10px 10px;
                width: 80px;
                font-weight: 700;
                color: white;
                margin-left: auto;
                display: block;
                margin-top: 10px;
            }
            .title-desc{
                font-size: 23px;
                font-weight: bold;
                color: #4b7dbb;
            }
        </style>
    </head>
    <body style="margin:0px">
        <jsp:include page="template/header.jsp" />
        <!--container-->
        <div style="height: 500px">
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
                    <div class="movie-description">Amazon princess
                        Diana finds her idyllic life 
                        interrupted when a pilot crash-lands nearby. 
                        After being told of conflict in the outside world, 
                        she decides to leave home to fight 
                        a war that can end all wars.
                    </div><br>
                    <div><span class="genre-tag">Action</span><span class="genre-tag">Action</span><span class="genre-tag">Super Heroes</span><span class="genre-tag">Action</span><span class="genre-tag">Action</span></div>
                    <form>
                        <input class="watch-btn" type="submit" value="Watch" name="btnAction">
                    </form>
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
        function nextSlide(n) {
            slideImage(slideIndex += n);
        }
        function currentSlide(n) {
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
        }


    </script>

</html>
