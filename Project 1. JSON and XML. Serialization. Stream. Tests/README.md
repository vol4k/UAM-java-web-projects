## Task:

Write a program that will fetch data about measurement stations available on the website: https://powietrze.gios.gov.pl/pjp/content/api

The program should display a list of measurement stations available at

https://api.gios.gov.pl/pjp-api/rest/station/findAll
and allow the user (through a text menu) to select a station and based on its ID, retrieve detailed information about the measurements by querying

https://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/{stationId}
Measurement results should be able to be saved in a PDF, JSON, or XML file. Use any Java library for creating PDF files. The format of the file is arbitrary; it should contain the information mentioned above and can also include additional elements.

Write tests for the used methods, making obligatory use of data mocking for data retrieved from the endpoints of the services.

In the program, use streams and at least two data structures (set, map, list, stack, etc.).

## Evaluation

- [x] Tests 20%
- [x] Streams 20%
- [x] JSON Serialization and Deserialization 20%
- [x] PDF Library 20%
- [x] Compliance with Specification 20%
