INSERT INTO store.stores(id, name, active)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb45', 'store_1', TRUE);
INSERT INTO store.stores(id, name, active)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb46', 'store_2', FALSE);

INSERT INTO store.products(id, name, price, available)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb47', 'product_1', 25.00, FALSE);
INSERT INTO store.products(id, name, price, available)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb48', 'product_2', 50.00, TRUE);
INSERT INTO store.products(id, name, price, available)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb49', 'product_3', 20.00, FALSE);
INSERT INTO store.products(id, name, price, available)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb50', 'product_4', 40.00, TRUE);

INSERT INTO store.store_products(id, store_id, product_id)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb51', 'd215b5f8-0249-4dc5-89a3-51fd148cfb45', 'd215b5f8-0249-4dc5-89a3-51fd148cfb47');
INSERT INTO store.store_products(id, store_id, product_id)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb52', 'd215b5f8-0249-4dc5-89a3-51fd148cfb45', 'd215b5f8-0249-4dc5-89a3-51fd148cfb48');
INSERT INTO store.store_products(id, store_id, product_id)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb53', 'd215b5f8-0249-4dc5-89a3-51fd148cfb46', 'd215b5f8-0249-4dc5-89a3-51fd148cfb49');
INSERT INTO store.store_products(id, store_id, product_id)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb54', 'd215b5f8-0249-4dc5-89a3-51fd148cfb46', 'd215b5f8-0249-4dc5-89a3-51fd148cfb50');
