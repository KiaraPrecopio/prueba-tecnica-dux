CREATE TABLE IF NOT EXISTS USUARIOS (
ID INT PRIMARY KEY AUTO_INCREMENT,
USERNAME VARCHAR(255) NOT NULL,
PASSWORD VARCHAR(255) NOT NULL
);
INSERT INTO USUARIOS (id, username, password) 
SELECT 1, 'test', '$2a$10$9mIY0yWHTWdrsvRA0SjKe.AOKOQu3NBCyBd1pGB1h9K0I4VnhMEji'
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE username = 'test');
CREATE TABLE IF NOT EXISTS EQUIPOS  (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NOMBRE VARCHAR(255),
    LIGA VARCHAR(255),
    PAIS VARCHAR(255)
);
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (1, 'Real Madrid', 'La Liga', 'España');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (2, 'FC Barcelona', 'La Liga', 'España');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (3, 'Manchester United', 'Premier League', 'Inglaterra');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (4, 'Liverpool FC', 'Premier League', 'Inglaterra');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (5, 'Juventus FC', 'Serie A', 'Italia');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (6, 'AC Milan', 'Serie A', 'Italia');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (7, 'Bayern Munich', 'Bundesliga', 'Alemania');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (8, 'Borussia Dortmund', 'Bundesliga', 'Alemania');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (9, 'Paris Saint-Germain', 'Ligue 1', 'Francia');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (10, 'Olympique de Marseille', 'Ligue 1', 'Francia');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (11, 'FC Porto', 'Primeira Liga', 'Portugal');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (12, 'Sporting CP', 'Primeira Liga', 'Portugal');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (13, 'Ajax Amsterdam', 'Eredivisie', 'Países Bajos');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (14, 'Feyenoord', 'Eredivisie', 'Países Bajos');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (15, 'Celtic FC', 'Scottish Premiership', 'Escocia');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (16, 'Rangers FC', 'Scottish Premiership', 'Escocia');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (17, 'Galatasaray SK', 'Süper Lig', 'Turquía');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (18, 'Fenerbahçe SK', 'Süper Lig', 'Turquía');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (19, 'FC Zenit Saint Petersburg', 'Premier League Rusa', 'Rusia');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (20, 'Spartak Moscow', 'Premier League Rusa', 'Rusia');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (21, 'SL Benfica', 'Primeira Liga', 'Portugal');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (22, 'Besiktas JK', 'Süper Lig', 'Turquía');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (23, 'SSC Napoli', 'Serie A', 'Italia');
INSERT INTO EQUIPOS (ID, NOMBRE, LIGA, PAIS) VALUES (24, 'Atlético Madrid', 'La Liga', 'España');
ALTER TABLE EQUIPOS ALTER COLUMN id RESTART WITH 25;