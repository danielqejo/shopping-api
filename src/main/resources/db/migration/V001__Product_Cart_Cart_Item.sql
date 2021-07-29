-- shopping.products definition
CREATE TABLE products (
    sku uuid NOT NULL DEFAULT gen_random_uuid(),
	"name" varchar NOT NULL,
	"value" money NOT NULL,
	CONSTRAINT products_pk PRIMARY KEY (sku)
);

-- shopping.carts definition
CREATE TABLE carts (
	id uuid NOT NULL DEFAULT gen_random_uuid(),
	name varchar NOT NULL,
	CONSTRAINT carts_pk PRIMARY KEY (id)
);

-- shopping.cart_items definition
CREATE TABLE cart_items (
    id serial NOT NULL,
    cart_id uuid NOT NULL,
	product_sku uuid NOT NULL,
	amount integer NOT NULL,
	CONSTRAINT cart_items_pk PRIMARY KEY (id),
	CONSTRAINT cart_items_fk FOREIGN KEY (cart_id) REFERENCES carts(id),
	CONSTRAINT cart_items_fk_1 FOREIGN KEY (product_sku) REFERENCES products(sku)
);
