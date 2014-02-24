package garden.game;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

class Almanac implements Serializable {

    private Map<Integer, Month> year = new TreeMap<>();  //runs Mar. - Nov.
    private int currentMonth;
    private int yearCount;

    public int getYearCount() {
        return yearCount;
    }
    
    public int getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentTemp() {
        return year.get(currentMonth).getTemp();
    }
    
    public int getCurrentRain() {
        return year.get(currentMonth).getRain();
    }
    
    public void initYear() {
        for (int i = 3; i < 12; i++) {
            year.put(i, new Month(i));
        }
        yearCount++;
        currentMonth = 3;
    }

    public void nextMonth() {
        if (currentMonth < 11) {
            System.out.println(prntMonthReview());
            currentMonth++;
        } else {
            System.out.println("A year has passed!\n");
            if (yearCount == 1) {
                System.out.println("Congrats on finishing your first year!\n");
            }
            System.out.println(prntYearReport());
            initYear();
        }
    }

    public void setYearCount(int yearCount) {
        this.yearCount = yearCount;
    }

    public String prntYearCount() {
        return "Year{" + "yearCount=" + yearCount + '}';
    }

    public String prntMonthForcast() {
        return year.get(currentMonth).forcast();
    }

    public String prntMonthReview() {
        return year.get(currentMonth).review();
    }

    public String prntYearReport() {
        //Add a harvest report!!!

        //Past Year Weather Report:
        StringBuilder sb = new StringBuilder();

        sb.append("\nThe weather for year ").append(yearCount)
                .append(" was as follows: \n");

        for (Map.Entry<Integer, Month> entry : this.year.entrySet()) {
            sb.append(entry.getValue().review()).append("\n");
        }

        sb.append("\n");
        String yearReport = sb.toString();
        return yearReport;
    }
}
