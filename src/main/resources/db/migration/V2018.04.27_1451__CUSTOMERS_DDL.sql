CREATE TABLE customers (
	id CHAR(32) NOT NULL,
	name TEXT NOT NULL,
	email TEXT NOT NULL,
	created_on TIMESTAMP NOT NULL DEFAULT now(),
	last_modified_on TIMESTAMP NOT NULL DEFAULT now(),
	version INTEGER NOT NULL DEFAULT 0,
	PRIMARY KEY (id),
	CONSTRAINT uk_email UNIQUE (email)
);
