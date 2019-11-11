# PowerThen e-Commerce Project
[![Build Status](https://github.com/twbs/bootstrap/workflows/Tests/badge.svg)](https://github.com/twbs/bootstrap/actions?workflow=Tests)[![Version](https://img.shields.io/badge/version-1.1.RELEASE-yellow)](https://github.com/hstdra/e-Commerce)
[![version](https://img.shields.io/badge/spring_boot-2.1.8.RELEASE-green.svg)](https://github.com/hstdra/e-Commerce)
> Powerthen - Hệ thống cung cấp linh kiện điện tử "hàng đầu Việt Nam"

## Table of contents
* [Documents](https://github.com/hstdra/e-Commerce/raw/master/docs/SRS_PowerThen.pdf)
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)

## General info
Provide system for management and selling product online

## Technologies
* Back-end
    * Java 8
    * Library
        * Spring Web
        * Spring Data JPA
        * Spring Security
        * Thymeleaf
        * Lombok
* Front-end
    * HTML5 - CSS3 -Javascript
    * Bootstrap - Fontawesome - Jquery
* Database: MySQL
* Cloud Server: AWS
* Third-party Interface: MOMO Payment

## Setup
Clone our github project and config like this:

./src/main/resources/application.properties
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
# OPTIONAL
hibernate.search.default.directory_provider=filesystem
hibernate.search.default.indexBase=/var/lucene/indexes
```
./src/main/resources/hibernate.properties 
```properties
##Configuration for development environment
DEV_MOMO_ENDPOINT=https://test-payment.momo.vn
DEV_ACCESS_KEY=key
DEV_PARTNER_CODE=key
DEV_SECRET_KEY=key

##Configuration for production environment
PROD_MOMO_ENDPOINT=https://payment.momo.vn
PROD_ACCESS_KEY=key
PROD_PARTNER_CODE=key
PROD_SECRET_KEY=key

##
PAY_GATE=/gw_payment/transactionProcessor
APP_IN_APP=/pay/app
PAY_POS=/pay/pos
PAY_QUERY_STATUS=/pay/query-status
PAY_REFUND=/pay/refund
PAY_CONFIRM=/pay/confirm
```

## Features
* Admin management
	* User management(admin + customer)
	    * View/Search/Filter
	    * Add user
	    * Edit user information
	    * Change user password
	* Category management
		* View/Search/Filter
	    * Add category
	    * Edit category
	        * Change category name
	        * Add field
	        * Change field name
	        * Delete field
	    * Delete category
	* Product management
		* View/Search/Filter
	    * Add product
        * Edit product 
        * Delete product
	* Storage management
	    * View/Search/Filter
	    * Import storage
	    * Export storage
	    * View import/export history
	* Order management
        * View/Search/Filter
        * Edit order information
        * Change order status
* Business web
    * Basic
        * Login
        * Logout
        * Signup
	* Shopping features
	    * Filter products
	        * By category
	        * By field-details
	        * By price
		* Sort products
			* Newest
			* Oldest
			* Most expensive
			* Cheapest
	    * Search products
	    * View product detail
	* Cart features(Need login)
	    * Add product to cart
	    * Change product quantity
	    * Delete product from cart
	* Order features(Need login)
		* Checkout
		    * COD
		    * MOMO
		* Management
		    * View history
		    * Cancel order 
		    * Repayment(MOMO)   
	

## To-do list:
Everything is done

## Status
Project is releasing

## Contact
Created by ABC team, contact us at hstdra@gmail.com
