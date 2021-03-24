# PrintDesk - Printer Setup System Remastered

## Overview

*Printer Setup System Remastered is a remaster of [PrinterSetupSystem](https://github.com/akshinmustafayev/PrinterSetupSystem) project originally written on PHP, to Java Web Application.*

Printer Setup System is a center that provides a single point of contact between users and printers. User can install required printers to his computer without technical support help. 

## Installation
System can be deployed both _Windows_ and _Linux_ Tomcat 8 server with MYSQL.

* Install Tomcat 8 and MYSQL on your server. 
* Download from release page or directly [PrinterSetupSystem.war](https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/releases/download/1.0.0/PrinterSetupSystem.war) file. 
* Rename it to _ROOT.war_ file. 
* Copy renamed WAR file to your Tomcat 8 installation _WEBAPPS_ folder
* Run http://yourserver:port/install, specify DB server ip (127.0.0.1 or localhost if mysql installed on the same server), login, password and press install button to create required tables
* Open http://yourserver:port/login to login into the system. Login: **admin** password: **admin**. Do not forget to change password for security reasons

## Fixing Issues
Database config file is located in: WEB-INF\classes\config.properties. To set manually database configuration change this file. To open install page, set: "db.configured=no". By default system creates "printersetupsystem" database and sets "db.configured=yes".

You can manually import database by loading WEB-INF\classes\printersetupsystem.sql file. To lock install page set "db.configured=yes" again. 


## Features:
* Home page where users can select branch first
* User can search branch on Home page
* Page where are shown list of printers of selected branch
* Ability to search a printer on a branch printers page
* Page where users can globally search for a Branch or a Printer
* Page with Manual, content of which you can edit via WYSIWYG editor in admin panel
* Admin panel which allows you to edit all system settings
* Possibility to edit DB connection config file without rebuilding an entire application
* Possibility to edit printer install script and its extension. (For example your ps1 script, or VBS script and so on)
* Ability to setup system database from system (http://yourserver:port/install link) at first setup
* Printer information page, where are shown following information
  * Name
  * Description
  * _Online_ badge if printer is online
  * IP
  * Printer location
  * Printer vendor
  * Printer page views count
  * Ability to send current print page link via email
  * Install button, which downloads your script to install printer
  * Printer type

## Features for future implementation:
- [ ] List of paper types which printer can print
- [ ] Create basic Admin Printer paper types page
- [ ] Create Reports page for statistic reports
- [x] Create common system tasks page:
- [x] Reset all printer views count
- [x] Clear all printers
- [x] Clear all branches
- [x] "Online" badge shows if printer is online

## Small fixes:
- [x] Fix all descriptions of the functions
- [x] Refactor Printer.jsp page logic

Images:

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/1.png" alt="PrintDesk">

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/2.png" alt="PrintDesk">

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/3.png" alt="PrintDesk">

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/4.png" alt="PrintDesk">

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/5.png" alt="PrintDesk">

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/6.png" alt="PrintDesk">

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/7.png" alt="PrintDesk">

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/8.png" alt="PrintDesk">

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/9.png" alt="PrintDesk">

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/10.png" alt="PrintDesk">

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/11.png" alt="PrintDesk">

<img src="https://github.com/akshinmustafayev/Printer-Setup-System-Remastered/blob/master/pssr/assets/images/12.png" alt="PrintDesk">
