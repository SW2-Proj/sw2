-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20220503.92457c1607
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2022 at 06:41 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parking`
--

-- --------------------------------------------------------

--
-- Table structure for table `freespots`
--

CREATE TABLE `freespots` (
  `spot` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `freespots`
--

INSERT INTO `freespots` (`spot`) VALUES
(4),
(5),
(6),
(7),
(8),
(3);

-- --------------------------------------------------------

--
-- Table structure for table `parkedcar`
--

CREATE TABLE `parkedcar` (
  `spot` int(11) NOT NULL,
  `platenum` text NOT NULL,
  `starttime` time DEFAULT NULL,
  `endtime` time DEFAULT NULL,
  `totaltime` time DEFAULT NULL,
  `payment` float DEFAULT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parkedcar`
--

INSERT INTO `parkedcar` (`spot`, `platenum`, `starttime`, `endtime`, `totaltime`, `payment`, `id`) VALUES
(3, 'Fady333', '00:00:00', NULL, NULL, NULL, 2001),
(4, 'Merna555', NULL, NULL, NULL, NULL, 2002);

-- --------------------------------------------------------

--
-- Table structure for table `totalcars`
--

CREATE TABLE `totalcars` (
  `id` int(11) NOT NULL,
  `spot` int(11) NOT NULL,
  `starttime` time NOT NULL,
  `endtime` time NOT NULL,
  `totaltime` time NOT NULL,
  `platenum` varchar(45) NOT NULL,
  `payment` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `totalcars`
--

INSERT INTO `totalcars` (`id`, `spot`, `starttime`, `endtime`, `totaltime`, `platenum`, `payment`) VALUES
(2003, 3, '05:51:55', '05:53:39', '00:01:44', 'Merna2001', 0.144444);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `parkedcar`
--
ALTER TABLE `parkedcar`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `totalcars`
--
ALTER TABLE `totalcars`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



