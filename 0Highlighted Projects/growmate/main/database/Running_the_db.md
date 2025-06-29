### Dumps

Creating database dumps of the database inside the docker container:

```
docker exec <container_name>  pg_dump -U <db_user> -F t <database_name> > dump.sql
```

Note: We need to manually delete the lines on the dump.sql file until the \connect db line (around line 600). We also need the lines "COPY . ." after all the ALTER TABLE and CREATE SEQUENCE statements, as well as delete the last line. 

### Commands

Important commands inside the container:

In order to access the database:
```
psql -U postgres -d db
```

Checking the tables:
```
\dt
```
