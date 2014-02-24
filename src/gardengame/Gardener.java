package gardengame;

import Produce.ProduceInterface;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gardener implements Serializable {

    private String name;

    private int money;
    private int gardenSize;

    private Almanac almanac;

    private Map<Locator, GardenSpot> garden;
    private List<ProduceInterface> harvestedProduce = new ArrayList();

    public Gardener(String name, Almanac almanac, Map garden) {
        this.name = name;
        this.money = 5; // expressed in $$$
        this.gardenSize = 2;
        this.almanac = almanac;
        this.garden = garden;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\tMoney: " + money + "\tGarden Size: "
                + gardenSize + "x" + gardenSize;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getGardenSize() {
        return gardenSize;
    }

    public Map<Locator, GardenSpot> getGarden() {
        return garden;
    }

    public String prntSpot(Locator loc) {
        return (garden.get(loc)).toString();
    }

    public String prntRow(Set rowSet) {
        StringBuilder sb = new StringBuilder();

        for (Object loc : rowSet) {
            sb.append(((Locator) loc).toString()).append("\n")
                    .append(garden.get((Locator) loc).toString())
                    .append("\n");
        }

        return sb.toString();
    }

    public String prntGarden() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Locator, GardenSpot> entry : this.garden.entrySet()) {
            sb.append((entry.getKey()).toString())
                    .append((entry.getValue()).toString())
                    .append("\n");
        }

        return sb.toString();
    }

    public void reviewForcast() {
        almanac.prntMonthForcast();
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setGardenSize(int gardenSize) {
        this.gardenSize = gardenSize;

        //change gardenInit to accept two ints.  Set garden could then 
        //reuse that code.  x and y would be passed.  
    }

    public void gardenInit() {
        for (int x = 0; x < gardenSize; x++) {
            for (int y = 0; y < gardenSize; y++) {
                //Add random to GardenSpot.soilQlty and .sunLte
                GardenSpot spot = new GardenSpot(10, 5, null, false);
                Locator loc = new Locator(x, y);
                garden.put(loc, spot);
            }
        }
    }

    public void addPlantToSpot(Scanner input) {
        int menuChoice;
        Locator loc = new Locator();

        String plantSpotMenu = "What type of plant?\n"
                + "1) Onion\n"
                + "2) Strawberry\n"
                + "3) Back\n";

        System.out.println(plantSpotMenu);

        while ((menuChoice = input.nextInt()) != 3) {
            if (menuChoice < 1 || menuChoice > 3) {
                System.out.println("Please choose from the menu.\n");
            }

            if ((loc = loc.setSpot(input, gardenSize)) != null) {

                input.nextLine();

                switch (menuChoice) {
                    case 1: {
                        (garden.get(loc)).setPlant(new Plants.OnionPlant());
                        break;
                    }
                    case 2: {
                        (garden.get(loc)).setPlant(new Plants.StrawberryPlant());
                        break;
                    }
                }
                System.out.println(plantSpotMenu);
            }
        }
    }

    public void addPlantToRow(Scanner input) {
        int menuChoice;

        String plantRowMenu = "Plant a Row:\n"
                + "1) Onion\n"
                + "2) Strawberry\n"
                + "3) Back\n";

        System.out.println(plantRowMenu);

        while ((menuChoice = input.nextInt()) != 3) {
            if (menuChoice < 1 || menuChoice > 3) {
                System.out.println("Please choose from the menu.\n");
            }

            Set row;

            if ((row = Locator.setRow(input, gardenSize)) != null) {

                input.nextLine();

                switch (menuChoice) {
                    case 1:
                        for (Object loc : row) {
                            (garden.get((Locator) loc))
                                    .setPlant(new Plants.OnionPlant());
                        }
                        break;
                    case 2:
                        for (Object loc : row) {
                            (garden.get((Locator) loc))
                                    .setPlant(new Plants.StrawberryPlant());
                        }
                        break;
                }
                System.out.println(plantRowMenu);
            }
        }
    }

    public void nextTurn() {

        for (Map.Entry<Locator, GardenSpot> entry : garden.entrySet()) {
            // Check if a plant exists in spot.
            if (entry.getValue().getPlant() != null) {

                // Call nextTurn on the plant in this spot.
                entry.getValue().getPlant().nextTurn(
                        almanac.getCurrentTemp(),
                        (double)almanac.getCurrentRain(),
                        entry.getValue().getSunLte(),
                        entry.getValue().getSoilQlty());
            }
        }

        almanac.nextMonth();
    }

    public static Gardener loadGardener() {
        System.out.println("Loading . . . . . . . . . . . .");

        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream("SaveGame.bin"))) {
            Gardener gardener = (Gardener) os.readObject();
            os.close();
            return gardener;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gardener.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void saveGardener(Gardener gardener) {
        System.out.println("Saving . . . . . . . . . . . .");

        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("SaveGame.bin"))) {
            os.writeObject(gardener);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
