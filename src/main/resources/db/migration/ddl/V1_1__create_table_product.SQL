CREATE TABLE product (
      id BIGINT NOT NULL AUTO_INCREMENT
    , name VARCHAR(50) NOT NULL
    , description VARCHAR(200) NOT NULL
    , quantity INT NOT NULL
    , price DECIMAL(10,2) NOT NULL
    , discount_pct DECIMAL(5,2) NOT NULL DEFAULT 0
    , product_status_id INT
    , is_active BIT NOT NULL DEFAULT 1
	, PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;