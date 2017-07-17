<%-- 
    Document   : favourite
    Created on : Jul 15, 2017, 6:14:03 PM
    Author     : StormNs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .pagination{
                display: block;
                margin: 22px 38px;
            }
            .pagination a.active{
                background-color: #7C7877;
                color: white;
            }
            .pagination a:hover{
                background-color: #c7ceb8;
            }
            .pagination a{
                text-decoration: none;
                color:#566270;
                padding: 8px 15px;
                transition: background-color 0.3s;
                border-radius: 3px;
            }
            .fav-row{
                margin:20px 38px;
            }
            .fav-Title{
                display: inherit;
                background-color: #e3dede;
                font-size: 22px;
                color: #8b8687;
                padding: 8px 35px;
                position: inherit;
            }
        </style>
    </head>
    <body>
        <jsp:include page="template/header.jsp" />
        <c:set var="favoriteList" value="${requestScope.moviesXml}"/>
        <c:if test="${not empty favoriteList}">
            <x:parse var="favList" xml="${favoriteList}" scope="request" />
            <div>
                <div class="fav-Title" ><span>Your Favorite Movie List</span></div>
                <div class="fav-row">
                    <x:forEach select="$favList/movies/movie" var="mItem" begin="0" end="${requestScope.moviePageSize}" >
                        <div class="movie-container">
                            <x:set var="mItem_Id" select="$mItem/id"/>
                            <x:set var="mItem_Img" select="$mItem/imageCover" />
                            <x:set var="mItem_Name" select="$mItem/name" />
                            <a href="Watch?mo=<x:out select="$mItem_Id"/>">
                                <img class="movie-picture" src="FileServlet/<x:out select="$mItem_Img"/>" alt="<x:out select="$mItem_Name"/> Poster"/>
                                <div class="movie-title-overlay"><x:out select="$mItem_Name"/></div>
                            </a>
                        </div>
                    </x:forEach>
                </div>
            </div>

            <div class="pagination">
                <c:if test="${requestScope.sIndex != 1}">
                    <a href="DispatchServlet?btnAction=FAVOURITE&sIndex=${requestScope.sIndex-30}">&laquo;</a>
                </c:if>
                <c:forEach begin="0" end="${requestScope.PageSize-1}" varStatus="counter">
                    <c:if test="${requestScope.sIndex >= counter.index*30 && requestScope.sIndex <= counter.index*30 +30}">
                        <a class="active" href="DispatchServlet?btnAction=FAVOURITE&sIndex=${counter.index*30+1}">${counter.index+1}</a>
                    </c:if>
                    <c:if test="${requestScope.sIndex < counter.index*30 || requestScope.sIndex > counter.index*30 +30}">
                        <a href="DispatchServlet?btnAction=FAVOURITE&sIndex=${counter.index*30+1}">${counter.index+1}</a>
                    </c:if>
                </c:forEach>
                <c:if test="${requestScope.sIndex+30 < requestScope.PageSize*30}">
                    <a href="DispatchServlet?btnAction=FAVOURITE&sIndex=${requestScope.sIndex+30}">&raquo;</a>
                </c:if>
            </div>

        </c:if>
        <c:if test="${empty favoriteList}">
            <span style="color: white">Your List is Empty</span>
        </c:if>

        <input type="hidden" value="no" id="refresher"/>
        <jsp:include page="template/footer.jsp" />
    </body>

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
    </script>
</html>
