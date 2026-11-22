import java.util.ArrayList;
import java.util.List;

/**
 * A cautious vision that can only see the squares directly
 * north, south, and east of the player's current position.
 */
public class CautiousVision extends Vision {

    /**
     * Constructs a CautiousVision tied to the given map.
     *
     * @param map the game map
     */
    public CautiousVision(Map map) {
        super(map);
    }

    /**
     * Returns only the north, south, and east squares.
     *
     * @return list of visible square coordinates [row, col]
     */
    @Override
    protected List<int[]> getVisibleSquares() {
        List<int[]> visible = new ArrayList<>();

        // North
        if (playerRow - 1 >= 0)
            visible.add(new int[]{playerRow - 1, playerCol});
        // South
        if (playerRow + 1 < map.getHeight())
            visible.add(new int[]{playerRow + 1, playerCol});
        // East
        if (playerCol + 1 < map.getWidth())
            visible.add(new int[]{playerRow, playerCol + 1});

        return visible;
    }
}