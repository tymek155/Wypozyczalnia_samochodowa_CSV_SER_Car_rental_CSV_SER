import java.util.Comparator;

public class PriceComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle v1, Vehicle v2){
        return Double.compare(v1.price, v2.price);
    }
}
