/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import javax.jms.MapMessage;

/**
 *
 * @author davidsantiagobarrera
 */
public interface MessagesSaver {

    void saveMessage(MapMessage msg);
}
