<%-- 
   Document   : newmovie
   Created on : Jun 27, 2017, 9:40:48 AM
   Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <style>
        .movie-container{
            animation: slide-right 1.5s;
            margin-bottom: 20px;
            margin-right: 21px;
        }
        .lastest-container{
            margin: 20px 7.0px;
            min-height: 600px;
        }
    </style>
    <body>
        <jsp:include page="template/header.jsp"/>
        <div>
            <div class="lastest-container">
                <div class="header-row">
                    <span class="title-row">Result for:  ❯ ${requestScope.keyword}</span>
                </div>
                <div class="lastest-row">
                    <c:set var = "check" value = "${requestScope.result}"/>
                    <c:choose>
                        <c:when test= "${check!=''}">
                            <c:forEach items="${requestScope.result}" var="result">
                                <div class="movie-container">
                                    <a href="Watch?mo=<c:out value="${result.id}"/>"> 
                                        <img class="movie-picture" alt="<c:out value="${result.alternateName}"/>" title="${result.name} Poster" src="FileServlet/${result.imageCover}">
                                        <div class="movie-title-overlay"><c:out value="${result.name}"/></div>
                                    </a>

                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p>No result was found. Sorry.</p>
                        </c:otherwise>
                    </c:choose>
                    <!--                        <div class="movie-container">
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
</html>
