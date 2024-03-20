-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2021 at 06:08 PM
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
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `password`, `username`) VALUES
(2, '$2a$10$VWpOaEIzvYn3v3KagH2zz.RrjawlOZVrARKuyPIWybO6vJ3x11xAm', 'user'),
(3, '$2a$10$ckdpvlQ5IqRsz0RXT5BIYuTXq8.f0VIfeZj0iCmCzys/3rileKV9e', 'admin'),
(4, '$2a$10$VgzpGeXbt8iIlo1l3XQ.bO1eZ.7lsoxErJIVY4vouGyoigCQcjClq', 'petras'),
(5, '$2a$10$EmplsPqAFFbAJzikFwHugOmzybzMbnJfEBmvWZa6wycL4qcpLBtSa', 'jonas'),
(6, '$2a$10$2PaZxIcurMwJwxjHx008weG/iaulA5N5Q2HlYFKCrpuoFSNshAJnK', 'antanas'),
(7, '$2a$10$tqnzELvRlwWxjSIP4ugYOOBNg9xW9XFUaADsaWcieA0s/zkGVRohm', 'ona'),
(8, '$2a$10$CogGrfUG7pk.Wg93fqAqeui6.8KTfVL3Rw9AQzC7NnDfaf8.L1GRK', 'jonukas'),
(9, '$2a$10$6/meHcLyEF7VnRGF/jp8dOr8dAD/BpDe1LAs.c1ErGbJ5ZL5KEzM2', 'lova'),
(10, '$2a$10$ks/XbMstqAuPqjx9UfqpaOPCE9LncZSj5H707Oqg0OZpZb0k6suDe', 'lova2'),
(11, '$2a$10$ZbqF5jajyKj/pFUp0eNRNO4WZRPtcV8NFoyx.zqWQt7f3Qbf5nV0q', 'bamba'),
(12, '$2a$10$sYZz55h3PYeJX0m/u7ANguH7CxW5AWCCqjPRMR7lom1d6n0qq7nDW', 'antans'),
(13, '$2a$10$ZbhrcPTW16I1eXWm4Tk/b.IwUbHKn3JZkQpN9sqK2PbETqU5Az4N2', 'penktas'),
(14, '$2a$10$hiIwGV8sXAwuus7MfBT6EeES8TsccaEs6.Iqa/V9sBzj9Bw66O14u', 'penktad'),
(15, '$2a$10$8y5mH6ca.m8NdUS68HM0EuYa2wfTDUGt6bnRUVzAa9kpWM7BzOYyO', 'dddddd'),
(16, '$2a$10$.I4yPnyTlWVyLAA2pynnUur91UZWlCuGj4SK.NMfoYxIczbvAH7K.', 'ddddddd'),
(17, '$2a$10$5mLtjqDinafqTtK8CdZR..d7agqCddgfJN1iaMcEYuRYrkQpRPo0S', 'algis');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
