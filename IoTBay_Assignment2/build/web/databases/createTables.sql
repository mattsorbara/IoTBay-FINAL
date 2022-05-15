/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  oliverguerreiro
 * Created: 09/05/2022
 */

CREATE TABLE USERS (
    userEmail VARCHAR(50) PRIMARY KEY NOT NULL,
    fullName VARCHAR(50),
    password VARCHAR(50),
    phone VARCHAR(50),
    userType VARCHAR(50),
    userActive BOOLEAN
);

CREATE TABLE LOGS (
    LogID int GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
    userEmail VARCHAR(50),
    Login TIMESTAMP,
    Logout TIMESTAMP,

    CONSTRAINT FK_userLog FOREIGN KEY (userEmail) REFERENCES USERS(userEmail)
);

CREATE TABLE PRODUCTS (
    productId INT GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
    productTitle VARCHAR(50),
    productPrice DECIMAL(5),
    productDescription VARCHAR(500),
    productImage VARCHAR(500),
    productStock INT,
    productType VARCHAR(20)
);

CREATE TABLE ORDERS (
    orderID INT GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
    userEmail VARCHAR(50) NOT NULL,
    productID INT NOT NULL,
    orderPrice DECIMAL(5) NOT NULL,
    orderQuantity INT NOT NULL,
    orderDate TIMESTAMP NOT NULL,
    orderStatus VARCHAR(50) NOT NULL,

    CONSTRAINT FK_userOrder FOREIGN KEY (userEmail) REFERENCES USERS(userEmail),
    CONSTRAINT FK_productOrder FOREIGN KEY (productID) REFERENCES PRODUCTS(productID)
);

CREATE TABLE PAYMENT (
    paymentID INT GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
    orderID INT NOT NULL,
    userEmail VARCHAR(50),
    paymentType VARCHAR(20),
    paymentAmount DECIMAL(5),
    cardNumber VARCHAR(16),
    cardCVC VARCHAR(3),
    cardExpiry VARCHAR(10),
    paymentDate TIMESTAMP,

    CONSTRAINT FK_paymentOrder FOREIGN KEY (orderID) REFERENCES ORDERS(orderID),
    CONSTRAINT FK_paymentUser FOREIGN KEY (userEmail) REFERENCES USERS(userEmail)
    
);

CREATE TABLE SAVEDPAYMENT (
    userEmail VARCHAR(50) PRIMARY KEY NOT NULL,
    cardNumber VARCHAR(16) NOT NULL,
    cardCVC VARCHAR(3) NOT NULL,
    cardExpiry VARCHAR(10),

    CONSTRAINT FK_userEmail FOREIGN KEY (userEmail) REFERENCES USERS(userEmail)
);

CREATE TABLE SHIPMENT ( 
    shipmentID INT GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
    orderID INT NOT NULL,
    userEmail VARCHAR(50) NOT NULL,
    status VARCHAR (50) NOT NULL,
    shipmethod VARCHAR (50),
    shipDate TIMESTAMP, 
    unitno INT,
    streetno VARCHAR (4),
    streetname VARCHAR (50),
    suburb VARCHAR (50),
    postcode INT,

    CONSTRAINT FK_orderShipment FOREIGN KEY (orderID) REFERENCES ORDERS(orderID),
    CONSTRAINT FK_userShipment FOREIGN KEY (userEmail) REFERENCES USERS(userEmail)
);

CREATE TABLE SAVEDSHIPMENT ( 
    userEmail VARCHAR(50) NOT NULL PRIMARY KEY,
    shipMethod VARCHAR (50),
    shipDate TIMESTAMP, 
    unitno INT,
    streetno VARCHAR (4),
    streetname VARCHAR (50),
    suburb VARCHAR (50),
    postcode INT,

    CONSTRAINT FK_userSavedShipment FOREIGN KEY (userEmail) REFERENCES USERS(userEmail)
);
