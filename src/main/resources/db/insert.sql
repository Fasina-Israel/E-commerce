set foreign_key_checks = 0;

truncate table product;
truncate table cart;
truncate table cart_item_list;
truncate table item;
truncate table app_user;


insert into product(id, name, price, quantity)
values(12, 'Luxury Mop', 2340, 3),
(13, 'Macbook air', 18320, 4),
(14, 'Rocking chair', 5340, 5),
(15, 'Purple T-shirt', 7340, 7),
(16, 'Bamboo chair', 540, 9);

insert into item(id,  quantity_added_to_cart, product_id)
values(510, 14, 12),
  (511, 12, 13),
  (512, 13, 14);


insert into cart(id, total_price)
values(345, 0.0),
       (355, 0.0),
       (366, 0.0);

insert into app_user(id,first_name, last_name, email, my_cart_id)
values(5005, 'John', 'Badmus', 'Johnny@myspace.com',345),
(5010, 'Chris', 'Tuck', 'Johnny@myspace.com',355),
(5015, 'Goodnews', 'Badmus', 'Goodnews@myspace.com',366);

insert into cart_item_list(cart_id, item_list_id)
values (345,510),
 (345,511),
 (345,512);


set foreign_key_checks = 1;