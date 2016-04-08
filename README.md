# events-scheduler
[![Build Status](https://travis-ci.org/julesbond007/events-scheduler.svg?branch=master)](https://travis-ci.org/julesbond007/events-scheduler)
[![Coverage Status](https://coveralls.io/repos/github/julesbond007/events-scheduler/badge.svg?branch=master)](https://coveralls.io/github/julesbond007/events-scheduler?branch=master)

Scheduled service to fetch events from github repositories and publish them to slack using producer/consumer pattern.

![Settings Window](https://raw.githubusercontent.com/julesbond007/events-scheduler/master/docs/producer-consumer.png)

If an event fails to process, the application will send an email notification.

#Running
1. Update [config.properties](https://github.com/julesbond007/events-scheduler/blob/master/project-service/src/main/resources/config.properties) to have correct slack token/channel & github login/password (optional for rate control)
2. To build the project:
  <p>if gradle is installed do `gradle clean build`</p>
  <p>otherwise `chmod +x gradlew` then `./gradlew clean build`</p>
3. Deploy the project to tomcat or other server: 
  <p>`cp -r project-api/build/libs/project-api-1.0.war $TOMCAT_HOME/webapps/proj.war`</p>
4. Start the scheduler (delay and frequency are in minutes): 
  <p>`POST http://localhost:8080/proj/api/v1/scheduler/actions/start` request body: `{"delay":1, "frequency":5}`</p>
5. Stop the scheduler: 
  <p>`POST http://localhost:8080/proj/api/v1/scheduler/actions/stop`</p>
6. Get status of the scheduler
  <p>`GET http://localhost:8080/proj/api/v1/scheduler/status`</p>
