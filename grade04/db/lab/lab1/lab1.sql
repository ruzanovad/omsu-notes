--- task 1
create schema contacts;

create table contacts.people (
id int serial primary key,
name varchar(255),
surname varchar(255),
email varchar(255)
);

insert into contacts.people (name, surname, email) values 
('Ivan', 'Ivanov', 'ivanowow@gmail.com'), 
('Elena', 'Ivanova', 'lena2004@mail.ru'),
('Denis', 'Denisov', 'superprog@inbox.ru');

select * from contacts.people;
select name, surname from contacts.people;

update contacts.people 
set email = 'qwerty@com.mail'
where name = 'Elena';

delete from contacts.people 
where name = 'Elena';
--- task 2
create schema shop;
create table shop.products (
product_id int serial primary key,
name varchar(255),
price int,
available boolean
);

create table shop.orders (
shop id int serial primary key,
product_id int references shop.products(product_id),
count int,
date_of_order date 
);

insert into shop.products (name, price, available) values
('Sausage', 100, true),
('Bread', 40, false),
('Butter', 110, true),
('Eggs', 20, true);

insert into shop.orders (product_id, count, date_of_order) values
(1, 5, '1999-01-08'),
(3, 10, '2024-06-08'),
(4, 30, '2024-07-20');

select * from shop.products
where price < 50;

select * from shop.orders
where date_of_order >= '2003-01-01';

update shop.orders 
set count = 300
where product_id = 3;

--- task 3
create schema blog;
create blog.authors (
    author_id int serial as identity primary key,
    surname varchar(255),
    name varchar(255),
    middle_name varchar(255),
);

create table blog.posts (
    post_id int serial as identity primary key,
    heading varchar(255),
    text varchar(1024),
    author_id int references blog.authors(author_id) on delete cascade,
);

create table blog.comments (
    comment_id int serial as identity primary key,
    text varchar(1024),
    author_id int references blog.authors(author_id) on delete cascade,
    post_id int references blog.posts(post_id) on delete cascade,
);

insert into blog.authors (name, surname, middle_name) values
('Ivanov', 'Ivan', 'Ivanovich'),
('Ivanova', 'Veronika', 'Ivanovna'),
('Kim', 'Jongkook', 'Jongkookovich');

insert into blog.posts (heading, text, author_id) values
('Heading', 'Text', 1),
('More cool heading', 'The text is even cooler', 1),
('Lorem Ipsum', 'Text 2', 3);

insert into blog.comments (text, author_id, post_id) values
('Such a deep post...', 3, 3),
('dislike', 3, 3),
('like', 1, 1);

select * from blog.posts
where author_id = 3;

select * from blog.comments
where post_id = 3;

delete from blog.comments 
where author_id = 1;

-- delete from blog.comments 
-- where author_id = 3;

-- delete from blog.posts 
-- where author_id = 3;

delete from blog.authors
where author_id = 3;