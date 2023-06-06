/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.PageContext;
import model.Account;

/**
 *
 * @author acer
 */
public class LoginServlet extends HttpServlet {

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
        Cookie[] arr = request.getCookies();
        String user = "";
        String pass = "";
        String checked = "";
        for (Cookie i : arr) {
            if (i.getName().equals("user")) {
                user = i.getValue();
            }
            else if (i.getName().equals("pass")) {
                pass = i.getValue();
            }
            else if (i.getName().equals("rem")) {
                checked = i.getValue();
            }
        }
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ĐĂNG NHẬP</title>\\n\"");
            out.println("<link rel=\"stylesheet\" href=\"style.css\"/>\n"
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
                    + "            #rmbMe{\n"
                    + "                width: 15px;\n"
                    + "                height: 15px;\n"
                    + "                margin-right: 15px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .dn {\n"
                    + "                text-decoration: none;\n"
                    + "                position: relative;\n"
                    + "            }\n"
                    + "\n"
                    + "            .dn:after {\n"
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
                    + "            h2 {\n"
                    + "                margin-left:20px;\n"
                    + "                margin-top:20px;\n"
                    + "                color:red\n"
                    + "            }\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "        </style>)");
            out.println("</head>");
            out.println("<body>");
            out.println("<ul class=\"menu_ttinBenLe\">\n"
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
                    + "                    <a href = \"signup\">ĐĂNG KÝ</a>\n"
                    + "                </div>\n");
            if (request.getAttribute("ms") != null) {
                out.println("<h2>" + request.getAttribute("ms") + "</h2>");
            }
            out.println("                <div class =\"loginForm\">\n"
                    + "                    <form action = \"login\" method = \"post\">\n"
                    + "                            <label for = \"user\">Email hoặc số điện thoại</label>\n"
                    + "                            <br/><!-- comment -->\n"
                    + "                            <input type =\"text\" id =\"user\" name =\"user\" placeholder =\"Email hoặc số điện thoại\"  value = \"" + user + "\"/>\n"
                    + "\n"
                    + "                            <br/><!-- comment -->\n"
                    + "\n"
                    + "                            <label for = \"pass\">Mật khẩu</label>\n"
                    + "                            <br/><!-- comment -->\n"
                    + "                            <input type =\"password\" id =\"pass\" name =\"pass\" placeholder =\"Mật khẩu\" value = \"" + pass + "\"/>\n"
                    + "\n"
                    + "                            <br/>\n");
            out.println("<input type =\"checkbox\" id = \"rmbMe\" name =\"rem\" value =\"ON\"" + ((checked != null && checked.equals("ON"))?"checked":"") + "/>Remember me\n"
                    + "            <!-- khi ng dung click vao checkbox ses co value la ON-->\n"
                    + "            \n"
                    + "            <br/><!-- comment -->"
                    + "\n"
                    + "                            <input id =\"logBtn\" type =\"submit\" value =\"ĐĂNG NHẬP\"/>\n"
                    + "                    </form>\n"
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
                    + "        </div>");
            out.println("</body>");
            out.println("</html>");
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
        //request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String u = request.getParameter("user");
        String p = request.getParameter("pass");
        String r = request.getParameter("rem");

        //tao 3 cookie: user, pass, remember me
        Cookie user = new Cookie("user", u);
        Cookie pass = new Cookie("pass", p);
        Cookie rem = new Cookie("rem", r);

        if (r != null) {
            user.setMaxAge(60 * 60 * 24 * 7); //7 ngay
            pass.setMaxAge(60 * 60 * 24 * 7); //7 ngay
            rem.setMaxAge(60 * 60 * 24 * 7); //7 ngay
        } else {
            user.setMaxAge(0); //xoa
            pass.setMaxAge(0); //xoa
            rem.setMaxAge(0); //xoa
        }
        response.addCookie(user);
        response.addCookie(pass);
        response.addCookie(rem);

        AccountDAO acd = new AccountDAO();
        Account a = acd.check(u, p);
        if (a != null) {
            HttpSession session = request.getSession();
            session.setAttribute("account", a);
            response.sendRedirect("home");
        } else {
            String ms = "Tài khoản hoặc mật khẩu không tồn tại";
            request.setAttribute("ms", ms);
            //request.getRequestDispatcher("login").forward(request, response);
            processRequest(request, response);
        }
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
