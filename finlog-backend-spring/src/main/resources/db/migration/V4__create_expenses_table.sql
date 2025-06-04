CREATE TABLE expenses
(
    exp_id          SERIAL PRIMARY KEY,
    exp_description VARCHAR(255)   NOT NULL,
    exp_value       DECIMAL(10, 2) NOT NULL,
    exp_date        TIMESTAMP      NOT NULL,

    exp_pmt_id      INTEGER        NOT NULL,
    exp_cat_id      INTEGER        NOT NULL,
    exp_adr_id      INTEGER        NOT NULL,

    FOREIGN KEY (exp_adr_id) REFERENCES addresses (adr_id),
    FOREIGN KEY (exp_pmt_id) REFERENCES payment_types (pmt_id),
    FOREIGN KEY (exp_cat_id) REFERENCES categories (cat_id)
);