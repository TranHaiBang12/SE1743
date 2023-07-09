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
                border-bottom: 1px solid black;
                padding: 15px;
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
                margin-bottom: 30px;
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
                    <img src="${requestScope.data.getImg()}" alt="alt"/>
                </div>

                <div class = "content">
                    <div class = "name">${requestScope.data.getMovName()}</div>
                    <div class = "oInfo">
                        <form action = "update" method = "post">
                            <span id = "error">${requestScope.ms}</span>
                            <br/>
                            <span>ID:  </span><input type ="text" readonly name ="id" value ="${requestScope.data.getMovID()}"/>
                            <br/>
                            <span>Tên:  </span><input type ="text" required name ="name" placeholder ="${requestScope.data.getMovName()}"/>
                            <br/>
                            <span>Đạo diễn:</span><input type ="text" required name ="director" placeholder =""/>
                            <br/>
                            <span>Diễn viên:  </span><input type ="text" required name ="star" placeholder =""/>
                            <br/>
                            <span>Thể loại:  </span><input type ="text" required name ="genre" placeholder =""/>
                            <br/>
                            <span>Khởi chiếu:  </span><input type ="text" required name ="startdate" placeholder ="${requestScope.data.getStartDate()}"/>
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
            </div><!-- comment -->
        </div>



        <div class = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
