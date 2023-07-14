<%-- 
    Document   : detail
    Created on : May 30, 2023, 8:42:22 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nội Dung Phim</title>
        <link rel="stylesheet" href="style.css"/>
        <style>
            .body{
                background-color: white;
                padding-left: 100px;
                padding-right: 100px;
                border-bottom: 2px dashed red;
            }

            .bodyTitle{
                color: black;
                font-size: 40px;
                padding: 15px;
                padding-top: 40px;
            }


            .bodyContent{
                display: flex;
                padding-top: 50px;
                padding-bottom: 50px;
                justify-content: flex-start;
            }

            .bodyContent .content{
                color: black;
                font-size: 20px;

            }

            .bodyContent .content span{
                font-weight: bold;
            }


            .img{
                width: 30%;
                margin-right: 50px;
            }

            .img img{
                width: 100%;
                height:90%;
            }

            .name{
                font-size: 40px;
                font-weight: bold;
                padding-bottom: 5px;
            }

            .oInfo{
                line-height: 35px;
            }

            input{
                font-size: 20px;

            }

            select{
                font-size: 20px;

            }

            .bodyContent .content button{
                margin-top: 30px;
                padding-top:10px;
                padding-bottom:10px;
                padding-left:25px;
                padding-right:25px;
                border-radius: 15px;
                background-color: red;
                color:white;
            }

            #error{
                color: red;
            }

            .uName{
                font-size: 40px;
                font-weight: bold;
                text-align: center;
                padding-top: 20px;
                margin-bottom: 20px;
            }
            .ttle{
                text-align: center;
                padding-top: 40px;
                font-size: 27px;
                font-weight: bold;
                margin-bottom: 40px;
                text-shadow: 10px 10px 5px #666666;
                color: brown;
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
                justify-content: center;
                ;

            }

            .IN4 img{
                width: 470px;
                height: 600px;
            }

            .eIN4 {
                font-size: 20px;
                margin-right: 20px;
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



            #dir div{
                margin-bottom: 3px;
            }

            #newdir{
                margin-left: 77px;
            }

            #newstar{
                margin-left: 77px;
            }

            #newgenre{
                margin-left: 77px;
            }

            #newgenre select{
                margin-top: 20px;
            }

            .eIN4{
            }

            .oInfo{
                padding-top: 0px;
                line-height: 55px;
                font-size: 20px;
            }

            .blk{
                font-weight: bold;
            }

            .content{
                display: flex;
                flex-direction: column;
            }
            
            .IN4{
                margin-left: 0px;
                padding-left: 0px;
            }
            
            .eIN4{
                width: 100%;
            }
            
            td img{
                width: 100%;
                height: 100%;
            }
        </style>

    </head>
    <body>
        <div class = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "bodyTitle">
                Nội Dung Phim:
            </div>
            <div class ="bodyContent">
                <div class = "img">  
                    <img class ="l" src="${requestScope.data.getImg()}" alt="alt"/>
                </div>
                <c:if test = "${requestScope.check == null}">
                    <div class = "content">
                        <div class = "name">${requestScope.data.getMovName()}</div>
                        <div class = "oInfo">
                            <form action = "update" method = "post">
                                <c:if test = "${requestScope.ms != null}">
                                    <span id = "error">${requestScope.ms}</span>
                                </c:if>
                                <span>ID:  </span><input type ="text" readonly name ="id" value ="${requestScope.data.getMovID()}"/>
                                <br/>
                                <span>Tên:  </span><input type ="text" required name ="name" placeholder ="${requestScope.data.getMovName()}"/>
                                <br/>
                                <div class ="IN4">
                                    <div class = "eIN4">
                                        <div id = "dir"> 
                                            <div class = "insidedir">
                                                <span>Đạo diễn:</span>
                                                <input type ="text"  required value ="${requestScope.accE.getLastName()}" name ="dir"/>
                                                <img onclick ="createNewDir()" src ="images/plusIcon.png"/>
                                            </div>
                                            <div id = "newdir">

                                            </div>
                                        </div>
                                        <div id = "star">
                                            <div class = "insidestar">
                                                <span class = "blk">Diễn viên:</span>
                                                <input type ="text"  required value ="${requestScope.accE.getLastName()}" name ="star"/>
                                                <img onclick ="createNewStar()" src ="images/plusIcon.png"/>
                                            </div>
                                            <div id = "newstar">

                                            </div>
                                        </div>

                                        <div><span class = "blk">Thể loại:</span>
                                            <select name ="genre">
                                                <c:forEach items = "${requestScope.list}" var = "i">
                                                    <option value = "${i.getGenreID()}">${i.getGenre()}</option>
                                                </c:forEach>
                                            </select>
                                            <img onclick ="createNewGenre('${requestScope.list}')" src ="images/plusIcon.png"/>
                                            <div id = "newgenre">
                                                <select hidden id ="slt1" name ="genre">
                                                    <c:forEach items = "${requestScope.list}" var = "i">
                                                        <option value = "${i.getGenreID()}">${i.getGenre()}</option>
                                                    </c:forEach>
                                                </select><!-- comment -->
                                                <select hidden id ="slt2" name ="genre">
                                                    <c:forEach items = "${requestScope.list}" var = "i">
                                                        <option value = "${i.getGenreID()}">${i.getGenre()}</option>
                                                    </c:forEach>
                                                </select>
                                                <select hidden id ="slt3" name ="genre">
                                                    <c:forEach items = "${requestScope.list}" var = "i">
                                                        <option value = "${i.getGenreID()}">${i.getGenre()}</option>
                                                    </c:forEach>
                                                </select>
                                                <select hidden id ="slt4" name ="genre">
                                                    <c:forEach items = "${requestScope.list}" var = "i">
                                                        <option value = "${i.getGenreID()}">${i.getGenre()}</option>
                                                    </c:forEach>
                                                </select>
                                                <select hidden id ="slt5" name ="genre">
                                                    <c:forEach items = "${requestScope.list}" var = "i">
                                                        <option value = "${i.getGenreID()}">${i.getGenre()}</option>
                                                    </c:forEach>
                                                </select>
                                                <select hidden id ="slt6" name ="genre">
                                                    <c:forEach items = "${requestScope.list}" var = "i">
                                                        <option value = "${i.getGenreID()}">${i.getGenre()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <input hidden type ="text" id ="gN" name ="gN" value ="0"/>
                                <span>Khởi chiếu:  </span><input type ="date" required name ="startdate" placeholder ="${requestScope.data.getStartDate()}"/>
                                <br/>
                                <span>Thời lượng:  </span><input type ="text" required name ="time" placeholder ="${requestScope.data.getTime()}"/>
                                <br/>
                                <span>Ngôn ngữ:  </span><input type ="text" required name ="lang" placeholder ="${requestScope.data.getLanguage()}"/>
                                <br/>
                                <span>Xuất xứ:  </span><input type ="text" required name ="org" placeholder ="${requestScope.data.getOrigin()}"/>
                                <br/>
                                <span>Tình trạng:  </span><input type ="text" required name ="status" placeholder ="${requestScope.data.getStatus()}"/>
                                <br/>
                                <span>Studio:  </span><input type ="text" required name ="studio" placeholder ="${requestScope.data.getStudio()}"/>
                                <br/>
                                <span>Hình ảnh:  </span><input type ="text" required name ="img" placeholder ="${requestScope.data.getImg()}"/>
                                <br/>
                                <button type ="submit" value ="MUA VÉ">UPDATE</button>
                            </form>
                        </div>
                    </div>
                </c:if>
                <c:if test = "${requestScope.check != null}">
                    <div class = "content">
                        <div class = "oInfo">
                            <c:if test = "${requestScope.ms != null}">
                                <span id = "error">${requestScope.ms}</span>
                            </c:if>
                                <br/>
                            <span class = "blk">Đạo diễn: </span>${requestScope.dir}
                            <br/>
                            <span class = "blk">Diễn viên:  </span>${requestScope.star}
                            <br/>
                            <span class = "blk">Thể loại:  </span>${requestScope.genre}
                            <br/>
                            <span class = "blk">Khởi chiếu:  </span>${requestScope.data.getStartDate()}
                            <br/>
                            <span class = "blk">Thời lượng:  </span>${requestScope.data.getTime()} phút
                            <br/>
                            <span class = "blk">Ngôn ngữ:  </span>${requestScope.data.getLanguage()}
                            <br/>
                            <span class = "blk">Xuất xứ:  </span>${requestScope.data.getOrigin()}
                            <br/>
                            <span class = "blk">Tình trạng:  </span>${requestScope.data.getStatus()}
                            <br/>
                        </div>
                        <div>
                            <a href = "update?id=${requestScope.id}"><button type ="button" value ="MUA VÉ">UPDATE</button></a>
                        </div>
                    </div>
                </c:if>
            </div><!-- comment -->
        </div>



        <div class = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script>
            var a = 0;
            var b = 0;
            var c = 0;
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

            function createNewGenre() {
                c++;
                document.getElementById("gN").value = c;
                console.log(c);
                document.getElementById("slt" + c).style.display = 'block';
            }
        </script>
    </body>
</html>
