-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mer 20 Septembre 2017 à 14:57
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `evenementbd`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `code` int(255) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `etat` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`code`, `nom`, `etat`) VALUES
(1, 'categorie 1', 0),
(2, 'cat2', 0),
(3, 'cat 3', 0),
(4, 'aaaa', 0),
(5, 'aaaa', 0),
(6, 'aaaa', 0),
(7, 'aaaa', 0),
(8, 'uu', 0),
(9, 'aaa', 0),
(10, 'aaaa', 0),
(11, 'aaaa', 0),
(12, 'aaaa', 0),
(13, 'aaaa', 0),
(14, 'aaaa', 0),
(15, 'uu', 0),
(16, 'uu', 0),
(17, 'aaa', 0),
(18, 'Categorie 1', 0),
(19, 'Categorie 12', 0),
(20, 'Categorie 3', 1),
(21, 'aaaa', 1),
(22, 'uu', 0),
(23, 'aaaa', 0);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(255) UNSIGNED NOT NULL,
  `titre_evenement` varchar(100) CHARACTER SET utf8 NOT NULL,
  `nom_lieu` varchar(50) CHARACTER SET utf8 NOT NULL,
  `adresse` varchar(255) CHARACTER SET utf8 NOT NULL,
  `codepostal` varchar(10) NOT NULL,
  `ville` varchar(30) CHARACTER SET utf8 NOT NULL,
  `latitude` varchar(30) CHARACTER SET utf8 NOT NULL,
  `longitude` varchar(30) CHARACTER SET utf8 NOT NULL,
  `date` datetime NOT NULL,
  `nom` varchar(60) CHARACTER SET utf8 NOT NULL,
  `prenom` varchar(60) CHARACTER SET utf8 NOT NULL,
  `tel` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(60) CHARACTER SET utf8 NOT NULL,
  `code_conseillere` varchar(20) CHARACTER SET utf8 NOT NULL,
  `date_creation` datetime NOT NULL,
  `valide` tinyint(1) NOT NULL DEFAULT '0',
  `categorie` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `evenement`
--

INSERT INTO `evenement` (`id`, `titre_evenement`, `nom_lieu`, `adresse`, `codepostal`, `ville`, `latitude`, `longitude`, `date`, `nom`, `prenom`, `tel`, `email`, `code_conseillere`, `date_creation`, `valide`, `categorie`) VALUES
(13, 'mode evenet', 'mode evenet mode evenet', 'mode evenet', '33000', 'bORDEAUX', '44.8350088', '-0.5872690', '2017-09-15 00:00:00', 'nom', 'prenom', '06 24 41 65 84', 'marie.louiz@gmail.com', '0100001', '2017-09-12 15:39:01', 0, 'Categorie 1'),
(12, 'collection été', 'chez mariane', '23 saint louis', '75000', 'paris', '44.8350088', '-0.5872690', '2017-11-24 00:00:00', 'nom', 'prenom', '06 96 36 96 52', 'anais.lemarchand@yahoo.fr', '0100001', '2017-09-12 15:29:37', 1, 'Categorie 1'),
(11, 'Shopping luxious', 'Vente privèe robe de luxe', '13 rue de bardanac', '75000', 'Paris', '44.8350088', '-0.5872690', '2017-09-03 15:00:00', 'nom', 'prenom', '07 52 25 45 85', 'marie.ani@gmail.com', '0100001', '2017-09-12 15:27:46', 0, 'Categorie 3'),
(10, 'vide dressing mari jo', 'Maison', 'Gambetta', '33000', 'Bordeaux', '44.8350088', '-0.5872690', '2017-09-23 00:00:00', 'nom', 'prenom', '06 24 41 65 90', 'francoise.lebone@gmail.com', '0100001', '2017-09-12 15:26:14', 0, 'Categorie 1'),
(9, 'Vente privèe robe de luxe', 'ibis hotel', '31 rue du canada', '13100', 'Marseille', '44.8350088', '-0.5872690', '2017-09-09 00:00:00', 'nom', 'prenom', '06 24 41 65 84', 'marie.louiz@gmail.com', '0100001', '2017-09-12 15:25:00', 0, 'Categorie 1'),
(14, 'Mode EVENT', 'Ccalifornia cafè', '32 anne sof', 'bordeaux', '33000', '44.8350088', '-0.5872690', '2017-09-16 00:00:00', 'nom', 'prenom', '06 24 41 65 84', 'marie.louiz@gmail.com', '0100001', '2017-09-12 15:40:01', 0, 'Categorie 3'),
(15, 'aaaa', 'aaa', 'aaa', 'aa', 'aaa', '44.8350088', '-0.5872690', '2017-09-13 00:00:00', 'nom', 'prenom', '06 24 41 65 84', 'aaa@gmail.com', '0100001', '2017-09-12 15:47:25', 0, 'Categorie 1'),
(16, 'aaaa', 'aaa', 'aaa', 'aa', 'aaa', '44.8350088', '-0.5872690', '2017-09-06 00:00:00', 'nom', 'prenom', '06 24 41 65 84', 'aaa@gmail.com', '0100001', '2017-09-12 15:47:37', 0, 'Categorie 3');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`code`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `code` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
