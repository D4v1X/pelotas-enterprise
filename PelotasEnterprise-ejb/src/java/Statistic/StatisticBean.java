/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.http.HttpSession;

/**
 *
 * @author davidsantiagobarrera
 */
@Singleton
@Startup
public class StatisticBean implements StatisticBeanLocal {

    private List<HttpSession> id_users;
    private String TESTVAL = "SI PUEDES VER ESTO @POSTCONSTRUCT NO HA SIDO LLAMADO";
    private Integer hitCount = 0;

    @PostConstruct
    void init() {
        TESTVAL = "@PostConstruct ha sido llamado: " + new Date();
        System.out.printf("\n\nIn init: %s\n", TESTVAL);
        this.id_users = new ArrayList<HttpSession>();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean addSession(HttpSession e) {
        return id_users.add(e);
    }

    @Override
    public List<HttpSession> getSession() {
        return id_users;
    }

    @Override
    public String getTest() {
        return hitCount.toString();
    }

    @Override
    public synchronized Integer incrementAndGetHitCount() {
        return hitCount++;
    }

    @Override
    public void removeSession(HttpSession session) {
        this.id_users.remove(session);
    }
    
}
