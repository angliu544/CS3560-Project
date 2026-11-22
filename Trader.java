/**
 * Represents a trader that lives in a square on the map.
 * The player can propose trades (gold, water, food exchanges).
 * The trader can accept, counter-offer, or reject.
 * Traders are always repeating — available every turn.
 */
public abstract class Trader {

    protected Square position;
    protected boolean repeating;

    /**
     * Constructs a Trader at the given square.
     *
     * @param position the square this trader is in
     */
    public Trader(Square position) {
        this.position  = position;
        this.repeating = true;
    }

    /**
     * Evaluates the player's offer and decides to accept it.
     * The offer represents what the PLAYER gives and receives.
     * The trader accepts if the deal favors the trader.
     *
     * @param offer the offer proposed by the player
     * @return true if the trader accepts
     */
    public abstract boolean acceptOffer(Offer offer);

    /**
     * Generates a counter-offer in response to the player's offer.
     * Returns null if the trader refuses to negotiate further.
     *
     * @param offer the offer proposed by the player
     * @return a counter-offer, or null to reject entirely
     */
    public abstract Offer counterOffer(Offer offer);

    /**
     * Generates the trader's opening offer to the player.
     * This is what the trader wants in a standard trade.
     *
     * @return the trader's initial offer
     */
    public abstract Offer getOpeningOffer();

    /**
     * Returns the square this trader is in.
     *
     * @return position square
     */
    public Square getPosition() {
        return position;
    }

    /**
     * Returns whether this trader is repeating.
     *
     * @return true (traders are always repeating)
     */
    public boolean isRepeating() {
        return repeating;
    }

    /**
     * Calculates how favorable an offer is for the trader.
     * Positive means the trader gains more than it gives.
     *
     * @param offer the offer to evaluate
     * @return net value for the trader
     */
    protected int traderNetValue(Offer offer) {
        int traderReceives =
            offer.getOfferedGold()  +
            offer.getOfferedWater() +
            offer.getOfferedFood();
        int traderGives =
            offer.getRequestedGold()  +
            offer.getRequestedWater() +
            offer.getRequestedFood();
        return traderReceives - traderGives;
    }
}
