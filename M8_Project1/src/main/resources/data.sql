-- INITIAL DATA FOR BOOKS TABLE
INSERT INTO books (book_id,title,author,is_available) VALUES (1, 'Title 1', 'Test Author', True);
INSERT INTO books (book_id,title,author,is_available) VALUES (15,'Title 2', 'Test Author 2', True);
INSERT INTO books (book_id,title,author,is_available) VALUES (324,'Title 3', 'Test Author 3', True);
INSERT INTO books (book_id,title,author,is_available) VALUES (122,'Title 4', 'Test Author 4', True);
INSERT INTO books (book_id,title,author,is_available) VALUES (25,'Title 5', 'Test Author 5', True);

-- INITIAL DATA FOR USER TABLE
INSERT INTO users (user_id,name) VALUES (1,'John');
INSERT INTO users (user_id,name) VALUES (2, 'Test');

--INITIAL DATA FOR LOANS TABLE
INSERT INTO loans (book_id,user_id,is_active) VALUES (15,1,true);
UPDATE books SET is_available = false WHERE book_id IN (15);