-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 20, 2026 at 07:28 PM
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
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE `klient` (
  `NrKlienta` int(6) NOT NULL,
  `Nazwisko` varchar(20) NOT NULL,
  `Adres` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `klient`
--

INSERT INTO `klient` (`NrKlienta`, `Nazwisko`, `Adres`) VALUES
(1111, 'Nowak', 'Warszawska 10'),
(1112, 'Kowalski', 'Pawia 2');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient_konto`
--

CREATE TABLE `klient_konto` (
  `NrKlienta` int(6) NOT NULL,
  `NrKonta` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `klient_konto`
--

INSERT INTO `klient_konto` (`NrKlienta`, `NrKonta`) VALUES
(1111, 2222);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `konto`
--

CREATE TABLE `konto` (
  `NrKonta` int(8) NOT NULL,
  `Saldo` float(8,2) NOT NULL,
  `Typ` enum('firmowe','indywidualne') NOT NULL,
  `DataOtwarcia` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `konto`
--

INSERT INTO `konto` (`NrKonta`, `Saldo`, `Typ`, `DataOtwarcia`) VALUES
(2222, 1234.56, 'indywidualne', '2026-04-20');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`NrKlienta`);

--
-- Indeksy dla tabeli `klient_konto`
--
ALTER TABLE `klient_konto`
  ADD PRIMARY KEY (`NrKlienta`,`NrKonta`),
  ADD KEY `NrKonta` (`NrKonta`);

--
-- Indeksy dla tabeli `konto`
--
ALTER TABLE `konto`
  ADD PRIMARY KEY (`NrKonta`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `klient_konto`
--
ALTER TABLE `klient_konto`
  ADD CONSTRAINT `klient_konto_ibfk_1` FOREIGN KEY (`NrKlienta`) REFERENCES `klient` (`NrKlienta`),
  ADD CONSTRAINT `klient_konto_ibfk_2` FOREIGN KEY (`NrKonta`) REFERENCES `konto` (`NrKonta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
