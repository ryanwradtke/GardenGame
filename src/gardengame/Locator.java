package gardengame;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Locator implements Comparable<Locator>, Serializable {

    private int row;
    private int column;

    public Locator() {

    }

    public Locator(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        String toString = "Spot: " + row + ", " + column + "\n";
        return toString;
    }

    public Locator setSpot(Scanner input, int gardenSize) {

        int pRow, pColumn;
        do {
          
            System.out.println("Row?");
            pRow = input.nextInt();
            System.out.println("Column?");
            pColumn = input.nextInt();
           
            if ((pRow < 0 || pRow >= gardenSize)
                    && (pColumn < 0 || pColumn > gardenSize)) {
                System.out.println("Sorry, that spot does not exist!");
            }
            
        } while ((pRow < 0 && pRow >= gardenSize)
                && (pColumn < 0 && pColumn > gardenSize));
        
        return new Locator(pRow, pColumn);
    }

    public static Set setRow(Scanner input, int gardenSize) {
        Set getRow = new TreeSet();
        Locator spot;
        int pRow;

        System.out.println("What row?");
        pRow = input.nextInt();
        if (pRow > gardenSize) {
            System.out.println("That row does not exist!");
        } else {
            for (int i = 0; i < gardenSize; i++) {
                spot = new Locator(pRow, i);
                getRow.add(spot);
            }
            return getRow;
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.row;
        hash = 47 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Locator other = (Locator) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Locator o) {
        if (this.row > o.row) {
            return 1;
        }
        if (this.row < o.row) {
            return -1;
        }
        if (this.column > o.column) {
            return 1;
        }
        if (this.column < o.column) {
            return -1;
        }
        return 0;
    }

}
