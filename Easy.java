/**
 * Easy difficulty: mostly plains terrain, nice traders,
 * and a cautious brain to help the player survive.
 */
public class Easy extends Difficulty {

    @Override
    public void setTrader() {
        // Easy mode uses NiceTraders (set during map generation)
    }

    @Override
    public void setBrain() {
        brain = new CautiousBrain();
    }

    @Override
    public void setMap(int width, int height) {
        map = new Map(width, height);
        map.generate("easy");
    }
}