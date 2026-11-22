/**
 * A goal-oriented brain that pushes east as aggressively
 * as possible. Only detours for resources when critical.
 */
public class GoalOrientedBrain extends Brain {

    private static final int CRITICAL = 1;

    /**
     * Makes a move based on a goal-oriented strategy.
     *
     * @param player the player being controlled
     */
    @Override
    public void makeMove(Player player) {
        Vision vision = player.getVision();
        vision.updatePosition(player.getRow(), player.getCol());

        boolean waterCritical =
            player.getCurrentWater() <= CRITICAL;
        boolean foodCritical  =
            player.getCurrentFood()  <= CRITICAL;

        // Only detour if critically low
        if (waterCritical || foodCritical) {
            if (waterCritical) {
                Path p = vision.closestWater();
                if (tryMove(player, p)) return;
            }
            if (foodCritical) {
                Path p = vision.closestFood();
                if (tryMove(player, p)) return;
            }
            // Try a trader as last resort
            Path p = vision.closestTrader();
            if (tryMove(player, p)) return;
        }

        // Push east, northeast, or southeast
        if (MapUtils.isSafeMove(player, Direction.EAST)) {
            player.move(Direction.EAST);
            return;
        }
        if (MapUtils.isSafeMove(player, Direction.NORTHEAST)) {
            player.move(Direction.NORTHEAST);
            return;
        }
        if (MapUtils.isSafeMove(player, Direction.SOUTHEAST)) {
            player.move(Direction.SOUTHEAST);
            return;
        }

        // Fall back to easiest available move
        Path easiest = vision.easiestPath();
        if (tryMove(player, easiest)) return;

        player.rest();
    }

    /**
     * Attempts to move the player along the first step of a path.
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
        if (MapUtils.isSafeMove(player, next)) {
            player.move(next);
            return true;
        }
        return false;
    }
}