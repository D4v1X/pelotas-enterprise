/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Score;

import javax.ejb.Stateless;
import pelotas.ranking.Ranking;
import pelotas.score.Score;

/**
 *
 * @author davidsantiagobarrera
 */
@Stateless
public class ScoreBean implements ScoreBeanLocal {

    ScoreManager scoreManager = new DBScore();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Ranking saveScore(Score scoresave) {
        scoreManager.saveScore(scoresave);
        return scoreManager.loadRanking();
    }
}
