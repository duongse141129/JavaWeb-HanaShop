<%-- 
    Document   : addFood
    Created on : Jan 8, 2021, 12:30:33 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP ADD NEW FOOD</title>
        <link rel="stylesheet" href="CSS\style.css">
        <script src="JS\event.js" ></script>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER==null || sessionScope.LOGIN_USER.roleID!='AD'}">
            <h5> Please to login</h5>
            <a href="MainController">Login</a>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER!=null && sessionScope.LOGIN_USER.roleID=='AD'}">
        
        <h1>ADD NEW FOOD </h1>
        <c:if test="${requestScope.ERROR ==null}">
            <c:set var="requestScope.ERROR.foodIDError" value=""/>
            <c:set var="requestScope.ERROR.foodNameError" value=""/>
            <c:set var="requestScope.ERROR.amountError" value=""/>
            <c:set var="requestScope.ERROR.priceError" value=""/>
        </c:if>
        <form action="MainController" method="POST">
            <div class="loadImg">
                <div class="form-input">
                    <label for="file-ip-1">Upload Image</label>
                    <input type="file" id="file-ip-1" name="img" accept="image/*" onchange="showPreview(event);">
                    <div class="preview">
                        <img id="file-ip-1-preview" width="250" height="250">
                    </div>
                </div>
            </div>
            FoodID:<input type="text" name="txtFoodId" value="" />${requestScope.ERROR.foodIDError}</br>
             Food Name:<input type="text" name="txtFoodName" value="" />${requestScope.ERROR.foodNameError}</br>
              Amount:<input type="number" name="txtAmount" min="1" max="999" value="1" />${requestScope.ERROR.amountError}</br>
              Price: <input type="text" name="txtPrice" value="0.0" />${requestScope.ERROR.priceError}</br>
              Create Date:<input type="date" name="txtCreateDate" value="${sessionScope.CURRENT_DATE}" readonly="true"/></br>
         Description:<textarea rows="6" cols="18" name="description"  >
                    </textarea></br>
             Type: <select name="cmbType" >
                 <c:forEach var="typeDto" items="${requestScope.LIST_TYPE}">
                     <option value="${typeDto.typeID}">${typeDto.typeName}</option>
                 </c:forEach>
                  </select></br>       
                  <input type="submit" name="btnAction" value="SaveNewFood" >
         </form>
         </c:if>
    </body>
</html>
