/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MapMessage;

/**
 *
 * @author davidsantiagobarrera
 */
public class DBMessages implements MessagesSaver{
        // Database
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost/pelotas";
    // Database credentials
    private String USER = "root";
    private String PASS = "";

    @Override
    public void saveMessage(MapMessage msg) {
        Connection connection;
        PreparedStatement ps;
        String sql;
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            System.out.println(msg);
            sql = "INSERT into messages (message) VALUES (?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, msg.getString("sms"));
            ps.executeUpdate();

            System.out.println(msg);
            // Clean-up environment
            ps.close();
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(DBMessages.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.out.println("Error SQLException");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado");
            System.out.println(e.getMessage());
        }
    }
    
}
