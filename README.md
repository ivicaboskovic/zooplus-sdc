# zooplus-sdc
ZooPlus Senior Developer Challange

Simple application for currency exchange.
Supported couple of currencies from challange request document.

In order to run it localy

1. download project from github
2. build project using maven 3 and command:

  mvn clean install -P local

3. Run application using maven 3 and command
  mvn tomcat7:run -P local
  
4. Access application with url : http://localhost:8080/zooplus-sdc/

Application is using in memory HSQL database with default un/pwd for application access test.user@zooplus.com/12

CI builds on :  https://travis-ci.org/ivicaboskovic/zooplus-sdc/builds
Test installation on  : http://130.211.101.32/zooplus

TODO:
1. Implement monitoring and management interface (JMX, REST, etc..)
2. Cache external request with configurable TTL
3. Implement REST API for conversion service and history queries
4. Check dependancies that not needed

For additional questions/discussions  ivica.boskovic@gmail.com or skype:bole_zupa
2.
3.
