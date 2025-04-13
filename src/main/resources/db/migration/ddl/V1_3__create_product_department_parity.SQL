CREATE TABLE product_department_parity (
      product_id BIGINT NOT NULL
    , department_id BIGINT NOT NULL
    , created_at DATETIME NOT NULL DEFAULT NOW()
    , PRIMARY KEY (product_id, department_id)
	, CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id)
	, CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES department(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;