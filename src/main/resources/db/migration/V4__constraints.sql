alter table deliveries
  add constraint fk_customers_deliveries foreign key (customer_id) references customers (id);

alter table delivery_methods
  add constraint fk_deliveries_delivery_methods foreign key (delivery_id) references deliveries (id);