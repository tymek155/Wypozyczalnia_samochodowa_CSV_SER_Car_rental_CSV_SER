import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarShowroomGUI extends Application {
    CarShowroomContainer centrum;
    TableView<Vehicle> tableView;
    ComboBox<String> showroomComboBox;
    TextField searchTxt;

    List<Vehicle> favVehicles = new ArrayList<>();

    @Override
    public void start(Stage app){
        readData();
        //sampleDataForClass();
        //ExportCSV.importShowroomCSV("showroom.csv", centrum.showrooms.get("Salon1"));
        //ExportCSV.importFavsCSV("favourite_vehicles.csv", favVehicles);

        app.setTitle("Wybierz salon: ");

        showroomComboBox = new ComboBox<>();

        List<String> showroomNames = new ArrayList<>(this.centrum.showrooms.keySet());
        showroomNames.add("Dowolny");
        showroomComboBox.setItems(FXCollections.observableArrayList(showroomNames));

        tableView = createTab();
        tableView.setFixedCellSize(70);
        searchTxt = new TextField();
        Button searchButton = new Button("Wyszukaj samochód");

        searchButton.setOnAction(event -> searchVehicles());

        showroomComboBox.setOnAction(event ->{
            String wybranySalon = showroomComboBox.getValue();
            if (wybranySalon.equals("Dowolny")){
                List<Vehicle> dowolne = new ArrayList<>();
                for (Map.Entry<String, CarShowroom> salon : this.centrum.showrooms.entrySet()){
                    dowolne.addAll(salon.getValue().base);
                }
                tableView.setItems(FXCollections.observableArrayList(dowolne));
            } else{
                tableView.setItems(FXCollections.observableArrayList(this.centrum.showrooms.get(wybranySalon).base));
            }
        });

        Button showFavsB = new Button("Pokaż ulubione");
        showFavsB.setOnAction(event -> showFavs());

        Button importCSVButton = new Button("Importuj dane salonu z pliku CSV");
        importCSVButton.setOnAction(event ->{
            String selectedShowroom = showroomComboBox.getValue();
            if(selectedShowroom != null){
                ExportCSV.importShowroomCSV(selectedShowroom + ".csv", centrum.showrooms.get(selectedShowroom));
                tableView.setItems(FXCollections.observableArrayList(centrum.showrooms.get(selectedShowroom).base));
                tableView.refresh();
            }
        });

        Button exportCSVButton = new Button("Eksportuj dane do pliku CSV");
        exportCSVButton.setOnAction(event->{
            String selectedShowroom = showroomComboBox.getValue();
            if(selectedShowroom!=null){
                ExportCSV.exportShowrooomCSV(selectedShowroom+".csv", centrum.showrooms.get(selectedShowroom));
            }
        });

        Button importFavList = new Button("Importuj listę ulubionych");
        importFavList.setOnAction(event ->{
            ExportCSV.importFavsCSV("favourite_vehicles.csv", favVehicles);
        });

        Button exportFavList = new Button("Eksportuj listę ulubionych");
        exportFavList.setOnAction(event->{
            ExportCSV.exportFavsCSV("favourite_vehicles.csv", favVehicles);
        });

        HBox hboxCSV = new HBox();
        hboxCSV.setSpacing(10);
        hboxCSV.getChildren().addAll(importCSVButton, exportCSVButton);

        HBox hboxFav = new HBox();
        hboxFav.setSpacing(10);
        hboxFav.getChildren().addAll(importFavList, exportFavList);


        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(new Label("Wybierz salon:"), showroomComboBox, new Label("Wyszukaj samochód: "), new HBox(searchTxt, searchButton), showFavsB, hboxCSV, hboxFav, tableView);

        Scene scena = new Scene(vbox, 850, 500);

        app.setScene(scena);
        app.setTitle("Lista pojazdów");
        app.show();

        app.setOnCloseRequest(event ->{
            if(saveData()){
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Zapisano dane!");
                info.setHeaderText(null);
                info.setContentText("Dane zostały zapisane pomyślnie za pomocą serializacji!");
                info.showAndWait();
            }else{
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Błąd zapisu!");
                error.setHeaderText(null);
                error.setContentText("Błąd zapisu danych przez serializację!");
                error.showAndWait();
            }

        });
    }

    TableView<Vehicle> createTab(){
        TableView<Vehicle> tabView = new TableView<>();
        tabView.setEditable(false);

        TableColumn<Vehicle, String> nameColumn = new TableColumn<>("Nazwa");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getMark());
        nameColumn.setSortType(TableColumn.SortType.ASCENDING);
        nameColumn.setResizable(true);
        nameColumn.setPrefWidth(105);

        TableColumn<Vehicle, Double> priceColumn = new TableColumn<>("Cena");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        priceColumn.setSortType(TableColumn.SortType.ASCENDING);
        priceColumn.setResizable(true);
        priceColumn.setPrefWidth(105);

        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().getModelP());
        modelColumn.setSortType(TableColumn.SortType.ASCENDING);
        modelColumn.setResizable(true);
        modelColumn.setPrefWidth(105);

        TableColumn<Vehicle, Integer> yearColumn = new TableColumn<>("Rok");
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().getYear().asObject());
        yearColumn.setSortType(TableColumn.SortType.ASCENDING);
        yearColumn.setResizable(true);
        yearColumn.setPrefWidth(105);

        TableColumn<Vehicle, String> fuelColumn = new TableColumn<>("Rodzaj paliwa");
        fuelColumn.setCellValueFactory(cellData -> cellData.getValue().getFuel());
        fuelColumn.setSortType(TableColumn.SortType.ASCENDING);
        fuelColumn.setResizable(true);
        fuelColumn.setPrefWidth(105);

        TableColumn<Vehicle, Integer> powerColumn = new TableColumn<>("Moc");
        powerColumn.setCellValueFactory(cellData -> cellData.getValue().getPower().asObject());
        powerColumn.setSortType(TableColumn.SortType.ASCENDING);
        powerColumn.setResizable(true);
        powerColumn.setPrefWidth(105);

        TableColumn<Vehicle, String> skColumn = new TableColumn<>("Skrzynia biegów");
        skColumn.setCellValueFactory(cellData -> cellData.getValue().getSk());
        skColumn.setSortType(TableColumn.SortType.ASCENDING);
        skColumn.setResizable(true);
        skColumn.setPrefWidth(105);

        tabView.getColumns().addAll(nameColumn, priceColumn, modelColumn, yearColumn, fuelColumn, powerColumn, skColumn);
        toolTip(tabView);

        TableColumn<Vehicle, String> actionColumn = new TableColumn<>("Akcja");
        booking(actionColumn);
        tabView.getColumns().add(actionColumn);

        TableColumn<Vehicle, Void> favouriteColumn = new TableColumn<>("Ulubione");
        favouriteColumn.setPrefWidth(105);
        favouriteColumn.setCellFactory(column -> new TableCell<Vehicle, Void>(){
            private final Button favButton = new Button("Dodaj do ulubionych");
            {
                favButton.setOnAction(event ->{
                    Vehicle veh = getTableView().getItems().get(getIndex());
                    if (favVehicles.contains(veh)){
                        removeFromFav(veh);
                        favButton.setText("Dodaj do ulubionych");
                    }else{
                        addToFav(veh);
                        favButton.setText("Usuń z ulubionych");
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empt){
                super.updateItem(item, empt);
                if(empt){
                    setGraphic(null);
                }else{
                    Vehicle veh = getTableView().getItems().get(getIndex());
                    if (favVehicles.contains(veh)){
                        favButton.setText("Usuń z ulubionych");
                    }else{
                        favButton.setText("Dodaj do ulubioncyh");
                    }
                    setGraphic(favButton);
                }
            }
        });
        tabView.getColumns().add(favouriteColumn);

        nameColumn.setSortable(true);
        priceColumn.setSortable(true);
        yearColumn.setSortable(true);
        modelColumn.setSortable(true);

        return tabView;
    }

    void booking(TableColumn<Vehicle,String> actionColumn){
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        actionColumn.setCellFactory(parameter -> new TableCell<>(){
            private final Button bookButton = new Button("Rezerwuj");

            @Override
            protected void updateItem(String item, boolean empty){
                super.updateItem(item, empty);

                if(empty){
                    setGraphic(null);
                    return;
                }

                Vehicle vehicle = getTableView().getItems().get(getIndex());
                setGraphic(bookButton);
                bookButton.setOnAction(event ->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Potwierdź rezerwację");
                    alert.setHeaderText("Czy na pewno chcesz zarezerwować?");
                    alert.setContentText("Samochód: " + vehicle.mark + "\nModel: " + vehicle.model + "\nCena: " + vehicle.price + "\nRok produkcji: "+ vehicle.productionYear + "\nDostępna ilość: " + vehicle.amount);
                    Optional<ButtonType> wynik = alert.showAndWait();
                    if(wynik.isPresent() && wynik.get() == ButtonType.OK && vehicle.amount>0){
                        vehicle.amount--;
                        Alert info = new Alert(Alert.AlertType.INFORMATION);
                        info.setTitle("Potwierdzono rezerwację");
                        info.setHeaderText(null);
                        info.setContentText("Samochód został zarezerwowany");
                        info.showAndWait();
                    }else{
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("Błąd rezerwacji");
                        error.setHeaderText(null);
                        error.setContentText("Brak dostępnego samochodu do rezerwacji!");
                        error.showAndWait();
                    }
                });
            }
        });
    }

    void toolTip(TableView<Vehicle> tableView){
        tableView.setRowFactory(tv ->{
            TableRow<Vehicle> row = new TableRow<>();
            Tooltip tip = new Tooltip();
            row.setOnMouseEntered(event ->{
                Vehicle veh = row.getItem();
                if (veh != null){
                    tip.setText("Stan: " + veh.state + "\nPrzebieg: " + veh.mileage + "\nPojemność: " + veh.capacity + "\nDostępna ilość sztuk: " + veh.amount);
                    tip.show(row, event.getScreenX(), event.getScreenY());
                }
            });
            row.setOnMouseExited(event-> tip.hide());
            return row;
        });
    }

    void searchVehicles(){
        String selectedShowroom = showroomComboBox.getValue();
        String keyWord = searchTxt.getText().toLowerCase().trim();

        List<Vehicle> vehiclesToShow;

        if (selectedShowroom == null || selectedShowroom.equals("Dowolny")){
            vehiclesToShow = this.centrum.showrooms.values().stream()
                    .flatMap(salon -> salon.base.stream())
                    .filter(product -> product.getMarkS().toLowerCase().contains(keyWord))
                    .collect(Collectors.toList());
        }else{
            vehiclesToShow = this.centrum.showrooms.get(selectedShowroom).base.stream()
                    .filter(product -> product.getMarkS().toLowerCase().contains(keyWord))
                    .collect(Collectors.toList());
        }
        tableView.setItems(FXCollections.observableArrayList(vehiclesToShow));
        booking((TableColumn<Vehicle, String>) tableView.getColumns().get(tableView.getColumns().size() - 1));
    }


    void sampleDataForClass(){
        this.centrum = new CarShowroomContainer("Centrum1", "Miasto1");
        Vehicle veh1 = new Vehicle("Marka1", "BDWQ7392", ItemCondition.NEW, 21000, 2012, 50000, 2500, "Benzyna", 190, "A");
        Vehicle veh2 = new Vehicle("Marka1", "BDWQ7392", ItemCondition.NEW, 71000, 2010, 20000, 2000, "Diesel", 190, "A");
        Vehicle veh3 = new Vehicle("Marka2", "CERR3424", ItemCondition.USED, 31000, 2013, 10000, 2000, "Diesel", 190, "M");
        Vehicle veh4 = new Vehicle("Marka2", "CERR3424", ItemCondition.DAMAGED, 1000, 2002, 90000, 2700, "Benzyna", 180, "A");
        Vehicle veh5 = new Vehicle("Marka3", "JWKW1234", ItemCondition.NEW, 81000, 2023, 70000, 2200, "Benzyna", 150, "A");
        Vehicle veh6 = new Vehicle("Marka4", "JWJW4232", ItemCondition.DAMAGED, 2000, 2015, 10000, 2100, "Benzyna", 250, "M");
        Vehicle veh7 = new Vehicle("Marka5", "PEIWJ214", ItemCondition.NEW, 221000, 2020, 30000, 2100, "Benzyna", 210, "M");
        Vehicle veh8 = new Vehicle("Marka6", "IREU1245", ItemCondition.USED, 11000, 2020, 60000, 2700, "Benzyna", 200, "M");

        CarShowroom crsm1 = new CarShowroom("Salon1", 20, "Miejscowosc1");
        CarShowroom crsm2 = new CarShowroom("Salon2", 10, "Miejscowosc2");

        crsm2.addProduct(veh1);
        crsm2.addProduct(veh2);
        crsm2.addProduct(veh3);
        crsm2.addProduct(veh4);
        crsm1.addProduct(veh5);
        crsm1.addProduct(veh6);
        crsm1.addProduct(veh7);
        crsm1.addProduct(veh8);

        this.centrum.addCenter(crsm1.showroomName, crsm1);
        this.centrum.addCenter(crsm2.showroomName, crsm2);
    }

    public static void main(String[] args) {
        launch(args);
    }

    void addToFav(Vehicle veh){
        if(!favVehicles.contains(veh)){
            favVehicles.add(veh);
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Lista ulubionych");
            info.setHeaderText(null);
            info.setContentText("Dodano do ulubionych!");
            info.showAndWait();
        }
        showFavs();
    }

    void removeFromFav(Vehicle veh){
        if(favVehicles.contains(veh)){
            favVehicles.remove(veh);
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Lista ulubionych");
            info.setHeaderText(null);
            info.setContentText("Usunięto z ulubionych!");
            info.showAndWait();
        }
        showFavs();
    }

    void showFavs(){
        ListView<Vehicle> favListView = new ListView<>();
        favListView.setItems(FXCollections.observableArrayList(favVehicles));

        favListView.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(Vehicle item, boolean empt){
                super.updateItem(item, empt);
                if(empt || item == null){
                    setText(null);
                }else{
                    setText("Samochód: " + item.getMarkS() + ", Model: " + item.getModel() + ", Cena: " + item.price);
                }
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label("Samochody dodane do ulubionych"), favListView);

        Scene scene = new Scene(vbox, 400, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ulubione samochody");
        stage.show();
    }

    void readData(){
        this.centrum = Serialization.loadShowrooms("showroom.ser");
        this.favVehicles = Serialization.loadFavsVehs("favourite_vehicles.ser");
    }

    public boolean saveData(){
        return Serialization.saveShowrooms("showroom.ser", centrum) && Serialization.saveFavs("favourite_vehicles.ser", favVehicles);
    }
}
