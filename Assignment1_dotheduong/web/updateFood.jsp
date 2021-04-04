<%-- 
    Document   : updateFood
    Created on : Jan 13, 2021, 10:58:45 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UPDATE FOOD JSP Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER==null || sessionScope.LOGIN_USER.roleID!='AD'}">
            <h5> Please to login</h5>
            <a href="MainController">Login</a>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER!=null && sessionScope.LOGIN_USER.roleID=='AD'}">
      
        <h1>UPDATE FOOD</h1>
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
                    <input type="file" id="file-ip-1" name="imgFile" accept="image/*" onchange="showPreview(event);">
                    <div class="preview">                    
                        <img src="${sessionScope.FOOD.img}" width="250" height="250"/></br>
                       <input type="hidden" name="img" value="${sessionScope.FOOD.img}" >
                    </div>
                </div>
            </div>
             FoodID:<input type="text" name="txtFoodId" value="${sessionScope.FOOD.foodID}" readonly="true"/></br>
             Food Name:<input type="text" name="txtFoodName" value="${sessionScope.FOOD.foodName}" />${requestScope.ERROR.foodNameError}</br>
              Amount:<input type="number" name="txtAmount" min="1" max="999" value="${sessionScope.FOOD.amount}" />${requestScope.ERROR.amountError}</br>
              Price: <input type="text" name="txtPrice" value="${sessionScope.FOOD.price}" />${requestScope.ERROR.priceError}</br>
          Create Date:<input type="date" name="txtCreateDate" value="${sessionScope.FOOD.createDate}" readonly="true"/></br>
         Description:<textarea rows="6" cols="18" name="description"  >${sessionScope.FOOD.description}
                    </textarea></br>
                            <select name="cmbType">
                                <c:forEach var="typeDto" items="${requestScope.LIST_TYPE}">
                                    <c:if test="${typeDto.typeID==sessionScope.FOOD.type}">
                                        <option value="${typeDto.typeID}" selected="">${typeDto.typeName}</option>
                                    </c:if>
                                    <c:if test="${typeDto.typeID!=sessionScope.FOOD.type}">
                                       <option value="${typeDto.typeID}">${typeDto.typeName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <select name="cmbStatus" >
                                <option value="true" selected="">active</option>
                                <c:if test="${sessionScope.FOOD.status==false}">
                                    <option value="false" selected="">inactive</option>
                                </c:if>
                                <c:if test="${sessionScope.FOOD.status==true}">
                                    <option value="false" >inactive</option>
                                </c:if>
                            </select>
                        <input type="submit" name="btnAction" value="SaveUpdateFood" >
         </form>
         </c:if>
    </body>
</html>
