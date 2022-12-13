package Console;

import Classes.Game;
import FileIO.GameDAO;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validator {

    private String lineEnd;

    public Validator() {
        this.lineEnd = "\n";
    }

    public String isString(String value, String name) {
        String msg = "";
        if (value.isEmpty()) {
            msg = name + " is required." + lineEnd;
        }
        return msg;
    }

    public String isInt(String value, String name) {
        String msg = "";
        int i = -1;
        try {
            i = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            msg = name + " must be a valid number." + lineEnd;
        }
        if (i < 0) {
            msg = name + " must be a valid number. " + lineEnd;
        }
        return msg;
    }

    // Method returns error message String if an invalid double is entered in the text field.
    public String isDouble(String value, String name) {
        String msg = "";
        double d = -1;
        try {
            d = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            msg = name + " must be a valid number." + lineEnd;
        }
        if (d < 0) {
            msg = name + " must be a valid number. " + lineEnd;
        }
        return msg;
    }

    public String isLocalDate(String value) {
        String msg = "";
        try {
            LocalDate gameDate = LocalDate.parse(value);
        } catch (DateTimeParseException e) {
           msg = "Game Date must be valid date format. " + lineEnd;
        }
        return msg;
    }


}
