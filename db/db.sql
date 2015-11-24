CREATE TABLE users (
id serial PRIMARY KEY,
login varchar(20) NOT NULL UNIQUE,
password varchar(144) NOT NULL,
email varchar(255) NOT NULL,
user_role varchar(255) NOT NULL,
user_enable boolean DEFAULT true 
);

CREATE TABLE  post (
post_id serial PRIMARY KEY,
user_id int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
sender int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
post_content varchar,
post_send_date timestamp without time zone,
img bytea,
likes int DEFAULT 0,
dislikes int  DEFAULT 0
);


CREATE TABLE  comment (
comment_id serial PRIMARY KEY,
post_id int references post(post_id) ON UPDATE CASCADE ON DELETE CASCADE,
sender int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
owner int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
comment_content varchar,
comment_send_date timestamp without time zone
);

CREATE TABLE  profile (
id serial PRIMARY KEY,
user_id int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
firstname varchar(125),
surname varchar(125),
secondname varchar(125),
country varchar(125),
city varchar(125),
avatar bytea,
sex varchar(15),
birthday  date
);

CREATE TABLE rating (
rating_id serial PRIMARY KEY,
post_id int references post(post_id) ON UPDATE CASCADE ON DELETE CASCADE,
sender int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
type varchar(255) NOT NULL
);


INSERT INTO users(login, password, email, user_role)
    VALUES ('admin', '23d4a6a2ea14ab8f999e9dfe47a9a8290e19519efe9f9eb68dc7b876ae631ee05cd3c0f522abb16fb60e51ea4d42662ba08f629e1b0ed2e6aa33b8851d660bc55e0415e4b66d50ee', 'admin@mail.ru', 'ADMIN');
