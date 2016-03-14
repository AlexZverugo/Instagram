INSERT INTO users(login, password, email, user_role)
    VALUES ('Kozrom', '51b7d0dfb79107094851a6e0e825da7420e4007298e9ee312a45e222d41cf9cc0c90e2762cd30515b25a20845bfb58b2c49fb804b7a172d92fe7e04dc5bca324ef4e38d08465e6b5', 'kozlov.roma@mail.ru', 'USER'),
	   ('Kilgor96', 'd099c006a76930003343a4f64b334de8211a068b04aafa7ab89ccca0f604c8091fa385a8ed1b58ae15eaf24966a92580368ecbeb926e7869f945878530275fe3417e8c731fce3c68', 'kilgor.artik@gmail.com', 'USER'),
	   ('shaki', 'd099c006a76930003343a4f64b334de8211a068b04aafa7ab89ccca0f604c8091fa385a8ed1b58ae15eaf24966a92580368ecbeb926e7869f945878530275fe3417e8c731fce3c68', 'shakivit@bsuir.by', 'USER'),
	   ('sofachka', 'd099c006a76930003343a4f64b334de8211a068b04aafa7ab89ccca0f604c8091fa385a8ed1b58ae15eaf24966a92580368ecbeb926e7869f945878530275fe3417e8c731fce3c68', 'sofa.bel@mail.ru', 'USER'),
	   ('Shinik', 'd099c006a76930003343a4f64b334de8211a068b04aafa7ab89ccca0f604c8091fa385a8ed1b58ae15eaf24966a92580368ecbeb926e7869f945878530275fe3417e8c731fce3c68', 'shinka.rus@mail.ru', 'USER'),
	   ('garbuz', '51b7d0dfb79107094851a6e0e825da7420e4007298e9ee312a45e222d41cf9cc0c90e2762cd30515b25a20845bfb58b2c49fb804b7a172d92fe7e04dc5bca324ef4e38d08465e6b5', 'garbuz.roma.fizra@mail.ru', 'USER'),
	   ('kolb', 'd099c006a76930003343a4f64b334de8211a068b04aafa7ab89ccca0f604c8091fa385a8ed1b58ae15eaf24966a92580368ecbeb926e7869f945878530275fe3417e8c731fce3c68', 'kolb.os@bsuir.by', 'USER'),
	   ('kolosok', 'd099c006a76930003343a4f64b334de8211a068b04aafa7ab89ccca0f604c8091fa385a8ed1b58ae15eaf24966a92580368ecbeb926e7869f945878530275fe3417e8c731fce3c68', 'kolosok.petia@gmail.com', 'USER'),
	   ('kuriaga', '51b7d0dfb79107094851a6e0e825da7420e4007298e9ee312a45e222d41cf9cc0c90e2762cd30515b25a20845bfb58b2c49fb804b7a172d92fe7e04dc5bca324ef4e38d08465e6b5', 'kuriaga@mail.ru', 'USER'),
	   ('latota', 'd099c006a76930003343a4f64b334de8211a068b04aafa7ab89ccca0f604c8091fa385a8ed1b58ae15eaf24966a92580368ecbeb926e7869f945878530275fe3417e8c731fce3c68', 'latotin.anton@mst.by', 'USER'),
	   ('kuznia', '51b7d0dfb79107094851a6e0e825da7420e4007298e9ee312a45e222d41cf9cc0c90e2762cd30515b25a20845bfb58b2c49fb804b7a172d92fe7e04dc5bca324ef4e38d08465e6b5', 'alex.kuznecov@gmail.com', 'USER');

INSERT INTO profile(
            user_id, country_id, firstname, surname, secondname, city, sex, birthday)
    VALUES (2, 2, 'Козловский', 'Роман', 'Генадьевич', 'Смоленск', 'male', '1996-12-09'),
	   (10, 7, 'Курегян', 'Самвел', 'Вазгенович', 'Киев', 'male', '1970-01-17'),
	   (3, 1, 'Барановский', 'Артем', 'Генадьевич', 'Могилев', 'male', '1995-12-31'),
	   (4, 1, 'Шакура', 'Виталина', 'Эдуардовна', 'Минск', 'female', '1990-03-08'),
	   (5, 9, 'Ермакова', 'Софья', 'Александровна', 'New York', 'female', '1967-06-23'),
	   (6, 1, 'Шинкарева', 'Наталья', 'Михаловна', 'Мстиславль', 'female', '2015-12-09'),
	   (7, 1, 'Гарбузов', 'Роман', 'Иванович', 'Мстиславль', 'male', '1988-07-13'),
	   (8, 1, 'Колб', 'Дмитрий', 'Григорьевич', 'Минск', 'male', '1980-09-01'),
	   (9, 1, 'Колосок', 'Петя', 'Мечеславович', 'Жлобин', 'male', '2000-10-09'),
	   (11, 1, 'Латотин', 'Анатолий', 'Борисович', 'Мстиславль', 'male', '1999-07-25'),
	   (12, 2, 'Кузнецов', 'Алексей', 'Михайлович', 'Москва', 'male', '1983-04-19');
