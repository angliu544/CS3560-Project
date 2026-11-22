import java.util.ArrayList;
import java.util.List;

/**
 * A wide vision that can see all 8 squares surrounding
 * the player's current position (including diagonals).
 */
public class WideVision extends Vision {

    /**
     * Constructs a WideVision tied to the given map.
     *
     * @param map the game map
     */
    public WideVision(Map map) {
        super(map);
    }

    /**
     * Returns all 8 surrounding squares that are within map bounds.
     *
     * @return list of visible square coordinates [row, col]
     */
    @Override
    protected List<int[]> getVisibleSquares() {
        List<int[]> visible = new ArrayList<>();
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int r = playerRow + dr[i];
            int c = playerCol + dc[i];
            if (r >= 0 && r < map.getHeight() && c >= 0 && c < map.getWidth()) {
                visible.add(new int[]{r, c});
            }
        }
        return visible;
    }
}