/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Phase;

import javax.ejb.Local;
import javax.ejb.Remove;
import pelotas.drawable.Drawable;

/**
 *
 * @author davidsantiagobarrera
 */
@Local
public interface PhaseBeanLocal {

    Drawable nextPhase();


    @Remove
    void finish();

}
