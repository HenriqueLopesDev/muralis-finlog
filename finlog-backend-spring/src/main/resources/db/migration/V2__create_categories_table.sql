CREATE TABLE categories
(
    cat_id          SERIAL PRIMARY KEY,
    cat_name        VARCHAR(255) NOT NULL,
    cat_description VARCHAR(255) NOT NULL
);