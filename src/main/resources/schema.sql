create table if not exists users(
                      username varchar(50) not null primary key,
                      password varchar(500) not null,
                      enabled boolean not null
);

INSERT INTO users (username, password, enabled)
SELECT 'admin', '$2a$12$vNN.gHzJul9MZQGvlzuTOuqpC6Mfx3bXbN9eQ25k/.RrsDDvX9G3C', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin');