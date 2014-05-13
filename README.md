Gumtree Adressbook – Development Guide
===================

## Introduction
The purpose of this application is to read structured data in provided AddressBook file and anwser following questions:

1. How many males are in the address book?  
2. Who is the oldest person in the address book?  
3. How many days older is Bill than Paul?  

## Structure
- build.gradle – gradle configuration for building application 
- src – all source code  
- doc – all available documentations 
- data – structured (real and test) data read by application 

## Preparation
To build application gradle must be present beforehand. To build source type in command line:
>gradle build

## Usage
To launch application use following command and follow instructions on screen:
>gradle run

## Testing
To run available tests type in command line:
>gradle test

There are 2 types of testing classes:
- access – for reading data from data files 
- controller – for anwsering required questions 

Tests report can be found in directory build/reports/tests/index.html

## Configuration
All configuration files are located in src/main/resources and src/test/resources.

- access.properties – defines mapping between entity name and file name 
- config.properties – specifies date format and path to data files 
- strings.properties – stored text shown to user that could be localized   
- log4j2.xml – default configuration file for log4j 2 library 

## Libraries       
Besides Gradle for dependency management and Java pacakges, following external libraries were used:

- Joda-time – for parsing, formating and doing date related operations 
- Google Guava – mainly for operations related matching data in collections 
- Log4j 2 – provides error logging capability 
