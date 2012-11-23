/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverpelotas;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author davidsantiagobarrera
 */
public class MessagesServlet extends HttpServlet {

    @Resource(mappedName = "jms/QueueFactory")
    javax.jms.QueueConnectionFactory queueConnection;
    @Resource(mappedName = "jms/Queue")
    javax.jms.Queue queue;

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
            Connection connection = queueConnection.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            MapMessage message = session.createMapMessage();
            // ------------- Entrada -------------
            // recuperamos el stream de entrada POST
            InputStream instream = request.getInputStream();
            ObjectInputStream bufferentrada = new ObjectInputStream(instream);

            // (R1) Score
            String sms = bufferentrada.readUTF();
            message.setString("sms", sms);
            producer.send(message);

            System.out.println("SMS Guardado");

            // ------------- Salida -------------
            // establecemos el formato de la respuesta
            String contentType = "application/x-java-serialized-object";
            response.setContentType(contentType);

            // Configurarmos un Stream de Salida GET
            OutputStream outputStream = response.getOutputStream();
            ObjectOutputStream buffersalida = new ObjectOutputStream(outputStream);

            // (W1) Escribimos los Datos:
            buffersalida.writeUTF("Mensaje Enviado");
            System.out.println("Mensaje Enviado");

            // Enviamos
            buffersalida.flush();
            //---------------------------------------
            producer.close();
            session.close();
            connection.close();
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
