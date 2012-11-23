/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Phase;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import pelotas.drawable.Drawable;

/**
 *
 * @author davidsantiagobarrera
 */
@Stateful
public class PhaseBean implements PhaseBeanLocal {

    private int currentPhase;
    private PhaseLoader phaseLoader = new DBPhase();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Drawable nextPhase() {
        currentPhase++;
        if (currentPhase > 5) {
            currentPhase = 1;
        }
        return phaseLoader.loadPhase(currentPhase);
    }

    @Override
    @Remove
    public void finish() {
        System.out.println("Bean Eliminado");
    }
}
