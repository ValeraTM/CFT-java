import model.options.DataType;
import model.options.OptionsParser;
import model.options.Order;

import java.io.*;
import java.util.ArrayList;

public class App {
    private static void showUsage() {
        System.out.println("usage:");
        System.out.println("optional -a/-d (in ascending/descending order)");
        System.out.println("-i/-s (integer/string)");
        System.out.println("output file");
        System.out.println("input file >= 1");
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            showUsage();
            return;
        }

        OptionsParser parser = new OptionsParser(args);
        try {
            Order order = parser.getSortOrder();
            DataType dataType = parser.getDataType();
            String nameOutputFile = parser.getNameOutputFile();
            String[] namesInputFiles = parser.getNamesInputFiles();

            OutputStream out = new FileOutputStream(nameOutputFile);
            ArrayList<InputStream> in = new ArrayList<>(namesInputFiles.length);
            for (String it : namesInputFiles) {
                try {
                    in.add(new FileInputStream(it));
                }
                catch (FileNotFoundException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            new Controller(order, dataType, out, in);
        }
        catch (IllegalArgumentException ex) {
            showUsage();
        }
        catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
}