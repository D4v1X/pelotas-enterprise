/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Score;

import javax.ejb.Local;
import pelotas.ranking.Ranking;
import pelotas.score.Score;

/**
 *
 * @author davidsantiagobarrera
 */
@Local
public interface ScoreBeanLocal {

    Ranking saveScore(Score scoresave);
}
