## Zadanie:

Napisz program, który wczyta dane na temat stacji pomiarowych dostępne na stronie: https://powietrze.gios.gov.pl/pjp/content/api

Program powinien wyświetlać listę stacji pomiarowych dostępnych na

https://api.gios.gov.pl/pjp-api/rest/station/findAll
oraz pozwolić użytkownikowi (w menu tekstowym) wybrać stację i na podstawie jej ID wybrać informacje szczeółowe na temat pomiarów kierując zapytanie na

https://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/{stationId}
Wyniki pomiarowe mamy mieć możliwość zapisania w pliku PDF, JSON lub XML. Wykorzystaj dowolną bibliotekę Javy do tworzenia plików PDF. Postać pliku jest dowolna, ma on zawierać podane wyżej informacje, może zawierać też dodatkowe elementy.

Napisz testy do wyrzystywanych metod, skorzystaj obowiązkowo z zamockowania danych dla danych pobieranych z endpointów serwisów.

W programie wykorzystaj strumienie oraz co najmniej dwie struktury danych (set, map, list, stack, etc.).

## Ocena

- [x] Testy 20%
- [x] Strumienie 20%
- [x] Serializacja i Deserializacja JSON 20%
- [x] Biblioteka PDF 20%
- [x] Działanie zgodne ze specyfikacją 20%
