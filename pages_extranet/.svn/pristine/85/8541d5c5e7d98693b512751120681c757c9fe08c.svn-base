-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mer 20 Septembre 2017 à 14:49
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `db_affectation`
--

-- --------------------------------------------------------

--
-- Structure de la table `candidate`
--

CREATE TABLE `candidate` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `lang` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `candidate`
--

INSERT INTO `candidate` (`id`, `adresse`, `lang`, `longitude`, `name`, `prenom`) VALUES
(1, '545 Rue Henry Delaunay, 13591 Aix-en-Provence', 'aaa', 'aaa', 'aaa', 'aa');

-- --------------------------------------------------------

--
-- Structure de la table `conseillere`
--

CREATE TABLE `conseillere` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `conseillere`
--

INSERT INTO `conseillere` (`id`, `adresse`, `name`, `prenom`) VALUES
(1, 'Square Narvik, 13232 Marseille', 'aaa', 'aaa');

-- --------------------------------------------------------

--
-- Structure de la table `distancemax`
--

CREATE TABLE `distancemax` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `distance_max` varchar(255) DEFAULT NULL,
  `distmax_maraine` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1),
(1),
(1),
(1);

-- --------------------------------------------------------

--
-- Structure de la table `parametter`
--

CREATE TABLE `parametter` (
  `id` bigint(20) NOT NULL,
  `namep` varchar(255) DEFAULT NULL,
  `valuep` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `candidate`
--
ALTER TABLE `candidate`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `conseillere`
--
ALTER TABLE `conseillere`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `distancemax`
--
ALTER TABLE `distancemax`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `parametter`
--
ALTER TABLE `parametter`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
