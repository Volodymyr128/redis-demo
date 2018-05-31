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