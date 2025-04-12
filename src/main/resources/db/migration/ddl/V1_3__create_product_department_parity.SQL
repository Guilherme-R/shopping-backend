CREATE TABLE product_department_parity (
      id BIGINT NOT NULL AUTO_INCREMENT
    , id_product BIGINT NOT NULL
    , id_department BIGINT NOT NULL
	, PRIMARY KEY (id)
	, CONSTRAINT fk_product FOREIGN KEY (id_product) REFERENCES product(id)
	, CONSTRAINT fk_department FOREIGN KEY (id_department) REFERENCES department(id)
   	, CONSTRAINT uq_product_department UNIQUE(id_product, id_department)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;