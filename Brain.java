/**
 * The Brain decides what the player does each turn.
 * It evaluates the player's current resource levels,
 * calls Vision to find nearby options, and tells the
 * player to move, rest, or trade.
 */
public abstract class Brain {

    protected String[] playerAttributes;
    protected String bonuses;

    /**
     * Decides the player's next move and executes it.
     * Subclasses implement their own strategy.
     *
     * @param player the player being controlled
     */
    public abstract void makeMove(Player player);
}