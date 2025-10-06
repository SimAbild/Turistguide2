DROP TABLE IF EXISTS attraction_tags;
DROP TABLE IF EXISTS attractions;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS tags;

CREATE TABLE cities

(
    cityID INT PRIMARY KEY,
    name   VARCHAR(100) NOT NULL
);


CREATE TABLE attractions
(
    attractionID INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    description  VARCHAR(1000),
    cityID       INT,
    FOREIGN KEY (cityID) REFERENCES cities (cityID) ON DELETE CASCADE
);


CREATE TABLE tags
(
    tagID INT PRIMARY KEY,
    name  VARCHAR(100) NOT NULL
);

CREATE TABLE attraction_tags
(
    attractionID INT,
    tagID        INT,
    PRIMARY KEY (attractionID, tagID),
    FOREIGN KEY (attractionID) REFERENCES attractions (attractionID) ON DELETE CASCADE,
    FOREIGN KEY (tagID) REFERENCES tags (tagID) ON DELETE CASCADE
);

-- testdata
INSERT INTO cities (cityID, name)
VALUES (1, 'Copenhagen');

INSERT INTO attractions (name, description, cityID)
VALUES ( 'Test Attraction', 'Description text',1);

INSERT INTO tags (tagID, name)
VALUES (1, 'fun');

INSERT INTO attraction_tags (attractionID, tagID)
VALUES (1, 1);
