# CTE Issue showcase
This repository showcases an issue with Blaze Persistence and H2 when using multiple CTE.

**Stackoverflow Question**: https://stackoverflow.com/questions/73987553/using-multiple-cte-with-blaze-persistence-and-h2 

Furthermore, inlining the query does not work as well. However, this might be my mistake...

## H2
The test class `com.github.jensgerdes.cteissue.H2Test` contains two test cases
that both fail:

a) a query with multiple CTE  
b) a query with multiple CTE inlined

## PostgreSQL
The test class `com.github.jensgerdes.cteissue.PostgreSQL` contains two test cases.
Only the inline example fails.

a) a query with multiple CTE  
b) a query with multiple CTE inlined

The PostgreSQL test expects a running PostgreSQL instance on `localhost:5432`
with the credentials defined in `application-postgres.yml`.

If you've got docker, you can use the `start_postgres.sh` script to bootstrap the database.

## Schema initialization
The schema is being initialized via:

* `data.sql`
* `schema.sql`

