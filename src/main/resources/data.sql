insert into user_details(id, name, birth_date)
values (100, 'Radek', current_date());

insert into user_details(id, name, birth_date)
values (101, 'Mateusz', current_date());

insert into user_details(id, name, birth_date)
values (102, 'Konrad', current_date());

insert into posts_details (id, post_description, user_id)
values (1, 'I want to master microservices', 100);

insert into posts_details (id, post_description, user_id)
values (2, 'I want to master Java', 101);

insert into posts_details (id, post_description, user_id)
values (3, 'I want to master AI', 102);