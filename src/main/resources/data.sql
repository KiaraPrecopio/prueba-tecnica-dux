DROP TABLE IF EXISTS EQUIPOS;
DROP TABLE IF EXISTS USUARIOS;
CREATE TABLE EQUIPOS  (
    ID BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NOMBRE VARCHAR(255),
    LIGA VARCHAR(255),
    PAIS VARCHAR(255)
);
CREATE TABLE USUARIOS (
ID BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
USERNAME VARCHAR(255) NOT NULL,
PASSWORD VARCHAR(255) NOT NULL
);
INSERT INTO USUARIOS (username, password) 
VALUES ('test', '$2a$10$9mIY0yWHTWdrsvRA0SjKe.AOKOQu3NBCyBd1pGB1h9K0I4VnhMEji');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Real Madrid', 'La Liga', 'España');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('FC Barcelona', 'La Liga', 'España');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Manchester United', 'Premier League', 'Inglaterra');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Liverpool FC', 'Premier League', 'Inglaterra');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Juventus FC', 'Serie A', 'Italia');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('AC Milan', 'Serie A', 'Italia');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Bayern Munich', 'Bundesliga', 'Alemania');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Borussia Dortmund', 'Bundesliga', 'Alemania');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Paris Saint-Germain', 'Ligue 1', 'Francia');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Olympique de Marseille', 'Ligue 1', 'Francia');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('FC Porto', 'Primeira Liga', 'Portugal');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Sporting CP', 'Primeira Liga', 'Portugal');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Ajax Amsterdam', 'Eredivisie', 'Países Bajos');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Feyenoord', 'Eredivisie', 'Países Bajos');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Celtic FC', 'Scottish Premiership', 'Escocia');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Rangers FC', 'Scottish Premiership', 'Escocia');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Galatasaray SK', 'Süper Lig', 'Turquía');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Fenerbahçe SK', 'Süper Lig', 'Turquía');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('FC Zenit Saint Petersburg', 'Premier League Rusa', 'Rusia');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Spartak Moscow', 'Premier League Rusa', 'Rusia');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('SL Benfica', 'Primeira Liga', 'Portugal');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Besiktas JK', 'Süper Lig', 'Turquía');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('SSC Napoli', 'Serie A', 'Italia');
INSERT INTO EQUIPOS ( NOMBRE, LIGA, PAIS) VALUES ('Atlético Madrid', 'La Liga', 'España');