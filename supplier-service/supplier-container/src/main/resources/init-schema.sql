DROP SCHEMA IF EXISTS supplier CASCADE;

CREATE SCHEMA supplier;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE supplier.suppliers
(
    id uuid NOT NULL,
    identificationId uuid NOT NULL,
    first_name character varying COLLATE pg_catalog."default" NOT NULL,
    last_name character varying COLLATE pg_catalog."default" NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    phone character varying COLLATE pg_catalog."default" NOT NULL,
    user_role character varying COLLATE pg_catalog."default" NOT NULL,
    addressId uuid NOT NULL,
    createdAt timestamp NOT NULL,
    updatedAt timestamp NOT NULL
    CONSTRAINT suppliers_pkey PRIMARY KEY (id)
);

DROP MATERIALIZED VIEW IF EXISTS supplier.order_supplier_m_view;

CREATE MATERIALIZED VIEW supplier.order_supplier_m_view
TABLESPACE pg_default
AS
SELECT id,
       identificationId
       first_name,
       last_name,
       email,
       phone,
       user_role,
       addressId
FROM supplier.suppliers
    WITH DATA;

refresh materialized VIEW supplier.order_supplier_m_view;

DROP function IF EXISTS supplier.refresh_order_supplier_m_view;

CREATE OR replace function supplier.refresh_order_supplier_m_view()
returns trigger
AS '
BEGIN
    refresh materialized VIEW supplier.order_supplier_m_view;
    return null;
END;
'  LANGUAGE plpgsql;

DROP trigger IF EXISTS refresh_order_supplier_m_view ON supplier.suppliers;

CREATE trigger refresh_order_supplier_m_view
    after INSERT OR UPDATE OR DELETE OR truncate
                    ON supplier.suppliers FOR each statement
                        EXECUTE PROCEDURE supplier.refresh_order_supplier_m_view();