/**
 * Medium difficulty: mixed terrain, mixed traders,
 * and a goal-oriented brain.
 */
public class Medium extends Difficulty {

    @Override
    public void setTrader() {
        // Medium mode uses a mix of trader types (set during map generation)
    }

    @Override
    public void setBrain() {
        brain = new GoalOrientedBrain();
    }

    @Override
    public void setMap(int width, int height) {
        map = new Map(width, height);
        map.generate("medium");
    }
}