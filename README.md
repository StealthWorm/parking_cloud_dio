# parking_cloud_dio
Project for Java Studies

## Run database
docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine

## Start and Stop

### Stop Database
docker stop parking-db

### Start Database
docker start parking-db

### API Endpoints Test
[Link To Swagger Interface](https://dashboard.heroku.com/apps/parking-tps)
> user: user
> password? 12345
