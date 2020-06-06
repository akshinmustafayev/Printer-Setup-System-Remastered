-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2020 at 05:53 PM
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
CREATE DATABASE IF NOT EXISTS `printersetupsystem` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `printersetupsystem`;

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
(1, 'None', 'If not selected any branch, printer will be added to this group', NULL, '');

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
(1, 'None', '');

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
(1, 'installscript', 'printerName = \"%PRINTER_SHARE_NAME%\"\r\n\r\nSet objExplorer = CreateObject(\"InternetExplorer.Application\")\r\n\r\nobjExplorer.Navigate \"about:blank\"  \r\nobjExplorer.ToolBar = 0\r\nobjExplorer.StatusBar = 0\r\nobjExplorer.Left = 500\r\nobjExplorer.Top = 250\r\nobjExplorer.Width = 500\r\nobjExplorer.Height = 220\r\nobjExplorer.Visible = 1\r\n\r\nobjExplorer.Document.Title = \"Installation of the printer\"\r\nobjExplorer.Document.Body.InnerHTML = \"<table style=\"\"width:100%\"\"><tr><td id=\"\"progress\"\" style=\"\"font-family:Segoe UI;text-align: center;font-size:48px;border-bottom:1px solid black;\"\">Installing printer: 0%</td></tr><tr><td style=\"\"font-family:Segoe UI;text-align: center;font-size:22px;\"\">\" & printerName & \"</td></tr></table>\"\r\n\r\nWscript.Sleep 500\r\nobjExplorer.document.getElementById(\"progress\").innerText = \"Installing printer: 10%\"\r\n\r\nSet WshNetwork = CreateObject(\"WScript.Network\")\r\nWshNetwork.AddWindowsPrinterConnection printerName\r\nWSHNetwork.SetDefaultPrinter printerName\r\n\r\nWscript.Sleep 200\r\nobjExplorer.document.getElementById(\"progress\").innerText = \"Installing printer: 20%\"\r\n\r\nWscript.Sleep 200\r\nobjExplorer.document.getElementById(\"progress\").innerText = \"Installing printer: 40%\"\r\n\r\nWscript.Sleep 200\r\nobjExplorer.document.getElementById(\"progress\").innerText = \"Installing printer: 60%\"\r\n\r\nWscript.Sleep 200\r\nobjExplorer.document.getElementById(\"progress\").innerText = \"Installing printer: 80%\"\r\n\r\nWscript.Sleep 100\r\nobjExplorer.document.getElementById(\"progress\").innerText = \"Printer installed!\"\r\n\r\nWscript.Sleep 3000\r\nobjExplorer.Quit'),
(2, 'installscriptextension', 'vbs'),
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
(1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Administrator', '', '', '');

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
