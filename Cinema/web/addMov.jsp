<%-- 
    Document   : addMov
    Created on : Jun 27, 2023, 7:48:18 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css"/>
        <style>
            .uName{
                font-size: 40px;
                font-weight: bold;
                text-align: center;
                padding-top: 20px;
                margin-bottom: 20px;
            }

            .rd{
                color: red;
            }

            .uIn4 {
                margin: 0 auto;
                font-size: 20px;
                margin-left: 550px;
            }

            .uIn4 div{
                margin-top: 15px;
            }

            .uIn4 div input{
                width: 200px;
                height: 30px;
                background-color: red;
                color: white;
                cursor: pointer;
            }

            #pass{
                background-color: white;
                color: black;
            }

            #t{
                display: none;
            }

            #t input{
                cursor: pointer;
                padding-left: 10px;
                margin-top: 15px;
            }

            #ms{
                color: red;
                margin-bottom: 15px;
            }

            .IN4 {
                display: flex;
                flex-direction: row;
                justify-content: flex-start;
                margin-left: 100px;
                ;

            }

            .IN4 img{
                width: 470px;
                height: 600px;
            }

            .eIN4 {
                font-size: 20px;
                margin-left: 50px;
            }

            .eIN4 div{
                margin-bottom: 20px;
            }

            .t{
                width: 200px;
                height: 30px;
                background-color: red;
                color: white;
                cursor: pointer;
            }

            #upd {
                display: none;
            }

            #cngPass {
                display: none;
            }

            input {
                font-size: 20px;
                margin-bottom: 10px;
            }

            select {
                font-size: 20px;
            }

            .k{
                margin-left: 10px;
            }

            .ms{
                margin-top: 20px;
                font-size: 20px;
                color: red;
            }

            .newInputBox{
                margin-left: 85px;
            }

            .eIN4 img{
                width: 25px;
                height: 25px;
                cursor: pointer;
                margin-left: 10px;
            }

            #dir {
                display: flex;
                flex-direction: column;
            }

            #dir input {
                margin-left: 7px;
            }

            .insidedir{
                display: flex;
            }

            #dir div{
                margin-bottom: 3px;
            }

            #newdir{
                margin-left: 77px;
            }

            #newstar{
                margin-left: 77px;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div><!-- comment -->
        <div class = "body">
            <div class = "IN4">
                <c:if test = "${requestScope.img != null}">
                    <div>
                        <img src ="${requestScope.accE.getImg()}">
                    </div>
                </c:if>
                <div class = "eIN4">

                    <div class = "ms">${requestScope.ms}</div>
                    <form id ="frm" action = "addmov" method = "post">
                        <div>ID: 

                            <input type ="text" required readonly value ="${requestScope.accE.getEmpID()}" name ="id"/>
                        </div>
                        <div>Tên phim: 

                            <input type ="text" readonly value ="${requestScope.accE.getUsername()}" name ="name"/>
                        </div>
                        <div id = "dir"> 
                            <div class = "insidedir">
                                Đạo diễn:
                                <input type ="text"  required value ="${requestScope.accE.getLastName()}" name ="dir"/>
                                <img onclick ="createNewDir()" src ="images/plusIcon.png"/>
                            </div>
                            <div id = "newdir">

                            </div>
                        </div>
                        <div id = "star">
                            <div class = "insidestar">
                                Diễn viên:
                                <input type ="text"  required value ="${requestScope.accE.getLastName()}" name ="star"/>
                                <img onclick ="createNewStar()" src ="images/plusIcon.png"/>
                            </div>
                            <div id = "newstar">

                            </div>
                        </div>

                        <div>Khởi chiếu: 
                            <input type ="date"  name ="startDate"/>
                        </div>

                        <div>Thời lượng: 
                            <input type ="number" required value ="${requestScope.accE.getFirstName()}" min ="0" name ="time"/>
                        </div>
                        <div>Ngôn ngữ:
                            <input type ="text" required value ="${requestScope.accE.getCccd()}" name ="lang"/>
                        </div>
                        <div>Xuất xứ: 
                            <input type ="text" required name ="org"/>
                        </div>
                        <div>Tinh trạng:
                            <select name ="stt">
                                <c:forEach items = "${requestScope.stt}" var = "i">
                                    <option value = "${i}">${i}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>Studio: 
                            <input type ="text" required value ="${requestScope.accE.getEmail()}" name ="studio"/>
                        </div>
                        <div>Hình ảnh: 
                            <input type ="text" required value ="${requestScope.accE.getAddress()}" name ="img"/>
                        </div>

                        <div>Ghi chú:
                            <input type ="text" required value ="${requestScope.accE.getAddress()}" name ="note"/>
                        </div>
                        <div><!-- comment -->
                            <input type ="submit" class ="t"  value ="ADD"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            var a = 0;
            var b = 0;
            function createNewDir() {
                if (a <= 5) {
                    // First create a DIV element.
                    var txtNewInputBox = document.createElement('div');

                    // Then add the content (a new input box) of the element.
                    txtNewInputBox.innerHTML = "<input type='text' id='newInputBox' name = 'dir'>";

                    // Finally put it where it is supposed to appear.
                    document.getElementById("newdir").appendChild(txtNewInputBox);
                    a++;
                } else {
                    alert("Lượng đạo diễn tối đa bạn có thể thêm là 6");
                }
            }

            function createNewStar() {
                if (b <= 5) {
                    // First create a DIV element.
                    var txtNewInputBox = document.createElement('div');

                    // Then add the content (a new input box) of the element.
                    txtNewInputBox.innerHTML = "<input type='text' id='newInputBox' name = 'star'>";

                    // Finally put it where it is supposed to appear.
                    document.getElementById("newstar").appendChild(txtNewInputBox);
                    b++;
                } else {
                    alert("Lượng đạo diễn tối đa bạn có thể thêm là 6");
                }
            }
        </script>
    </body>
</html>
