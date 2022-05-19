
-- CategoryDao:insert 
insert into category values (null,'IT');

-- CategoryDao:findAll
select no, category_name from category;

-- MemberDao:insert 
insert into member values (null,'테스트네임','111-1111-1111','테스트@메일.com','1234');

-- MemberDao:findAll
select no,name,tell,email,password from member;

-- BookDao:insert 
insert into book  values (null,'타이틀',1,1);

-- CartDao:insert 
insert into cart values(1,1,1);

-- OrderDao:insert 
insert into orders values(null,1000,'부산북구',1);

-- OrderDao:insertOrderBook 
insert into orders_book values(1,1,1);

select * from cart;

show tables;
select * from book;

delete from orders_book;
alter table orders_book auto_increment = 1;
delete from orders;
alter table orders auto_increment = 1;
delete from cart;
alter table cart auto_increment = 1;
delete from member;
alter table member auto_increment = 1;
delete from book;
alter table book auto_increment = 1;
delete from category;
alter table category auto_increment = 1;
