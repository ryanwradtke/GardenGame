package gardengame;

import java.util.Scanner;
import java.util.TreeMap;

public class GardenGame {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            int menuChoice;
            
            System.out.println("\nWelcome to the Garden Game!");
            String mainMenu = "Main Menu: \n"
                    + "1) New Gardener\n"
                    + "2) Load Gardener\n"
                    + "3) Exit";
            System.out.println(mainMenu);
            
            while ((menuChoice = input.nextInt()) != 3) {
                if (menuChoice < 1 || menuChoice > 3) {
                    System.out.println("Please choose from the menu.");
                } else {
                    input.nextLine();
                    switch (menuChoice) {
                        case 1: {
                            Gardener gardener;
                            Almanac almanac = new Almanac();
                            
                            System.out.println("Hello! Whats your name?");
                            gardener = new Gardener(input.nextLine()
                                    , almanac, new TreeMap<>());
                            almanac.initYear();
                            gardener.gardenInit();
                            GardenMenu.gardenMenu(input, gardener);
                            
                            break;
                        }
                        case 2: {
                            Gardener gardener = Gardener.loadGardener();
                            GardenMenu.gardenMenu(input, gardener);
                            break;
                        }
                    }
                    System.out.println(mainMenu);
                }
            }
        }
    }
}
