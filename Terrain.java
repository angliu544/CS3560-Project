/**
 * Represents the terrain type of a square on the map.
 * Each terrain type has movement, water, and food costs
 * that are deducted from the player when they enter.
 */
public class Terrain {

    private String terrainName;
    private int movementCost;
    private int waterCost;
    private int foodCost;

    /**
     * Constructs a Terrain with the given name and costs.
     *
     * @param terrainName  the name of the terrain
     * @param movementCost the movement cost to enter
     * @param waterCost    the water cost to enter
     * @param foodCost     the food cost to enter
     */
    public Terrain(
            String terrainName,
            int movementCost,
            int waterCost,
            int foodCost) {
        this.terrainName  = terrainName;
        this.movementCost = movementCost;
        this.waterCost    = waterCost;
        this.foodCost     = foodCost;
    }

    /**
     * Returns the name of this terrain type.
     *
     * @return terrain name
     */
    public String getTerrainName() {
        return terrainName;
    }

    /**
     * Returns the movement cost of this terrain.
     *
     * @return movement cost
     */
    public int getMovementCost() {
        return movementCost;
    }

    /**
     * Returns the water cost of this terrain.
     *
     * @return water cost
     */
    public int getWaterCost() {
        return waterCost;
    }

    /**
     * Returns the food cost of this terrain.
     *
     * @return food cost
     */
    public int getFoodCost() {
        return foodCost;
    }

    @Override
    public String toString() {
        return terrainName +
               " (move:" + movementCost +
               " water:" + waterCost    +
               " food:"  + foodCost + ")";
    }
}
