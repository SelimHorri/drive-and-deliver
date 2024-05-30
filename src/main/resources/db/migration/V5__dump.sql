insert into customers (firstname, lastname) values
('amine', 'ladjimi'),
('selim', 'horri'),
('aziz', 'horri');

insert into deliveries (delivery_reference, customer_id) values
('rhjfbeirujh', 2),
('rhjfbeirujh', 3),
('sdjknhvmojiur', 2);

insert into delivery_methods (deliv_method, start_date, end_date, delivery_id) values
('DELIVERY_ASAP', '2024-05-30 12:30:00', '2024-05-30 13:00:00', 2),
('DELIVERY_ASAP', '2024-05-30 14:15:00', '2024-05-30 16:15:00', 1),
('DELIVERY_TODAY', '2024-05-29 08:00:00', '2024-05-29 12:00:00', 3);