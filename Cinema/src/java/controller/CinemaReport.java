/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.DeviceDAO;
import dal.EmployeeDAO;
import dal.FoodDAO;
import dal.ScheDAO;
import dal.TicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Cinema;
import model.TIcketDate;

/**
 *
 * @author acer
 */
public class CinemaReport extends HttpServlet {

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
            out.println("<title>Servlet CinemaReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CinemaReport at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("cinRp.jsp").forward(request, response);
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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        CinemaDAO cnd = new CinemaDAO();
        DeviceDAO dvd = new DeviceDAO();
        TicketDAO tkd = new TicketDAO();
        FoodDAO fd = new FoodDAO();

        String start_raw = request.getParameter("start");
        String end_raw = request.getParameter("end");

        String dateS = "", monthS = "", yearS = "";
        String dateE = "", monthE = "", yearE = "";
        String t = start_raw;
        int cnt = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                yearS = t.substring(cnt, i);
                cnt = i;
            } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                monthS = t.substring(cnt + 1, i);
                cnt = i;
            }
        }
        dateS = t.substring(cnt + 1);
        cnt = 0;
        t = end_raw;
        for (int i = 0; i < t.length(); i++) {
            if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                yearE = t.substring(cnt, i);
                cnt = i;
            } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                monthE = t.substring(cnt + 1, i);
                cnt = i;
            }
        }
        dateE = t.substring(cnt + 1);

        List<Cinema> list = cnd.getAllCinema();
        List<TIcketDate> listTID1 = new ArrayList<>();

        //doanh thu tat ca rap
        int a[] = new int[list.size()];
        int numOnlAllT = 0;
        int numOffAllT = 0;
        int numOnlAllF = 0;
        int numOffAllF = 0;
        for (int i = 0; i < list.size(); i++) {
            numOnlAllT += tkd.getOnlineIncomeByCinAD(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
            numOffAllT += tkd.getOfflineIncomeByCinAD(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
            numOnlAllF += fd.getIncomeOnlByDAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
            numOffAllF += fd.getIncomeOffByDAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
        }

        for (int i = 0; i < list.size(); i++) {
            String PCOnlT = "0";
            String PCOffT = "0";
            String PCOnlF = "0";
            String PCOffF = "0";
        }

        //onl va off
        int numOnlA = numOnlAllT + numOnlAllF;
        int numOffA = numOffAllT + numOffAllF;

        String PcOnlA = "0";
        String PcOffA = "0";
        if (numOnlA + numOffA != 0) {
            PcOnlA = decimalFormat.format((double) numOnlA / (double) (numOnlA + numOffA) * 100);
            PcOffA = decimalFormat.format((double) numOffA / (double) (numOnlA + numOffA) * 100);
        }

        TIcketDate TIDonl = new TIcketDate("Website", numOnlA, PcOnlA);
        TIcketDate TIDoff = new TIcketDate("Trực tiếp", numOffA, PcOffA);

        String PcOnlT = "0";
        String PcOffT = "0";
        String PcOnlF = "0";
        String PcOffF = "0";
        if (numOnlA != 0) {
            PcOnlT = decimalFormat.format((double) numOnlAllT / (double) (numOnlA) * 100);
            PcOnlF = decimalFormat.format((double) numOnlAllF / (double) (numOnlA) * 100);
        }

        listTID1.add(new TIcketDate("Vé", numOnlAllT, PcOnlT));
        listTID1.add(new TIcketDate( "Đồ Ăn", numOnlAllF, PcOnlF));

        List<TIcketDate> listTID2 = new ArrayList<>();

        if (numOffA != 0) {
            PcOffT = decimalFormat.format((double) numOffAllT / (double) (numOffA) * 100);
            PcOffF = decimalFormat.format((double) numOffAllF / (double) (numOffA) * 100);
        }

        listTID2.add(new TIcketDate("Vé", numOffAllT, PcOffT));
        listTID2.add(new TIcketDate("Đồ Ăn", numOffAllF, PcOffF));

        //doanh thu ve online tung rap
        cnt = 0;
        List<TIcketDate> listTID3 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String PC = "0";

            if (numOnlAllT != 0) {
                PC = decimalFormat.format((double) tkd.getOnlineIncomeByCinAD(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) / (double) numOnlAllT * 100);
            }
            listTID3.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName(), tkd.getOnlineIncomeByCinAD(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()), PC, "TK"));
            listTID1.get(0).setTkd(listTID3);
        }

        //doanh thu do an online tung rap
        listTID3 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String PC = "0";
            if (numOnlAllF != 0) {
                PC = decimalFormat.format((double) fd.getIncomeOnlByDAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) / (double) numOnlAllF * 100);
            }
            listTID3.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName(), fd.getIncomeOnlByDAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()), PC, "FD"));
            listTID1.get(1).setTkd(listTID3);
        }

        listTID3 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String PC = "0";

            if (numOffAllT != 0) {
                PC = decimalFormat.format((double) tkd.getOfflineIncomeByCinAD(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) / (double) numOffAllT * 100);
            }
            listTID3.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName(), tkd.getOfflineIncomeByCinAD(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()), PC, "TK"));
            listTID2.get(0).setTkd(listTID3);
        }

        //doanh thu do an online tung rap
        listTID3 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String PC = "0";
            if (numOffAllF != 0) {
                PC = decimalFormat.format((double) fd.getIncomeOffByDAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) / (double) numOffAllF * 100);
            }
            listTID3.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName(), fd.getIncomeOffByDAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()), PC, "FD"));
            listTID2.get(1).setTkd(listTID3);
        }

        int costImpD = dvd.getSumPriceDeviceDistByDate(Date.valueOf(start_raw), Date.valueOf(end_raw));
        int costImpE = dvd.getSumPriceDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw));

        List<TIcketDate> listTID4 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String PC = "0";
            if (costImpD != 0) {
                PC = decimalFormat.format((double) dvd.getSumPriceDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) / (double) costImpD * 100);
            }
            listTID4.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName(), dvd.getSumPriceDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()), PC));
        }

        List<TIcketDate> listTID5 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String PC = "0";
            if (costImpE != 0) {
                PC = decimalFormat.format((double) dvd.getSumPriceDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) / (double) costImpE * 100);
            }
            listTID5.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName(), dvd.getSumPriceDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()), PC));
        }

        int numD = dvd.getNumDeviceDistByDate(Date.valueOf(start_raw), Date.valueOf(end_raw));
        int numE = dvd.getNumDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw));

        List<TIcketDate> listTID6 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String PC = "0";
            if (numD != 0) {
                PC = decimalFormat.format((double) dvd.getNumDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) / (double) numD * 100);
            }
            listTID6.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName(), dvd.getNumDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()), PC));
        }

        List<TIcketDate> listTID7 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String PC = "0";
            if (numE != 0) {
                PC = decimalFormat.format((double) dvd.getNumDeviceErrorByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) / (double) numE * 100);
            }
            listTID7.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName(), dvd.getNumDeviceErrorByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()), PC));
        }

        List<TIcketDate> listTID8 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            int onlT = tkd.getOnlineIncomeByCinAD(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
            int offT = tkd.getOfflineIncomeByCinAD(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
            int onlF = fd.getIncomeOnlByDAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
            int offF = fd.getIncomeOffByDAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
            String PC = "0";
            if (TIDonl.getNo() + TIDoff.getNo() - costImpD - costImpE != 0) {
                if (onlT + offT + onlF + offF - dvd.getNumDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) - dvd.getNumDeviceErrorByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) > 0) {
                    if (TIDonl.getNo() + TIDoff.getNo() - costImpD - costImpE > 0) {
                        PC = decimalFormat.format((double) (onlT + offT + onlF + offF - dvd.getSumPriceDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) - dvd.getSumPriceDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID())) / (double) (TIDonl.getNo() + TIDoff.getNo() - costImpD - costImpE) * 100);
                    } else if (TIDonl.getNo() + TIDoff.getNo() - costImpD - costImpE < 0) {

                        PC = decimalFormat.format((double) (onlT + offT + onlF + offF - dvd.getSumPriceDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) - dvd.getSumPriceDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID())) / (double) (costImpD + costImpE - TIDonl.getNo() - TIDoff.getNo()) * 100 * -1);
                    }
                } else if (onlT + offT + onlF + offF - dvd.getNumDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) - dvd.getNumDeviceErrorByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) < 0) {
                    if (TIDonl.getNo() + TIDoff.getNo() - costImpD - costImpE > 0) {
                        PC = decimalFormat.format((double) (dvd.getSumPriceDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) + dvd.getSumPriceDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) - onlT - offT - onlF - offF) / (double) (TIDonl.getNo() + TIDoff.getNo() - costImpD - costImpE) * 100 * -1);
                    } else if (TIDonl.getNo() + TIDoff.getNo() - costImpD - costImpE < 0) {
                        PC = decimalFormat.format((double) (dvd.getSumPriceDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) + dvd.getSumPriceDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) - onlT - offT - onlF - offF) / (double) (costImpD + costImpE - TIDonl.getNo() - TIDoff.getNo()) * 100);
                    }
                }
            }

            if (onlT + offT + onlF + offF - dvd.getSumPriceDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) - dvd.getSumPriceDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) >= 0) {
                listTID8.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName() + ", Lời", (onlT + offT + onlF + offF - dvd.getSumPriceDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) - dvd.getSumPriceDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID())), PC));
            } else {
                listTID8.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName() + ", Lỗ", -(onlT + offT + onlF + offF - dvd.getSumPriceDeviceDistByDateAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) - dvd.getSumPriceDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID())), PC));
            }
        }
        
        EmployeeDAO ed = new EmployeeDAO();
        
        List<TIcketDate> listTID9 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String PC = "0";
            if(ed.getAllEmployee().size() != 0) {
                PC = decimalFormat.format((double)ed.getNumEmpByCin(list.get(i).getCinID(), Date.valueOf(start_raw), Date.valueOf(end_raw)) / (double)ed.getAllEmployee().size() * 100);
            }
            listTID9.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName(), ed.getNumEmpByCin(list.get(i).getCinID(), Date.valueOf(start_raw), Date.valueOf(end_raw)), PC));
        }
        
        //movie
        List<TIcketDate> listTID10 = new ArrayList<>();
        ScheDAO scd = new ScheDAO();
        for (int i = 0; i < list.size(); i++) {
            String PC = "0";
            if(scd.getScheInTimeByCin(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) != 0) {
                PC = decimalFormat.format((double)scd.getScheInTimeByCin(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()) / (double)scd.getAllScheInTime(Date.valueOf(start_raw), Date.valueOf(end_raw)).length * 100);
            }
            listTID10.add(new TIcketDate(list.get(i).getCinID(), "Rạp " + list.get(i).getCinName(), scd.getScheInTimeByCin(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID()), PC));
        }
        
        request.setAttribute("listTID10", listTID10);
        request.setAttribute("listTID9", listTID9);
        request.setAttribute("listTID8", listTID8);

        request.setAttribute("numD", numD);
        request.setAttribute("numE", numE);

        request.setAttribute("listTID6", listTID6);
        request.setAttribute("listTID7", listTID7);

        request.setAttribute("listTID4", listTID4);
        request.setAttribute("listTID5", listTID5);

        request.setAttribute("costImpD", costImpD);
        request.setAttribute("costImpE", costImpE);

        request.setAttribute("listTID1", listTID1);
        request.setAttribute("listTID2", listTID2);

        request.setAttribute("TIDonl", TIDonl);
        request.setAttribute("TIDoff", TIDoff);
        request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
        request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
        request.setAttribute("startR", start_raw);
        request.setAttribute("endR", end_raw);
        request.setAttribute("check", 1);
        request.getRequestDispatcher("cinRp.jsp").forward(request, response);
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
