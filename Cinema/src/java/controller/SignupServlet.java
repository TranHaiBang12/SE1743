/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author acer
 */
public class SignupServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <title>ĐĂNG KÝ</title>\n"
                    + "    \n"
                    + "        <<link rel=\"stylesheet\" href=\"style.css\"/>\n"
                    + "        <style>\n"
                    + "            .body{\n"
                    + "                background-color: white;\n"
                    + "                margin-top: 0px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .loginForm label{\n"
                    + "                font-weight: bold;\n"
                    + "            }\n"
                    + "\n"
                    + "            .loginForm{\n"
                    + "                padding: 25px;\n"
                    + "                font-size: 18px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .loginTitle {\n"
                    + "                background-color: red;\n"
                    + "                color: white;\n"
                    + "                padding: 15px;\n"
                    + "                text-align: center;\n"
                    + "                width: 540px;\n"
                    + "                display: flex;\n"
                    + "                margin-left: 10px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .loginTitle a{\n"
                    + "                margin-left: 15px;\n"
                    + "                margin-right: 65px;\n"
                    + "                font-weight: bold;\n"
                    + "                text-decoration: none;\n"
                    + "                color: white;\n"
                    + "            }\n"
                    + "\n"
                    + "            .dk {\n"
                    + "                text-decoration: none;\n"
                    + "                position: relative;\n"
                    + "            }\n"
                    + "\n"
                    + "            .dk:after {\n"
                    + "                content: '';\n"
                    + "                width: 100%;\n"
                    + "                position: absolute;\n"
                    + "                left: 0;\n"
                    + "                bottom: -17px;\n"
                    + "                border-width: 0 0 5px;\n"
                    + "                border-style: solid;\n"
                    + "            }\n"
                    + "\n"
                    + "            .loginForm input {\n"
                    + "                width: 500px;\n"
                    + "                height: 25px;\n"
                    + "                margin-top: 10px;\n"
                    + "                margin-bottom: 10px;\n"
                    + "            }\n"
                    + "\n"
                    + "            #logBtn{\n"
                    + "                background-color: red;\n"
                    + "                color: white;\n"
                    + "                font-weight: bold;\n"
                    + "                padding-top: 15px;\n"
                    + "                padding-bottom: 30px;\n"
                    + "                border: 1px solid black;\n"
                    + "                border-radius: 15px;\n"
                    + "                cursor: pointer;\n"
                    + "            }\n"
                    + "            \n"
                    + "            .header {\n"
                    + "            }\n"
                    + "            \n"
                    + "            .body .logo img{\n"
                    + "                width: 250px;\n"
                    + "                height: 250px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .body{\n"
                    + "                width: 100%;\n"
                    + "                margin-top: 0px;\n"
                    + "                display: flex;\n"
                    + "                justify-content: space-between;\n"
                    + "                border-top: 5px dotted red;\n"
                    + "                border-bottom: 5px dotted red;\n"
                    + "            }\n"
                    + "            \n"
                    + "            \n"
                    + "            \n"
                    + "            .logn {\n"
                    + "                padding-top: 30px;\n"
                    + "                padding-bottom: 30px;\n"
                    + "            }\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + ".body{\n"
                    + "                background-color: white;\n"
                    + "                margin-top: 0px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .loginForm label{\n"
                    + "                font-weight: bold;\n"
                    + "            }\n"
                    + "\n"
                    + "            .loginForm{\n"
                    + "                padding: 25px;\n"
                    + "                font-size: 18px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .loginTitle {\n"
                    + "                background-color: red;\n"
                    + "                color: white;\n"
                    + "                padding: 15px;\n"
                    + "                text-align: center;\n"
                    + "                width: 540px;\n"
                    + "                display: flex;\n"
                    + "                margin-left: 10px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .loginTitle a{\n"
                    + "                margin-left: 15px;\n"
                    + "                margin-right: 65px;\n"
                    + "                font-weight: bold;\n"
                    + "                text-decoration: none;\n"
                    + "                color: white;\n"
                    + "            }\n"
                    + "\n"
                    + "            .dk {\n"
                    + "                text-decoration: none;\n"
                    + "                position: relative;\n"
                    + "            }\n"
                    + "\n"
                    + "            .dk:after {\n"
                    + "                content: '';\n"
                    + "                width: 100%;\n"
                    + "                position: absolute;\n"
                    + "                left: 0;\n"
                    + "                bottom: -17px;\n"
                    + "                border-width: 0 0 5px;\n"
                    + "                border-style: solid;\n"
                    + "            }\n"
                    + "\n"
                    + "            .loginForm input {\n"
                    + "                width: 500px;\n"
                    + "                height: 25px;\n"
                    + "                margin-top: 10px;\n"
                    + "                margin-bottom: 10px;\n"
                    + "            }\n"
                    + "\n"
                    + "            #logBtn{\n"
                    + "                background-color: red;\n"
                    + "                color: white;\n"
                    + "                font-weight: bold;\n"
                    + "                padding-top: 15px;\n"
                    + "                padding-bottom: 30px;\n"
                    + "                border: 1px solid black;\n"
                    + "                border-radius: 15px;\n"
                    + "                cursor: pointer;\n"
                    + "            }\n"
                    + "            \n"
                    + "            .header {\n"
                    + "            }\n"
                    + "            \n"
                    + "            .body .logo img{\n"
                    + "                width: 250px;\n"
                    + "                height: 250px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .body{\n"
                    + "                width: 100%;\n"
                    + "                margin-top: 0px;\n"
                    + "                display: flex;\n"
                    + "                justify-content: space-between;\n"
                    + "                border-top: 5px dotted red;\n"
                    + "                border-bottom: 5px dotted red;\n"
                    + "            }\n"
                    + "            \n"
                    + "            \n"
                    + "            \n"
                    + "            .logn {\n"
                    + "                padding-top: 30px;\n"
                    + "                padding-bottom: 30px;\n"
                    + "            }\n"
                    + "            \n"
                    + "            #rule{\n"
                    + "                height:15px;\n"
                    + "                width:20px;\n"
                    + "                margin-right:10px;\n"
                    + "                padding: 0px;\n"
                    + "            }\n"
                    + "            \n"
                    + "            #nam{\n"
                    + "                height:15px;\n"
                    + "                width:20px;\n"
                    + "                margin-top: 2px;\n"
                    + "                margin-bottom: 12px;\n"
                    + "                margin-right:10px;\n"
                    + "                padding: 0px;\n"
                    + "            }\n"
                    + "            \n"
                    + "            #nu{\n"
                    + "                height:15px;\n"
                    + "                width:20px;\n"
                    + "                margin-right:10px;\n"
                    + "                margin-top: 2px;\n"
                    + "                margin-bottom: 12px;\n"
                    + "                padding: 0px;\n"
                    + "            }\n"
                    + "            \n"
                    + "            h2 {\n"
                    + "                margin-left:0px;\n"
                    + "                margin-top:0px;\n"
                    + "                margin-bottom:10px;\n"
                    + "                color:red\n"
                    + "            }\n"
                    + "            \n"
                    + "            #khac{\n"
                    + "                height:15px;\n"
                    + "                width:20px;\n"
                    + "                margin-right:10px;\n"
                    + "                margin-top: 2px;\n"
                    + "                margin-bottom: 12px;\n"
                    + "                padding: 0px;\n"
                    + "            }\n"
                    + "\n"
                    + ""
                    + "        </style>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <ul class=\"menu_ttinBenLe\">\n"
                    + "            <li><a href=\"#\">TUYỂN DỤNG</a></li>\n"
                    + "            <li><a href=\"#\">TIN MỚI & ƯU ĐÃI</a></li>\n"
                    + "            <li><a href=\"#\">VÉ CỦA TÔI</a></li>\n"
                    + "\n"
                    + "            <c:if test=\"${sessionScope.account==null}\">\n"
                    + "                <li><a href=\"login\">ĐĂNG NHẬP/ĐĂNG KÝ</a></li>\n"
                    + "                </c:if>\n"
                    + "            <li><a href=\"#\">CSKH</a></li>\n"
                    + "            <li><a href=\"#\">EN</a></li>\n"
                    + "                <c:if test = \"${sessionScope.account!=null}\">\n"
                    + "                \n"
                    + "                <div class=\"dropdown\">\n"
                    + "                    <li id = \"userN\">${sessionScope.account.getUserName()}</li>\n"
                    + "                    <div class=\"dropdown-content\">\n"
                    + "                        <div class=insidedropdown-content>\n"
                    + "                            <a href=\"#\">Thông Tin Tài Khoản</a>\n"
                    + "                            <a href=\"logout\">Đăng Xuất</a>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </c:if>\n"
                    + "        </ul>\n"
                    + "\n"
                    + "        <div class=\"wrapper_menu\">\n"
                    + "\n"
                    + "            <ul class=\"menu\">\n"
                    + "                <li><a href = \"home\"><img class=\"cinemaLogo\" src=\"images/logoCinema.png\" /></a></li>\n"
                    + "                <li>\n"
                    + "                    <div class=\"dropdown\">\n"
                    + "                        <div class=\"dropbtn\">\n"
                    + "                            <img class=\"movieIcon\" src=\"images/movieIcon.jpg\" />\n"
                    + "                            <a href=\"#\">PHIM</a>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"dropdown-content\">\n"
                    + "                            <div class=insidedropdown-content>\n"
                    + "                                <a href=\"#\">Phim Đang Chiếu</a>\n"
                    + "                                <a href=\"#\">Phim Sắp Chiếu</a>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </li>\n"
                    + "                <li>\n"
                    + "                    <div class=\"dropdown\">\n"
                    + "                        <div class=\"dropbtn\">\n"
                    + "                            <img class=\"cinemaIcon\" src=\"images/cinemaIcon.png\" />\n"
                    + "                            <a href=\"#\">RẠP</a>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"dropdown-content\">\n"
                    + "                            <div class=insidedropdown-content>\n"
                    + "                                <a href=\"#\">Tất Cả Các Rạp</a>\n"
                    + "                                <a href=\"#\">Rạp Đặc Biệt</a>\n"
                    + "                                <a href=\"#\">Rạp 3D</a>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </li>\n"
                    + "                <li>\n"
                    + "                    <div class=\"dropdown\">\n"
                    + "                        <div class=\"dropbtn\">\n"
                    + "                            <img class=\"memberIcon\" src=\"images/memberIcon.jpg\" />\n"
                    + "                            <a href=\"#\">THÀNH VIÊN</a>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"dropdown-content\">\n"
                    + "                            <div class=insidedropdown-content>\n"
                    + "                                <a href=\"#\">Tài Khoản</a>\n"
                    + "                                <a href=\"#\">Quyền Lợi</a>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </li>\n"
                    + "                <li>\n"
                    + "                    <div class=\"dropdown\">\n"
                    + "                        <div class=\"dropbtn\">\n"
                    + "                            <img class=\"culturePlexIcon\" src=\"images/culturePlexIcon.png\" />\n"
                    + "                            <a href=\"#\">CULTUREPLEX</a>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"dropdown-content\">\n"
                    + "                            <div class=insidedropdown-content>\n"
                    + "                                <a href=\"#\">Nội Quy</a>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </li>\n"
                    + "                <li><a href=\"#\"><img class=\"storeLogo\" src=\"images/storeiCON.png\" /></a></li>\n"
                    + "            </ul>\n"
                    + "\n"
                    + "\n"
                    + "        </div>"
                    + "        <div class = \"body\">\n"
                    + "            <div class = \"logn\">\n"
                    + "                <div class = \"loginTitle\">\n"
                    + "                    <a href = \"login\" class = \"dn\">ĐĂNG NHẬP</a>\n"
                    + "                    <a href = \"signup\"class = \"dk\">ĐĂNG KÝ</a>\n"
                    + "                </div>\n"
                    + "                <div class =\"loginForm\">\n");
            if (request.getAttribute("ms") != null) {
                out.println("<h2>" + request.getAttribute("ms") + "</h2>");
            }

            out.println("                    <form  method = \"post\">\n"
                    + "                        <label for = \"name\">Tên tài khoản <span style=\"color:red\">*</span></label>\n"
                    + "                            <br/><!-- comment -->\n"
                    + "                            <input type =\"text\" id =\"name\" name =\"name\" placeholder =\"Tên tài khoản\"/>\n"
                    + "\n"
                    + "                            <br/><!-- comment -->\n"
                    + "                            \n"
                    + "                            <label for = \"gender\">Giới tính <span style=\"color:red\">*</span>:</label>\n"
                    + "                            <label for =\"nam\">Nam</label>\n"
                    + "                            <input type =\"radio\" id =\"nam\" name =\"gender\" value =\"1\"/>\n"
                    + "                            <label for =\"nam\">Nữ</label>\n"
                    + "                            <input type =\"radio\" id =\"nu\" name =\"gender\" value =\"0\"/>\n"
                    + "                            <label for =\"nam\">Khác</label>\n"
                    + "                            <input type =\"radio\" id =\"khac\" checked name =\"gender\" value =\"2\"/>\n"
                    + "\n"
                    + "                            <br/><!-- comment -->\n"
                    + "\n"
                    + "                            <label for = \"sdt\">Số điện thoại <span style=\"color:red\">*</span></label>\n"
                    + "                            <br/><!-- comment -->\n"
                    + "                            <input type =\"text\" id =\"sdt\" name =\"sdt\" placeholder =\"Số điện thoại\"/>\n"
                    + "\n"
                    + "                            <br/>\n"
                    + "                            \n"
                    + "                            <label for = \"email\">Email <span style=\"color:red\">*</span></label>\n"
                    + "                            <br/><!-- comment -->\n"
                    + "                            <input type =\"text\" id =\"email\" name =\"email\" placeholder =\"Email\"/>\n"
                    + "\n"
                    + "                            <br/>\n"
                    + "                            \n"
                    + "                            <label for = \"pass\">Mật khẩu <span style=\"color:red\">*</span></label>\n"
                    + "                            <br/><!-- comment -->\n"
                    + "                            <input type =\"password\" id =\"pass\" name =\"pass\" placeholder =\"Mật khẩu\"/>\n"
                    + "\n"
                    + "                            <br/>\n"
                    + "                            \n"
                    + "                            <label for = \"dob\">Ngày sinh <span style=\"color:red\">*</span></label>\n"
                    + "                            <br/>\n"
                    + "                            <input type =\"date\" id =\"dob\" name =\"dob\" placeholder =\"Ngày sinh\"/>\n"
                    + "                            <br/>\n"
                    + "                            \n"
                    + "                            <label for = \"area\">Khu vực <span style=\"color:red\">*</span></label>\n"
                    + "                            <br/><!-- comment -->\n"
                    + "                            <input type =\"text\" id =\"area\" name =\"area\" placeholder =\"Khu vực\"/>\n"
                    + "\n"
                    + "                            <br/>\n"
                    + "                            \n"
                    + "                            <input type =\"checkbox\" checked id =\"rule\" name =\"rule\"><label for = \"rule\">Tôi đồng ý với Điều Khoản Sử Dụng </label><span style=\"color:red\">*</span>\n"
                    + "                            <br/>\n"
                    + "\n"
                    + "                            <input id =\"logBtn\" type =\"submit\" value =\"ĐĂNG KÝ\"/>\n"
                    + "                    </form>"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            <div class = \"logo\">\n"
                    + "                <img src = \"images/logoCinema.png\">\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class = \"footer\">\n"
                    + "            <div class = \"service\">\n"
                    + "                <div class = \"cskh\">\n"
                    + "                    <div class = \"tt\">\n"
                    + "                        Chăm sóc khách hàng\n"
                    + "                    </div>\n"
                    + "                    <br/>\n"
                    + "                    <div class = \"ct\">\n"
                    + "                        <div class = \"d\">Hotline: 1900 6017</div>\n"
                    + "\n"
                    + "                        <div>Giờ làm việc: 8:00 - 22:00 (Tất cả các ngày bao gồm cả Lễ Tết)</div>\n"
                    + "\n"
                    + "                        <div>Email hỗ trợ: tranhaibang665@gmail.com</div>\n"
                    + "                        <br/>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "\n"
                    + "                <div class = \"lh\">\n"
                    + "                    <div class = \"tt\">\n"
                    + "                        Kết nối với chúng tôi\n"
                    + "                    </div>\n"
                    + "                    <br/>\n"
                    + "                    <div class = \"ct\">\n"
                    + "                        <a href = \"#\"><img src =\"images/facebookIcon.png\"/></a>\n"
                    + "                        <a href = \"#\"><img src =\"images/twitterIcon.jpg\"/></a>\n"
                    + "                        <a href = \"#\"><img src =\"images/instagramIcon.png\"/></a>\n"
                    + "                        <a href = \"#\"><img src =\"images/zaloIcon.png\"/></a>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            \n"
                    + "            <div class = \"tTin\">\n"
                    + "                <div class = \"logoCty\">\n"
                    + "                    <img src =\"images/logoCinema.png\"/>\n"
                    + "                </div>\n"
                    + "                \n"
                    + "                <div class = \"abtCty\">\n"
                    + "                    <span>CÔNG TY TNHH CJ MAST VIETNAM</span>\n"
                    + "                    <br/>\n"
                    + "                    Giấy CNĐKDN: 0303675393, đăng ký lần đầu ngày 31/7/2008, đăng ký thay đổi lần thứ 5 ngày 14/10/2015, cấp bởi thành phố Hà Nội.\n"
                    + "                    <br/>\n"
                    + "                    Địa Chỉ: ...\n"
                    + "                    <br/>\n"
                    + "                    Hotline: 1900 6017\n"
                    + "                    <br/>\n"
                    + "                    COPYRIGHT 2017 CJ MAST. All RIGHTS RESERVED\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>"
                    + "    </body>\n"
                    + "</html>");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        AccountDAO acd = new AccountDAO();
        String user = request.getParameter("name");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("sdt");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String dob = request.getParameter("dob");
        String area = request.getParameter("area");
        String rule = request.getParameter("rule");
        System.out.println(gender);
        if (user.equals("") || gender.equals("") || phone.equals("") || email.equals("") || pass.equals("") || dob.equals("") || area.equals("") || (rule != null && rule.equals(""))) {
            String ms = "Vui lòng điền đầy đủ tất cả thông tin";
            //request.getRequestDispatcher("")
            request.setAttribute("ms", ms);
            processRequest(request, response);
        } else if (acd.checkU(user) != null) {
            String ms = "Tên tài khoản đã tồn tại";
            //request.getRequestDispatcher("")
            request.setAttribute("ms", ms);
            processRequest(request, response);
        } else if (acd.checkP(phone) != null) {
            String ms = "Số điện thoại đã tồn tại";
            //request.getRequestDispatcher("")
            request.setAttribute("ms", ms);
            processRequest(request, response);
        } else if (phone.length() != 10) {
            String ms = "Vui lòng nhập đúng số điện thoại";
            //request.getRequestDispatcher("")
            request.setAttribute("ms", ms);
            processRequest(request, response);
        } else if (phone.length() == 10) {
            try {
                int p = Integer.parseInt(phone);
            } catch (Exception e) {
                String ms = "Vui lòng nhập đúng số điện thoại";
                request.setAttribute("ms", ms);
                processRequest(request, response);
            }
        } if (acd.checkE(email) != null) {
            String ms = "Email đã tồn tại";
            //request.getRequestDispatcher("")
            request.setAttribute("ms", ms);
            processRequest(request, response);
        } 
