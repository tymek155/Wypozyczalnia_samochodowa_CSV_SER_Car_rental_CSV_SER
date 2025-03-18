import java.util.Comparator;

public class AmountComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle v1, Vehicle v2){
        return Integer.compare(v2.amount, v1.amount);
    }
}
