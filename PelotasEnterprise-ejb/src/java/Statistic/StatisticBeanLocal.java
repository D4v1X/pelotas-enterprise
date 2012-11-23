/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistic;

import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.HttpSession;

/**
 *
 * @author davidsantiagobarrera
 */
@Local
public interface StatisticBeanLocal {

    boolean addSession(HttpSession e);
    List<HttpSession> getSession();
    String getTest();
    Integer incrementAndGetHitCount();
    void removeSession(HttpSession session);
}
