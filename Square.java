import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single cell on the map.
 * Each square has a terrain type and can contain
 * zero or more items (including traders).
 */
public class Square {

    private Terrain terrain;
    private List<Item> items;
    private Trader trader;

    /**
     * Constructs a Square with the given terrain and no items.
     *
     * @param terrain the terrain type of this square
     */
    public Square(Terrain terrain) {
        this.terrain = terrain;
        this.items = new ArrayList<>();
        this.trader = null;
    }

    /**
     * Returns the terrain of this square.
     *
     * @return terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * Returns all items in this square.
     *
     * @return list of items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Adds an item to this square.
     *
     * @param item the item to add
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Returns the trader in this square, or null if there is none.
     *
     * @return trader or null
     */
    public Trader getTrader() {
        return trader;
    }

    /**
     * Sets the trader for this square.
     *
     * @param trader the trader to place here
     */
    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    /**
     * Returns whether this square has a trader.
     *
     * @return true if a trader is present
     */
    public boolean hasTrader() {
        return trader != null;
    }

    /**
     * Resets all items in this square (for a new player run).
     */
    public void reset() {
        for (Item item : items) {
            item.reset();
        }
    }

    @Override
    public String toString() {
        return "Square [terrain=" + terrain.getTerrainName() +
               ", items=" + items.size() +
               ", trader=" + (trader != null ? "yes" : "no") + "]";
    }
}