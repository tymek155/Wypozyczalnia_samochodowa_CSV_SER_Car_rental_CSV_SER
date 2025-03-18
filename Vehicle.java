import javafx.beans.property.*;
import java.util.Objects;
import java.io.Serializable;

public class  Vehicle implements Comparable<Vehicle>, Serializable {
    @TableColumnInfo(name = "Marka", order = 1)
    public String mark;
    @TableColumnInfo(name = "Model", order = 2)
    public String model;
    @ExcludeFromExport
    public transient ItemCondition state;
    @TableColumnInfo(name = "Cena", order = 3)
    public double price;
    @TableColumnInfo(name = "Rok produkcji", order =4)
    public int productionYear;
    @TableColumnInfo(name = "Przebieg", order = 5)
    public double mileage;
    @TableColumnInfo(name = "Pojemność", order = 6)
    public double capacity;
    @TableColumnInfo(name = "Ilość", order = 7)
    public int amount;
    @TableColumnInfo(name="Paliwo", order = 8)
    public String fuel;
    @TableColumnInfo(name = "Moc", order = 9)
    public int power;
    @TableColumnInfo(name = "Skrzynia biegów", order = 10)
    public String sb;

    public Vehicle(){

    }

    public Vehicle(String marka, String mod, ItemCondition st, double cena, int rok, double przebieg, double pojemnosc, String fl, int pwr, String skrz){
        this.mark = marka;
        this.model = mod;
        this.state = st;
        this.price = cena;
        this.productionYear = rok;
        this.mileage = przebieg;
        this.capacity = pojemnosc;
        this.amount = 1;
        this.fuel = fl;
        this.power = pwr;
        this.sb = skrz;
    }

    public Vehicle(String mod){
        this.model = mod;
    }

    public void print(){
        System.out.println("Samochód marki " + this.mark +", model " + this.model + ", stan "+this.state+", cena "+ this.price +", rok produkcji "+ this.productionYear + ", przebieg " +this.mileage+ ", pojemnosc "+ this.capacity + " ilosc " + this.amount);
    }

    public String getMarkS(){return this.mark;}
    public StringProperty getMark(){return new SimpleStringProperty(this.mark);}
    public StringProperty getModelP(){return new SimpleStringProperty(this.model);}
    public IntegerProperty getYear(){return new SimpleIntegerProperty((this.productionYear));}
    public DoubleProperty getPrice(){return new SimpleDoubleProperty(this.price);}

    public StringProperty getFuel(){return new SimpleStringProperty(this.fuel);}
    public IntegerProperty getPower(){return new SimpleIntegerProperty(this.power);}
    public StringProperty getSk(){return new SimpleStringProperty(this.sb);}

    public String getModel(){
        return this.model;
    }

    public int getAmount(){
        return this.amount;
    }

    @Override
    public int compareTo(Vehicle other){
        return this.model.compareTo(other.model);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vehicle other = (Vehicle) obj;
        return Double.compare(other.price, price) == 0 &&
                productionYear == other.productionYear &&
                Double.compare(other.mileage, mileage) == 0 &&
                Double.compare(other.capacity, capacity) == 0 &&
                amount == other.amount &&
                power == other.power &&
                Objects.equals(mark, other.mark) &&
                Objects.equals(model, other.model) &&
                Objects.equals(fuel, other.fuel) &&
                Objects.equals(sb, other.sb);
    }
}
