/* Роли*/
INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_ORDERER');
INSERT INTO roles VALUES (3, 'ROLE_COURIER');

INSERT INTO cafes (id, name, description, img) values (DEFAULT, 'Ханума', 'Кавказская, ланчи, ..', null );
INSERT INTO cafes (id, name, description, img) values (DEFAULT, 'Добрая Столовая', 'Щебуреки, токмачи, ..', null );
INSERT INTO cafes (id, name, description, img) values (DEFAULT, 'McDonalds', 'бургеры, газировки, ..', null );

/*Добавляем супер-админа */
INSERT INTO users (id, created_at, update_at, avatar, firstname, lastname, login, password, phone) VALUES (DEFAULT, '2018-05-03 22:54:33.370000', '2018-05-03 22:54:33.370000', null , 'Админ', 'Админов', 'admin', '$2a$10$rElcXUk5MVGnwgnMudmqN.WgTQDH.wLwA/smiqejDBtSMB8QrbIeq','89000000000');
INSERT INTO user_role VALUES (1, 1);
INSERT INTO user_role VALUES (1, 2);
INSERT INTO user_role VALUES (1, 3);

/*Тестовые данные пользователей*/

INSERT INTO users (id, created_at, update_at, avatar, firstname, lastname, login, password, phone) VALUES (DEFAULT, '2018-05-03 22:54:33.370000', '2018-05-03 22:54:33.370000', null , 'Renat', 'Gayfutdinov', 'renat', '$2a$10$rElcXUk5MVGnwgnMudmqN.WgTQDH.wLwA/smiqejDBtSMB8QrbIeq','89999999998');
INSERT INTO users (id, created_at, update_at, avatar, firstname, lastname, login, password, phone) VALUES (DEFAULT, '2018-05-03 22:54:33.370000', '2018-05-03 22:54:33.370000', null , 'Lenar', 'Gilyazov', 'lenar', '$2a$10$rElcXUk5MVGnwgnMudmqN.WgTQDH.wLwA/smiqejDBtSMB8QrbIeq','89999999997');
INSERT INTO users (id, created_at, update_at, avatar, firstname, lastname, login, password, phone) VALUES (DEFAULT, '2018-05-03 22:54:33.370000', '2018-05-03 22:54:33.370000', null , 'Dinar', 'Raimov', 'dinar', '$2a$10$rElcXUk5MVGnwgnMudmqN.WgTQDH.wLwA/smiqejDBtSMB8QrbIeq','89270309497');
INSERT INTO users (id, created_at, update_at, avatar, firstname, lastname, login, password, phone) VALUES (DEFAULT, '2018-05-03 22:54:33.370000', '2018-05-03 22:54:33.370000', null , 'Zara', 'Smile', 'zara', '$2a$10$rElcXUk5MVGnwgnMudmqN.WgTQDH.wLwA/smiqejDBtSMB8QrbIeq','89999999996');
INSERT INTO users (id, created_at, update_at, avatar, firstname, lastname, login, password, phone) VALUES (DEFAULT, '2018-05-03 22:54:33.370000', '2018-05-03 22:54:33.370000', null , 'Курьер', '1', 'courier1', '$2a$10$rElcXUk5MVGnwgnMudmqN.WgTQDH.wLwA/smiqejDBtSMB8QrbIeq','89999999995');
INSERT INTO users (id, created_at, update_at, avatar, firstname, lastname, login, password, phone) VALUES (DEFAULT, '2018-05-03 22:54:33.370000', '2018-05-03 22:54:33.370000', null , 'Курьер', '2', 'courier2', '$2a$10$rElcXUk5MVGnwgnMudmqN.WgTQDH.wLwA/smiqejDBtSMB8QrbIeq','89999999994');

INSERT INTO user_role VALUES (2, 2);
INSERT INTO user_role VALUES (3, 2);
INSERT INTO user_role VALUES (4, 2);
INSERT INTO user_role VALUES (5, 2);
INSERT INTO user_role VALUES (6, 3);
INSERT INTO user_role VALUES (7, 3);

/*Заказы*/
INSERT INTO orders (id, user_id, price_order, quantity, destination_address, comment) VALUES (DEFAULT, 2, 350, 3, 'Кремлевская, 35', 'Буду на паре, оставьте на вахте!');
INSERT INTO orders (id, user_id, price_order, quantity, destination_address, comment) VALUES (DEFAULT, 3, 550, 5, 'Кремлевская, 35', 'Буду на паре, оставьте на вахте!');
INSERT INTO orders (id, user_id, price_order, quantity, destination_address, comment) VALUES (DEFAULT, 4, 350, 3, 'Кремлевская, 35', 'Буду на паре, оставьте на вахте!');
INSERT INTO orders (id, user_id, price_order, quantity, destination_address, comment) VALUES (DEFAULT, 5, 350, 3, 'Кремлевская, 35', 'Буду на паре, оставьте на вахте!');
