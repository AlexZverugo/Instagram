CREATE TABLE users (
id serial PRIMARY KEY,
login varchar(255) NOT NULL UNIQUE,
password varchar(144) NOT NULL,
email varchar(255) NOT NULL,
user_role varchar(255) NOT NULL
);




CREATE TABLE  post (
post_id serial PRIMARY KEY,
user_id int references users(id),
sender int references users(id),
post_content varchar,
likes int DEFAULT 0,
dislikes int  DEFAULT 0
);


CREATE TABLE  comment (
comment_id serial PRIMARY KEY,
post_id int references post(post_id),
sender int references users(id),
comment_content varchar,
likes int DEFAULT 0,
dislikes int  DEFAULT 0
);