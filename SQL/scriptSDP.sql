/* UC1 - Tabel Speler aanmaken */
/* =========================== */

USE ID429726_g32;
CREATE TABLE IF NOT EXISTS Speler(
	geboortejaar INT NOT NULL,
	gebruikersnaam VARCHAR(50) NOT NULL,
	aantalGewonnen INT,
	aantalGespeeld INT,
PRIMARY KEY (gebruikersnaam));


/* UC2 - Tabel Dominotegel + Vak aanmaken */
/* ====================================== */

/* Dominotegel aanmaken */
/* -------------------- */
CREATE TABLE IF NOT EXISTS Dominotegel(
	nummer int NOT NULL,
	PRIMARY KEY (nummer)
);

/* Vak aanmaken */
/* ------------ */
CREATE TABLE IF NOT EXISTS Vak(
	aantalKronen int,
	landschapstype VARCHAR(50) NOT NULL,
	nummer int NOT NULL,
	plaats VARCHAR(1) NOT NULL,
	PRIMARY KEY (aantalKronen, landschapstype, nummer, plaats),
	FOREIGN KEY (nummer) REFERENCES Dominotegel(nummer)
);

/* Alle dominotegels toevoegen aan database */
/* ---------------------------------------- */
INSERT INTO Dominotegel(nummer)
VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10),
(11), (12), (13), (14), (15), (16), (17), (18), (19), (20),
(21), (22), (23), (24), (25), (26), (27), (28), (29), (30),
(31), (32), (33), (34), (35), (36), (37), (38), (39), (40),
 (41), (42), (43), (44), (45), (46), (47), (48);
 
 
/* Alle vakken van alle dominotegels toevoegen aan database */
/* -------------------------------------------------------- */
INSERT INTO Vak(aantalKronen, landschapstype, nummer, plaats)
VALUES (0, ‘akker’, 1, ‘L’), (0, ‘akker’, 1, ‘R’),
		(0, ‘akker’, 2, ‘L’), (0, ‘akker’, 2, ‘R’),
		(0, ‘bos’, 3, ‘L’), (0, ‘bos’, 3, ‘R’),
		(0, ‘bos’, 4, ‘L’), (0, ‘bos’, 4, ‘R’),
		(0, ‘bos’, 5, ‘L’), (0, ‘bos’, 5, ‘R’),
		(0, ‘bos’, 6, ‘L’), (0, ‘bos’, 6, ‘R’),
		(0, ‘water’, 7, ‘L’), (0, ‘water’, 7, ‘R’),
		(0, ‘water’, 8, ‘L’), (0, ‘water’, 8, ‘R’),
		(0, ‘water’, 9, ‘L’), (0, ‘water’, 9, ‘R’),
		(0, ‘gras’, 10, ‘L’), (0, ‘gras’, 10, ‘R’),
		(0, ‘gras’, 11, ‘L’), (0, ‘gras’, 11, ‘R’),
		(0, ‘moeras’, 12, ‘L’), (0, ‘moeras’, 12, ‘R’),
		(0, ‘akker’, 13, ‘L’), (0, ‘bos’, 13, ‘R’),
		(0, ‘akker’, 14, ‘L’), (0, ‘water’, 14, ‘R’),
		(0, ‘akker’, 15, ‘L’), (0, ‘gras’, 15, ‘R’),
		(0, ‘akker’, 16, ‘L’), (0, ‘moeras’, 16, ‘R’),
		(0, ‘bos’, 17, ‘L’), (0, ‘water’, 17, ‘R’),
		(0, ‘bos’, 18, ‘L’), (0, ‘gras’, 18, ‘R’),
		(1, ‘akker’, 19, ‘L’), (0, ‘water’, 19, ‘R’),
		(1, ‘akker’, 20, ‘L’), (0, ‘bos’, 20, ‘R’),
		(1, ‘akker’, 21, ‘L’), (0, ‘gras’, 21, ‘R’),
		(1, ‘akker’, 22, ‘L’), (0, ‘moeras’, 22, ‘R’),
		(1, ‘akker’, 23, ‘L’), (0, ‘mijn’, 23, ‘R’),
		(1, ‘bos’, 24, ‘L’), (0, ‘akker’, 24, ‘R’),
		(1, ‘bos’, 25, ‘L’), (0, ‘akker’, 25, ‘R’),
		(1, ‘bos’, 26, ‘L’), (0, ‘akker’, 26, ‘R’),
		(1, ‘bos’, 27, ‘L’), (0, ‘akker’, 27, ‘R’),
		(1, ‘bos’, 28, ‘L’), (0, ‘water’, 28, ‘R’),
		(1, ‘bos’, 29, ‘L’), (0, ‘gras’, 29, ‘R’),
		(1, ‘water’, 30, ‘L’), (0, ‘akker’, 30, ‘R’),
		(1, ‘water’, 31, ‘L’), (0, ‘akker’, 31, ‘R’),
		(1, ‘water’, 32, ‘L’), (0, ‘bos’, 32, ‘R’),
		(1, ‘water’, 33, ‘L’), (0, ‘bos’, 33, ‘R’),
		(1, ‘water’, 34, ‘L’), (0, ‘bos’, 34, ‘R’),
		(1, ‘water’, 35, ‘L’), (0, ‘bos’, 35, ‘R’),
		(0, ‘akker’, 36, ‘L’), (1, ‘gras’, 36, ‘R’),
		(0, ‘water’, 37, ‘L’), (1, ‘gras’, 37, ‘R’),
		(0, ‘akker’, 38, ‘L’), (1, ‘moeras’, 38, ‘R’),
		(0, ‘gras’, 39, ‘L’), (1, ‘moeras’, 39, ‘R’),
		(1, ‘mijn’, 40, ‘L’), (0, ‘akker’, 40, ‘R’),
		(0, ‘akker’, 41, ‘L’), (2, ‘gras’, 41, ‘R’),
		(0, ‘water’, 42, ‘L’), (2, ‘gras’, 42, ‘R’),
		(0, ‘akker’, 43, ‘L’), (2, ‘moeras’, 43, ‘R’),
		(0, ‘gras’, 44, ‘L’), (2, ‘moeras’, 44, ‘R’),
		(2, ‘mijn’, 45, ‘L’), (0, ‘akker’, 45, ‘R’),
		(0, ‘moeras’, 46, ‘L’), (2, ‘mijn’, 46, ‘R’),
		(0, ‘moeras’, 47, ‘L’), (2, ‘mijn’, 47, ‘R’),
		(0, ‘akker’, 48, ‘L’), (3, ‘mijn’, 48, ‘R’);