-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2021 at 06:07 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db`
--

-- --------------------------------------------------------

--
-- Table structure for table `skaiciai`
--

CREATE TABLE `skaiciai` (
  `id` int(11) NOT NULL,
  `rezult` int(11) DEFAULT NULL,
  `sk1` int(11) DEFAULT NULL,
  `sk2` int(11) DEFAULT NULL,
  `zenklas` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `skaiciai`
--

INSERT INTO `skaiciai` (`id`, `rezult`, `sk1`, `sk2`, `zenklas`) VALUES
(16, 20, 10, 10, '+'),
(17, 20, 10, 10, '+'),
(18, 20, 10, 10, '+'),
(19, 20, 10, 10, '+'),
(23, 2000, 1000, 1000, '+'),
(25, 10, 5, 5, '+'),
(27, 9, 3, 3, '*'),
(28, 3, 2, 1, '+'),
(29, 66, 33, 33, '+'),
(30, 4761, 69, 69, '*'),
(31, 4, 2, 2, '*'),
(32, 3, 2, 1, '+'),
(33, 0, 0, 0, '+'),
(34, 3, 3, 0, '+'),
(35, 4, 2, 2, '+'),
(36, 20, 5, 4, '*'),
(37, 2, 2, 1, '*');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `skaiciai`
--
ALTER TABLE `skaiciai`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `skaiciai`
--
ALTER TABLE `skaiciai`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
