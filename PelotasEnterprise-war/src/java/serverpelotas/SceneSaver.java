/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverpelotas;

/**
 *
 * @author davidsantiagobarrera
 */
public interface SceneSaver {

    Object load(String name);

    Boolean write(String name, Object obj);
}
