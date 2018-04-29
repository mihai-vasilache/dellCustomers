CREATE TABLE customers (
	id CHAR(32) NOT NULL,
	name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	created_on TIMESTAMP NOT NULL DEFAULT now(),
	last_modified_on TIMESTAMP NOT NULL DEFAULT now(),
	version INTEGER NOT NULL DEFAULT 0,
	PRIMARY KEY (id),
);

CREATE UNIQUE INDEX email_unique_idx on customers (LOWER(email));
