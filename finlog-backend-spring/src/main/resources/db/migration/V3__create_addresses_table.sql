CREATE TABLE addresses
(
    adr_id           SERIAL PRIMARY KEY,
    adr_zip_code     CHAR(8)      NOT NULL,
    adr_state        CHAR(2)      NOT NULL,
    adr_city         VARCHAR(255) NOT NULL,
    adr_neighborhood VARCHAR(255) NOT NULL,
    adr_street       VARCHAR(255) NOT NULL,
    adr_number       VARCHAR(255) NOT NULL,
    adr_complement   VARCHAR(255) NULL DEFAULT NULL
);