<%-- 
    Document   : shopping
    Created on : Mar 15, 2021, 5:00:39 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping JSP Page</title>
        <script src="JS\event.js" ></script>
        <link rel="stylesheet" href="CSS\style.css">
    </head>
    <body>
        <h1>Shopping..</h1>
        <c:if test="${sessionScope.LOGIN_USER.roleID=='AD'}">
            <h5> Please to login</h5>
            <a href="MainController">Login</a>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER==null}">
            <a href="MainController">Login</a>
        </c:if> 
        <c:if test="${sessionScope.LOGIN_USER!=null || sessionScope.LOGIN_USER.roleID=='US'}">
  

                <a href="MainController?btnAction=Logout" >Logout</a>
                <a href="MainController?btnAction=ViewOrder" >View Order</a>
                <a href="MainController?btnAction=ViewHistoryShopping" >View History Shopping</a>
                <c:if test="${session.LOGIN_USER!=null && not empty session.LOGIN_USER}">
                    <h1>Welcome: ${session.LOGIN_USER}</h1>
                </c:if>
             
         </c:if>
        <form action="MainController">
            Price: <input type="number" name="price" min="0" max="1000000" step="1000" value="0">            
            Search <input type="text" name="txtSearch" value=${param.txtSearch==null ? "" : param.txtSearch} >
            Type: <input type="text" name="txtType" >
            <input type="submit" value="Search" name="btnAction"/>    
        </form>
            <c:if test="${requestScope.MESSAGE !=null && not empty requestScope.MESSAGE}">
                <h3>${requestScope.MESSAGE}</h3>
            </c:if>
            <c:if test="${requestScope.LIST!=null}">
                <c:forEach var="foodDto" varStatus="counter" items="${requestScope.LIST}">
                    <form action="MainController" class="nbs-flexisel-item">
                        </br>
                        <div class="col-sm-4">
                            <img src="${foodDto.img}" width="250" height="250"/>
                            <input type="hidden" name="img" value="${foodDto.img}" >
                        </div>
                        <div class="col-sm-8">
                            <h3 class="title">${foodDto.foodName}</h3>
                            <input type="hidden" name="txtFoodName" value="${foodDto.foodName}" readonly="true"/>
                            <input type="hidden" name="txtFoodID" value="${foodDto.foodID}" readonly="true"/>
                            Amount:<input type="number" name="txtAmount" min="1" max="999" value="${foodDto.amount}" /></br>
                            Price: <input type="text" name="txtPrice" value="${foodDto.price}" readonly="true"/></br>
                            Create Date:<input type="date" name="txtCreateDate" value="${foodDto.createDate}" readonly="true"/></br>
                            Description:<textarea rows="6" cols="18" name="description"  readonly="true">${foodDto.description}
                            </textarea></br>
                            <input type="hidden" name="txtTypeID" value="${foodDto.type}" readonly="true"/></br>
                            <input type="submit" class="add2cart" name="btnAction" value="AddToCart">
                            <input type="number" name="numToCart" min="1" max="${foodDto.amount}" value="1"/>
                        </div>
                        <input type="hidden" name="price" min="0" max="1000000" step="50000" value="0">            
                        <input type="hidden" name="txtSearch" value=${param.txtSearch==null ? "" : param.txtSearch} >
                        <input type="hidden" name="txtType" >
                    </form>
                </c:forEach>
            </c:if>           
        <footer >
            <div class="footer">
                <ul class="pagination justify-content-center">
                    <c:set var="totalPage" value="1"/>
                    <c:if test="${requestScope.PAGE!=null}">
                        <c:set var="totalPage" value="${requestScope.PAGE}"/>
                    </c:if>
                    <c:if test="${totalPage>1}">
                        <c:forEach var="pageNum" begin="1" end="${totalPage}">
                            <li class="page-item">
                                <a class="page-link" href="MainController?btnAction=Search&pageNum=${pageNum}&txtSearch=${param.txtSearch}&price=${param.price}&txtType=${param.txtType}">${pageNum}</a>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>   
        </footer>
      
    </body>
</html>