//        else if (email.matches("^[A-Z0-9._%+-]@[A-Z0-9.-]+\\\\\\\\.[A-Z]{2,6}$") == false) {
//            String ms = "Vui lòng nhập đúng email";
//            //request.getRequestDispatcher("")
//            request.setAttribute("ms", ms);
//            processRequest(request, response);
//        } 
        //bo sung them phan check email
        
        else if (checkPass(pass) == false) {
            String ms = "Mật khẩu cần có ít nhất 8 ký tự và nhiều nhất là 20 ký tự, bao gồm ít nhất 1 ký tự thường, 1 ký tự viết hoa, 1 chữ số và 1 trong các ký tự đặc biệt sau: ! # $ @ _ + , ? . -";
            //request.getRequestDispatcher("")
            request.setAttribute("ms", ms);
            processRequest(request, response);
        }
        else if(rule==null) {
            String ms = "Bạn cần đồng ý với điều khoản sử dụng ";
            //request.getRequestDispatcher("")
            request.setAttribute("ms", ms);
            processRequest(request, response);
        }
        else {
            if(gender.equals("1"))
                gender = "Nam";
            else if(gender.equals("0")) {
                gender = "Nữ";
            }
            else {
                gender = "Khác";
            }
            acd.insert(user, gender, Date.valueOf(dob), phone, email, area, 2, pass);
            response.sendRedirect("login");
        }
    }
    
    public boolean checkPass(String pass) {
        int check = 0;
        String a = "abcdefghijklmnopqrstuvwxyz";
        String A = a.toUpperCase();
        String num = "0123456789";
        String spe = "/@#$%^&*!";
        for (int i = 0; i < a.length(); i++) {
            if(pass.contains(a.substring(i, i + 1))) {
                check = 1;
                break;
            }
        }
        for (int i = 0; i < A.length(); i++) {
            if(pass.contains(A.substring(i, i + 1))) {
                check++;
                break;
            }
        }
        for (int i = 0; i < num.length(); i++) {
            if(pass.contains(num.substring(i, i + 1))) {
                check++;
                break;
            }
        }
        for (int i = 0; i < spe.length(); i++) {
            if(pass.contains(spe.substring(i, i + 1))) {
                check++;
                break;
            }
        }
        if(check == 4 && pass.length() >= 8) {
            return true;
        }
        
        return false;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
