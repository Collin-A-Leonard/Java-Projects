import java.util.List;

/*
*Author: Collin Leonard
*Date: 2/11/21
* This application stores a number of Conversions in a .txt file that the ConversionItems class draws from.
* The main application allows you to add or delete conversions, as well as calculate conversions from those
* saved.
*/
public class LengthConverter {

    // Instantiate new ConversionItems object.
    static ConversionItems conversions = new ConversionItems();

    public static void main(String[] args) {
        // Welcome user, render the menu, gets user input choice.
        System.out.println("Length Converter\n");
        renderMenu();
        int choice = Console.getInt("Enter a menu number: ", 1, 4);
        // Loop continues while user does not input int 4
        // Loop calls appropriate method.
        while (choice != 4) {
            System.out.println();
            if (choice == 1) {
                convertLength();
           } else if (choice ==2) {
                addConversion();
            } else {
                deleteConversion();
            }
            System.out.println();
            // Render menu and get user choice again.
            renderMenu();
            choice = Console.getInt("Enter a menu number: ", 1, 4);
        }
        System.out.println("\nGoodbye.");
    }

    // Method displays available conversions, user chooses which one, then method calculates and displays it.
    public static void convertLength() {
        List<Conversion> myList = conversions.getConversions();
        if (myList.size() >= 1) {
            for (int i = 0; i < myList.size(); i++) {
                System.out.println((i + 1) + " - " + myList.get(i).choose());
            }
            System.out.println();
            int choice = Console.getInt("Enter a conversion number: ", 1, myList.size());
            Conversion c = myList.get(choice - 1);
            System.out.println();
            c.setFromValue(Console.getDouble("Enter " + c.getFromUnit() + ": "));
            System.out.println(c.toString());
        } else {
            System.out.println("No conversions on file, add them.");
        }
    }

    // Method adds Conversion object to ConversionItems, which saves it to output file.
    public static void addConversion() {
        String fromUnit = Console.getString("Enter 'From' unit: ");
        String toUnit = Console.getString("Enter 'To' unit: ");
        double conversionRatio = Console.getDouble("Enter the conversion ratio: ");
        Conversion c = new Conversion(fromUnit, toUnit, conversionRatio);
        conversions.add(c);
        System.out.println("\nThis entry has been saved.");
    }

    // Gets user input and deletes matching Conversion object from program and output file.
    public static void deleteConversion() {
        String fromUnit = Console.getString("Enter 'From' unit: ");
        String toUnit = Console.getString("Enter 'To' unit: ");
        Conversion c = conversions.get(fromUnit, toUnit);
        if(c != null) {
            conversions.delete(c);
            System.out.println("Conversion has been deleted.");
        } else {
            System.out.println("No conversion matches those fields.");
        }
    }

    // Renders the main menu
    static void renderMenu() {
        String menu = "1 - Convert a length\n" +
                "2 - Add a type of conversion\n" +
                "3 - Delete a type of conversion\n" +
                "4 - Exit\n";
        System.out.println(menu);
    }

}
