INSERT INTO tb_user(id, create_at, login, password)
    VALUES(1, '2021-03-22 10:36:15.566586000', 'teste002@gmail.com', '$2a$10$PJnwKheUSw3ScN2SeyIxaeCa.aUJ1IlWorMynErzSMohkauFrEVo6');

INSERT INTO category(id, name) VALUES(1, 'automóvel');
INSERT INTO category(id, name, category_id) VALUES(2, 'carro', 1);

INSERT INTO product(id, name, price, available_quantity, description, category_id, owner_id, create_at)
    VALUES(1, 'fogão', 566.80, 1000, 'pequena descrição do produto', 1, 1, '2021-03-22 10:36:15.566586000');
--
--INSERT INTO buy(id, amount, code, gateway, price, status, logged_user_id, product_id)
--    VALUES(1, 1, '47575c09-c33c-4b65-9bba-a9078f73935a', 'paypal', 566.80, 'INITIATED', 1, 1);