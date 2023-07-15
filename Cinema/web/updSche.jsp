<%-- 
    Document   : updSche
    Created on : Jun 21, 2023, 10:24:45 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css?version=1"/><!-- comment -->
        <style>
            .frm{
                margin-left: 450px;
            }

            .tket{
                cursor: pointer;
            }

            input {
                margin-top :10px;
                margin-bottom: 10px;
                width: 500px;
                height: 30px;
                border: 1px solid black;
                border-radius: 10px;
                font-size: 20px;
                padding-left: 10px;
            }

            select{
                margin-top :10px;
                margin-bottom: 10px;
                width: 500px;
                height: 30px;
                border: 1px solid black;
                border-radius: 10px;
                font-size: 20px;
                padding-left: 10px;
            }

            label{
                font-size: 20px;
            }

            .sbmit input{
                background-color: red;
                color: white;
                cursor: pointer;
            }

            .sbmit{
            }

            .ms{
                margin-left: 450px;
                color: red;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div><!-- comment -->
        <div class = "body">
            <div class = "ms">
                <h2>${requestScope.ms}</h2>
            </div>
            <div class = "frm">
                <c:if test = "${msD == null}">
                    <form id ="frm" action = "updsche" method = "post">
                        <c:if test = "${room != null}">
                            <div>
                                <label for = "scheNo">Mã lịch chiếu: </label>
                                <input type ="text" readonly id ="scheNo" name ="scheNo" value = "${requestScope.id}"/>
                            </div>
                        </c:if>

                        <c:if test = "${room != null}">
                            <div>
                                <label for ="startDate">Nhập ngày chiếu: </label>
                                <input type ="date"required id ="startDate" name ="startDate"/>
                            </div>
                        </c:if>

                        <c:if test = "${room != null}">
                            <div>
                                <label for ="startTime">Nhập thời gian chiếu: </label>
                                <input type ="time" required id ="startTime" name ="startTime"/>
                            </div>
                        </c:if>

                        <div>
                            <label for ="movName">Phim: </label>
                            <select>
                                <c:forEach items = "${requestScope.allmov}" var = "j">
                                    <option value ="${j.getMovID()}" ${j.getMovID() == requestScope.movie.getMovID()?"selected":""}>${j.getMovName()}</option>
                                </c:forEach>
                            </select>
                            <input type ="text" id ="movID" name ="movID" readonly hidden value = "${movID}"/>
                        </div>

                        <c:if test = "${form != null}">
                            <div>
                                <label for ="form">Kiểu chiếu: </label>
                                <select name ="form">
                                    <c:forEach items = "${requestScope.form}" var = "j">
                                        <option value = "${j.getId()}" ${j.getId() == requestScope.formP.getId()?"selected":""}>${j.getFormName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                        <input type ="text" hidden value ="${requestScope.id}" name ="id"/>
                        <input type ="text" hidden value ="0" id ="check" name ="check"/>
                        <input type ="text" hidden value ="${requestScope.movie.getMovID()}" name ="mov"/>

                        <div>
                            <label for ="cin">Rạp: </label>
                            <select id ="ciN" onchange ="cnge()" name ="cin">
                                <c:forEach items = "${requestScope.cin}" var = "j">
                                    <option ${requestScope.cinP.getCinID() == j.getCinID()?"selected":""} id ="${j.getCinID()}" value = "${j.getCinID()}">${j.getCinName()}</option>

                                </c:forEach>
                            </select>
                        </div>



                        <c:if test = "${room != null}">
                            <div>
                                <label for ="room">Phòng: </label>
                                <select name = "room">
                                    <c:forEach items = "${requestScope.room}" var = "k">
                                        <option value = "${k.getRoomID()}" ${requestScope.roomP.getRoomID() == k.getRoomID()?"selected":""} >${k.getRoomID()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>

                        <div class = "sbmit">
                            <input type ="submit" value ="UPDATE"/>
                        </div>

          
                    </form>
                </c:if>
                <c:if test = "${msD != null}">
                    <div class = "ms">
                        ${requestScope.msD}
                    </div>
                </c:if>
                
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function cnge() {
                var selectedValue = document.getElementById("ciN").selectedIndex;
                document.getElementById("check").value = 1;
                document.getElementById("frm").submit();

            }

        
        </script>
    </body>
</html>
