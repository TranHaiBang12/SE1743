<%-- 
    Document   : test
    Created on : May 29, 2023, 1:45:51 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action = "test" method = "post" >
            <input type ="file" id ="file" name ="file">
            <input type ="submit" value ="s" />
        </form>
        <script type="text/javascript">
            function download() {
                var filename = document.getElementById("file").files[0].name;
                console.log(filename);
                if (filename == "" || filename == null) {
                    alert('Error');
                } else {
                    var file = document.getElementById("file").files[0];
                    var filename = document.getElementById("file").files[0].name;
                    var blob = new Blob([file]);
                    var url = URL.createObjectURL(blob);

                    $('.downloadupaddinvoice').attr({'download': filename, 'href': "C:\PRJ301-BackendWeb\Assignment\images"});
                    filename = "";
                }
            }
            function setCookie(cname, cvalue, exdays) {
                var d = new Date();
                d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
                var expires = "expires=" + d.toUTCString();
                document.cookie = cname + "=" + cvalue + "; " + expires;
            }
            setCookie("Bang", "pro", 2);
// Hàm lấy Cookie
            function getCookie(cname) {
                var name = cname + "=";
                var ca = document.cookie.split(';');
                for (var i = 0; i < ca.length; i++) {
                    var c = ca[i];
                    while (c.charAt(0) == ' ')
                        c = c.substring(1);
                    if (c.indexOf(name) == 0)
                        return c.substring(name.length, c.length);
                }
                return "";
            }

            console.log(getCookie("Bang"));
        </script>
    </body>
</html>
