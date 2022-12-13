import java.math.RoundingMode;
import java.text.NumberFormat;

public class Conversion {

    // Instance variables
    String fromUnit;
    double fromValue;
    String toUnit;
    double toValue;
    double conversionRatio;

    // Instantiates new Conversion object with these 3 required fields.
    public Conversion(String fromUnit, String toUnit, double conversionRatio) {
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.conversionRatio = conversionRatio;
    }

    // Getters and setters
    public void setFromValue(double fromValue) {
        this.fromValue = fromValue;
        // calculateConversion is called after fromValue is set as all required info is obtained.
        calculateConversion();
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public double getConversionRatio() {
        return conversionRatio;
    }

    // Sets toValue as calculated and formatted double value.
    private void calculateConversion() {
        NumberFormat numFormat = NumberFormat.getInstance();
        numFormat.setRoundingMode(RoundingMode.HALF_UP);
        numFormat.setMaximumFractionDigits(2);
        String tempValue = numFormat.format(fromValue * conversionRatio);
        toValue = Double.parseDouble(tempValue);
    }

    // Methods to return String values helpful in the program.
    public String choose() {
        return (fromUnit+ " to " + toUnit + ": " + conversionRatio);
    }

    public String toString() {
        return (fromValue + " " + fromUnit + " = " + toValue + " " + toUnit);
    }

}
