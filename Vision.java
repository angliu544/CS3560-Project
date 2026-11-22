import java.util.ArrayList;
import java.util.List;

/**
 * The Vision class examines neighboring squares on the map
 * and returns Path objects for the player's Brain to use.
 * Subclasses define how many squares can be seen.
 */
public abstract class Vision {

    protected Map map;
    protected int playerRow;
    protected int playerCol;

    /**
     * Constructs a Vision object tied to the given map.
     *
     * @param map the game map
     */
    public Vision(Map map) {
        this.map = map;
    }

    /**
     * Updates the player's current position.
     *
     * @param row the player's current row
     * @param col the player's current column
     */
    public void updatePosition(int row, int col) {
        this.playerRow = row;
        this.playerCol = col;
    }

    /**
     * Returns all squares this vision can currently see.
     * Subclasses define which squares are visible.
     *
     * @return list of visible [row, col] coordinate pairs
     */
    protected abstract List<int[]> getVisibleSquares();

    /**
     * Checks the square to the north of the player.
     *
     * @param playerPosition the player's current square
     * @return square to the north, or null if out of bounds
     */
    public Square checkNorth(Square playerPosition) {
        if (playerRow - 1 >= 0) {
            return map.getSquare(playerCol, playerRow - 1);
        }
        return null;
    }

    /**
     * Checks the square to the south of the player.
     *
     * @param playerPosition the player's current square
     * @return square to the south, or null if out of bounds
     */
    public Square checkSouth(Square playerPosition) {
        if (playerRow + 1 < map.getHeight()) {
            return map.getSquare(playerCol, playerRow + 1);
        }
        return null;
    }

    /**
     * Checks the square to the west of the player.
     *
     * @param playerPosition the player's current square
     * @return square to the west, or null if out of bounds
     */
    public Square checkWest(Square playerPosition) {
        if (playerCol - 1 >= 0) {
            return map.getSquare(playerCol - 1, playerRow);
        }
        return null;
    }

    /**
     * Checks the square to the east of the player.
     *
     * @param playerPosition the player's current square
     * @return square to the east, or null if out of bounds
     */
    public Square checkEast(Square playerPosition) {
        if (playerCol + 1 < map.getWidth()) {
            return map.getSquare(playerCol + 1, playerRow);
        }
        return null;
    }

    /**
     * Finds the path to the closest square with a food bonus.
     * Ties broken by lowest movement cost, then furthest east.
     *
     * @return path to closest food, or null if none found
     */
    public Path closestFood() {
        return findClosest("food", false);
    }

    /**
     * Finds the path to the closest square with a water bonus.
     *
     * @return path to closest water, or null if none found
     */
    public Path closestWater() {
        return findClosest("water", false);
    }

    /**
     * Finds the path to the closest square with a gold bonus.
     *
     * @return path to closest gold, or null if none found
     */
    public Path closestGold() {
        return findClosest("gold", false);
    }

    /**
     * Finds the path to the closest square with a trader.
     *
     * @return path to closest trader, or null if none found
     */
    public Path closestTrader() {
        return findClosestTrader(false);
    }

    /**
     * Finds the path to the square with the lowest movement cost.
     *
     * @return path to easiest square
     */
    public Path easiestPath() {
        List<int[]> visible = getVisibleSquares();
        Path best = null;
        int lowestCost = Integer.MAX_VALUE;

        for (int[] coord : visible) {
            int r = coord[0], c = coord[1];
            Square sq = map.getSquare(c, r);
            int cost = sq.getTerrain().getMovementCost();
            if (cost < lowestCost) {
                lowestCost = cost;
                best = buildPath(r, c);
            }
        }
        return best;
    }

    /**
     * Finds the second closest square with a food bonus.
     *
     * @return path to second closest food, or null
     */
    public Path secondClosestFood() {
        return findClosest("food", true);
    }

