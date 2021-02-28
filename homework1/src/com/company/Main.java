package com.company;
import java.util.HashMap;

public class Main {

    public static void printBytes(double num) {
        HashMap<Integer, String> measurementUnits = new HashMap<>() {{
            put( 0, "B");
            put( 1, "KB");
            put( 2, "MB");
            put( 3, "GB");
            put( 4, "TB");
            put( 5, "PB");
            put( 6, "EB");
            put( 7, "ZB");
            put( 8, "YB");
        }};
        int counter = 0;
        while (num >= 1024) {
            num /= 1024;
            counter ++;
        }
        System.out.println(String.format("%.1f", num).replace(",",".")
                + " "
                + measurementUnits.get(counter));
    }

    public static void main(String[] args) {
        printBytes(53692044);
    }
}
