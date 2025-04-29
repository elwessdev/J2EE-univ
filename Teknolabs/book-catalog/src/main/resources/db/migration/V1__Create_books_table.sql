CREATE TABLE IF NOT EXISTS books (
                                     id SERIAL PRIMARY KEY,
                                     title VARCHAR(100) NOT NULL,
    author VARCHAR(200),
    isbn VARCHAR(20) UNIQUE,
    publication_date DATE,
    description TEXT,
    page_count INTEGER
    );

-- Insert initial data
INSERT INTO books (title, author, isbn, publication_date, description, page_count)
VALUES
    ('To Kill a Mockingbird', 'Harper Lee', '9780061120084', '1960-07-11', 'A novel about racial injustice in a small Alabama town during the Great Depression.', 281),
    ('1984', 'George Orwell', '9780451524935', '1949-06-08', 'A dystopian novel set in a totalitarian society ruled by the Party, which has total control over every action and thought of the people.', 328),
    ('The Great Gatsby', 'F. Scott Fitzgerald', '9780743273565', '1925-04-10', 'A novel that explores themes of decadence, idealism, resistance to change, social upheaval, and excess.', 180),
    ('The Hobbit', 'J.R.R. Tolkien', '9780345339683', '1937-09-21', 'A fantasy novel about the adventures of the hobbit Bilbo Baggins.', 310),
    ('Pride and Prejudice', 'Jane Austen', '9780141439518', '1813-01-28', 'A romantic novel of manners that follows the character development of Elizabeth Bennet.', 432);