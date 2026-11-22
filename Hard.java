/**
 * Hard difficulty: heavy mountains and desert terrain,
 * stingy traders, and a goal-oriented brain.
 */
public class Hard extends Difficulty {

    @Override
    public void setTrader() {
        // Hard mode uses StingyTraders (set during map generation)
    }

    @Override
    public void setBrain() {
        brain = new GoalOrientedBrain();
    }

    @Override
    public void setMap(int width, int height) {
        map = new Map(width, height);
        map.generate("hard");
    }
}