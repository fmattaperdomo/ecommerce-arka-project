DROP SCHEMA IF EXISTS customer CASCADE;

CREATE SCHEMA customer;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customer.customers
(
    id uuid NOT NULL,
    type_identification character varying COLLATE pg_catalog."default" NOT NULL,
    document_number character varying COLLATE pg_catalog."default" NOT NULL,
    first_name character varying COLLATE pg_catalog."default" NOT NULL,
    last_name character varying COLLATE pg_catalog."default" NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    phone character varying COLLATE pg_catalog."default" NOT NULL,
    user_role character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);

DROP MATERIALIZED VIEW IF EXISTS customer.order_customer_m_view;

CREATE MATERIALIZED VIEW customer.order_customer_m_view
TABLESPACE pg_default
AS
SELECT id,
       type_identification,
       document_number,
       first_name,
       last_name,
       email,
       phone,
       user_role
FROM customer.customers
    WITH DATA;

refresh materialized VIEW customer.order_customer_m_view;

DROP function IF EXISTS customer.refresh_order_customer_m_view;

CREATE OR replace function customer.refresh_order_customer_m_view()
returns trigger
AS '
BEGIN
    refresh materialized VIEW customer.order_customer_m_view;
    return null;
END;
'  LANGUAGE plpgsql;

DROP trigger IF EXISTS refresh_order_customer_m_view ON customer.customers;

CREATE trigger refresh_order_customer_m_view
    after INSERT OR UPDATE OR DELETE OR truncate
                    ON customer.customers FOR each statement
                        EXECUTE PROCEDURE customer.refresh_order_customer_m_view();