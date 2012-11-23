/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Phase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pelotas.drawable.Drawable;

/**
 *
 * @author davidsantiagobarrera
 */
public class DBPhase implements PhaseLoader {
    // Database

    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost/pelotas";
    // Database credentials
    private String USER = "root";
    private String PASS = "";

    @Override
    public Drawable loadPhase(Integer P) {
        Connection connection;
        Statement statement;
        ResultSet resultset;
        String sql;
        ObjectInputStream objectIn = null;
        Object obj = null;
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            statement = connection.createStatement();

            sql = "Select * from phase where phase = \"" + P.toString() + "\"";
            resultset = statement.executeQuery(sql);

            // Extract data from result set

            while (resultset.next()) {
                byte[] buf = resultset.getBytes("objeto");
                if (buf != null) {
                    objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
                }
                obj = objectIn.readObject();
                System.out.println(obj);
            }

            // Clean-up environment
            resultset.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error Exception");
            System.out.println(e.getMessage());
        }
        return (Drawable) obj;
    }
}
