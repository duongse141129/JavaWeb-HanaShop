<%-- 
    Document   : viewOrder
    Created on : Mar 15, 2021, 5:06:10 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order JSP Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER==null || sessionScope.LOGIN_USER.roleID=='AD'}">
            <h5> Please to login</h5>
            <a href="MainController">Login</a>
        </c:if> 
   
       
        <c:if test="${sessionScope.LOGIN_USER!=null || sessionScope.LOGIN_USER.roleID=='US' && sessionScope.LOGIN_USER.roleID=='UG'}">
            
        
        <h1>Your Cart</h1>
        <h1>Welcome  ${sessionScope.LOGIN_USER}</h1>
            <c:if test="${requestScope.MESSAGE!=null && not empty requestScope.MESSAGE}">
                <h1>${requestScope.MESSAGE}</h1>
            </c:if>
            <form action="MainController">
                Date:<input type="date" name="dateOrder" value="${requestScope.CURRENT_DATE}" readonly="true"></br>
                Address Delivery: <input type="text" name="txtAddressDelivery" value="${sessionScope.LOGIN_USER.address}">
                <input type="submit" name="btnAction" value="UpdateInformation" /></br>

                <c:set var="total" value="0"/>
                <c:if test="${sessionScope.CART!=null}">
                    <c:if test="${sessionScope.CART_DETAIL!=null}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>NAME</th>
                                    <th>AMOUNT</th>
                                    <th>Price</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="foodDto" varStatus="counter" items="${sessionScope.CART_DETAIL}">
                                    <c:set var="total" value="${total=total+foodDto.amount*foodDto.price}"/>
                                    

                            <form action="MainController">
                                <tr>
                                    <td>
                                        ${foodDto.foodName}
                                        <input type="hidden" name="txtFoodName" value="${foodDto.foodName}"/>
                                    </td>
                                    <td>
                                        <input type="number" name="txtAmount" min="1" max="999" step="1" value="${foodDto.amount}"/>
                                    </td>
                                    <td>
                                        ${foodDto.price}
                                    </td>
                                    <td>
                                        <input type="submit" name="btnAction" value="UpdateBill" />
                                        <input type="hidden" name="txtFoodID" value="${foodDto.foodID}"/>
                                    </td>
                                    <td>
                                        <input type="hidden" name="txtFoodID" value="${foodDto.foodID}"/>  
                                        <input type="submit" name="btnAction" value="DeleteBill" onclick="return confirm('Are you sure you want to delete this item?');" />
                                    </td>
                                </tr>
                            </form>
                           </c:forEach>
                            </tbody>
                        </table>
                        <h2>Total: ${total}</h2>

                        <input type="hidden" name="txtTotal" value="${total}"/>
                        <input type="submit" name="btnAction" value="Bill"/></br>
                    </form>  
                    </c:if>
                </c:if>


         <a href="MainController?btnAction=Search">Add More Food</a>
         </c:if>
    </body>
</html>
