package gardengame;

import java.io.Serializable;

class Month implements Serializable  {

    protected String month; //March through November.
    protected int temp;  // 20 - 110: in degrees
    protected int rain;  // 0-10: 0 is no rain and 10 = very wet.
    protected int tMin, tMax, rMin, rMax;

    public Month(int monthNum) {

        switch (monthNum) {
            case 3: {
                this.month = "Mar.";
                this.tMin = 25;
                this.tMax = 55;
                this.rMin = 2;
                this.rMax = 6;
                break;
            }
            case 4: {
                this.month = "Apr.";
                this.tMin = 35;
                this.tMax = 55;
                this.rMin = 3;
                this.rMax = 6;
                break;
            }
            case 5: {
                this.month = "May";
                this.tMin = 45;
                this.tMax = 65;
                this.rMin = 6;
                this.rMax = 10;
                break;
            }
            case 6: {
                this.month = "Jun.";
                this.tMin = 55;
                this.tMax = 80;
                this.rMin = 4;
                this.rMax = 8;
                break;
            }
            case 7: {
                this.month = "Jul.";
                this.tMin = 75;
                this.tMax = 110;
                this.rMin = 0;
                this.rMax = 5;
                break;
            }
            case 8: {
                this.month = "Aug.";
                this.tMin = 75;
                this.tMax = 110;
                this.rMin = 0;
                this.rMax = 4;
                break;
            }
            case 9: {
                this.month = "Sep.";
                this.tMin = 65;
                this.tMax = 80;
                this.rMin = 2;
                this.rMax = 6;
                break;
            }
            case 10: {
                this.month = "Oct.";
                this.tMin = 45;
                this.tMax = 75;
                this.rMin = 2;
                this.rMax = 8;
                break;
            }
            case 11: {
                this.month = "Nov.";
                this.tMin = 20;
                this.tMax = 55;
                this.rMin = 2;
                this.rMax = 8;
                break;
            }
        }
        this.temp = (new Randomizer(tMin, tMax)).getRandomInt();
        this.rain = (new Randomizer(rMin, rMax)).getRandomInt();
    }

    public String getMonth() {
        return month;
    }

    public int getTemp() {
        return temp;
    }

    public int getRain() {
        return rain;
    }

    public String forcast() {

        return "\nForcast for month: " + month + "\nTemp. Range: " + tMin 
                + "-" + tMax + " degrees." + "\nRainfall Range: " + rMin + "-" 
                + rMax + " inches.\n";
    }
    
    public String review() {
        return "\nFor month: " + month + "\nThe average temp. was: " + temp
                + ".\nRainfall: " + rain + " inches.\n";
    }
}
