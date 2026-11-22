/**
 * Utility class with shared helper methods used across
 * Brain subclasses and other game logic.
 */
public class MapUtils {

    /**
     * Returns the square one step in the given direction
     * from the player's current position.
     *
     * @param player    the current player
     * @param direction the direction to look
     * @return the square in that direction, or null if out of bounds
     */
    public static Square getNextSquare(
            Player player, Direction direction) {
        int r   = player.getRow();
        int c   = player.getCol();
        Map map = player.getMap();

        switch (direction) {
            case NORTH:     r--;       break;
            case SOUTH:     r++;       break;
            case EAST:      c++;       break;
            case WEST:      c--;       break;
            case NORTHEAST: r--; c++; break;
            case NORTHWEST: r--; c--; break;
            case SOUTHEAST: r++; c++; break;
            case SOUTHWEST: r++; c--; break;
        }

        if (r >= 0 && r < map.getHeight()
                && c >= 0 && c < map.getWidth()) {
            return map.getSquare(c, r);
        }
        return null;
    }

    /**
     * Returns true if the player can safely afford to move
     * in the given direction without dying from resource loss.
     *
     * @param player    the current player
     * @param direction the direction to check
     * @return true if the move is safe
     */
    public static boolean isSafeMove(
            Player player, Direction direction) {
        Square sq = getNextSquare(player, direction);
        return sq != null && player.canEnter(sq);
    }
}
