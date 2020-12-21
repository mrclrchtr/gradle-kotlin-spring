# Gradle, Kotlin, Spring Skeleton [![Build Status](https://travis-ci.com/mrclrchtr/gradle-kotlin-spring.svg?branch=master)](https://travis-ci.com/mrclrchtr/gradle-kotlin-spring)
This is a multi-project / multi-module skeleton with the following details:

- Gradle 6.7.1 (Kotlin DSL)
- Kotlin 1.3.71
 - Spring 2.2.6.RELEASE
 - Spring Dependency-Management 
 
 ## Run it!
 The factory:
   
    gradle :message-factory:bootRun
 
 The Dashboard:
    
    gradle :message-dashboard:bootRun
    
 And now visit http://localhost:8080/dashboard/Mr.Bean
 
 You can enter every name in place of "Mr.Bean". 
