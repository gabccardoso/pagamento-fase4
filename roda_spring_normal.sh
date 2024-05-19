#!/usr/bin/env bash

mvn clean install -DskipTests

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:1239 -Djdk.httpclient.HttpClient.log=requests"
