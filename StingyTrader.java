/**
 * A stingy trader that drives a hard bargain.
 * Only accepts offers that clearly favor the trader,
 * and walks away after too many counter-offers.
 */
public class StingyTrader extends Trader {

    private int counterOfferCount;
    private static final int MAX_COUNTERS = 2;

    /**
     * Constructs a StingyTrader at the given square.
     *
     * @param position the square this trader is in
     */
    public StingyTrader(Square position) {
        super(position);
        this.counterOfferCount = 0;
    }

    /**
     * Accepts only if the trader nets at least +2 value.
     * Walks away after MAX_COUNTERS counter-offers.
     *
     * @param offer the offer proposed by the player
     * @return true if the trader accepts
     */
    @Override
    public boolean acceptOffer(Offer offer) {
        if (counterOfferCount >= MAX_COUNTERS) {
            Display.printEvent(
                "StingyTrader: I've had enough. No deal!"
            );
            counterOfferCount = 0;
            return false;
        }
        return traderNetValue(offer) >= 2;
    }

    /**
     * Makes a counter-offer demanding more from the player.
     * Returns null after too many rounds.
     *
     * @param offer the offer proposed by the player
     * @return a tougher counter-offer, or null to quit
     */
    @Override
    public Offer counterOffer(Offer offer) {
        counterOfferCount++;
        if (counterOfferCount > MAX_COUNTERS) {
            Display.printEvent(
                "StingyTrader: Done negotiating. Goodbye."
            );
            counterOfferCount = 0;
            return null;
        }
        Display.printEvent(
            "StingyTrader: Not good enough. How about this?"
        );
        // Demand one more water than the player offered
        return new Offer(
            offer.getOfferedGold(),
            offer.getOfferedWater() + 1,
            offer.getOfferedFood(),
            offer.getRequestedGold(),
            offer.getRequestedWater(),
            offer.getRequestedFood()
        );
    }

    /**
     * Returns the stingy trader's opening offer.
     * Wants 3 water, gives 1 food in return.
     *
     * @return opening offer
     */
    @Override
    public Offer getOpeningOffer() {
        // Player gives 3 water, receives 1 food
        return new Offer(0, 3, 0, 0, 0, 1);
    }
}