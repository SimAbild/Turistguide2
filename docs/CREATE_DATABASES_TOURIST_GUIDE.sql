-- Brug databasen
USE turistguide;

-- Drop eksisterende tabeller hvis de findes (så scriptet kan køres igen uden fejl)
DROP TABLE IF EXISTS attraction_tags;
DROP TABLE IF EXISTS attractions;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS tags;

-- Opret tabeller
CREATE TABLE cities (
    cityID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE attractions (
    attractionID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    cityID INT,
    FOREIGN KEY (cityID) REFERENCES cities(cityID) ON DELETE CASCADE
);

CREATE TABLE tags (
    tagID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Mange-til-mange mellem attraction og tags (junction tabellen)
CREATE TABLE attraction_tags (
    attractionID INT,
    tagID INT,
    PRIMARY KEY (attractionID, tagID),
    FOREIGN KEY (attractionID) REFERENCES attractions(attractionID) ON DELETE CASCADE,
    FOREIGN KEY (tagID) REFERENCES tags(tagID) ON DELETE CASCADE
);