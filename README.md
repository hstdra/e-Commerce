# e-Commerce Project
> Powerthen - Hệ thống cung cấp linh kiện điện tử "hàng đầu Việt Nam"

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)

## General info
Provide system for management and selling product online

## Technologies
* Spring Boot Framework
* Web
* Hibernate and JPA
* Thymeleaf

## Setup
Clone our github project and config like this:

./src/main/resources/applicaiton.properties
```properties
spring.datasource.url=<url>
spring.datasource.username=<username>
spring.datasource.password=<password>

# OPTIONAL
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.generate-ddl=true
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
```
./src/main/resources/hibernate.properties 
```properties
hibernate.search.default.directory_provider=filesystem
hibernate.search.default.indexBase=/var/lucene/indexes
```

## Features
* Admin managment
	* Customer management
	* Storage history management
	* Category and Field manegement
	* Product management
	* Order management
* Business web
	* View all products
	* Shopping feature
	* Checkout order
		* COD
		* Online payment with MOMO
	* Some manage feature for customer

## To-do list:
* Admin managment
	* User and Product management (HIU)
	* Storage history management (POM)
	* Order manegement (INSIDE)
	
* Business Web
	* View one product (HST)
	* Shop cart (HST)
	* Check out (hST)
## Status
Project is inprogress

## Contact
Created by ABC team, contact us at hstdra@gmail.com
