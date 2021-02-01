# backend-client-product


docker run -d --name mariadb -eMARIADB_ROOT_PASSWORD=5fX7raV5WLN79kdh mariadb/server:latest

docker inspect mariadb | grep IPAddress

```
mvn clean package
docker build -t pb/client-product .
docker run -p 8080:8080 pb/client-product
```