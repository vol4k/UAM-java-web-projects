## Zadanie:

Stworzenie programu, który będzie zapisywał i odczytywał określony model danych.

Każda osoba musi obmyślić jakiego typu dane chce przechowywać w końcowej aplikacji (informacje o osobach, filmach, autach, piłkarzach, komputerach etc.) Należy zamodelować te dane poprzez określenie pól, tabel i zależności między nimi.

## Ocena:

- [x] 10% punktów: Dane muszą zawierać co najmniej 4 tabele, jenda relacja OneToOne i jedna relacja OneToMany (lub ManyToMany).

- [x] 25% punktów: Do danych należy stworzyć zapytania (najlepiej użyteczne w docelowym projekcie). Zapytań musi być co najmniej 3, ~~co najmniej jedno powinno być stworozne jako zapytanie @Query z parametrem~~. Każde zapytanie powinno być odrębną metodą w serwisie. Jedno z zapytań musi być stronicowane.

- [x] 25% punktów: Należy użyć w projekcie pola do zapisu daty - najlepiej ZonedDateTime lub JodaTime.

- [x] 20% punktów: Program powinien posiadać endpointy REST-owe do tworzenia, odczytu, edycji, usuwania obiektów z bazy danych,

- [x] 20% punktów: Aplikacja powinna wykorzystywać bazę danych nie zapisywaną w pamięci, czyli np. PostgreQSL, MyQSL, ORACLE itp.

Nie trzeba tworzyć żadnego menu do projektu, testowanie może odbywać się poprzez Postmana / Swagera lub poprzez napisanie testów w kodzie aplikacji.

Uwaga! Odczyt i zapis powinien się powodzić w przypadku podania częściowych danych (pewne pola puste) oraz niepowodzić w przypadku podania nieprawidłowych danych.
