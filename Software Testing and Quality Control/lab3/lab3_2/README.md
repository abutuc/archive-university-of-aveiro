**To run the Integration Test with the MySQL instance run the following command:**
```
docker run --name mysql5tqs -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=tqs -e MYSQL_USER=root -e MYSQL_PASSWORD=root -p 33060:3306 -d mysql/mysql-server:5.7
```
