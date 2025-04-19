vmulps DEST, SRC1, SRC2
vfmadd132ps ymm0, ymm1, ymm2  ; ymm0 = (ymm0*ymm2)+ymm1
vpmulld
vmulpd
select distinct country from customers;
select distinct country,city from customers;
select count(distinct country)from customers;
select count(*) from distinctcountries from
                (select distinct country from  customers);
select * from customers where country ='Mexico';
select * from customers where customerid > 80;
select * from products order by price;
select city, price from products order by city, price asc;
select city, price from products order by city, price desc;
select * from products order by price desc;
select * from products order by productname;
select * from customers order by country asc, customername desc;
select * from customers where country = 'spain' and customername like '%H%';
select * from customers where county = 'brazil'and customerid > 50;
select * from customers where country ='spain' or customername like 'G%' or customername like 'R%';
select * from customers where country ='germany' or country = 'spain'
select * from customers where NOT country = 'spain'
select * from customers where customername not like 'A%'
select * from customers where customerid not between  10 and 60;
select * from customers where city not in ('paris', 'london');
select * from customers where not customerid > 50;
select * from customers where not customerid < 50;
insert into table_name values(value1);
insert into table_name(column1) values (value1);
insert into tablename values(value1),(value2),(value3);
select customername from customers where address is null;
select customername from customers where address is not null;
update tablename set column1=value1 where condition;
update tablename set contactname='Juan';
delete from customers;
drop table customers;
select top 3 from customers;
select columnname from customers where condition limit 3;
select columnname from customers where ROWNUM<= number;
select columnname from tablename order by columnnname fetch first number ROWS ONLY;
select * from (select columnname from tablename order by columnname)
                 where ROWNNUM <=number;
select top 50 percent * from customers;
select * from customers fetch first 50 percent rows only;
select min(price) from products;
select max(price) from products;
select min(price) as smallestprice from products group by categoryid;
select count(productid) from products where productid >30;
select count(disctinct price) from products;
select count(*) as numberofrecords,categoryid from customers group by categoryid;
select sum(quantity) from orderdetails;
select sum(quantity) as total from orderdetails;
select orderid,sum(quantity) as totalquantity from orderdetails group by orderid;
select sum(price*quantity) from orderdetails left join products on orderdetails.producid=products.productid;
select avg(price) from products;
select avg(price ) from products where categoryid =1;
select avg(price) as averageprice from products;
select * from products where price> (select avg(price) from products);
select avg(price ) as averageprice, categoryid from products group by categoryid;
select * from customers where customername like 'a%';
select * from customers where city like 'L_nd__';
select * from customers where customername like 'a__%';
select * from customers where customername like '_r%';
select * from customers where customername like '[bsp]%';
select * from customer where customername like '[a-f]%';
select * from customers where customername like 'a__%';
select * from customers where customername like '_r%';
select * from customers where country not in ('a','b');
select * from customers where customerid in ( select customerid from orders);
select * from customers where customerid not in (select customerid from orders);
select * from products where price between 10 and 20;
select * from products where price not between 10 and 20;
select * from products where price between 10 and 20 and categoryid in (1,2,3);
select * from products where productname between 'ab' and 'xy' order by productname;
select * from orders where orderdate between #07/01/1996# and #07/31/1996#
select * from orders where orderdate between '1996-01-01' and '1996-07-01'
select customerid as id from customers;
select customer id from customers;
select columnname as aliasname from tablename;
select customerid as id , customername as customer from customers;
select productname as somename from products;
select columnname, columnname2 + ' '+ columnaname3+ ' '+ columnname3 from customers;
select columnanme,  concat(address, ' ',phonetype) from customers;
select columnname, (addressname|| ','|| columnname2) from customers;
select * from customers as persons;
select productid , productname, categoryname from products where products inner join categories
                                                           on products.categoryid =
    categories.categoryid;
