<%-- 
    Document   : header
    Created on : Jun 22, 2017, 7:51:25 PM
    Author     : USER
--%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/style.css"/>
<style>
    body{
        background-color: #afb4bb;
        margin: 0px;
    }
    #menu{
        background-color: #24292e;
        overflow: hidden;
        /*opacity: 0.6;*/
        font-size: 20px;
        /*position: fixed;*/
        width: 100%;
        top: 0px;
        left:0px;
        -webkit-box-shadow:0px 9px 21px 0px #505050;
        -moz-box-shadow:0px 9px 21px 0px #505050;
        box-shadow:0px 9px 21px 0px #505050;
    }
    #menu-container >a{
        float:left;
        font-size: 17px;
        display: block;
        text-align: center;
        color: lightgray;
        padding: 20px 40px;
    }
    #menu-account > a{
        float: right;
        display: block;
        font-size: 17px;
        text-align: center;
        color: lightgray;
        padding: 20px 40px;
    }
    #menu-account > span{
        float: right;
        display: block;
        font-size: 17px;
        text-align: center;
        color: lightgray;
        padding: 20px 40px;
    }

    #menu a:hover{
        color: white;
        cursor: pointer;
    }
    #menu-container{
        width: 70%;
        margin-left: 10%;
        margin-right:auto;
    }
    #menu-account{
        margin-left: auto;
        margin-right:auto;
    }
    a i{
        padding-right: 10px;
        font-size: 18px !important;
    }
    .brand-icon{
        font-size: 50px !important;
        margin-top: -15px;
    }
    .search-container{
        float: left;
        font-size: 17px;
        display: block;
        color: lightgray;
        padding: 16px 40px;
    }
    #search-bar{
        border-top-left-radius: 4px;
        border-bottom-left-radius: 4px;
        margin: 0px;
        background-color: #d8d6d6;
        border-style: none;
        border-color: #7b7d7f;
        height: 24px;
        padding-left: 15px;
        padding-right: 10px;
    }
    .search-btn{
        background-color: #6c7076;
        color: #d8d6d6;
        border-top-right-radius: 4px;
        border-bottom-right-radius: 4px;
        cursor: pointer;
        margin: 0px;
        display: inline;
        height: 26px;
        margin-left: -4px;
        border: none;
    }
    .sub-menu{
        display: grid;
        border-radius: 5px;
        border: solid 1px #6d6d6d;
        position: absolute;
        display: none;
        background-color: rgb(25, 24, 24);
        padding: 15px 15px 15px 10px;
        z-index: 1000;
    }
    .submenu-container{
        display: -webkit-box;
        margin: 0;
        float: left;
        text-align: center;
        color: lightgray;
        padding: 20px 40px;
        font-size: 17px;
    }
    .fast-search-result{
        display: -webkit-box;
        margin: 0;
        float: left;
        text-align: center;
        color: lightgray;
    }
    .movie-picture-result{
        float: left;
        padding-right: 10px;
        width: 50px;
        height: 80px;
    }
    .movie-title-result{
        vertical-align: top;
    }
    .movie-link{
        text-decoration: none;
        padding-bottom: 10px;
        color: lightgray;
    }
    .movie-link:hover{
        color: white;
    }
    .movie-result-container{
        max-width: 300px;
        max-height: 80px;
    }
    #fast-result-container{
        display: grid;
        border-radius: 5px;
        border: solid 1px #6d6d6d;
        position: absolute;
        display: none;
        background-color: rgb(25, 24, 24);
        padding: 15px 15px 0px 10px;
        z-index: 1000;
    }
</style>
<header role="banner">
    <div id="menu">
        <div id="menu-container">
            <!--<img src="../XMLProject/asset/play.png" height="50px" width="50px" style="float: left;margin-top: 4px;margin-left: 15px;margin-right: 15px">-->
            <a style="padding-bottom: 0px;"><i class="fa fa-play-circle brand-icon" style="color:lightgray"></i></a>
            <div class="submenu-container" onmouseover="showSubMenu('moviessub')" onmouseout="hideSubMenu('moviessub')">
                <a>MOVIES <span class="fa fa-caret-down"></span></a>
                <div class="sub-menu" id="moviessub">
                    <a>Test</a>
                    <a>Test</a>
                    <a>Test</a>
                </div>
            </div>


            <div class="submenu-container" onmouseover="showSubMenu('seriesssub')" onmouseout="hideSubMenu('seriesssub')">
                <a>SERIES <span class="fa fa-caret-down"></span></a>
                <div class="sub-menu" id="seriesssub">
                    <a>Test</a>
                    <a>Test</a>
                    <a>Test</a>
                </div>
            </div>
            <a>ABOUT</a>
            <div class="search-container">
                <div class="fast-search-result">
                    <input id="search-bar" type="text" placeholder="Search..." oninput="fastSearch()"/>
                    <button class="search-btn"><i class="fa fa-search"></i></button>
                    <!--FAST RESULT-->
                    <div id="fast-result-container" style="display: none">
                        <!--                        <a href="" class="movie-link"> 
                                                    <div class="movie-result-container">
                                                        <img class="movie-picture-result" alt="Wonder Woman Poster" title="Wonder Woman Poster" src="https://images-na.ssl-images-amazon.com/images/M/MV5BNDFmZjgyMTEtYTk5MC00NmY0LWJhZjktOWY2MzI5YjkzODNlXkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_UX182_CR0,0,182,268_AL_.jpg">
                                                        <span class="movie-title-result">WONDER WOMAN</span>
                                                        <br>
                                                        <span class="movie-title-result">Nguoi dep Amazon</span>
                                                    </div>
                                                </a>
                                                <a href="#" class="movie-link"> 
                                                    <div class="movie-result-container">
                                                        <img class="movie-picture-result" alt="Wonder Woman Poster" title="Wonder Woman Poster" src="https://images-na.ssl-images-amazon.com/images/M/MV5BNDFmZjgyMTEtYTk5MC00NmY0LWJhZjktOWY2MzI5YjkzODNlXkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_UX182_CR0,0,182,268_AL_.jpg">
                                                        <span class="movie-title-result">WONDER WOMAN</span>
                                                        <br>
                                                        <span class="movie-title-result">Nguoi dep Amazon</span>
                                                    </div>
                                                </a>-->

                        <!--<a href="#" class="movie-link">More</a>--> 
                    </div>
                </div>
            </div>
        </div>
        <div id="menu-account">
            <a>Sign up</a>
            <a><i class="fa fa-user"></i> Login</a>

            <!--            <a>Logout</a>
                        <span>Welcome <span style="color: lightseagreen">Danh</span></span>-->

        </div>
    </div>
