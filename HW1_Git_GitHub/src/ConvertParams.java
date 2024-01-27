import java.util.Hashtable;

public record ConvertParams(
        Hashtable<Double, Double> historyHashTable,
        String requested,
        double Constant,
        String from,
        String to
) {
}
