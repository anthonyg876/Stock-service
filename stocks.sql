
grant select, insert, update, delete on users to knighthawk;
grant select, insert, update, delete on users to "jesse.maki";
grant select, insert, update, delete on users to woodsnib;

grant select, insert, update, delete on account to knighthawk;
grant select, insert, update, delete on account to jesse.maki;
grant select, insert, update, delete on account to woodsnib;

grant select, insert, update, delete on stock to knighthawk;
grant select, insert, update, delete on stock to jesse.maki;
grant select, insert, update, delete on stock to woodsnib;

grant select, insert, update, delete on stockIndex to knighthawk;
grant select, insert, update, delete on stockIndex to jesse.maki;
grant select, insert, update, delete on stockIndex to woodsnib;

grant select, insert, update, delete on belongs_to to knighthawk;
grant select, insert, update, delete on belongs_to jesse.maki;
grant select, insert, update, delete on belongs_to to woodsnib;

grant select, insert, update, delete on stockPrices to knighthawk;
grant select, insert, update, delete on stockPrices to jesse.maki;
grant select, insert, update, delete on stockPrices to woodsnib;

-- Deletion of the tables, with foreign keys, needs the cascade constraints.
drop table StockIndex cascade constraints;
drop table Stock cascade constraints;
drop table belongs_to cascade constraints;
drop table account cascade constraints;
drop table stockprices cascade constraints;
drop table users cascade constraints;

create table users
(email varchar(30),
firstname varchar(20) not null,
lastName varchar(20) not null,
primary key(email));

create table account(
id int, 
userName varchar(20) not null unique, 
password varchar(30) not null,
held_by varchar(30),
primary key(id),
foreign key(held_by) references users(email));

create table stock
(symbol varchar(10),
fullName varchar(350) unique not null,
inDJGT varchar(10),
inDJI varchar(10),
inNDX varchar(10),
primary key(symbol));

create table StockIndex(
id varchar(30),
numberOfStocks int not null,
name varchar(30) not null,
primary key(id));

create table belongs_to(
symbol varchar(5) not null,
id int not null,
primary key(symbol, id),
foreign key(symbol) references stock(symbol),
foreign key(id) references account(id));

create table stockPrices(
dateOfPrice date not null, 
open float not null, 
high float not null,
low float not null, 
adjClosed float not null,
volume int not null, 
companyID varchar(5) not null, 
foreign key(companyID) references stock(symbol));

-- View tables
select * from users;

select * from account;

select * from stock;
select * from stock
    order by symbol asc
    fetch first 100 rows only;
drop table stock cascade constraints;

select count(*) from stock;
select * from stock where inndx = 'NDX';
select * from stock where inDJI = 'DJI';
select * from stock where inDJGT = 'DJGT';


select * from stockIndex;

select * from belongs_to;

select * from stockPrices where companyID = 'FTAI';
drop table stockPrices;

select count(*) from stockPrices;

select * from account where id = 1;

select * from users;


