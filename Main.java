import javax.swing.*;
import java.util.List;

import static javafx.application.Application.launch;

public class Main {
    public static void main (String[] args){
        CarShowroomGUI GUI = new CarShowroomGUI();
        //GUI.readData();
        GUI.launch(CarShowroomGUI.class, args);
        //GUI.saveData();
        };

//        Vehicle veh1 = new Vehicle("Marka1", "BDWQ7392", ItemCondition.NEW, 21000, 2012, 50000, 2500);
//        Vehicle veh2 = new Vehicle("Marka1", "BDWQ7392", ItemCondition.NEW, 71000, 2010, 20000, 2000);
//        Vehicle veh3 = new Vehicle("Marka2", "CERR3424", ItemCondition.USED, 31000, 2013, 10000, 2000);
//        Vehicle veh4 = new Vehicle("Marka2", "CERR3424", ItemCondition.DAMAGED, 1000, 2002, 90000, 2700);
//        Vehicle veh5 = new Vehicle("Marka3", "JWKW1234", ItemCondition.NEW, 81000, 2023, 70000, 2200);
//        Vehicle veh6 = new Vehicle("Marka4", "JWJW4232", ItemCondition.DAMAGED, 2000, 2015, 10000, 2100);
//        Vehicle veh7 = new Vehicle("Marka5", "PEIWJ214", ItemCondition.NEW, 221000, 2020, 30000, 2100);
//        Vehicle veh8 = new Vehicle("Marka6", "IREU1245", ItemCondition.USED, 11000, 2020, 60000, 2700);
//
//        CarShowroom crsm1 = new CarShowroom("Salon1", 20, "Miejscowosc1");
//        CarShowroom crsm2 = new CarShowroom("Salon2", 3, "Miejscowosc2");
//
//        crsm1.addProduct(veh5);
//        crsm1.addProduct(veh6);
//        crsm1.addProduct(veh7);
//        crsm1.addProduct(veh8);
//
//        crsm1.getVehicle(veh6);
//
//        crsm1.summary();
//
//        crsm1.removeProduct(veh8);
//        crsm1.summary();
//
//        crsm1.addProduct(veh6);
//        crsm1.addProduct(veh8);
//
//        Vehicle szuk = crsm1.serach(veh6.model);
//        szuk.print();
//        System.out.println();
//
//        List<Vehicle> l1 = crsm1.searchPartial("JW");
//        for(Vehicle v: l1){
//            v.print();
//        }
//
//        System.out.println(crsm1.countByCondition(ItemCondition.DAMAGED));
//
//        crsm1.sortByName();
//        crsm1.summary();
//        crsm1.sortByPrice();
//        crsm1.summary();
//
//        crsm2.addProduct(veh3);
//        crsm2.addProduct(veh1);
//        crsm2.addProduct(veh2);
//        //crsm2.addProduct(veh4);
//        crsm2.summary();
//        crsm2.sortByAmount();
//        crsm2.summary();
//        System.out.println();
//        Vehicle vhmax = crsm2.max();
//        vhmax.print();
//
//        CarShowroom crsm0 = new CarShowroom("SalonW", 3, "Miejscowosc0");
//        CarShowroom crsm3 = new CarShowroom("Salon3", 3, "Miejscowosc3");
//        CarShowroomContainer csc1 = new CarShowroomContainer("Nazwa1", "Lokalizacja1");
//        csc1.addCenter(crsm1.showroomName, crsm1);
//        csc1.addCenter(crsm2.showroomName, crsm2);
//        csc1.addCenter(crsm3.showroomName, crsm3);
//        csc1.addCenter(crsm0.showroomName, crsm0);
//        csc1.removeCenter(crsm0.showroomName);
//        csc1.summary();
//        csc1.addCenter(crsm0.showroomName, crsm0);
//        System.out.println();
//
//        List<CarShowroom> l2 = csc1.findEmpty();
//        for(CarShowroom c: l2){
//            c.print();
//        }

    }

