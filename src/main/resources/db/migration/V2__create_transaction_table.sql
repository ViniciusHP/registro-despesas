CREATE TABLE transaction (
        id BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID(), 1)),
        description VARCHAR(255) NOT NULL,
        amount DECIMAL(13, 2) NOT NULL,
        category_id BINARY(16) NOT NULL,
        FOREIGN KEY (category_id) REFERENCES category(id)
);