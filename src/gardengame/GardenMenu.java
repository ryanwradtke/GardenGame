package gardengame;

import java.util.Scanner;
import java.util.Set;

public class GardenMenu {

    public static void gardenMenu(Scanner input, Gardener gardener) {
        System.out.println("\nHello " + gardener.getName() + ", welcome to"
                + " your garden!");
        System.out.println("\n");

        int menuChoice;

        String gardenMenu = "Garden Menu:\n"
                + "1) New Plant\n"
                + "2) Garden Info\n"
                + "3) Harvest\n"
                + "4) Tools and Chemicals\n"
                + "5) Buy More Space\n"
                + "6) Next Turn\n"
                + "7) Save and Exit";

        gardener.reviewForcast();
        System.out.println("\n" + gardener.toString() + "\n");
        System.out.println(gardenMenu);

        while ((menuChoice = input.nextInt()) != 7) {
            if (menuChoice < 1 || menuChoice > 7) {
                System.out.println("Please choose from the menu.");
            } else {
                switch (menuChoice) {
                    case 1: {
                        newPlant(input, gardener);
                        break;
                    }
                    case 2: {
                        gardenInfo(input, gardener);
                        break;
                    }
                    case 3: {
                        harvest();
                        break;
                    }
                    case 4: {
                        toolsAndChems();
                        break;
                    }
                    case 5: {
                        buySpace();
                        break;
                    }
                    case 6: {
                        gardener.nextTurn();
                        break;
                    }
                }
            }
            gardener.reviewForcast();
            System.out.println("\n" + gardener.toString() + "\n");
            System.out.println(gardenMenu);
        }
        gardener.saveGardener(gardener);
    }

    private static void newPlant(Scanner input, Gardener gardener) {
        int menuChoice;
        String newPlantMenu = "New Plant Menu:\n"
                + "1) Plant a Spot\n"
                + "2) Plant a Row\n"
                + "3) Back\n";

        System.out.println(newPlantMenu);

        while ((menuChoice = input.nextInt()) != 3) {
            if (menuChoice < 1 || menuChoice > 3) {
                System.out.println("Please choose from the menu.");
            }

            switch (menuChoice) {
                case 1: {
                    gardener.addPlantToSpot(input);
                    break;
                }
                case 2: {
                    gardener.addPlantToRow(input);
                    break;
                }
            }
            System.out.println(newPlantMenu);
        }
    }

    private static void gardenInfo(Scanner input, Gardener gardener) {

        int menuChoice;
        String gardenInfoMenu = "Garden Info:\n"
                + "1) View Full Garden\n"
                + "2) View by Row\n"
                + "3) View by Space\n"
                + "4) Back";

        System.out.println(gardenInfoMenu);

        while ((menuChoice = input.nextInt()) != 4) {
            if (menuChoice < 1 || menuChoice > 4) {
                System.out.println("Please choose from the menu.");
            } else {

                switch (menuChoice) {
                    case 1: {
                        System.out.println("\n" + gardener.prntGarden());
                        break;
                    }
                    case 2: {
                        Set rowSet;
                        rowSet = Locator.setRow(input, gardener.getGardenSize());
                        System.out.println("\n" + gardener.prntRow(rowSet));
                        break;
                    }
                    case 3: {
                        Locator loc = new Locator();
                        loc = loc.setSpot(input, gardener.getGardenSize());
                        System.out.println("\n" + gardener.prntSpot(loc));
                        break;
                    }
                }
                System.out.println(gardenInfoMenu);
            }
        }
    }

    private static void harvest() {

    }

    private static void toolsAndChems() {

    }

    private static void buySpace() {

    }


}
