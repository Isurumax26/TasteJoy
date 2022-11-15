# TasteJoy Application

## Main features of the application

This is a small application which allows user to order pizzas and drinks.

All the order details are stored in the database

Admin of an application can view the prevailing orders and can set order status as approved or not.

Users need to sign in to to the application before they put any order.

Clients are assigned a role as ROLE_USER and employees are assigned a ROLE_ADMIN

Users can edit their personel information if there is any change occured.

User detailes are stored in the database with encryption techniques to store secure fields.

## Available end points

These are some available endpoints in this application

### GET http://localhost:8083/login

login to the application

### GET http://localhost:8083/pizzas

Details of all the available pizzas

### GET http://localhost:8083/drinks

Details of all the avialble drinks

### GET http://localhost:8083/orders

Details of all the orders

### GET http://localhost:8083/users

Details of all the users

### GET http://localhost:8083/users/:username

Details of the logged in user

### GET http://localhost:8083/orders/create

Created a new order
Required - ID of the product



### POST http://localhost:8083/pizzas

Admin users can add new pizzas.

### POST http://localhost:8083/drinks

Admin users can add new drinks.

### POST http://localhost:8083/users

Create a new user. 

### DELETE http://localhost:8083/users/delete/:username

delete a user


**Tech Stack**

Spring boot

Thymleaf

mysql

jQuery








