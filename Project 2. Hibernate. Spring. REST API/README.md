## Task:

Create a program that will save and read a specific data model.

Each person must decide what type of data they want to store in the final application (information about people, movies, cars, football players, computers, etc.). You need to model this data by specifying fields, tables, and relationships between them.

## Evaluation:

- [x] 10% of points: The data must include at least 4 tables, one OneToOne relationship, and one OneToMany (or ManyToMany) relationship.

- [x] 25% of points: Queries should be created for the data (preferably useful in the final project). There must be at least 3 queries, ~~at least one should be created as a @Query with a parameter~~. Each query should be a separate method in the service. One of the queries must be paginated.

- [x] 25% of points: You should use fields to store dates - preferably ZonedDateTime or JodaTime.

- [x] 20% of points: The program should have REST endpoints for creating, reading, editing, and deleting objects from the database.

- [x] 20% of points: The application should use a database not stored in memory, such as PostgreQSL, MySQL, ORACLE, etc.

There is no need to create any menu for the project; testing can be done through Postman / Swagger or by writing tests in the application code.

Note! Reading and writing should succeed when partial data is provided (some fields empty) and fail when invalid data is provided.
