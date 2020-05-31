# Printer Setup System Remastered

## Overview

*Printer Setup System Remastered is a remaster of [PrinterSetupSystem](https://github.com/akshinmustafayev/PrinterSetupSystem) project originally written on PHP, to Java Web Application.*

Printer Setup System is a center that provides a single point of contact between users and printers. User can install required printers to his computer without technical support help. 

## Installation
System can be deployed both _Windows_ and _Linux_ Tomcat 8 server with MYSQL.

* Install Tomcat 8 and MYSQL to your server. 
* Download from release page or directly [PrinterSetupSystem.war](https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/tree/master/deploy) file. 
* Rename it to _ROOT.war_ file. 
* Copy renamed WAR file to your tomcat installation _WEBAPPS_ folder
* Run http://yourserver:port/install, specify DB server ip (127.0.0.1 or localhost if mysql installed on the same server), login, password and press install button to create required tables

## Tasks which should be done:
- [x] Create basic Home Page
- [x] Create basic Branch Printers Page
- [x] Create basic Printer Page
- [x] Create basic Manual Page
- [x] Create basic Search Page
- [x] Create basic Admin Login page
- [x] Create basic Admin Home page
- [x] Create basic Admin Branches page
- [x] Create basic Admin Printers page
- [x] Create basic Admin Create Printer page
- [x] Create basic Admin Administrators page
- [x] Create basic Admin Printer types
- [x] Create basic Admin Manual Page 
- [x] Create basic Install Script Page 
- [ ] Refactor code
- [ ] Implement additional features

## Known bugs:
- [ ] Send printer link via Email not working

## Features for future implementation:
- [ ] List of paper types which printer can print
- [ ] Create basic Admin Printer paper types page
- [ ] Create Reports page for statistic reports
- [ ] Create common system tasks page:

* Reset all printer views count
* Clear all printers
* Clear all branches

## Small fixes:
- [ ] Fix all descriptions of the functions
- [ ] Refactor Printer.jsp page logic

Images:
