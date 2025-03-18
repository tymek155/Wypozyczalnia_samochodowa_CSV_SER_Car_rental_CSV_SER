
import java.io.Serializable;
import java.util.*;

public class CarShowroom implements Serializable {
    public String showroomName;
    public List<Vehicle> base;
    public int maxCapacity;
    public String location;

    public CarShowroom(String name, int capacity, String loc){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Niepoprawne dane");
        }
        else if(loc == null || loc.isEmpty()){
            throw new IllegalArgumentException("Niepoprawne dane");
        }
        else if(capacity <= 0){
            throw new IllegalArgumentException("Niepoprawne dane");
        }

        this.showroomName = name;
        this.maxCapacity = capacity;
        this.location = loc;
        this.base = new ArrayList<>();
    }

    public int currentCapacity(){
        int sum = 0;
        for(Vehicle vc : base){
            sum += vc.amount;
        }
        return sum;
    }
    public void addProduct(Vehicle pojazd){
        if (pojazd == null){
            throw new IllegalArgumentException("Niepoprawne dane");
        }
        int sum = this.currentCapacity();
        if(sum < maxCapacity){
            boolean check = false;
            for(Vehicle vc : base){
                if(vc.mark.equals(pojazd.mark) && vc.model.equals(pojazd.model)) {
                    check = true;
                    vc.amount++;
                    break;
                }
            }
            if(check == false){
                this.base.add(pojazd);
            }
        }
        else{
            System.err.println("Przekroczono ilosc samochodow w bazie!");
        }
    }

    public void removeProduct(Vehicle pojazd){
        if (!base.contains(pojazd)){
            throw new NoSuchElementException("Brak pojazdu");
        }
        this.base.remove(pojazd);
    }

    public void getVehicle(Vehicle pojazd){
        if(base.contains(pojazd)){
            if(pojazd.amount > 0){
                pojazd.amount--;
                if(pojazd.amount == 0){
                    this.removeProduct(pojazd);
                }
            }
            else{
                System.err.println("Brak dostepnego samochodu!");
            }
        }
    }

    public Vehicle serach(String mod){
        if (base.isEmpty()){
            System.err.println("Baza jest pusta!");
            return null;
        }
        Comparator<Vehicle> comp = Comparator.comparing(Vehicle::getModel);
        for(Vehicle vc: base){
            if(comp.compare(vc, new Vehicle(mod)) == 0){
                return vc;
            }
        }
        return null;
    }

    public List<Vehicle> searchPartial(String part){
        List<Vehicle> fVehicles = new ArrayList<>();

        for(Vehicle vc: base){
            if(vc.getModel().contains(part)){
                fVehicles.add(vc);
            }
        }
        return fVehicles;
    }

    public int countByCondition(ItemCondition con){
        int sum = 0;
        for(Vehicle vc: base){
            if (vc.state == con) {
                sum++;
            }
        }
        return sum;
    }

    public void summary(){
        for(Vehicle vc: base){
            vc.print();
        }
        System.out.println();
    }

    public List<Vehicle> sortByName(){
        Collections.sort(this.base);
        return this.base;
    }

    public List<Vehicle> sortByAmount(){
        Collections.sort(this.base, new AmountComparator());
        return this.base;
    }

    public Vehicle max(){
        Comparator<Vehicle> amountComp = Comparator.comparingInt(Vehicle::getAmount);
        return Collections.max(this.base, amountComp);
    }

    public List<Vehicle> sortByPrice(){
        Collections.sort(this.base, new PriceComparator());
        return this.base;
    }

    public void print(){
        double prc = ((double) this.currentCapacity() /this.maxCapacity)*100;
        System.out.println("Salon o nazwie " + this.showroomName + " w lokalizacji " + this.location + " - zapelnienie " + prc +"%");
    }
}
