-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2020 at 09:43 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `printersetupsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `branches`
--

CREATE TABLE `branches` (
  `id` int(32) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `description` text NOT NULL,
  `image` varchar(255) NOT NULL DEFAULT '',
  `createddate` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `branches`
--

INSERT INTO `branches` (`id`, `name`, `description`, `image`, `createddate`) VALUES
(1, 'None', 'If not selected any branch, printer will be added to this group', '', 'N/A'),
(2, 'Head Office', 'Printers from Head Office', '', '17:53 17/05/2020'),
(3, 'Toronto branch', 'Branch located in Canada, Toronto.', '', '19.05.2020 00:00');

-- --------------------------------------------------------

--
-- Table structure for table `printers`
--

CREATE TABLE `printers` (
  `id` int(255) NOT NULL,
  `name` text NOT NULL,
  `description` text NOT NULL,
  `image` varchar(255) NOT NULL DEFAULT '',
  `branchid` int(32) NOT NULL DEFAULT 1,
  `ip` text NOT NULL DEFAULT '',
  `vendor` text NOT NULL DEFAULT '',
  `createddate` text NOT NULL DEFAULT '',
  `printertypeid` int(5) NOT NULL DEFAULT 1,
  `views` int(255) NOT NULL DEFAULT 0,
  `serversharename` text NOT NULL DEFAULT 'None'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `printers`
--

INSERT INTO `printers` (`id`, `name`, `description`, `image`, `branchid`, `ip`, `vendor`, `createddate`, `printertypeid`, `views`, `serversharename`) VALUES
(1, 'HP LaserJet 4350  1st floor', 'Printer on the 1st floor', '', 2, '192.168.100.10', 'HP', '18.05.2020 00:00', 5, 37, '\\\\server01\\printer1'),
(2, 'Xerox WorkCentre 5325 floor6', 'Xerox work centre printer which is located on 6th floor, near legal issues divison. This printer can print 1000 pages in 10 minutes.', '', 2, '192.168.16.16', 'Xerox', '19.05.2020 00:00', 3, 28, '\\\\server01\\printer2'),
(3, 'HP Color Laserjet 1224 floor3', 'Color printer for all purposes which can print using A4, A3 and A5 type papers. ', '', 3, '192.168.67.67', 'HP', '19.05.2020 00:00', 7, 14, 'None'),
(4, 'SomePrinter 3456 floor7', 'Some printer on the 7th floor, which can printer 1000 pages per minute', '', 3, '192.168.99.99', 'Unknown', '20.05.2020 00:00', 9, 6, '\\\\printerserver02\\printer9');

-- --------------------------------------------------------

--
-- Table structure for table `printerstype`
--

CREATE TABLE `printerstype` (
  `id` int(32) NOT NULL,
  `type` text NOT NULL DEFAULT '',
  `createddate` text NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `printerstype`
--

INSERT INTO `printerstype` (`id`, `type`, `createddate`) VALUES
(1, 'None', '18.05.2020 00:00'),
(2, 'Laser Printer', '18.05.2020 00:00'),
(3, 'Solid Ink Printer', '18.05.2020 00:00'),
(4, 'LED Printer', '18.05.2020 00:00'),
(5, 'Business InkJet Printer', '18.05.2020 00:00'),
(6, 'Home Office Printer', '18.05.2020 00:00'),
(7, 'Multifunction printer', '18.05.2020 00:00'),
(8, 'All-in-One InkJet', '18.05.2020 00:00'),
(9, 'Dot Matrix', '18.05.2020 00:00'),
(10, 'A3 Printer', '18.05.2020 00:00'),
(11, '3D Printer', '18.05.2020 00:00');

-- --------------------------------------------------------

--
-- Table structure for table `systemsettings`
--

CREATE TABLE `systemsettings` (
  `id` int(5) NOT NULL,
  `parameter` text NOT NULL DEFAULT '',
  `value` longtext NOT NULL DEFAULT '\'\\\'\\\'\''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `systemsettings`
--

INSERT INTO `systemsettings` (`id`, `parameter`, `value`) VALUES
(1, 'installscript', ''),
(2, 'installscriptextension', ''),
(3, 'helpmanual', '<p>To be able to install printer please follow instructions below.</p><ul><li>1</li><li>2</li><li>3</li><li>4</li><li>5</li><li>6</li></ul><p style=\"text-align: left;\">Thank you!&nbsp;</p><p style=\"text-align: left;\"><br></p><p style=\"text-align: left;\"></p>');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(32) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` text NOT NULL,
  `lastlogindate` text NOT NULL DEFAULT '',
  `passwordsalt` varchar(255) NOT NULL DEFAULT '',
  `session` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `fullname`, `lastlogindate`, `passwordsalt`, `session`) VALUES
(1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Administrator', '2020-05-22 17:06', '', 'e642b784d932072ed9842e10275aa4cb30dc1790bdfa38b9ee118d09358055a3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `branches`
--
ALTER TABLE `branches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_2` (`id`);

--
-- Indexes for table `printers`
--
ALTER TABLE `printers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_2` (`id`),
  ADD KEY `branchid` (`branchid`),
  ADD KEY `printertypeid` (`printertypeid`),
  ADD KEY `views` (`views`);

--
-- Indexes for table `printerstype`
--
ALTER TABLE `printerstype`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_2` (`id`);

--
-- Indexes for table `systemsettings`
--
ALTER TABLE `systemsettings`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_2` (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_2` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `branches`
--
ALTER TABLE `branches`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `printers`
--
ALTER TABLE `printers`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `printerstype`
--
ALTER TABLE `printerstype`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `systemsettings`
--
ALTER TABLE `systemsettings`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