</header>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        loadMovies();
    });
    function loadMovies() {

        if (window.XMLHttpRequest)
        {// IE7+, Firefox, Chrome, Opera, Safari
            xmlDOM = new XMLHttpRequest();
        } else
        {// IE6, IE5
            xmlDOM = new ActiveXObject("Microsoft.XMLHTTP");
        }

    }

    function searchNode(node, searchVal, displayId) {
        if (node === null) {
            return;
        }
        if (node.tagName === "name") {
            var tmp = node.firstChild.nodeValue.toLowerCase();
            if (tmp.indexOf(searchVal.toLowerCase(), 0) > -1) {
                var name = node.firstChild.nodeValue;
                var sibling = node.previousSibling;
                var num = sibling.firstChild.nodeValue;
                var sibling = sibling.nextSibling;
                var sibling = sibling.nextSibling;
                if (sibling !== null) {
                    var alt = sibling.firstChild.nodeValue;
                } else {
                    var alt = "";
                }
                if (sibling !== null) {
                    var sibling = sibling.nextSibling;
                }
                if (sibling !== null) {
                    var sibling = sibling.nextSibling;
                }

                if (sibling !== null) {
                    var cover = sibling.firstChild.nodeValue;
                } else {
                    var cover = "";
                }
                addResult(name, num, alt, cover, displayId);
            }
        }
        var childs = node.childNodes;
        for (var i = 0; i < childs.length; i++) {
            searchNode(childs[i], searchVal, displayId);
        }
    }

    function addResult(name, num, alt, cover, displayId) {
        var result = document.createElement('a');
        result.className = 'movie-link';
        result.href = "#";
        var resultDiv = document.createElement('div');
        resultDiv.className = 'movie-result-container';
        var resultImg = document.createElement('img');
        resultImg.className = 'movie-picture-result';
        resultImg.src = cover;
        resultImg.alt = name + " cover";
        resultImg.title = name + " cover";
        var resultSpan1 = document.createElement('span');
        resultSpan1.className = "movie-title-result";
        resultSpan1.innerHTML = name;
        var br = document.createElement('br');
        var resultSpan2 = document.createElement('span');
        resultSpan2.className = "movie-title-result";
        resultSpan2.innerHTML = alt;

        var container = document.getElementById(displayId);
        container.appendChild(result);
        result.appendChild(resultDiv);
        resultDiv.appendChild(resultImg);
        resultDiv.appendChild(resultSpan1);
        resultDiv.appendChild(br);
        resultDiv.appendChild(resultSpan2);
    }

    function fastSearchMovies(displayId, searchValue) {
        var container = document.getElementById(displayId);
        if (sessionStorage.getItem("list") === null || sessionStorage.getItem("list") === '') {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'DispatchServlet',false);
            xhr.onload = function () {
                if (xhr.status === 200) {
                    sessionStorage.setItem("list", xhr.responseText);
                } else {
                    alert('Request failed');
                }
            };
            xhr.send();
            parser = new DOMParser();
            xmlDOM = parser.parseFromString(sessionStorage.getItem("list"), "text/xml");

            container.innerHTML = "";
            searchNode(xmlDOM, searchValue, displayId);
        } else {
//            xmlDOM.async = false;
//            xmlDOM.loadXML(localStorage.getItem("list"));
            parser = new DOMParser();
            xmlDOM = parser.parseFromString(sessionStorage.getItem("list"), "text/xml");

            container.innerHTML = "";
            searchNode(xmlDOM, searchValue, displayId);

        }
        if (document.getElementById("fast-result-container").innerHTML === "") {
            var result = document.createElement('a');
            result.className = 'movie-link';
            result.href = "#";
            result.innerHTML = "No Result";
            document.getElementById("fast-result-container").appendChild(result);
        } else {
            var result = document.createElement('a');
            result.className = 'movie-link';
            result.href = "#";
            result.innerHTML = "More";
            document.getElementById("fast-result-container").appendChild(result);
        }
    }
    function showSubMenu(id) {
        var submenu = document.getElementById(id);
        if (submenu.style.display === 'none' || submenu.style.display === "") {
            document.getElementById(id).style.display = 'grid';
        }
    }
    function hideSubMenu(id) {
        var submenu = document.getElementById(id);
        if (submenu.style.display !== 'none' && submenu.style.display !== "") {
            document.getElementById(id).style.display = 'none';
        }
    }
    function fastSearch() {
        var x = document.getElementById("search-bar").value;
        if (x.length >= 1) {
            fastSearchMovies("fast-result-container", x);
            document.getElementById("fast-result-container").style.display = 'grid';

        } else {
            document.getElementById("fast-result-container").style.display = 'none';
        }
    }
</script>
