package gardengame;

public class Randomizer {

    private int random;
    private double percent;

    public Randomizer(int min, int max) {
        int range = (max - min) + 1;
        this.random = (int) (Math.random() * range) + min;
    }
    
    public Randomizer(){
        this.percent = Math.random();
    }
    

    public int getRandomInt() {
        return random;
    }
    
    public boolean getRandomTF(double pChance) {
        return percent < pChance;
    }
}
