-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2015 at 10:43 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `productdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `announcement`
--

CREATE TABLE IF NOT EXISTS `announcement` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `date` varchar(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  `postby` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `announcement`
--

INSERT INTO `announcement` (`id`, `date`, `content`, `postby`) VALUES
(1, '2014-04-13', 'Today we have 3 special stuff', 'amey kelekar'),
(2, '2014-04-13', 'Today we have 3 special stuff', 'amey kelekar'),
(3, '2014-04-13', 'Today we have 3 special stuff', 'amey kelekar'),
(4, '2015-47-22 17:47:11', 'new product in the market', 'amey kelekar');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE IF NOT EXISTS `cart` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `productid` bigint(20) unsigned NOT NULL,
  `name` varchar(20) NOT NULL,
  `price` decimal(9,2) NOT NULL,
  `quantity` int(10) NOT NULL,
  `description` varchar(255) NOT NULL,
  `flag` tinyint(1) NOT NULL,
  `username` varchar(20) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `productid`, `name`, `price`, `quantity`, `description`, `flag`, `username`, `note`) VALUES
(18, 3, 'Subway Ticket', '50.00', 2, 'Use it to travell Boston', 0, 'abhay', 'I need two tickets to Boston'),
(19, 2, 'JAVA Book', '29.98', 2, 'Teach you JAVA', 0, 'abhay', 'Good book for JAVA and need it urgently'),
(20, 1, 'Dell', '817.36', 1, 'Dell PC, good stuff', 1, 'meenal', 'Looks a good laptop'),
(21, 2, 'JAVA Book', '29.98', 2, 'Teach you JAVA', 1, 'meenal', 'Need 2 books'),
(23, 2, 'JAVA Book', '74.95', 5, 'Teach you JAVA', 1, 'Sabrishm1@', 'NA'),
(29, 4, 'Acer', '720.55', 1, 'New Acer Laptop', 1, 'sab23eorpe', '23'),
(30, 2, 'JAVA Book', '75.00', 5, 'Teach you JAVA', 0, 'sabrishmenon12334', '3'),
(32, 4, 'Acer', '3602.75', 5, 'New Acer Laptop', 0, 'sabrishmenon12334', 'sss');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `productname` varchar(255) NOT NULL,
  `productprice` decimal(5,2) NOT NULL,
  `status` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  `requesteddate` varchar(255) NOT NULL,
  `updatedate` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`id`, `username`, `address`, `email`, `productname`, `productprice`, `status`, `note`, `requesteddate`, `updatedate`, `quantity`) VALUES
(1, 'abhay', 'Northeastern University', 'abhaykelekar1@gmail.com', 'Dell', '817.36', 'Received', 'send me soon', '2013-09-07', '2015-19-24 14:19:13', 1),
(2, 'abhay', 'Northeastern University', 'abhaykelekar1@gmail.com', 'Dell', '817.36', 'Waiting', 'send me soon', '2013-09-17', '2013-09-07', 1),
(3, 'meenal', 'Northeastern University', 'meenalkelekar1@gmail.com', 'Dell', '817.36', 'Waiting', 'send me soon', '2013-12-22', '2013-09-07', 1),
(4, 'meenal', 'Northeastern University', 'abhaykelekar4@yahoo.com', 'Dell', '817.36', 'Waiting', 'Looks a good laptop', '2015-33-23 20:33:23', '2015-33-23 20:33:23', 1),
(5, 'meenal', 'Northeastern University', 'abhaykelekar4@yahoo.com', 'JAVA Book', '29.98', 'Waiting', 'Need 2 books', '2015-33-23 20:33:23', '2015-33-23 20:33:23', 2);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payment_amount` float NOT NULL,
  `card_type` varchar(255) NOT NULL,
  `card_number` varchar(255) NOT NULL,
  `cvv` varchar(255) NOT NULL,
  `emailid` varchar(255) NOT NULL,
  `member_name` varchar(255) NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `UK_dvmcdsv23drs96d0d0vh8u7ly` (`card_type`,`card_number`,`cvv`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_id`, `payment_amount`, `card_type`, `card_number`, `cvv`, `emailid`, `member_name`) VALUES
(1, 847.34, 'visa', '1111222233334444', '123', 'abhaykelekar4@yahoo.com', 'abhay Kelekar'),
(3, 720.55, 'express', '1234561121111111', '000', 'me@123.qer', 'hello'),
(4, 720.55, 'express', '1234561121111111', '444', 'me@123.qertyeuwiqoeiiouieouoiqueq', 'hello'),
(5, 720.55, 'visa', '1234561121111111', '997', 'sa123@0', 'hello'),
(6, 720.55, 'visa', '1234561121111111', '798', 'sa123@0laaaa', 'hello'),
(7, 720.55, 'discover', '1234561121111111', '121', 'sa123@0laaaa', 'hello');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `description`) VALUES
(1, 'Dell', '817.36', 'Dell PC, good stuff'),
(2, 'JAVA Book', '15.00', 'Teach you JAVA'),
(3, 'Subway Ticket', '25.00', 'Use it to travell Boston'),
(4, 'Acer', '720.55', 'New Acer Laptop');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `name`, `password`, `email`, `address`, `role`) VALUES
(1, 'ameykelekar', 'amey kelekar', 'kelekar123', 'ameykelekar2@gmail.com', 'Northeastern University', 'manager'),
(2, 'abhaykelekar', 'abhay kelekar', 'kelekar123', 'abhaykelekar@gmail.com', 'Northeastern University', 'customer'),
(3, 'meenalkelekar', 'meenal kelekar', 'kelekar123', 'meenalkelekar2@gmail.com', 'Northeastern University', 'customer'),
(4, 'sabrishmenon', 'Sabrish Menon', 'kelekar123', 'menon.sab@husky.neu.edu', '123 Boston, MA', 'customer'),
(5, 'nevillefernandez', 'neville fernandez', 'kelekar123', 'sabri12@ert.co.in', 'Cambridge', 'customer'),
(7, 'derylrodrigues', 'Deryl Rodrigues', 'deryl@123', 'derylrodrigues@gmail', 'Boston', 'customer'),
(9, 'PravvenKumar', 'Praveen Kumar', 'kelekar1234', 'praveen@gail.com', 'mumbai', 'customer'),
(10, 'sabrishmenon12334', 'Sabrish Menon', 'sabrishmenon', 'sabrishmenon@live', 'Dahisar', 'customer');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
