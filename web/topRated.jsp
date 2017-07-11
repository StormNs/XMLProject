<%-- 
    Document   : topRatedjsp
    Created on : Jul 10, 2017, 5:10:21 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    .movie-container{
        margin-bottom: 20px;
    }
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Top Rated</title>
    </head>
    <body onload="loadTopMovies()">
        <jsp:include page="template/header.jsp"/>
        <div class="lastest-container">
            <div class="header-row">
                <span class="title-row">Top Rated Movie</span>
            </div>
            <div id="topMovieContainer">
            </div>
        </div>
        <jsp:include page="template/footer.jsp"/>
    </body>
</html>
<script>
    function getNewLayout() {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'NewMovie', false);
        xhr.onload = function () {
            if (xhr.status === 200) {
                sessionStorage.setItem("layout", xhr.responseText);
            } else {
                alert('Request failed. Please try again later.');
            }
        };
        xhr.send();
    }
    function loadTopMovies() {
        if (sessionStorage.getItem("layout") === null || sessionStorage.getItem("layout") === '') {
            getNewLayout();
        }
        if (sessionStorage.getItem("resetTime") === null || sessionStorage.getItem("resetTime") === "") {
            startResetTime();
        } else if (new Date(sessionStorage.getItem("resetTime")) < new Date()) {
            sessionStorage.removeItem("topMovie");
            sessionStorage.removeItem("resetDate");
            startResetTime();
        }
        if (sessionStorage.getItem("topMovie") === null || sessionStorage.getItem("topMovie") === '') {
            getTopMovies();
        }
        if (sessionStorage.getItem("list") === null || sessionStorage.getItem("list") === '') {
            getMovieList();
        } else {
//            xmlDOM.async = false;
//            xmlDOM.loadXML(localStorage.getItem("list"));
        }
        parser = new DOMParser();
        xmlDOM = parser.parseFromString(sessionStorage.getItem("topMovie"), "text/xml");
        xslDOM = parser.parseFromString(sessionStorage.getItem("layout"), "text/xml");
        displayNewMovie(xmlDOM, xslDOM);
    }
    function displayNewMovie(xml, xsl) {
        if (window.ActiveXObject)
        {
            xhttp = new ActiveXObject("Msxml2.XMLHTTP");
        } else
        {
            xhttp = new XMLHttpRequest();
        }
        //IE
        if (window.ActiveXObject || xhttp.responseType === "msxml-document")
        {
            ex = xml.transformNode(xsl);
            document.getElementById("topMovieContainer").innerHTML = ex;
        }
        // code for Chrome, Firefox, Opera, etc.
        else if (document.implementation && document.implementation.createDocument)
        {
            xsltProcessor = new XSLTProcessor();
            xsltProcessor.importStylesheet(xsl);
            resultDocument = xsltProcessor.transformToFragment(xml, document);
            document.getElementById("topMovieContainer").appendChild(resultDocument);
        }
    }
</script>