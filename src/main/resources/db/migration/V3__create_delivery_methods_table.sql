create table delivery_methods (
    id int primary key auto_increment,
    deliv_method enum('DRIVE', 'DELIVERY', 'DELIVERY_TODAY', 'DELIVERY_ASAP'),
    start_date datetime,
    end_date datetime,
    delivery_id int not null
);