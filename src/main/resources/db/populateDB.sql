DELETE FROM USER_ROLES;
DELETE FROM DISHES;
DELETE FROM VOTES;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (name) VALUES
('KFC'),
('Burger King'),
('McDonalds');

INSERT INTO dishes (RESTAURANT_ID, NAME, price, DATE_TIME) VALUES
(100002, 'Stripes', 114, '2019-03-01'),
(100002, 'Wings', 189, '2019-03-01'),
(100002, 'Basket', 509, '2019-03-01'),
(100003, 'Whopper', 184, '2019-03-01'),
(100003, 'BigFish', 127, '2019-03-01'),
(100003, 'Nuggets', 99, '2019-03-01'),
(100004, 'BigMac', 207, '2019-03-01'),
(100004, 'Fries', 122, '2019-03-01'),
(100004, 'McFlurry', 153, '2019-03-01');

INSERT INTO VOTES (VOTE_DATE, USER_ID, RESTAURANT_ID) VALUES
('2019-03-01', 100000, 100002),
('2019-03-01', 100001, 100003);
