USE inline;

CREATE TABLE users (
    user_id INT(100) NOT NULL AUTO_INCREMENT,
    username VARCHAR(200) NOT NULL ,
    password VARCHAR(60) NOT NULL ,
    token VARCHAR(200) ,
    enabled TINYINT NOT NULL DEFAULT 1 ,
    PRIMARY KEY (user_id));

CREATE TABLE user_roles (
    user_role_id INT(11) NOT NULL AUTO_INCREMENT,
    user_id INT(100) NOT NULL,
    ROLE VARCHAR(45) NOT NULL,
    PRIMARY KEY (user_role_id),
    UNIQUE KEY uni_username_role (ROLE,user_id),
    KEY fk_username_idx (user_id),
    CONSTRAINT fk_username FOREIGN KEY (user_id) REFERENCES users (user_id));

CREATE TABLE IF NOT EXISTS user_data (
    user_data_id INT(100) NOT NULL AUTO_INCREMENT,
    user_id INT(100) NOT NULL,
    name VARCHAR(150) NOT NULL,
    surname VARCHAR(150) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    email VARCHAR(200) NOT NULL,
    PRIMARY KEY (user_data_id),
    UNIQUE KEY uni_user_data (user_data_id),
    KEY fk_username_idx (user_id),
    CONSTRAINT fk_username_data FOREIGN KEY (user_id) REFERENCES users (user_id));