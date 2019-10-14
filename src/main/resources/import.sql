insert into role(role_id,role) values (1,'ROLE_ADMIN');
insert into role(role_id,role) values (2,'ROLE_USER');

insert into user_table(user_id,name,username,password,email,surname) values (-1,'berk','admin','$2a$10$06H.QM0KXUeRrzP9bIl5wuW5HAUvVHBWVuVjqQ0rux1J5AR9lpOf6','berkaltug96@gmail.com','altug');

insert into user_role(user_id,role_id) values(-1,1);
insert into user_role(user_id,role_id) values(-1,2);