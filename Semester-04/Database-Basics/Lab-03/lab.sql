-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 20, 2026 at 08:48 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lab`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kategoria_produktu`
--

CREATE TABLE `kategoria_produktu` (
  `KATEGORIA` char(8) NOT NULL,
  `OPIS` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE `klient` (
  `ID_KLIENTA` int(6) NOT NULL,
  `IMIE` varchar(20) NOT NULL,
  `NAZWISKO` varchar(20) NOT NULL,
  `ADRES` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pozycja_zamowienia`
--

CREATE TABLE `pozycja_zamowienia` (
  `ID_PRODUKTU` int(8) NOT NULL,
  `NR_ZAMOWIENIA` int(8) NOT NULL,
  `LICZBA_SZTUK` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkt`
--

CREATE TABLE `produkt` (
  `ID_PRODUKTU` int(8) NOT NULL,
  `NAZWA` varchar(15) NOT NULL,
  `CENA` double(5,2) NOT NULL,
  `OPIS` tinytext NOT NULL,
  `KATEGORIA` char(8) NOT NULL,
  `LICZBA_SZTUK` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienie`
--

CREATE TABLE `zamowienie` (
  `NR_ZAMOWIENIA` int(8) NOT NULL,
  `ID_KLIENTA` int(6) NOT NULL,
  `DATA` date NOT NULL,
  `STATUS` enum('ZLOZONE','W REALIZACJI','OPLACONE','ZREALIZOWANE') NOT NULL,
  `TYP_DOSTAWY` char(8) NOT NULL,
  `WARTOSC` double(8,2) NOT NULL,
  `RABAT` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `kategoria_produktu`
--
ALTER TABLE `kategoria_produktu`
  ADD PRIMARY KEY (`KATEGORIA`);

--
-- Indeksy dla tabeli `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`ID_KLIENTA`);

--
-- Indeksy dla tabeli `pozycja_zamowienia`
--
ALTER TABLE `pozycja_zamowienia`
  ADD PRIMARY KEY (`ID_PRODUKTU`,`NR_ZAMOWIENIA`),
  ADD KEY `NR_ZAMOWIENIA` (`NR_ZAMOWIENIA`);

--
-- Indeksy dla tabeli `produkt`
--
ALTER TABLE `produkt`
  ADD PRIMARY KEY (`ID_PRODUKTU`),
  ADD KEY `KATEGORIA` (`KATEGORIA`);

--
-- Indeksy dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD PRIMARY KEY (`NR_ZAMOWIENIA`),
  ADD KEY `ID_KLIENTA` (`ID_KLIENTA`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pozycja_zamowienia`
--
ALTER TABLE `pozycja_zamowienia`
  ADD CONSTRAINT `pozycja_zamowienia_ibfk_1` FOREIGN KEY (`ID_PRODUKTU`) REFERENCES `produkt` (`ID_PRODUKTU`),
  ADD CONSTRAINT `pozycja_zamowienia_ibfk_2` FOREIGN KEY (`NR_ZAMOWIENIA`) REFERENCES `zamowienie` (`NR_ZAMOWIENIA`);

--
-- Constraints for table `produkt`
--
ALTER TABLE `produkt`
  ADD CONSTRAINT `produkt_ibfk_1` FOREIGN KEY (`KATEGORIA`) REFERENCES `kategoria_produktu` (`KATEGORIA`);

--
-- Constraints for table `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD CONSTRAINT `zamowienie_ibfk_1` FOREIGN KEY (`ID_KLIENTA`) REFERENCES `klient` (`ID_KLIENTA`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
