-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: com-linweb850.srv.combell-ops.net:3306
-- Gegenereerd op: 08 mei 2024 om 20:56
-- Serverversie: 8.0.36-28
-- PHP-versie: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ID429726_g32`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Dominotegel`
--

CREATE TABLE `Dominotegel` (
  `nummer` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Gegevens worden geëxporteerd voor tabel `Dominotegel`
--

INSERT INTO `Dominotegel` (`nummer`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22),
(23),
(24),
(25),
(26),
(27),
(28),
(29),
(30),
(31),
(32),
(33),
(34),
(35),
(36),
(37),
(38),
(39),
(40),
(41),
(42),
(43),
(44),
(45),
(46),
(47),
(48);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Speler`
--

CREATE TABLE `Speler` (
  `geboortejaar` int NOT NULL,
  `gebruikersnaam` varchar(50) NOT NULL,
  `aantalGewonnen` int DEFAULT NULL,
  `aantalGespeeld` int DEFAULT NULL,
  `kleur` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Gegevens worden geëxporteerd voor tabel `Speler`
--

INSERT INTO `Speler` (`geboortejaar`, `gebruikersnaam`, `aantalGewonnen`, `aantalGespeeld`, `kleur`) VALUES
(2001, 'Brittney001', 0, 0, 'GEEN_KLEUR'),
(2000, 'europapa', 2, 7, 'GEEL'),
(1997, 'GameBe', 1, 2, 'ROOS'),
(1955, 'koopaTroopa', 2, 7, 'BLAUW'),
(1999, 'MightyKen', 0, 1, 'BLAUW'),
(2005, 'Xaremios', 0, 6, 'GROEN'),
(2004, 'Zepje_', 0, 1, 'BLAUW');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Vak`
--

CREATE TABLE `Vak` (
  `aantalKronen` int NOT NULL,
  `landschapstype` varchar(50) NOT NULL,
  `nummer` int NOT NULL,
  `plaats` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Gegevens worden geëxporteerd voor tabel `Vak`
--

INSERT INTO `Vak` (`aantalKronen`, `landschapstype`, `nummer`, `plaats`) VALUES
(0, 'akker', 1, 'L'),
(0, 'akker', 1, 'R'),
(0, 'akker', 2, 'L'),
(0, 'akker', 2, 'R'),
(0, 'bos', 3, 'L'),
(0, 'bos', 3, 'R'),
(0, 'bos', 4, 'L'),
(0, 'bos', 4, 'R'),
(0, 'bos', 5, 'L'),
(0, 'bos', 5, 'R'),
(0, 'bos', 6, 'L'),
(0, 'bos', 6, 'R'),
(0, 'water', 7, 'L'),
(0, 'water', 7, 'R'),
(0, 'water', 8, 'L'),
(0, 'water', 8, 'R'),
(0, 'water', 9, 'L'),
(0, 'water', 9, 'R'),
(0, 'gras', 10, 'L'),
(0, 'gras', 10, 'R'),
(0, 'gras', 11, 'L'),
(0, 'gras', 11, 'R'),
(0, 'moeras', 12, 'L'),
(0, 'moeras', 12, 'R'),
(0, 'akker', 13, 'L'),
(0, 'bos', 13, 'R'),
(0, 'akker', 14, 'L'),
(0, 'water', 14, 'R'),
(0, 'akker', 15, 'L'),
(0, 'gras', 15, 'R'),
(0, 'akker', 16, 'L'),
(0, 'moeras', 16, 'R'),
(0, 'bos', 17, 'L'),
(0, 'water', 17, 'R'),
(0, 'bos', 18, 'L'),
(0, 'gras', 18, 'R'),
(0, 'water', 19, 'R'),
(1, 'akker', 19, 'L'),
(0, 'bos', 20, 'R'),
(1, 'akker', 20, 'L'),
(0, 'gras', 21, 'R'),
(1, 'akker', 21, 'L'),
(0, 'moeras', 22, 'R'),
(1, 'akker', 22, 'L'),
(0, 'mijn', 23, 'R'),
(1, 'akker', 23, 'L'),
(0, 'akker', 24, 'R'),
(1, 'bos', 24, 'L'),
(0, 'akker', 25, 'R'),
(1, 'bos', 25, 'L'),
(0, 'akker', 26, 'R'),
(1, 'bos', 26, 'L'),
(0, 'akker', 27, 'R'),
(1, 'bos', 27, 'L'),
(0, 'water', 28, 'R'),
(1, 'bos', 28, 'L'),
(0, 'gras', 29, 'R'),
(1, 'bos', 29, 'L'),
(0, 'akker', 30, 'R'),
(1, 'water', 30, 'L'),
(0, 'akker', 31, 'R'),
(1, 'water', 31, 'L'),
(0, 'bos', 32, 'R'),
(1, 'water', 32, 'L'),
(0, 'bos', 33, 'R'),
(1, 'water', 33, 'L'),
(0, 'bos', 34, 'R'),
(1, 'water', 34, 'L'),
(0, 'bos', 35, 'R'),
(1, 'water', 35, 'L'),
(0, 'akker', 36, 'L'),
(1, 'gras', 36, 'R'),
(0, 'water', 37, 'L'),
(1, 'gras', 37, 'R'),
(0, 'akker', 38, 'L'),
(1, 'moeras', 38, 'R'),
(0, 'gras', 39, 'L'),
(1, 'moeras', 39, 'R'),
(0, 'akker', 40, 'R'),
(1, 'mijn', 40, 'L'),
(0, 'akker', 41, 'L'),
(2, 'gras', 41, 'R'),
(0, 'water', 42, 'L'),
(2, 'gras', 42, 'R'),
(0, 'akker', 43, 'L'),
(2, 'moeras', 43, 'R'),
(0, 'gras', 44, 'L'),
(2, 'moeras', 44, 'R'),
(0, 'akker', 45, 'R'),
(2, 'mijn', 45, 'L'),
(0, 'moeras', 46, 'L'),
(2, 'mijn', 46, 'R'),
(0, 'moeras', 47, 'L'),
(2, 'mijn', 47, 'R'),
(0, 'akker', 48, 'L'),
(3, 'mijn', 48, 'R');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `Dominotegel`
--
ALTER TABLE `Dominotegel`
  ADD PRIMARY KEY (`nummer`);

--
-- Indexen voor tabel `Speler`
--
ALTER TABLE `Speler`
  ADD PRIMARY KEY (`gebruikersnaam`);

--
-- Indexen voor tabel `Vak`
--
ALTER TABLE `Vak`
  ADD PRIMARY KEY (`aantalKronen`,`landschapstype`,`nummer`,`plaats`),
  ADD KEY `nummer` (`nummer`);

--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `Vak`
--
ALTER TABLE `Vak`
  ADD CONSTRAINT `Vak_ibfk_1` FOREIGN KEY (`nummer`) REFERENCES `Dominotegel` (`nummer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
