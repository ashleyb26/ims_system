-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 14, 2023 at 02:33 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ims_system`
--
CREATE DATABASE IF NOT EXISTS `ims_system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ims_system`;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `quantity`) VALUES
(1, 'Asus Monitor TUF VG-9Q', 759.00, 100),
(2, 'Logitech Keyboard K120', 20, 50),
(3, 'HP Wireless Mouse X3000', 15.99, 30),
(4, 'Samsung SSD 500GB', 99.99, 20),
(5, 'Canon PIXMA MX492 Printer', 69.95, 15),
(6, 'Dell Ultrasharp U2719D Monitor', 349.99, 80),
(7, 'Corsair K95 RGB Platinum XT Keyboard', 199.99, 25),
(8, 'Razer DeathAdder Elite Gaming Mouse', 69.99, 40),
(9, 'Western Digital WD Black 1TB HDD', 54.99, 30),
(10, 'Epson EcoTank ET-2760 All-in-One Printer', 249.99, 10),
(11, 'LG 27GL83A-B Gaming Monitor', 379.99, 60),
(12, 'Microsoft Sculpt Ergonomic Keyboard', 79.99, 15);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
