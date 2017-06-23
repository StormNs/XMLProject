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
            .container{
                position: relative;
            }
            .slideshow-contain{
                width: 800px;
                margin: auto;
            }
            .slideshow-image{
                width: 100%;
                height:340px;
                transition-delay: 2s;
                margin-bottom:-5px;
            }

            .display-bottommiddle{
                position: absolute;
                text-align: center;
                left: 50%;
                margin-bottom:20px;
                bottom: 0;
            }
            .text-white{
                color: white;
            }
            .display-left{
                float:left !important;
                position: absolute;
                top:50%;
                left:0%;
                cursor: pointer;

            }
            .display-right{
                float:right !important;
                position: absolute;
                top:50%;
                right:0%;
                cursor: pointer;
            }
            .button-black{
                color:white;
                background-color: black;
            }
            .slide-button{
                border: none;
                padding: 8px 16px;
                overflow:hidden;
                text-decoration: none;
                cursor: pointer;
                outline: none;
            }
            .slide-button:hover{
                background-color: darkgrey;
                color:black;
            }
            .slide-description{
                padding: 16px 10px !important;
                overflow: hidden;
                position:absolute;
                color:white;
                background-color: black;
                bottom:0%;
                left:0%
            }
            .slide-badge{
                border-radius: 50%;
                border: white 1px solid;
                padding: 0px 8px;
            }

        </style>
    </head>
    <body>
        <h1>Welcome u have logged in</h1>
        <h2>Index: ${sessionScope.index}</h2>
        <c:set value="${sessionScope.index}" var="hehe"/>

        <div class="slideshow-contain container">
            <div class="container Slider">
                <img src="https://www.w3schools.com/w3css/img_fjords_wide.jpg"
                     alt="Image1" class="slideshow-image"/>
                <div class="slide-description">
                    Wonder Woman 1
                </div>
            </div>
             <div class="container Slider">
                <img src="https://www.w3schools.com/w3css/img_lights_wide.jpg"
                     alt="Image2" class="slideshow-image"/>
                <div class="slide-description">
                    Wonder Woman 2
                </div>
            </div>
             <div class="container Slider">
                <img src="https://www.w3schools.com/w3css/img_mountains_wide.jpg"
                     alt="Image3" class="slideshow-image"/>
                <div class="slide-description">
                    Wonder Woman 3
                </div>
            </div>
             <div class="container Slider">
                <img src="https://www.w3schools.com/w3css/img_forest.jpg"
                     alt="Image4" class="slideshow-image"/>
                <div class="slide-description">
                    Wonder Woman 4
                </div>
            </div>

            <button class="text-white display-left slide-button button-black" onclick="nextSlide(-1)"> &#10094;</button>
            <button class="text-white display-right slide-button button-black" onclick="nextSlide(1)"> &#10095;</button>
            <div class="display-bottommiddle">
                <span class="slide-badge"></span>
                <span class="slide-badge"></span>
                <span class="slide-badge"></span>
            </div>
        </div>


    </body>
    <script>
        var slideIndex = 0;
        document.addEventListener("DOMContentLoaded", function () {
            slideImage(slideIndex);
        });
        function nextSlide(n) {
            slideImage(slideIndex += n);
        }

        function slideImage(x) {
            var i;
            var images = document.getElementsByClassName("Slider");
            if (x > images.length - 1) {
                slideIndex = 0;
            }
            if (x < 0) {
                slideIndex = images.length - 1;
            }
            for (i = 0; i < images.length; i++) {
                images[i].style.display = "none";
            }
            images[slideIndex].style.display = "block";
        }


    </script>

</html>
