/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverpelotas;

import Statistic.StatisticBean;
import Statistic.StatisticBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author davidsantiagobarrera
 */
@EJB(name = "StatisitcBean", beanInterface = StatisticBeanLocal.class)
public class StatisticServlet extends HttpServlet {

    @EJB
    private StatisticBeanLocal statisticBean;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servidor de Estadisticas:</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><br>El Servidor de Estadisticas esta en el habito de: " + request.getContextPath() + "</h1>");
            out.println("<h2><br>ESTADISTICAS:</h2>");
            out.println("<br>----------------------");
            List<HttpSession> sessiones = statisticBean.getSession();
            for (HttpSession session : sessiones) {
                out.println("<br>ID de la Session: " + session.getId() + "");
                out.println("<br>Session Creada: " + new Date(session.getCreationTime()) + "");
                out.println("<br>Ultimo acceso: " + new Date(session.getLastAccessedTime()) + "");
                out.println("<br>----------------------");
            }
            out.println("<br>Numero de Veces Vista la Pagina: " + statisticBean.incrementAndGetHitCount() + "");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
