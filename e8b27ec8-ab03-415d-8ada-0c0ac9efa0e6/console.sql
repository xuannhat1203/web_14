create database session14;
use session14;
create table users (
    id int auto_increment primary key,
    username varchar(50) not null unique,
    password varchar(255) not null,
    email varchar(100) not null unique,
    created_at datetime default current_timestamp
);
delimiter \\
create procedure add_new_usser(
    in p_username varchar(50),
    in p_password varchar(255),
    in p_email varchar(100)
)
begin
    insert into users (username, password, email) values (p_username, p_password, p_email);
end \\
delimiter \\;
delimiter \\
create procedure get_all_users()
begin
    select * from users;
end \\
delimiter \\