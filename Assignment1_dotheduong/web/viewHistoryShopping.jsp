<%-- 
    Document   : viewHistoryShopping
    Created on : Jan 11, 2021, 10:41:52 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View History Shopping Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER==null || sessionScope.LOGIN_USER.roleID=='AD'}">
            <h5> Please to login</h5>
            <a href="MainController">Login</a>
        </c:if> 
        <c:if test="${sessionScope.LOGIN_USER!=null || sessionScope.LOGIN_USER.roleID=='US' && sessionScope.LOGIN_USER.roleID=='UG'}">
        <h1>Your History Shopping</h1>
        <a href="MainController?btnAction=Search">Shopping Page</a>
        <form action="MainController">
            Search
            <c:if test="${param.txtSearch ==null}">
                <input type="text" name="txtSearch" value="">
            </c:if>
            <c:if test="${param.txtSearch !=null}">
                <input type="text" name="txtSearch" value="${param.txtSearch}">
            </c:if>  
            <c:if test="${requestScope.txtDate ==null}">
                <input type="date" name="txtDate" value="">
            </c:if>
            <c:if test="${requestScope.txtDate !=null}">
                <input type="date" name="txtDate" value="${requestScope.txtDate}">
            </c:if>      
            
            <input type="submit" value="SearchHistory" name="btnAction"/>    
        </form>
        <c:if test="${requestScope.MESSAGE!=null && not empty requestScope.MESSAGE}">
                    <h1>${requestScope.MESSAGE}</h1>
        </c:if>

             <c:if test="${requestScope.HISTORY_SHOPPING!=null}">
                 
                 <c:forEach var="dto" varStatus="counter" items="${requestScope.HISTORY_SHOPPING}">
                     <table border="1">
                     <thead>
                         <tr>
                             <th>STT</th>
                             <th>Order ID</th>
                             <th>Total Price</th>
                             <th>DATE BUY</th>
                             <th>Address</th>
                         </tr>
                     </thead>
                     <tbody>

                         <tr>
                             <td>${counter.count}</td>
                             <td><c:out value="${dto.orderID}"/></td>
                             <td><c:out value="${dto.totalPrice}"/></td>
                             <td><c:out value="${dto.date}"/></td>
                             <td><c:out value="${dto.addressDelivery}"/></td>
                         </tr>
                         <tr>
                             <th>Detail</th>
                         </tr>
                          <c:forEach var="detaildto" varStatus="counter1" items="${requestScope.HISTORY_SHOPPING_DETAIL}">
                              <c:if test="${detaildto.orderID==dto.orderID}">
                                <tr>
                                    <td><c:out value="${detaildto.foodID}"/></td>
                                    <td><c:out value="${detaildto.amount}"/></td>
                                    <td><c:out value="${detaildto.price}"/></td>
                                </tr>
                              </c:if>  
                         </c:forEach>  

                     </tbody>
                     </table>
                     </br>
                  </c:forEach>   
             </c:if>
            <c:if test="${requestScope.HISTORY_SHOPPING==null}">
                 <h2>There is empty </h2>
             </c:if>
            </c:if>
            
    </body>
</html>
