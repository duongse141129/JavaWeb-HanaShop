<%-- 
    Document   : management
    Created on : Mar 15, 2021, 4:42:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Management JSP Page</title>
        <script src="JS\event.js" ></script>
        <link rel="stylesheet" href="CSS\style.css">
      

    </head>
    <body>
        
        

        <c:if test="${sessionScope.LOGIN_USER==null || sessionScope.LOGIN_USER.roleID!='AD'}">
            <h5> Please to login</h5>
            <a href="MainController">Login</a>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER!=null && sessionScope.LOGIN_USER.roleID=='AD'}">
 
            <h1>Management</h1>
                <a href="MainController?btnAction=Logout" >Logout</a>
                <h1>Hello: ${sessionScope.LOGIN_USER}</h1>
               
        <form action="MainController">
            <input type="submit" name="btnAction" value="NewFood">   
            Price: <input type="number" name="price" min="0" max="1000000" step="1000" value="0">            
            Search <input type="text" name="txtSearch" value=${param.txtSearch==null ? "" : param.txtSearch}>
            Type: <input type="text" name="txtType" >
            <input type="submit" value="SearchForAdmin" name="btnAction"/>   
        </form>
            

            
            <form action="MainController"> 
                <input type="submit" name="btnAction" value="DeleteFood" onclick="return confirm('Do you want to delete');"/> 
                <c:if test="${requestScope.MESSAGE!=null && not empty requestScope.MESSAGE}">
                    <h1>${requestScope.MESSAGE}</h1>
                </c:if>
            <c:if test="${requestScope.LIST!=null}">
                 <c:set var="no" value="1"/>
            <table border="1">
                <thead>
                    <tr>
                        <th>Select</th>
                        <th>STT</th>
                        <th>Image</th>
                        <th>Food ID</th>
                        <th>Food Name</th>
                        <th>Amount</th>
                        <th>Price</th>
                        <th>Create Date</th>
                        <th>Description</th>
                        <th>Type</th>
                        <th>Status</th>
                        <th>Update Food</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="foodDto" varStatus="counter1" items="${requestScope.LIST}">

                    <tr>
                        <td><input type="checkbox" name="cb${no}" value="${foodDto.foodID}"/></td>
                      <form action="MainController">
                        <td>${counter1.count}</td>
                        <td>
                            <div class="col-sm-4"> 
                                <img src="${foodDto.img}" id="file-ip-1-preview" width="250" height="250"/></br>
                                <input type="hidden" name="img" value="${foodDto.img}" >
                                <%--<input type="file" id="file-ip-1" name="imgFile" accept="image/*" onchange="readURL(this);"> --%>
                            </div>
                        </td>
                        <td><input type="text" name="txtFoodId" value="${foodDto.foodID}" readonly="true"/></td>
                        <td><input type="text" name="txtFoodName" value="${foodDto.foodName}" readonly="true"/></td>
                        <td><input type="number" name="txtAmount" min="1" max="1000" value="${foodDto.amount}"readonly="true" /></td>
                        <td><input type="text" name="txtPrice" value="${foodDto.price}" readonly="true"/></td>
                        <td><input type="date" name="txtCreateDate" value="${foodDto.createDate}" readonly="true"/></td>
                        <td><textarea rows="6" cols="18" name="description" readonly="true"  >${foodDto.description}
                            </textarea>
                        </td>
                        <td>
                            <select name="cmbType" readonly="true" >
                                <c:forEach var="typeDto" items="${requestScope.LIST_TYPE}">
                                    <c:if test="${typeDto.typeID==foodDto.type}">
                                        <option value="${typeDto.typeID}" selected="">${typeDto.typeName}</option>
                                    </c:if>
                                    <c:if test="${typeDto.typeID!=foodDto.type}">
                                       <option value="${typeDto.typeID}">${typeDto.typeName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="cmbStatus" readonly="true">
                                <option value="true" selected="">active</option>
                                <c:if test="${foodDto.status==false}">
                                    <option value="false" selected="">inactive</option>
                                </c:if>
                               <c:if test="${foodDto.status==true}">
                                    <option value="false" >inactive</option>
                                </c:if>  
                            </select>
                        </td>
                        <td><input type="submit" name="btnAction" value="UpdateFood"></td>
                    </tr>
                </form>
                </c:forEach>
              </c:if>
                </tbody>              
            </table>
            <input type="hidden" name="selete" value="${no}"/>
        </form>
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
                                    <a class="page-link" href="MainController?btnAction=SearchForAdmin&pageNum=${pageNum}&txtSearch=${param.txtSearch}&price=${param.price}&txtType=${param.txtType}">${pageNum}</a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>   
            </footer>
           </c:if>
            
    </body>
</html>
