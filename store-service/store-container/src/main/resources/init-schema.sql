DROP SCHEMA IF EXISTS store CASCADE;

CREATE SCHEMA store;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS store.stores CASCADE;

CREATE TABLE store.stores
(
    id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL,
    CONSTRAINT stores_pkey PRIMARY KEY (id)
);

DROP TYPE IF EXISTS approval_status;

CREATE TYPE approval_status AS ENUM ('APPROVED', 'REJECTED');

DROP TABLE IF EXISTS store.order_approval CASCADE;

CREATE TABLE store.order_approval
(
    id uuid NOT NULL,
    store_id uuid NOT NULL,
    order_id uuid NOT NULL,
    status approval_status NOT NULL,
    CONSTRAINT order_approval_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS store.products CASCADE;

CREATE TABLE store.products
(
    id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    price numeric(10,2) NOT NULL,
    available boolean NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS store.store_products CASCADE;

CREATE TABLE store.store_products
(
    id uuid NOT NULL,
    store_id uuid NOT NULL,
    product_id uuid NOT NULL,
    CONSTRAINT store_products_pkey PRIMARY KEY (id)
);

ALTER TABLE store.store_products
    ADD CONSTRAINT "FK_STORE_ID" FOREIGN KEY (store_id)
        REFERENCES store.stores (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE RESTRICT
    NOT VALID;

ALTER TABLE store.store_products
    ADD CONSTRAINT "FK_PRODUCT_ID" FOREIGN KEY (product_id)
        REFERENCES store.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE RESTRICT
    NOT VALID;

DROP TYPE IF EXISTS outbox_status;
CREATE TYPE outbox_status AS ENUM ('STARTED', 'COMPLETED', 'FAILED');

DROP TABLE IF EXISTS store.order_outbox CASCADE;

CREATE TABLE store.order_outbox
(
    id uuid NOT NULL,
    saga_id uuid NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    processed_at TIMESTAMP WITH TIME ZONE,
    type character varying COLLATE pg_catalog."default" NOT NULL,
    payload jsonb NOT NULL,
    outbox_status outbox_status NOT NULL,
    approval_status approval_status NOT NULL,
    version integer NOT NULL,
    CONSTRAINT order_outbox_pkey PRIMARY KEY (id)
);

CREATE INDEX "store_order_outbox_saga_status"
    ON "store".order_outbox
        (type, approval_status);

CREATE UNIQUE INDEX "store_order_outbox_saga_id"
    ON "store".order_outbox
        (type, saga_id, approval_status, outbox_status);

DROP MATERIALIZED VIEW IF EXISTS store.order_store_m_view;

CREATE MATERIALIZED VIEW store.order_store_m_view
TABLESPACE pg_default
AS
SELECT r.id AS store_id,
       r.name AS store_name,
       r.active AS store_active,
       p.id AS product_id,
       p.name AS product_name,
       p.price AS product_price,
       p.available AS product_available
FROM store.stores r,
     store.products p,
     store.store_products rp
WHERE r.id = rp.store_id AND p.id = rp.product_id
    WITH DATA;

refresh materialized VIEW store.order_store_m_view;

DROP function IF EXISTS store.refresh_order_store_m_view;

CREATE OR replace function store.refresh_order_store_m_view()
returns trigger
AS '
BEGIN
    refresh materialized VIEW store.order_store_m_view;
    return null;
END;
'  LANGUAGE plpgsql;

DROP trigger IF EXISTS refresh_order_store_m_view ON store.store_products;

CREATE trigger refresh_order_store_m_view
    after INSERT OR UPDATE OR DELETE OR truncate
                    ON store.store_products FOR each statement
                        EXECUTE PROCEDURE store.refresh_order_store_m_view();