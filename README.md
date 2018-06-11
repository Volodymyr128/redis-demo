To build redis-demo image:

To run everything:
1. Build docker image if absent
   docker build . -t 'redis-demo:1.0'
2. Run everything
   docker-compose up
3. Create remote configuration in Intellij https://medium.com/@lhartikk/development-environment-in-spring-boot-with-docker-734ad6c50b34
   - ensure excludeDevtools configuration at build plugin
   - create configuration as here https://stackoverflow.com/questions/40091700/how-to-auto-reload-spring-boot-application-in-docker-using-spring-dev-tools
4. Run/Debug remote configuration
5. Test with 'curl -X GET localhost:8080/api/test'
6. Change code and rebuild project
7. Test with 'curl -X GET localhost:8080/api/test', docker container have to respond with new logic

Resources:
1. Redis datatypes https://redis.io/topics/data-types-intro
2. Spring Data Redis repositories tutorial http://www.baeldung.com/spring-data-redis-tutorial

To debug create remote configuration on 8000 port

To connect redis do:
 1. 'docker container ls' do get id of redis container
 2. 'docker exec -i -t <redis_container_id> redis-cli'

 #todo Redis + PostgreSQL: both as cache and as datasync

 Multi-dimensional index:
 Suppose you have all point from 0 to 100. Search ZRANGEBYLEX from = 00111100000000 to = 00111100111111.
 You substituted last 6 symbols. You will get rectangle with side length = 2^(6/2) - 1 = 2^3 - 1 = 7.
 The left bottom point of your rectangle will be value of 'from' = 00111100000000 = 48.

 1. curl -X POST localhost:8080/api/location/init
 2. curl -X GET localhost:8080/api/location/00111100000000/00111100111111/download -o ~/Desktop/result.csv
 3. Open ~/Desktop/result.csv at Microsoft Excel
 4. Select first two columns and Insert->Suggested Graphs...
