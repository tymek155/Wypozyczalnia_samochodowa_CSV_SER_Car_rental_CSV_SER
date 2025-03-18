# Ogólne informacje
Projekt realizuje implementację aplikacji do zarządzania salonami/wypożyczalniami samochodowymi, wraz z zarządzaniem 
znajdujących się w nich samochodami, graficznym interfejsem użytkownika GUI, operacjami CRUD, zapisem za pomocą 
serializacji/pliku CSV oraz z zaawansowanymi możliwościami filtrowania oraz sortowania. System wyświetla informacje 
o dostępnych samochodach w danym salonie lub dowolnym salonie, możliwa jest filtracja/sortowanie otrzymanych wyników, 
zaprojetkowane zostały także interaktywne przyciski do zarezerowowania samochodu lub dodania go do listy ulubionych, 
możliwość ręcznej serializacji/zapisu do CSV, automatyczny zapis aplikacji po zamknięciu przez serializację. Całość 
zwizualizowana jest za pomocą JavaFX.

# Technologie
W kodzie użyto:
* Java 17
* JavaFX 19
* mechanizm zapisu do CSV/zapisu przez serializację
	
# Wykorzystanie
Kod był uruchamiany i napisany w środowisku IntelliJ IDEA. Struktura projektu składa 
się z plików `AmountComparator.java`, `CarShowroom.java`, `CarShowroomContainer.java` (kontenert zarządzający 
wszytskimi salonami - przechwouje salony, dodaje i usuwa salony, wyszukuje salony oraz sortuje je po pojemności),
`CarShowroomGUI.java` (klasa główna interfejsu graficznego, zawiera tablę z listą pojazdów, mechanizm wyszukiwania 
po marce, system ulubionych pojazdów, integrację z CSV oraz serializacją oraz opcje informacji w dymku o pojeździe), 
`ItemCondition.java`, `Main.java`, `PriceComparator.java`, `Vehicle.java`, `ExcludeFromExport.java`, `ExportCSV.java`, 
`Serialization.java` (klasa odpowiadająca za serializację oraz deserializację danych - dane są odczytywane 
i zapisywane wraz z listą ulubionych, stan samochodu jest ustawiany na nowy przy wczytywaniu), 
`TableColumnInfo.java`
