INSERT INTO books (title, author, price, created_at) VALUES
-- TOO NEW (0–7 days → NOT allowed)
('Harry Potter and The Deathly Hollows', 'JK Rowlings', 500, CURRENT_DATE - INTERVAL '2 days'),
('A Song of Ice and Fire', 'George R. R. Martin', 450, CURRENT_DATE - INTERVAL '5 days'),

-- BETWEEN 1 WEEK AND 1 YEAR (Allowed)
('A Dance with Dragons', 'George R. R. Martin', 600, CURRENT_DATE - INTERVAL '2 months'),
('Pet Sematary', 'Stephen King', 700, CURRENT_DATE - INTERVAL '6 months'),
('Romeo and Juliet', 'William Shakespeare', 800, CURRENT_DATE - INTERVAL '10 months'),

-- OLD BOOKS (> 1 year → Not allowed)
('Clean Code', 'Robert C. Martin', 550, CURRENT_DATE - INTERVAL '2 years'),
('Design Patterns', 'GoF', 750, CURRENT_DATE - INTERVAL '3 years'),
('Effective Java', 'Joshua Bloch', 650, CURRENT_DATE - INTERVAL '5 years');

INSERT INTO roles (role) VALUES 
--@ ADMIN
('ROLE_ADMIN'),
--@ USER
('ROLE_USER');