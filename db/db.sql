CREATE TABLE users (
id serial PRIMARY KEY,
login varchar(255) NOT NULL UNIQUE,
password varchar(144) NOT NULL,
email varchar(255) NOT NULL,
user_role varchar(255) NOT NULL,
user_enable boolean DEFAULT true 
);

CREATE TABLE  post (
post_id serial PRIMARY KEY,
user_id int references users(id),
sender int references users(id),
post_content varchar,
img bytea,
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



INSERT INTO users(login, password, email, user_role)
    VALUES ('admin', '23d4a6a2ea14ab8f999e9dfe47a9a8290e19519efe9f9eb68dc7b876ae631ee05cd3c0f522abb16fb60e51ea4d42662ba08f629e1b0ed2e6aa33b8851d660bc55e0415e4b66d50ee', 'admin@mail.ru', 'ADMIN');
