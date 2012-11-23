/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Score;

import pelotas.ranking.Ranking;
import pelotas.score.Score;

/**
 *
 * @author davidsantiagobarrera
 */
public interface ScoreManager {

    Ranking loadRanking();

    public void saveScore(Score scoresave);
}
