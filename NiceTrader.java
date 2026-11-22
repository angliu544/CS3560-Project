/**
 * A nice trader that is easy to deal with.
 * Accepts fair offers and makes reasonable counter-offers.
 */
public class NiceTrader extends Trader {

    private int counterOfferCount;
    private static final int MAX_COUNTERS = 4;

    /**
     * Constructs a NiceTrader at the given square.
     *
     * @param position the square this trader is in
     */
    public NiceTrader(Square position) {
        super(position);
        this.counterOfferCount = 0;
    }

    /**
     * Accepts if the trader nets at least 0 (break-even or better).
     *
     * @param offer the offer proposed by the player
     * @return true if the trader accepts
     */
    @Override
    public boolean acceptOffer(Offer offer) {
        return traderNetValue(offer) >= 0;
    }

    /**
     * Makes a reasonable counter-offer, slightly in trader's favor.
     * Returns null after MAX_COUNTERS rounds.
     *
     * @param offer the offer proposed by the player
     * @return a fair counter-offer, or null to give up
     */
    @Override
    public Offer counterOffer(Offer offer) {
        counterOfferCount++;
        if (counterOfferCount > MAX_COUNTERS) {
            Display.printEvent(
                "NiceTrader: I've tried my best. No deal."
            );
            counterOfferCount = 0;
            return null;
        }
        Display.printEvent(
            "NiceTrader: How about we meet in the middle?"
        );
        // Ask for one more food than the player offered
        return new Offer(
            offer.getOfferedGold(),
            offer.getOfferedWater(),
            offer.getOfferedFood() + 1,
            offer.getRequestedGold(),
            offer.getRequestedWater(),
            offer.getRequestedFood()
        );
    }

    /**
     * Returns the nice trader's opening offer.
     * Wants 2 food, gives 2 water in return.
     *
     * @return opening offer
     */
    @Override
    public Offer getOpeningOffer() {
        // Player gives 2 food, receives 2 water
        return new Offer(0, 0, 2, 0, 2, 0);
    }
}
