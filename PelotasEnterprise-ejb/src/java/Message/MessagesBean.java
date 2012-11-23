/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author davidsantiagobarrera
 */
@MessageDriven(mappedName = "jms/Queue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessagesBean implements MessageListener {
    MessagesSaver messagesSaver = new DBMessages();

    public MessagesBean() {
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Entramos en el MDB...");
        try {
            MapMessage msg = (MapMessage) message;
            messagesSaver.saveMessage(msg);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
