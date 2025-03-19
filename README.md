Go to [English version](#english-version)
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

## Przykładowa wizualizacja startowego widoku aplikacji - lista samochodów posiadanych przez dowolne salony
<div align="center">
<img width="952" alt="image" src="https://github.com/user-attachments/assets/ef47d078-ea87-42eb-9bdb-784ace27d9f6" />  
</div>

## Widok listy pojazdów dodanych do ulubionych (program przy uruchomieniu za każdym razem wczytuje dane z plików)
<div align="center">
<img width="957" alt="image" src="https://github.com/user-attachments/assets/2b0cb4d5-743e-413d-bdba-6bdb8e9d6ba4" />  
</div>

## Przykład użycia wyszukiwarki samochodów 
<div align="center">
<img width="837" alt="image" src="https://github.com/user-attachments/assets/d95855a8-7ba9-40df-876d-201e7b394116" />  
</div>

## Widok samochodów posiadanych przez wybrany salon
<div align="center">
<img width="946" alt="image" src="https://github.com/user-attachments/assets/ac77ac7a-708f-4466-9faa-d9d9ee52451a" />  
</div>

## Przykładowe użycie dymku z informacjami o konkretnym samochodzie
<div align="center">
<img width="950" alt="image" src="https://github.com/user-attachments/assets/6b251a0c-c903-4bd3-9eeb-5bfd9848b878" />  
</div>

## Przykład użycia rezerwacji wybranego samochodu
<div align="center">
<img width="950" alt="image" src="https://github.com/user-attachments/assets/1d2947ab-4a15-4afd-9ac7-96b3bf305d33" />  
</div>

## Widok po wyłączeniu aplikacji, wszystkie dane są zapisywane za pomocą serializacji
<div align="center">
<img width="946" alt="image" src="https://github.com/user-attachments/assets/00e13bd0-8281-4aca-8720-3486614d1796" />  
</div>

# English version

# General Information 
The project implements an application for managing car dealerships/rental 
services, including the management of cars within them, a graphical user 
interface (GUI), CRUD operations, saving via serialization/CSV file, and advanced 
filtering and sorting capabilities. The system displays information about 
available cars in a specific dealership or any dealership, with the possibility 
of filtering/sorting the retrieved results. Interactive buttons have also been 
designed for reserving a car or adding it to a favorites list, as well as the 
option for manual serialization/saving to CSV. The application automatically 
saves on exit through serialization. The entire system is visualized using JavaFX.

# Technologies  
The code uses:  
* Java 17
* JavaFX 19
* mechanism for saving to CSV/saving via serialization

# Usage 
The code was written and executed in the IntelliJ IDEA environment. The project 
structure consists of the following files `AmountComparator.java` (responsible 
for sorting vehicles by quantity in descending order, using `Comparator` for 
sorting implementation), `CarShowroom.java` (a class representing a single car 
dealership, containing all information about it, including a list of vehicles, 
functions for adding and removing vehicles, sorting operations by name, price, 
and quantity, as well as calculating the current capacity of the dealership), 
`CarShowroomContainer.java` (a container managing all dealerships – it stores, 
adds, and removes dealerships, searches for them, and sorts them by capacity), 
`CarShowroomGUI.java` (the main GUI class, containing a table with a list of 
vehicles, a search mechanism by brand, a favorite vehicle system, CSV and 
serialization integration, and a tooltip option with vehicle information), 
`ItemCondition.java` (an `enum` class containing possible vehicle conditions, 
with an additional `toString` method for printing names in Polish), `Main.java` 
(the main program class, initializing the GUI construction, also containing 
commented-out elements (examples for testing the program)), 
`PriceComparator.java` (responsible for sorting vehicles by price in ascending 
order, using `Comparator` for sorting implementation), `Vehicle.java` (a class 
containing data about a single vehicle, using field annotations via 
`TableColumnInfo`), `ExcludeFromExport.java` (a mechanism for custom annotation 
used to exclude temporary fields from export), `ExportCSV.java` (a class for 
exporting/importing data to/from a CSV file, where CSV headers are generated 
using reflection, marked fields are excluded, and primitive types (including 
`ItemCondition`) are handled), `Serialization.java` (a class responsible for 
data serialization and deserialization – data is read and saved along with the 
favorites list, and vehicle condition is set to "new" upon loading), 
`TableColumnInfo.java` (used for defining metadata as an annotation for fields 
in the `Vehicle` class, necessary for GUI tables and CSV file headers during 
export).

## Sample visualization of the application's starting view - a list of cars owned by any showrooms
<div align="center">
<img width="952" alt="image" src="https://github.com/user-attachments/assets/ef47d078-ea87-42eb-9bdb-784ace27d9f6" />  
</div>

## View of the list of vehicles added to favorites (the program loads data from files each time it is started)
<div align="center">
<img width="957" alt="image" src="https://github.com/user-attachments/assets/2b0cb4d5-743e-413d-bdba-6bdb8e9d6ba4" />  
</div>

## Example of using the car search engine
<div align="center">
<img width="837" alt="image" src="https://github.com/user-attachments/assets/d95855a8-7ba9-40df-876d-201e7b394116" />  
</div>

## View of cars owned by the selected showroom
<div align="center">
<img width="946" alt="image" src="https://github.com/user-attachments/assets/ac77ac7a-708f-4466-9faa-d9d9ee52451a" />  
</div>

## Example use of a bubble with information about a specific car
<div align="center">
<img width="950" alt="image" src="https://github.com/user-attachments/assets/6b251a0c-c903-4bd3-9eeb-5bfd9848b878" />  
</div>

## Example of using the reservation of the selected car
<div align="center">
<img width="950" alt="image" src="https://github.com/user-attachments/assets/1d2947ab-4a15-4afd-9ac7-96b3bf305d33" />  
</div>

## View after closing the application, all data is saved using serialization
<div align="center">
<img width="946" alt="image" src="https://github.com/user-attachments/assets/00e13bd0-8281-4aca-8720-3486614d1796" />  
</div>