    /**
     * Finds the second closest square with a water bonus.
     *
     * @return path to second closest water, or null
     */
    public Path secondClosestWater() {
        return findClosest("water", true);
    }

    /**
     * Finds the second closest square with a gold bonus.
     *
     * @return path to second closest gold, or null
     */
    public Path secondClosestGold() {
        return findClosest("gold", true);
    }

    /**
     * Finds the second closest square with a trader.
     *
     * @return path to second closest trader, or null
     */
    public Path secondClosestTrader() {
        return findClosestTrader(true);
    }

    /**
     * Finds the closest (or second closest) visible square
     * containing an item of the given bonus type.
     *
     * @param bonusType  the type of bonus to search for
     * @param secondBest if true, returns the second best option
     * @return the best or second best path, or null
     */
    private Path findClosest(String bonusType, boolean secondBest) {
        List<int[]> visible = getVisibleSquares();
        Path best   = null;
        Path second = null;

        for (int[] coord : visible) {
            int r = coord[0], c = coord[1];
            Square sq = map.getSquare(c, r);

            boolean hasBonus = sq.getItems().stream().anyMatch(
                item -> item.getBonusType().equals(bonusType)
                     && !item.isTaken()
            );

            if (hasBonus) {
                Path p = buildPath(r, c);
                if (best == null) {
                    best = p;
                } else if (
                    p.getTotalMovementCost()
                        < best.getTotalMovementCost()
                    || (p.getTotalMovementCost()
                        == best.getTotalMovementCost()
                        && c > playerCol)
                ) {
                    second = best;
                    best   = p;
                } else if (second == null) {
                    second = p;
                }
            }
        }
        return secondBest ? second : best;
    }

    /**
     * Finds the closest (or second closest) visible square
     * that contains a trader.
     *
     * @param secondBest if true, returns the second best option
     * @return best or second best path to a trader, or null
     */
    private Path findClosestTrader(boolean secondBest) {
        List<int[]> visible = getVisibleSquares();
        Path best   = null;
        Path second = null;

        for (int[] coord : visible) {
            int r = coord[0], c = coord[1];
            Square sq = map.getSquare(c, r);
            if (sq.hasTrader()) {
                Path p = buildPath(r, c);
                if (best == null) {
                    best = p;
                } else {
                    second = best;
                    best   = p;
                }
            }
        }
        return secondBest ? second : best;
    }

    /**
     * Builds a Path from the player's position to the target.
     *
     * @param targetRow the target row
     * @param targetCol the target column
     * @return a Path representing the route
     */
    protected Path buildPath(int targetRow, int targetCol) {
        Square start = map.getSquare(playerCol, playerRow);
        Path path = new Path(start);

        int r = playerRow;
        int c = playerCol;

        while (r != targetRow || c != targetCol) {
            Direction dir  = null;
            int nextR = r, nextC = c;

            if (r > targetRow && c < targetCol) {
                dir = Direction.NORTHEAST; nextR--; nextC++;
            } else if (r < targetRow && c < targetCol) {
                dir = Direction.SOUTHEAST; nextR++; nextC++;
            } else if (r > targetRow && c > targetCol) {
                dir = Direction.NORTHWEST; nextR--; nextC--;
            } else if (r < targetRow && c > targetCol) {
                dir = Direction.SOUTHWEST; nextR++; nextC--;
            } else if (r > targetRow) {
                dir = Direction.NORTH; nextR--;
            } else if (r < targetRow) {
                dir = Direction.SOUTH; nextR++;
            } else if (c > targetCol) {
                dir = Direction.WEST;  nextC--;
            } else if (c < targetCol) {
                dir = Direction.EAST;  nextC++;
            }

            if (dir == null) break;

            Square next = map.getSquare(nextC, nextR);
            path.addMove(
                dir,
                next.getTerrain().getMovementCost(),
                next.getTerrain().getWaterCost(),
                next.getTerrain().getFoodCost()
            );
            r = nextR;
            c = nextC;
        }
        return path;
    }
}
