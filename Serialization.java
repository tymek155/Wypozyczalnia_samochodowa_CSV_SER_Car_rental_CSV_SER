import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization {
    public static boolean saveShowrooms(String name, CarShowroomContainer container){
        try(ObjectOutputStream output  = new ObjectOutputStream(new FileOutputStream(name))){
            output.writeObject(container);
            System.out.println("Zapisano stan salonu");
            return true;
        } catch(IOException exp){
            exp.printStackTrace();
            return false;
        }
    }

    public static CarShowroomContainer loadShowrooms(String name){
        CarShowroomContainer container = null;
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(name))){
            container = (CarShowroomContainer) input.readObject();
            for (CarShowroom showroom : container.showrooms.values()) {
                for (Vehicle vehicle : showroom.base) {
                    vehicle.state = ItemCondition.NEW;
                }
            }
            System.out.println("Odczytano stan salonu z pliku!");
        } catch (IOException | ClassNotFoundException exp){
            exp.printStackTrace();
        }
        return container;
    }

    public static boolean saveFavs(String name, List<Vehicle> favVehs){
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(name))){
            output.writeObject(favVehs);
            System.out.println("Zapisano ulubione samochody do pliku!");
            return true;
        }catch(IOException exp){
            exp.printStackTrace();
            return false;
        }
    }

    public static List<Vehicle> loadFavsVehs(String name){
        List<Vehicle> favVehs = new ArrayList<>();
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(name))){
            favVehs = (List<Vehicle>) input.readObject();
            for (Vehicle vehicle : favVehs) {
                if (vehicle.state == null) {
                    vehicle.state = ItemCondition.NEW;
                }
            }
            System.out.println("Odcyztano listę ulubionych z pliku!");
        }catch(IOException | ClassNotFoundException exp){
            exp.printStackTrace();
        }
        return favVehs;
    }
}
