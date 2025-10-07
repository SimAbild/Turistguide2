

-- Indsæt data i cities
INSERT INTO cities (name) VALUES 
('Copenhagen'),
('Aarhus'),
('Odense'),
('Husum'),
('Albertslund'),
('Klampenborg');

-- Indsæt data i tags
INSERT INTO tags (name) VALUES 
('Student discount'),
('Art'),
('Museum'),
('Free'),
('Nature'),
('Family friendly'),
('Amusement park'),
('Indoor'),
('Zoo'),
('Music'),
('Theater'),
('Cinema'),
('Science');

-- Indsæt 6 attraktioner
INSERT INTO attractions (name, description, cityID) VALUES
('Tivoli', 'A famous amusement park in the heart of Copenhagen.', 1),
('Bakken', 'Worlds oldest amusement park', 6),
('Planetariet', 'Copenhagen’s space and science center with exhibitions and films.', 1),
('Experimentarium', 'Interactive science center for kids and adults.', 1),
('Den Blå Planet', 'The National Aquarium Denmark, largest in Northern Europe.', 1),
('H.C. Andersens hus', 'Step into the fairytale', 3);

-- Tilknyt tags til attraktionerne
INSERT INTO attraction_tags (attractionID, tagID) VALUES
(1, 5),
(1, 6),
(1, 7),
(1, 10);

INSERT INTO attraction_tags (attractionID, tagID) VALUES
(2, 5),
(2, 6),
(2, 7);

INSERT INTO attraction_tags (attractionID, tagID) VALUES
(3, 3),
(3, 6),
(3, 8),
(3, 12);

INSERT INTO attraction_tags (attractionID, tagID) VALUES
(4, 6),
(4, 8),
(4, 13);

INSERT INTO attraction_tags (attractionID, tagID) VALUES
(5, 6),
(5, 8),
(5, 9);

INSERT INTO attraction_tags (attractionID, tagID) VALUES
(6, 3), 
(6, 6),
(6, 8);