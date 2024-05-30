create table deliveries (
    id int primary key auto_increment,
    delivery_reference varchar(255),
    customer_id int not null
);