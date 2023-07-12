/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import dal.MovieDAO;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import model.Movies;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author acer
 */
public class Test extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Test</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Test at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
//        PrintWriter out = response.getWriter();
//        String fileName = "event1.jpg";
//        String filePath = "C:/PRJ301-BackendWeb/Assignment/images/";
//        response.setContentType("APPLICATION/OCTET-STREAM");
//        response.setHeader("Content-Disposition", "attachment;fileName=\"" + fileName + "\"");
//        int i;
//        FileInputStream file = new FileInputStream(filePath + fileName);
//        while ((i = file.read()) != -1) {
//            out.write(i);
//        }
//        file.close();
//        out.close();

//        String filePath = "C:/PRJ301-BackendWeb/Assignment/images/event3.jpg";
//        File downloadFile = new File(filePath);
//        FileInputStream inStream = new FileInputStream(downloadFile);
//         
//        // if you want to use a relative path to context root:
//        String relativePath = "C:/PRJ301-BackendWeb/Assignment/images";
//        System.out.println("relativePath = " + relativePath);
//         
//        // obtains ServletContext
//        ServletContext context = getServletContext();
//         
//        // gets MIME type of the file
//        String mimeType = context.getMimeType(filePath);
//        if (mimeType == null) {        
//            // set to binary type if MIME mapping not found
//            mimeType = "application/octet-stream";
//        }
//        System.out.println("MIME type: " + mimeType);
//         
//        // modifies response
//        response.setContentType(mimeType);
//        response.setContentLength((int) downloadFile.length());
//         
//        // forces download
//        String headerKey = "Content-Disposition";
//        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
//        response.setHeader(headerKey, headerValue);
//         
//        // obtains response's output stream
//        OutputStream outStream = response.getOutputStream();
//         
//        byte[] buffer = new byte[4096];
//        int bytesRead = -1;
//         
//        while ((bytesRead = inStream.read(buffer)) != -1) {
//            outStream.write(buffer, 0, bytesRead);
//        }
//         
//        inStream.close();
//        outStream.close();
        request.getRequestDispatcher("test.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final ServletFileUpload uploader = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        // Get PrintWriter object
        PrintWriter out = response.getWriter();
        // File name
        String file = request.getParameter("file");
        String filename = file;
        System.out.println(file);
        // File path
        String filepath = "C:/Users/acer/Pictures/Screenshots";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

       
    }

// getFileName() method to get the file name from the part  
    private String getFileName(final Part part) {
        // get header(content-disposition) from the part  
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);

        // code to get file name from the header  
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        // it will return null when it doesn't get file name in the header   
        return null;
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
