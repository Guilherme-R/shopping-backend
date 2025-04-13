CREATE TABLE product (
      id BIGINT NOT NULL AUTO_INCREMENT
    , name VARCHAR(50) NOT NULL
    , description VARCHAR(200)
    , quantity INT NOT NULL DEFAULT 0
    , price DECIMAL(10,2) NOT NULL
    , discount_pct DECIMAL(5,2) NOT NULL DEFAULT 0
    , product_status_id TINYINT NOT NULL
    , is_active BIT NOT NULL DEFAULT 1
    , created_at DATETIME NOT NULL DEFAULT NOW()
	, PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;