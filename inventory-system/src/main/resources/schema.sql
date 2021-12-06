drop table if exists asset;
drop table if exists account;
drop table if exists role;
drop table if exists asset_definition;
drop table if exists category;

CREATE TABLE role (
  id VARCHAR(128) NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE account (
  id VARCHAR(128) NOT NULL,
  full_name VARCHAR(255) ,
  username VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  role_id VARCHAR(128) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE category (
  id   VARCHAR(128) NOT NULL,
  name VARCHAR(128) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE asset_definition (
  id   VARCHAR(128) NOT NULL,
  model VARCHAR(255) NOT NULL,
  manufacturer VARCHAR(255),
  category_id VARCHAR(128) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE asset (
  id VARCHAR(128) NOT NULL,
  serial_number VARCHAR(255) NOT NULL UNIQUE,
  status VARCHAR(255),
  account_id VARCHAR(128),
  asset_definition_id VARCHAR(128) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  assigned_at TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES account(id),
  FOREIGN KEY (asset_definition_id) REFERENCES asset_definition(id)
);