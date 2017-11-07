-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 27 Lut 2015, 15:48
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kilometrowka`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `firmy`
--

CREATE TABLE IF NOT EXISTS `firmy` (
  `id_firmy` int(11) NOT NULL AUTO_INCREMENT,
  `id_uzytkownika` int(11) NOT NULL,
  `nazwa_firmy` varchar(64) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `adres` varchar(64) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `miasto` varchar(64) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `kod_pocztowy` varchar(16) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `nip` varchar(32) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `regon` varchar(32) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id_firmy`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `logi`
--

CREATE TABLE IF NOT EXISTS `logi` (
  `id_logu` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `akcja` int(11) NOT NULL,
  `ip` varchar(64) COLLATE utf8_polish_ci NOT NULL,
  `useragent` varchar(256) COLLATE utf8_polish_ci NOT NULL,
  `tresc` varchar(256) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id_logu`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=173 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pojazdy`
--

CREATE TABLE IF NOT EXISTS `pojazdy` (
  `id_pojazdu` int(11) NOT NULL AUTO_INCREMENT,
  `id_uzytkownika` int(11) NOT NULL,
  `nazwa_pojazdu` varchar(128) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `numer_rejestracyjny` varchar(16) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `pojemnosc_silnika` int(11) NOT NULL,
  `opis` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id_pojazdu`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownicy`
--

CREATE TABLE IF NOT EXISTS `pracownicy` (
  `id_pracownika` int(11) NOT NULL AUTO_INCREMENT,
  `id_uzytkownika` int(11) NOT NULL,
  `imie` varchar(64) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` varchar(64) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `adres` varchar(64) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `miasto` varchar(64) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `kod_pocztowy` varchar(16) CHARACTER SET utf32 COLLATE utf32_polish_ci NOT NULL,
  `telefon` varchar(32) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id_pracownika`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przejazdy`
--

CREATE TABLE IF NOT EXISTS `przejazdy` (
  `id_przejazdu` int(11) NOT NULL AUTO_INCREMENT,
  `id_uzytkownika` int(11) NOT NULL,
  `id_pojazdu` int(11) NOT NULL,
  `id_trasy` int(11) NOT NULL,
  `id_firmy` int(11) NOT NULL,
  `id_pracownika` int(11) NOT NULL,
  `cel_wyjazdu` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `adnotacje` text CHARACTER SET utf8 COLLATE utf8_polish_ci,
  `data` date NOT NULL,
  PRIMARY KEY (`id_przejazdu`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci AUTO_INCREMENT=140 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `trasy`
--

CREATE TABLE IF NOT EXISTS `trasy` (
  `id_trasy` int(11) NOT NULL AUTO_INCREMENT,
  `id_uzytkownika` int(11) NOT NULL,
  `nazwa_trasy` varchar(128) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `miejscowosc_zrodlowa` varchar(64) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `miejscowosc_docelowa` varchar(64) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `odleglosc` int(11) NOT NULL,
  `opis` text CHARACTER SET utf8 COLLATE utf8_polish_ci,
  PRIMARY KEY (`id_trasy`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE IF NOT EXISTS `uzytkownicy` (
  `id_uzytkownika` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(32) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `haslo` varchar(32) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `sol` int(11) NOT NULL,
  PRIMARY KEY (`id_uzytkownika`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=51 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
