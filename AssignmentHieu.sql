CREATE DATABASE MilkTeaManagementHieu

USE MilkTeaManagementHieu
GO

CREATE TABLE tblRoles
(
  roleID VARCHAR(10) NOT NULL,
  roleName NVARCHAR(50) NOT NULL,
  PRIMARY KEY (roleID)
);
ALTER TABLE tblUser
ADD address VARCHAR(25)
ALTER TABLE tblUser
DROP COLUMN Address
CREATE TABLE tblCategory
(
  categoryID VARCHAR(10) NOT NULL,
  name NVARCHAR(50) NOT NULL,
  PRIMARY KEY (categoryID)
);


CREATE TABLE tblUser
(
  userID VARCHAR(250) NOT NULL,
  Name NVARCHAR(50) NOT NULL,
  Email VARCHAR(50) NOT NULL,
  [Address] VARCHAR(25) NOT NULL,
  roleID VARCHAR(10) NOT NULL,
  PRIMARY KEY (userID),
  FOREIGN KEY (roleID) REFERENCES tblRoles(roleID)
);
DROP TABLE tblOrder;
CREATE TABLE tblOrder
(
  orderID INT IDENTITY(0,1) NOT NULL,
  userID VARCHAR(250) NOT NULL,
  [date] DATE NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (orderID),
  FOREIGN KEY (userID) REFERENCES tblUser(userID)
);

CREATE TABLE tblProduct
(
  productID VARCHAR(10) NOT NULL,
  Name NVARCHAR(50) NOT NULL,
  price INT NOT NULL,
  quantity INT NOT NULL,
  categoryID VARCHAR(10) NOT NULL,
  PRIMARY KEY (productID),
  FOREIGN KEY (categoryID) REFERENCES tblCategory(categoryID)
);
DROP TABLE tblOrderDetail;
CREATE TABLE tblOrderDetail
(
  detailID INT IDENTITY(0,1) NOT NULL,
  orderID INT NOT NULL,
  productID VARCHAR(10) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY(detailID),
  FOREIGN KEY (orderID) REFERENCES tblOrder(orderID),
  FOREIGN KEY (productID) REFERENCES tblProduct(productID)
);