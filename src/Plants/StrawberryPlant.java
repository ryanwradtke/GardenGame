/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plants;

import Produce.ProduceInterface;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan
 */
public class StrawberryPlant extends Plant {

    List<ProduceInterface> produce = new ArrayList();

    private final String type = "Straberry";

    private double size = 0; // 0 - 10: 10 is largest for variety of plant.
    private double health = 2; // 0 - 10, 10 highest

    private final int plantsPerSpot = 1; //Plants per Garden Spot- e.g. onions will have 6.
    private final double produceLimit = .20; //% of size or 0 if only allowed 1 per plant.
    private final double growthSpeed = 3; //growth per turn. (10 turns per season.)

    private final int sunlteReq = 3; // 1- 5: 5 is highest need for sunlight
    private final int waterReq = 2; // 1-5: 5 is highest need for water

    private final double hardiness = .4; // percent resistance to temp.
    private final double resistToPests = .5; // percent resistance to pests
    private final double resistToFungus = .3; // percent resistance to fungus
    private final double resistToRot = .3; // percent resistance to rot

    private boolean readyToPick = false;
    private boolean pests = false;
    private boolean fungus = false;
    private boolean plantRot = false;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public int getPlantsPerSpot() {
        return plantsPerSpot;
    }

    @Override
    public double getProduceLimit() {
        return produceLimit;
    }

    @Override
    public double getGrowthSpeed() {
        return growthSpeed;
    }

    @Override
    public int getSunlteReq() {
        return sunlteReq;
    }

    @Override
    public int getWaterReq() {
        return waterReq;
    }

    @Override
    public double getHardiness() {
        return hardiness;
    }

    @Override
    public double getResistToPests() {
        return resistToPests;
    }

    @Override
    public double getResistToFungus() {
        return resistToFungus;
    }

    @Override
    public double getResistToRot() {
        return resistToRot;
    }

    @Override
    public boolean isReadyToPick() {
        return readyToPick;
    }

    @Override
    public boolean hasPests() {
        return pests;
    }

    @Override
    public boolean hasFungus() {
        return fungus;
    }

    @Override
    public boolean hasPlantRot() {
        return plantRot;
    }

    @Override
    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public void setHasPests(boolean tf) {
        this.pests = tf;
    }

    @Override
    public void setHasFungus(boolean tf) {
        this.fungus = tf;
    }

    @Override
    public void setHasPlantRot(boolean tf) {
        this.plantRot = tf;
    }
}
