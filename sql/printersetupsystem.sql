-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2020 at 01:26 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

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
  `image` mediumblob DEFAULT NULL,
  `createddate` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `branches`
--

INSERT INTO `branches` (`id`, `name`, `description`, `image`, `createddate`) VALUES
(1, 'None', 'If not selected any branch, printer will be added to this group', NULL, '2020-06-04 00:45'),
(7, 'Head Office', 'Main office of company', '', '2020-06-04 00:45'),
(8, 'Toronto branch', 'Branch located in Toronto', '', '2020-06-04 00:45');

-- --------------------------------------------------------

--
-- Table structure for table `printers`
--

CREATE TABLE `printers` (
  `id` int(255) NOT NULL,
  `name` text NOT NULL,
  `description` text NOT NULL DEFAULT 'None',
  `image` mediumblob DEFAULT NULL,
  `branchid` int(32) NOT NULL DEFAULT 1,
  `ip` text NOT NULL DEFAULT '',
  `vendor` text NOT NULL DEFAULT 'Undefined',
  `createddate` text NOT NULL DEFAULT '',
  `printertypeid` int(5) NOT NULL DEFAULT 1,
  `views` int(255) NOT NULL DEFAULT 0,
  `serversharename` text NOT NULL DEFAULT 'None',
  `location` text NOT NULL DEFAULT 'None',
  `customfield1` text NOT NULL DEFAULT '\'\''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `printers`
--

INSERT INTO `printers` (`id`, `name`, `description`, `image`, `branchid`, `ip`, `vendor`, `createddate`, `printertypeid`, `views`, `serversharename`, `location`, `customfield1`) VALUES
(12, 'HP LaserJet 4350 1floor', 'Printer on the first floor near first department', '', 7, '192.168.0.11', 'HP', '2020-06-04 00:31', 8, 4, '\\\\serverasd\\\\printer01', '1st floor', ''),
(13, 'Canon 1250 4floor', 'Canon printer on 4th floor near HR department', '', 8, '192.168.10.10', 'Canon', '2020-06-04 00:32', 10, 2, '\\\\serverasd\\\\printer05', '4th floor', ''),
(14, 'Xerox WorkCentre 5325 floor7', 'Xerox printer located on 7th floor', '', 8, '192.168.7.7', 'Xerox', '2020-06-04 00:33', 1, 8, '\\\\serverasd\\\\printer09', '7th floor', ''),
(15, 'Some Printer 5 floor', 'Unknown vendor printer', '', 7, '192.168.16.16', 'Undefined', '2020-06-04 00:34', 4, 3, '\\\\serverasd\\\\printer07', '5th floor', '');

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
(1, 'installscript', '#SOME SCRIPT\r\n\r\nName = \"%PRINTER_NAME%\"\r\nShare name = \"%PRINTER_SHARE_NAME%\"\r\nCustom Field 1 = \"%PRINTER_CUSTOM_FIELD1%\"\r\n\r\nFUNCTION AAA()\r\n{\r\nint a = 0;\r\nint b = 1; int c=4;\r\nreturn a+b-c;\r\n}\r\n\r\nAAA();\'?\';'),
(2, 'installscriptextension', 'bat'),
(3, 'helpmanual', '<p>To be able to install printer please follow instructions below.</p><ul><li>1</li><li>2</li><li>3</li><li>4</li><li>5</li><li>6</li></ul><p style=\"text-align: left;\">Thank you!</p><p style=\"text-align: left;\"></p>');

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
(1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Administrator', '2020-06-04 14:45', '', '522e67bafc764a6c2e9ac19b6c9b7bd73f53e94683f68058e1d8e86d9009574'),
(7, 'user', '4f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', 'user', '2020-06-02 22:44', '', 'e68693b9a0bd5d0f41b57f655d450d2d8a2d6eb08665a2400bfcf78dafd66a64');

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
  ADD UNIQUE KEY `login` (`login`),
  ADD KEY `id_2` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `branches`
--
ALTER TABLE `branches`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `printers`
--
ALTER TABLE `printers`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `printerstype`
--
ALTER TABLE `printerstype`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `systemsettings`
--
ALTER TABLE `systemsettings`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
