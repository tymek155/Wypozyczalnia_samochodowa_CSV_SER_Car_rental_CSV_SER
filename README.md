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
się z plików `AmountComparator.java` (odpowiada docelowo za sortowanie pojazdów względem ich ilości, 
malejąco, wykorzystuje `Comparator` do realizacji sortowania), `CarShowroom.java` (klasa 
reprezentująca pojedyńczy salon samochodowy, zawiera wszystkie infomracje na jego temat, znajduje 
się w niej lista pojazdów, funkcje związane z dodawaniem i usuwaniem pojazdów, operacje związane z 
sortowaniem po nazwie, cenie oraz ilości pojazdów oraz obliczanie bieżącej pojemności salonu), 
`CarShowroomContainer.java` (kontenert zarządzający wszytskimi salonami - przechwouje salony, dodaje 
i usuwa salony, wyszukuje salony oraz sortuje je po pojemności),`CarShowroomGUI.java` (klasa główna 
interfejsu graficznego, zawiera tablę z listą pojazdów, mechanizm wyszukiwania po marce, system 
ulubionych pojazdów, integrację z CSV oraz serializacją oraz opcje informacji w dymku o pojeździe), 
`ItemCondition.java` (klasa typu `enum` zawierająca możliwe stany pojazdów, dodatkowo zawiera w 
swojej strukturze metodę `toString` dla drukowania nazw po polsku), `Main.java` (główna klasa 
programu, uruchamiająca inicjalizację budowy GUI, zawiera także zakomentowane elementy 
(przykładowem, do testowania działania programu)), 
`PriceComparator.java` (odpowiada docelowo za sortowanie pojazdów względem ich ceny, rosnąco, 
wykorzystuje `Comparator` do realizacji sortowania), `Vehicle.java` (klas zawierająca dane na temat pojedynczego samochodu, korzysta z adnotacji do pól przez `TableColumnInfo`, `ExcludeFromExport.java` 
(mechanizm służacy do niestandardowej adnotacji, służacej do pomijania pól tymczasowych w 
eksporcie), `ExportCSV.java` (klasa służąca do eksportowania/importowania danych do/z pliku CSV, 
generowane są w niej nagłówki CSV za pomocą refleksji, oznaczone pola są pomijane, dodana została 
także obsługa typów prostych (do `ItemCondition`)), `Serialization.java` (klasa odpowiadająca za 
serializację oraz deserializację danych - dane są odczytywane i zapisywane wraz z listą ulubionych, 
stan samochodu jest ustawiany na nowy przy wczytywaniu), `TableColumnInfo.java` (służy do definicji metadanych, jako adnotacja dla pól w klasie `Vehicle` (konieczne w tabelach GUI oraz nagłówkach plików CSV przy eksporcie)).
