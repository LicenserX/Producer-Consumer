package utils;

public class Converter {

    public static String getFormattedSerialNumber(long serialNumber){
                return String.format("%020d", serialNumber);
    }
}
