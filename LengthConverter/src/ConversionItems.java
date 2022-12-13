import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ConversionItems {

    ArrayList<Conversion> conversions;
    Path conversionPath;
    File conversionFile;
    static String delim = ",";

    // Initializes output file and creates new instance of class.
    public ConversionItems() {
        conversionPath = Paths.get("conversion_types.txt");
        try {
            if (Files.notExists(conversionPath)) {
                System.out.println("Data file not found.");
                System.out.println("Creating file: " +
                        conversionPath.toAbsolutePath() + "\n");
                Files.createFile(conversionPath);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        conversionFile = conversionPath.toFile();
        conversions = this.getConversions();
    }


    // Returns ArrayList of Conversion objects.
    public ArrayList<Conversion> getConversions() {
        if (conversions != null) {
            return conversions;
        }

        conversions = new ArrayList<>();

        if (Files.exists(conversionPath)) { // prevent the FileNotFoundException
            try (BufferedReader in
                         = new BufferedReader(
                    new FileReader(conversionFile))) {
                // read all customers stored in the file
                // into the array list
                String line = in.readLine();
                while (line != null) {
                    String[] columns = line.split(delim);
                    String fromUnit = columns[0];
                    String toUnit = columns[1];
                    double conversionRatio = Double.parseDouble(columns[2]);
                    Conversion c = new Conversion(fromUnit, toUnit, conversionRatio);
                    conversions.add(c);

                    line = in.readLine();
                }
            } catch (IOException e) {
                System.out.println(e);
                return null;
            }
        } else {
            System.out.println("Path is empty");
            return null;
        }
        return conversions;
    }


    // Saves all Conversion objects in ArrayList to output .txt file
    public boolean saveConversions() {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(conversionFile)))) {

            // write all customers in the array list
            // to the file
            for (Conversion c : conversions) {
                out.print(c.getFromUnit() + delim);
                out.print(c.getToUnit() + delim);
                out.println(c.getConversionRatio());
            }
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    // Returns matching Conversion object from ArrayList
    public Conversion get(String fromUnit, String toUnit) {
        for(Conversion c: conversions) {
            if(fromUnit.equalsIgnoreCase(c.getFromUnit()) && toUnit.equalsIgnoreCase(c.getToUnit())) {
                return c;
            }
        }
        return null;
    }

    // Adds Conversion object to conversions ArrayList
    public boolean add(Conversion c) {
        conversions.add(c);
        return this.saveConversions();
    }

    // Deletes Conversion Object from ArrayList
    public boolean delete(Conversion c) {
        conversions.remove(c);
        return this.saveConversions();
    }

}
