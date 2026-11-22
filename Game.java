/**
 * Controls the overall flow of the WSS game.
 * Sets up the difficulty, map, and player, then
 * runs the player through the map turn by turn.
 */
public class Game {

    private Difficulty difficulty;
    private Map map;
    private Player player;
    private int turn;

    /**
     * Sets the difficulty for this game.
     *
     * @param difficulty the chosen difficulty
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Creates the map using the selected difficulty.
     *
     * @param width  the width of the map
     * @param height the height of the map
     */
    public void setSize(int width, int height) {
        difficulty.setMap(width, height);
        this.map = difficulty.getMap();
    }

    /**
     * Creates the player with the brain from the difficulty.
     */
    public void createPlayer() {
        Brain brain  = difficulty.getBrain();
        Vision vision = new WideVision(map);
        player = new Player(20, 15, 15, vision, brain, map);
    }

    /**
     * Runs the game loop until the player escapes or dies.
     * Clears the screen once per turn, then prints the map,
     * stats, and any events that happened that turn.
     */
    public void run() {
        turn = 1;

        while (!player.hasEscaped() && !player.isDead()) {
            Display.clearScreen();
            Display.drawMap(map, player.getRow(), player.getCol());
            Display.printLegend();
            Display.drawStats(player, turn);
        Display.flushEvents();
            printWarnings();

            // Collect events for this turn, print after stats
            player.takeTurn();
            Display.flushEvents();
            turn++;

            Display.pause(600);

            if (turn > 2000) {
                Display.printEvent("Turn limit reached.");
                break;
            }
        }

        // Final screen
        Display.clearScreen();
        Display.drawMap(map, player.getRow(), player.getCol());
        Display.drawStats(player, turn);
        Display.flushEvents();

        if (player.hasEscaped()) {
            Display.printWin(turn);
        } else {
            Display.printGameOver(turn);
        }
    }

    /**
     * Prints resource warnings if any stat is critically low.
     */
    private void printWarnings() {
        double strRatio =
            (double) player.getCurrentStrength()
            / player.getMaxStrength();
        double waterRatio =
            (double) player.getCurrentWater()
            / player.getMaxWater();
        double foodRatio =
            (double) player.getCurrentFood()
            / player.getMaxFood();

        if (strRatio < 0.3) {
            Display.printWarning("Strength is critically low!");
        }
        if (waterRatio < 0.3) {
            Display.printWarning("Water is critically low!");
        }
        if (foodRatio < 0.3) {
            Display.printWarning("Food is critically low!");
        }
    }
}