select Products.product from products join categories on products.categoryid= categories.productid;
select orders.orderid ,customers.customerid from ((
    orders inner join customers on order.customerid = customers.customerid
    ) inner join shippers on orders.shipperid= shippers.shipperid);
select columnname from tablename left join tablename2 on table1.columnname = table2.columnname;
select customers.customername, orders.orderid from customers left join orders on customers.customerid = orders.customerid
order by customers.customername;
select orders.orderid from orders right join employees on orders.employeeid = employees.employeeid
order by orders.orderid;
select customers.customername, orders.orderid from customers full outer join orders on
    customers.customerid= orders.customerid order by customers.customername;
select a.customername as customername1 from customers a , customers b where a.customerid<>
                                                                            b.customerid and a.city =b.city
order by a.city;
select columnname from table1 union all select columnname from table2;
select columnaname from table1 union select columnname from table2;
select city from customers union select city from suppliers order by city;
select city from customers union all select city from suppliers order by city;
select city, country from customers where country ='Germany' union select city, country from
suppliers where country = 'Germany' order by city;
select count(customerid), country from customers group by country;
select count(customerid), country from customers group by county orderby count(customerid) desc;
select shippers.shippername, count(orders.orderid) as numberoforders from orders left join shippers on
    orders.shipperid = shipper.shipperid group by shippername;
select count(customerid), country from customers group by country having count(customerid)>5;
select count(customerid), country from customers group by
 country having count(customerid) > 5 order by count(
customerid )> 5 order by count(customerid) desc;
select employees.lastname, count(orders.orderid )as
numberoforders from (orders inner join employees on
    orders.employeeid = employees.employeeid) where
lastname ='Davilio' or lastname ='fuller' group by lastname
having count(orders.orderid)> 25;
select employees.lastname , count(orders.orderid ) as numberoforders
from orders inner join employees on orders.employeeid =
                                    employees.employeeid
where lastname ='Davilio' or firstname 'Sam' group by
        lastname having count(orders.orderid) > 25;
select columnname from tablename where exists (select columnname
from tablename where condition);
select suppliername from suppliers where exists (select productname
from products where products.supplierid = suppliers.supplierid )
and price<=20);
select suppliername from suppliers where exists (
    select productname from products where products.supplierid =
                                           supplier.supplierid
                                     and price=22);
select employeeid,name,department,salary, max(salary) over
(parition by department) as maxsalaryindept from employees;
select employeeid,name,department,salary,sum(salary) over
(partition by department)as depttotalsalary from employees;
select employeeid,department,salary,rownumber() over (
partition by department order by salary desc
) as rownum from employees;
select employeeid, name, department, salary, rank() over
(partition by department order by salary desc) as rankindept,
denserank() over (partition by department order by salary desc)
as denserankdept;
select employeeid, name,salary,sum(salary) over (order by
employeeid )as runningtotal from employees;
select employeeid,name,salary,ntile(4) over (order by salary)
as salaryquartile from employees;
select productname from products where productid = ANY (select  productid from
orderdetails where quantity=10 );
select productname from products where productid = ANY( select productid from orderdetails
where quantity >99);
select productname from products where productid = ANY(
    select productid from orderdetails where quantity > 1000
    );
select all productname from products where true;
select productname from products where productid = ALL (select productid from
orderdetails where quantity = 10);
select * into customersgemany from customers where country ='germany';
select customername, contactname into customersbackup2017 from customers;
select customers.customername, orders.orderid into customersbackup from customers
left join orders on customers.customerid = orders.customerid;
select into newtable from oldtable where 1 =0;
insert into table2 select * from table1 where condition;
select orderid,quantity, case when quantity>30 then'greater' when quantity=30 then
'quantity is 30' else 'quantity is under 30' end as quantitytext from orderdetails;
select customername, city, country from customers order by (case when city is null
    then country else city end)
select customername ,city, country from customers order by ( case when
    city is null then country else city end);
