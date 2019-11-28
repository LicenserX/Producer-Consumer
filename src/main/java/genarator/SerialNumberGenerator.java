package genarator;

public class SerialNumberGenerator {
    private long serialNumberGenerator = 0;

    public long nextSerialNumber() {
        serialNumberGenerator++;
        return serialNumberGenerator;
    }
}
