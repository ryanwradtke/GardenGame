package Plants;

import garden.game.Randomizer;
import java.io.Serializable;

/**
 *
 * @author Ryan
 */
public abstract class Plant implements Serializable {

    public abstract String getType();

    public abstract double getSize();

    public abstract double getHealth();

    public abstract int getPlantsPerSpot();

    public abstract double getProduceLimit();

    public abstract double getGrowthSpeed();

    public abstract int getSunlteReq();

    public abstract int getWaterReq();

    public abstract double getHardiness();

    public abstract double getResistToPests();

    public abstract double getResistToFungus();

    public abstract double getResistToRot();

    public abstract boolean isReadyToPick();

    public abstract boolean hasPests();

    public abstract boolean hasFungus();

    public abstract boolean hasPlantRot();

    public abstract void setSize(double size);

    public abstract void setHealth(double health);

    public abstract void setHasPests(boolean tf);

    public abstract void setHasFungus(boolean tf);

    public abstract void setHasPlantRot(boolean tf);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String typeString = null;

        sb.append("Type: ").append(getType()).append("\tSize: ").append(getSize())
                .append("\tHealth: ").append(getHealth()).append("\nStatus:");

        if (!hasPests() && !hasFungus() && !hasPlantRot()) {
            sb.append("\tGrowing!");
        }
        if (isReadyToPick()) {
            sb.append("\tReady to Pick!");
        }
        if (hasPests()) {
            sb.append("\tPests!!!");
        }
        if (hasFungus()) {
            sb.append("\tFungus!!!");
        }
        if (hasPlantRot()) {
            sb.append("\tRot!!!");
        }

        String toString = sb.toString();
        return toString;
    }

    public void nextTurn(int temp, double rain, int sunLte, double soilQlty) {

        double growth = this.getSize();
        double health = this.getHealth();

        double vulnrblty = 1 - this.getHardiness();
        double vulnrbltyToPests = 1 - this.getResistToPests();
        double vulnrbltyToFungus = this.getResistToFungus();
        double vulnrbltyToRot = 1 - this.getResistToRot();
        double waterReq = this.getWaterReq();
        double sunReq = this.getSunlteReq();

        /**
         * Temp Effect. -Too hot or too cold causes damage. Calculated by
         * dividing the hardiness of the plant by the health. -The ideal range
         * gives a bonus.
         */
        if (temp < 30 || temp > 105) {
            if (new Randomizer().getRandomTF(vulnrblty / health)) {
                growth -= .25;
                health -= .25;
            }
        }

        if (temp > 65 || temp < 85) {
            growth += .25;
            health += .25;
        }

        /**
         * Rain effect: Too much or too little causes rot or fungus.
         * -Calculating the rain effect by calculating the the distance between
         * the required water and amount of rain and then dividing it by the
         * total amount of rain possible. -Just enough gives a bonus.
         */
        if (new Randomizer().getRandomTF(
                (rain > waterReq) ? ((rain - waterReq) / 5)
                : ((waterReq - rain) / 5))) {
            if (new Randomizer().getRandomTF(vulnrbltyToRot / health)) {
                this.setHasPlantRot(true);
            }
            if (new Randomizer().getRandomTF(vulnrbltyToFungus / health)) {
                this.setHasFungus(true);
            }

        } else {
            growth += .25;
            health += .25;
        }

        /**
         * Sun Effect. Also checks for new Pests.
         */
        if (new Randomizer().getRandomTF(
                (sunLte > sunReq) ? ((sunLte - sunReq) / 5)
                : ((sunReq - sunLte) / 5))) {
            if (new Randomizer().getRandomTF(vulnrblty / health)) {
                growth -= .25;
                health -= .25;

            }

        } else {
            growth += .25;
            health += .25;
        }

        /**
         * Soil Effect.
         */
        if (soilQlty < 1) {
            if (new Randomizer().getRandomTF(vulnrbltyToPests / health)) {
                this.setHasPests(true);
            }

            growth -= .25;
            health -= .25;
        }

        if (soilQlty > 4) {
            growth += .25;
            health += .25;
        }

        /**
         * Run Pests/Fungus/Rot Damage Check. -Take the resistance level and
         * divide it by the health to calculate the risk. Rot is included
         *
         */
        if (hasPests()) {
            if (new Randomizer().getRandomTF(vulnrbltyToPests / health)) {
                growth -= .25;
                health -= .25;
            }
        }

        if (hasFungus()) {
            if (new Randomizer().getRandomTF(vulnrbltyToFungus / health)) {
                growth -= .25;
                health -= .25;
            }
        }

        if (hasPlantRot()) {
            if (new Randomizer().getRandomTF(vulnrbltyToRot / health)) {
                growth -= .25;
                health -= .25;
            }
        }

        if (growth > 10) {
            this.setSize(10);
        } else if (growth + this.getGrowthSpeed() < 0) {
            this.setSize(0);
        } else {
            this.setSize(growth + this.getGrowthSpeed());
        }

        if (health > 10) {
            this.setHealth(10);
        } else if (health + this.getHardiness() < 0) {
            this.setHealth(0);
        } else {
            this.setHealth(health + this.getHardiness());
        }
    }
}
