/**
 * A cautious brain that prioritizes survival.
 * Seeks water first if low, then food, then a trader
 * if resources are needed, otherwise takes the
 * easiest path eastward.
 */
public class CautiousBrain extends Brain {

    private static final int LOW_THRESHOLD = 3;

    /**
     * Makes a move based on a cautious survival strategy.
     *
     * @param player the player being controlled
     */
    @Override
    public void makeMove(Player player) {
        Vision vision = player.getVision();
        vision.updatePosition(player.getRow(), player.getCol());

        // Seek water if low
        if (player.getCurrentWater() <= LOW_THRESHOLD) {
            Path waterPath = vision.closestWater();
            if (tryMove(player, waterPath)) return;
        }

        // Seek food if low
        if (player.getCurrentFood() <= LOW_THRESHOLD) {
            Path foodPath = vision.closestFood();
            if (tryMove(player, foodPath)) return;
        }

        // Seek trader if both water and food are moderate
        if (player.getCurrentWater() <= LOW_THRESHOLD + 1
                || player.getCurrentFood() <= LOW_THRESHOLD + 1) {
            Path traderPath = vision.closestTrader();
            if (tryMove(player, traderPath)) return;
        }

        // Otherwise take the easiest path
        Path easiest = vision.easiestPath();
        if (tryMove(player, easiest)) return;

        // Last resort: rest
        player.rest();
    }

    /**
     * Attempts to move the player along the first step of a path.
     * Returns true if the move was made successfully.
     *
     * @param player the player to move
     * @param path   the path to follow (may be null)
     * @return true if move succeeded
     */
    private boolean tryMove(Player player, Path path) {
        if (path == null || path.getPath().isEmpty()) {
            return false;
        }
        Direction next = path.getPath().get(0);
        Square nextSq  = MapUtils.getNextSquare(player, next);
        if (nextSq != null && player.canEnter(nextSq)) {
            player.move(next);
            return true;
        }
        return false;
    }
}
