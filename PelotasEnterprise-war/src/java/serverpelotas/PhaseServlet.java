/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverpelotas;

import Phase.PhaseBeanLocal;
import Statistic.StatisticBeanLocal;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author davidsantiagobarrera
 */
@EJB(name = "PhaseBean", beanInterface = PhaseBeanLocal.class)
public class PhaseServlet extends HttpServlet {
    
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
        try {

            // ------------- Session -------------
            HttpSession session = request.getSession(true);
            PhaseBeanLocal bean = (PhaseBeanLocal) session.getAttribute("Bean");
            if (bean == null) {
                InitialContext context = new InitialContext();
                bean = (PhaseBeanLocal) context.lookup("java:comp/env/PhaseBean");
                statisticBean.incrementAndGetHitCount();
                statisticBean.addSession(session);
                session.setAttribute("Bean", bean);
            }
            // ------------- Entrada -------------
            // recuperamos el stream de entrada POST
            InputStream instream = request.getInputStream();
            ObjectInputStream bufferentrada = new ObjectInputStream(instream);

            // (R1) Phase
            Integer p = (Integer) bufferentrada.readObject();
            // Cargar BD
            Object resultado = null;
            if (p == 0) {
                bean.finish();
                statisticBean.removeSession(session);
            } else {
                resultado = bean.nextPhase();
            }
            System.out.println("Phase Cargando de BD");

            // ------------- Salida -------------
            // establecemos el formato de la respuesta
            String contentType = "application/x-java-serialized-object";
            response.setContentType(contentType);

            // Configurarmos un Stream de Salida GET
            OutputStream outputStream = response.getOutputStream();
            ObjectOutputStream buffersalida = new ObjectOutputStream(outputStream);

            // (W1) Escribimos los Datos:
            buffersalida.writeObject(resultado);

            // (W2) Enviamos la id de la sesion:
            buffersalida.writeUTF(session.getId());
            System.out.println(session.getId());

            // Enviamos
            buffersalida.flush();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhaseServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            ex.getCause();
            ex.getMessage();
        } catch (Exception ex) {
            ex.getMessage();
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
