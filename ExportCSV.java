import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExportCSV {
    public static void exportShowrooomCSV(String name, CarShowroom showroom){
        try(PrintWriter write = new PrintWriter(new FileWriter(name))){
            write.println(getHeaderLine(Vehicle.class));

            for (Vehicle veh: showroom.base){
                write.println(getDataLine(veh));
            }
            System.out.println("Wyeksporotwano do CSV!");
        }catch (IOException exp){
            exp.printStackTrace();
        }
    }

    public static void importShowroomCSV(String name, CarShowroom showroom){
        try(BufferedReader read = new BufferedReader(new FileReader(name))){
            String headerLine = read.readLine();
            String[] headers = headerLine.split(",");
            String line;
            showroom.base.clear();
            while((line = read.readLine())!= null){
                String[] data = line.split(",");
                Vehicle veh = new Vehicle();
                for(int i = 0; i < headers.length; i++){
                    String header = headers[i];
                    java.lang.reflect.Field field = getFieldByColumnName(Vehicle.class, header);
                    if (field != null){
                        field.setAccessible(true);
                        String value = data[i];
                        if(field.getType() == ItemCondition.class){
                            continue;
                        }
                        if (field.getType() == int.class){
                            field.setInt(veh, Integer.parseInt(value));
                        } else if (field.getType() == double.class) {
                            field.setDouble(veh, Double.parseDouble(value));
                        } else{
                            field.set(veh, value);
                        }
                    }
                }
                veh.state = ItemCondition.NEW;
                if(!showroom.base.contains(veh)) {
                    showroom.base.add(veh);
                }
            }
            System.out.println("Wczytano dane salonu z CSV!");
        }catch (IOException | IllegalAccessException exp){
            exp.printStackTrace();
        }
    }

    private static java.lang.reflect.Field getFieldByColumnName(Class<?> cl, String columnName) {
        for (java.lang.reflect.Field field : cl.getDeclaredFields()) {
            if (field.isAnnotationPresent(ExcludeFromExport.class)) {
                continue;
            }
            if (field.isAnnotationPresent(TableColumnInfo.class)) {
                TableColumnInfo annotation = field.getAnnotation(TableColumnInfo.class);
                if (annotation.name().equals(columnName)) {
                    return field;
                }
            }
        }
        return null;
    }

    public static void exportFavsCSV(String name, List<Vehicle> favs){
        try(PrintWriter write = new PrintWriter(new FileWriter(name))){
            write.println(getHeaderLine(Vehicle.class));

            for (Vehicle veh: favs){
                write.println(getDataLine(veh));
            }
            System.out.println("Zapsiano ulubione do CSV!");
        }catch (IOException exp){
            exp.printStackTrace();
        }
    }

    public static void importFavsCSV(String name, List<Vehicle> favs){
        try(BufferedReader read = new BufferedReader(new FileReader(name))){
            String headerLine = read.readLine();
            String[] headers = headerLine.split(",");
            String line;
            favs.clear();
            while((line = read.readLine())!= null){
                String[] data = line.split(",");
                Vehicle veh = new Vehicle();
                for(int i = 0; i < headers.length; i++){
                    String header = headers[i];
                    java.lang.reflect.Field field = getFieldByColumnName(Vehicle.class, header);
                    if (field != null){
                        field.setAccessible(true);
                        String value = data[i];
                        if(field.getType() == ItemCondition.class){
                            continue;
                        }
                        if (field.getType() == int.class){
                            field.setInt(veh, Integer.parseInt(value));
                        } else if (field.getType() == double.class) {
                            field.setDouble(veh, Double.parseDouble(value));
                        } else{
                            field.set(veh, value);
                        }
                    }
                }
                veh.state = ItemCondition.NEW;
                if (!favs.contains(veh)){
                    favs.add(veh);
                }
            }
            System.out.println("Wczytano dane salonu z CSV!");
        }catch (IOException | IllegalAccessException exp){
            exp.printStackTrace();
        }
    }

    private static String getHeaderLine(Class<?> cl){
        StringBuilder header = new StringBuilder();
        for(java.lang.reflect.Field field : cl.getDeclaredFields()){
            if(field.isAnnotationPresent(ExcludeFromExport.class)){
                continue;
            }
            if(field.isAnnotationPresent(TableColumnInfo.class)){
                TableColumnInfo annot = field.getAnnotation(TableColumnInfo.class);
                header.append(annot.name()).append(",");
            }
        }
        header.deleteCharAt(header.length()-1);
        return header.toString();
    }

    private static String getDataLine(Vehicle vehicle){
        StringBuilder data = new StringBuilder();
        for(java.lang.reflect.Field field : Vehicle.class.getDeclaredFields()){
            if(field.isAnnotationPresent(ExcludeFromExport.class)){
                continue;
            }
            field.setAccessible(true);
            try{
                Object value = field.get(vehicle);
                data.append(value).append(",");
            }catch(IllegalAccessException exp){
                exp.printStackTrace();
            }
        }
        data.deleteCharAt(data.length()-1);
        return data.toString();
    }


}
