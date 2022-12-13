public class Validator {

    private String lineEnd;

    public Validator() {
        this.lineEnd = "\n";
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

}
