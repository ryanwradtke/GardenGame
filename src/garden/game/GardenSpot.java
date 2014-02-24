package garden.game;

import Plants.Plant;
import java.io.Serializable;

public class GardenSpot implements Serializable {

    private double soilQlty; //Quality of soil 0-10
    private int sunLte;  //Amount of sun 0-5
    private Plant plant;
    private boolean weeds;

    public GardenSpot(double soilQlty, int sunLte, Plant plant, boolean weeds) {
        this.soilQlty = soilQlty; // 0 - 5:
        this.sunLte = sunLte; // 0 - 5:
        this.plant = plant;
        this.weeds = weeds;
    }

    public double getSoilQlty() {
        return soilQlty;
    }

    public int getSunLte() {
        return sunLte;
    }

    public Plant getPlant() {
        return plant;
    }

    public boolean isWeeds() {
        return weeds;
    }

    public void setSoilQlty(int soilQlty) {
        this.soilQlty = soilQlty;
    }

    public void setSunLte(int sunLte) {
        this.sunLte = sunLte;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public void setWeeds(boolean weeds) {
        this.weeds = weeds;
    }

    public void nextTurn() {
        /**
         * Add a check for height of surrounding plants.
         */
        
        this.soilQlty -= .2;
        if (this.soilQlty < 0) this.soilQlty = 0;
    }
    
    @Override
    public String toString() {
        String pString;
        if (plant == null) {
            pString = "No plant in this spot!";
        } else {
            pString = plant.toString();
        }

        String toString = "Soil Quility: " + soilQlty + "/10\n"
                + "Sun Light: " + sunLte + "/5\n"
                + pString + "\n";

        return toString;
    }
}
