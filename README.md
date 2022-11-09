# parking_cloud_dio
Project for Java Studies

In this project I had the challenge of developing a set of APIs using **Spring Boot** to control a parking lot. The entry, exit and amount to be charged from the customer will be controlled. All API development best practices were applied, including security with **Spring Security** and access to **PostgreSQL** databases. Tests and test coverage reports were performed. After completion, the app was deployed to the Heroku cloud in order to make our API available to the Internet.

You can access the Swagger Endpoints using the url below, after execute the main project
> http://localhost:8080/swagger-ui/index.html
> 
> user: user <br>
> password: 12345

## Run database, create Docker Image for testeng local database
docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine

## Start and Stop

### Stop Database
docker stop parking-db

### Start Database
docker start parking-db

### API Endpoints Test
[Link To Swagger Interface](https://dashboard.heroku.com/apps/parking-tps)

