ALTER TABLE product
ADD CONSTRAINT fk_product_status
FOREIGN KEY (product_status_id)
REFERENCES product_status (id)