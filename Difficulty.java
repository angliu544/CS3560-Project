/**
 * Abstract base class representing a game difficulty setting.
 * Subclasses (Easy, Medium, Hard) define how the map,
 * trader types, and brain are configured.
 */
public abstract class Difficulty {

    protected Trader trader;
    protected Brain brain;
    protected Map map;

    /**
     * Sets the trader type for this difficulty.
     */
    public abstract void setTrader();

    /**
     * Sets the brain type for this difficulty.
     */
    public abstract void setBrain();

    /**
     * Generates the map for this difficulty.
     *
     * @param width  the width of the map
     * @param height the height of the map
     */
    public abstract void setMap(int width, int height);

    public Trader getTrader() { return trader; }
    public Brain getBrain()   { return brain; }
    public Map getMap()       { return map; }
}