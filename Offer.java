/**
 * Represents a trade offer between the player and a trader.
 * An offer specifies how much gold, water, and food is being
 * offered in exchange for how much gold, water, and food.
 */
public class Offer {

    private int offeredGold;
    private int offeredWater;
    private int offeredFood;
    private int requestedGold;
    private int requestedWater;
    private int requestedFood;

    /**
     * Constructs an Offer with the given amounts.
     *
     * @param offeredGold    amount of gold being offered
     * @param offeredWater   amount of water being offered
     * @param offeredFood    amount of food being offered
     * @param requestedGold  amount of gold being requested
     * @param requestedWater amount of water being requested
     * @param requestedFood  amount of food being requested
     */
    public Offer(
            int offeredGold,   int offeredWater,  int offeredFood,
            int requestedGold, int requestedWater, int requestedFood) {
        this.offeredGold    = offeredGold;
        this.offeredWater   = offeredWater;
        this.offeredFood    = offeredFood;
        this.requestedGold  = requestedGold;
        this.requestedWater = requestedWater;
        this.requestedFood  = requestedFood;
    }

    public int getOfferedGold()    { return offeredGold; }
    public int getOfferedWater()   { return offeredWater; }
    public int getOfferedFood()    { return offeredFood; }
    public int getRequestedGold()  { return requestedGold; }
    public int getRequestedWater() { return requestedWater; }
    public int getRequestedFood()  { return requestedFood; }

    @Override
    public String toString() {
        return "Offer [giving: gold="  + offeredGold +
               " water=" + offeredWater +
               " food="  + offeredFood  +
               " | receiving: gold="   + requestedGold +
               " water=" + requestedWater +
               " food="  + requestedFood + "]";
    }
}
