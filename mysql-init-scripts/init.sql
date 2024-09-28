CREATE DATABASE IF NOT EXISTS bookdb;

USE bookdb;

CREATE TABLE IF NOT EXISTS book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    release_date DATE NOT NULL,
    author VARCHAR(255) NOT NULL,
    rating INT
);

INSERT INTO book (title, release_date, author, rating) VALUES
('Clean Code', '2008-08-01', 'Robert C. Martin', 5),
('Effective Java', '2001-05-28', 'Joshua Bloch', 5),
('Java Concurrency in Practice', '2001-05-28', 'Brian Goetz', 4),
('The Godfather: The Lost Years', '2006-05-01', 'Mark Winegardner', 4),
('The Shawshank Redemption: The Definitive History of the Making of a Modern Classic', '2017-09-20', 'The Shawshank Redemption Team', 5),
('Pulp Fiction: A Quentin Tarantino Screenplay', '1994-09-23', 'Quentin Tarantino', 5),
('The Dark Knight: The Creation of a Cult Film', '2008-07-18', 'Chris Nolan', 5),
('Forrest Gump: The Complete Screenplay', '1994-07-06', 'Eric Roth', 5),
('The Lord of the Rings: The Fellowship of the Ring: The Official Movie Guide', '2001-12-01', 'Brian Sibley', 5),
('Inception: The Shooting Script', '2010-07-16', 'Christopher Nolan', 5),
('Fight Club: A Novel', '1996-09-17', 'Chuck Palahniuk', 5),
('Goodfellas: The True Story of the Making of the Movie', '1990-09-19', 'Nicholas Pileggi', 5),
('The Matrix: The Shooting Script', '1999-03-31', 'Lana Wachowski, Lilly Wachowski', 5),
('Star Wars: The Original Trilogy', '1997-05-25', 'George Lucas', 5),
('The Silence of the Lambs: The Official Book of the Film', '1991-02-14', 'Thomas Harris', 4),
('The Social Network: The Movie: The Unofficial Book', '2010-10-01', 'Ben Mezrich', 4),
('Saving Private Ryan: The Shooting Script', '1998-07-24', 'Robert Rodat', 5),
('The Usual Suspects: The Script', '1995-08-16', 'Christopher McQuarrie', 5),
('Se7en: The Official Movie Book', '1995-09-22', 'Andrew Kevin Walker', 4),
('The Departed: The Shooting Script', '2006-10-06', 'William Monahan', 5),
('City of God: 10 Years Later', '2013-01-01', 'Fernando Meirelles', 4),
('The Lion King: The Official Illustrated Movie Companion', '2019-07-18', 'David Stephenson', 5),
('La La Land: The Complete Screenplay', '2016-12-09', 'Damien Chazelle', 5);
