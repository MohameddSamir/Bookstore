CREATE SCHEMA bookstore;

CREATE TABLE book(
	book_id INT NOT NULL AUTO_INCREMENT,
    authors VARCHAR(100) DEFAULT NULL,
    price INT DEFAULT NULL,
    publication_year INT DEFAULT NULL,
    title VARCHAR(100) DEFAULT NULL,
    image_url TEXT,
    PRIMARY KEY(book_id)
);

CREATE TABLE user(
	id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) DEFAULT NULL,
    last_name VARCHAR(50) DEFAULT NULL,
    email VARCHAR(100) DEFAULT NULL UNIQUE,
    password VARCHAR(50) DEFAULT NULL,
    role VARCHAR(50) DEFAULT NULL,
    enabled INT DEFAULT 1,
    PRIMARY KEY(id)
);

INSERT INTO user VALUES
(1,"Mohamed","Samir","mohamed@gmail.com","{noop}1234","ROLE_ADMIN",1),
(2,"jack","sparo","jack@gmail.com","{noop}1234","ROLE_USER",1);

CREATE TABLE user_book(
	book_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY(book_id,user_id),
    FOREIGN KEY(book_id) REFERENCES book(book_id),
    FOREIGN KEY(user_id) REFERENCES user(id)
);