-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 06 déc. 2023 à 07:33
-- Version du serveur : 5.7.36
-- Version de PHP : 8.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `csharp_data`
--
CREATE DATABASE IF NOT EXISTS `csharp_data` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `csharp_data`;

-- --------------------------------------------------------

--
-- Structure de la table `personnel`
--

DROP TABLE IF EXISTS `personnel`;
CREATE TABLE IF NOT EXISTS `personnel` (
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `service` varchar(50) NOT NULL,
  `sexe` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- Base de données : `database`
--
CREATE DATABASE IF NOT EXISTS `database` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `database`;

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `idclasse` int(11) NOT NULL AUTO_INCREMENT,
  `niveau` varchar(5) NOT NULL,
  PRIMARY KEY (`idclasse`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`idclasse`, `niveau`) VALUES
(18, 'L1 IG');

-- --------------------------------------------------------

--
-- Structure de la table `emploi_du_temps`
--

DROP TABLE IF EXISTS `emploi_du_temps`;
CREATE TABLE IF NOT EXISTS `emploi_du_temps` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idsalle` int(11) NOT NULL,
  `idprof` varchar(50) NOT NULL,
  `idclasse` varchar(50) NOT NULL,
  `cours` varchar(100) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idsalle` (`idsalle`),
  KEY `idprof` (`idprof`),
  KEY `idclasse` (`idclasse`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `emploi_du_temps`
--

INSERT INTO `emploi_du_temps` (`id`, `idsalle`, `idprof`, `idclasse`, `cours`, `date`) VALUES
(27, 28, '100', '18', 'mathematique', '2023-07-14 08:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

DROP TABLE IF EXISTS `professeur`;
CREATE TABLE IF NOT EXISTS `professeur` (
  `idprof` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `grade` varchar(80) NOT NULL,
  PRIMARY KEY (`idprof`)
) ENGINE=MyISAM AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `professeur`
--

INSERT INTO `professeur` (`idprof`, `nom`, `prenom`, `grade`) VALUES
(100, 'RAMANDIMBY', 'Bogosy', 'Docteur HDR');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `idsalle` int(11) NOT NULL AUTO_INCREMENT,
  `design` varchar(255) CHARACTER SET utf8 NOT NULL,
  `occupation` varchar(100) NOT NULL,
  PRIMARY KEY (`idsalle`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`idsalle`, `design`, `occupation`) VALUES
(28, 'Maninday', 'Non');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `idu` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mdp` varchar(50) NOT NULL,
  PRIMARY KEY (`idu`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idu`, `nom`, `prenom`, `email`, `mdp`) VALUES
(12, 'ANDRIANAIVOMANJAKA', 'Tahina', 'bryanandriamanjaka12@gmail.com', '1234'),
(13, 'pala', 'real', 'palareal@gmail.com', '1234');
--
-- Base de données : `datacourrier`
--
CREATE DATABASE IF NOT EXISTS `datacourrier` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `datacourrier`;

-- --------------------------------------------------------

--
-- Structure de la table `archive`
--

DROP TABLE IF EXISTS `archive`;
CREATE TABLE IF NOT EXISTS `archive` (
  `numArchive` int(11) NOT NULL AUTO_INCREMENT,
  `numCourrier` varchar(50) NOT NULL,
  `dateArchive` date NOT NULL,
  PRIMARY KEY (`numArchive`)
) ENGINE=MyISAM AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `entrant`
--

DROP TABLE IF EXISTS `entrant`;
CREATE TABLE IF NOT EXISTS `entrant` (
  `numEntrant` int(50) NOT NULL AUTO_INCREMENT,
  `dateEntrant` date NOT NULL,
  `numCourrier` varchar(50) NOT NULL,
  `origine` varchar(50) NOT NULL,
  `nature` varchar(50) NOT NULL,
  `observation` varchar(50) NOT NULL,
  `destination` varchar(50) NOT NULL,
  PRIMARY KEY (`numEntrant`)
) ENGINE=MyISAM AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `entrant`
--

INSERT INTO `entrant` (`numEntrant`, `dateEntrant`, `numCourrier`, `origine`, `nature`, `observation`, `destination`) VALUES
(91, '2023-11-05', '788', 'ivato', 'dossier', 'non', 'lova'),
(80, '2023-11-08', '544', 'ihaaa', 'ok', 'non', 'oko'),
(90, '2023-11-04', '118', 'CISCO Ambositra', 'taratasy', 'bien', 'Toky'),
(19, '2023-11-02', '11', 'CISCO Ambositra', 'dfs', 'fa', 'lop'),
(82, '2023-11-02', '89', 'fajmnmans', 'wf', 'fsd', 'ffs'),
(83, '2023-11-09', '78', 'ambanidia', 'tara', 'sion', 'aina'),
(86, '2023-11-09', '45', 'Toliara', 'ok', 'da', 'da'),
(95, '2023-12-06', '1588', 'Veuillez selectionner', 'blalal', 'oui', 'toky');

-- --------------------------------------------------------

--
-- Structure de la table `responsable`
--

DROP TABLE IF EXISTS `responsable`;
CREATE TABLE IF NOT EXISTS `responsable` (
  `IM` varchar(50) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Poste` varchar(50) NOT NULL,
  `CIN` varchar(20) NOT NULL,
  `Telephone` int(20) DEFAULT NULL,
  `Adresse` varchar(50) NOT NULL,
  PRIMARY KEY (`IM`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `responsable`
--

INSERT INTO `responsable` (`IM`, `Nom`, `Prenom`, `Poste`, `CIN`, `Telephone`, `Adresse`) VALUES
('302810', 'ANDRIAMAHEFASOA', 'JEAN RENE', 'Chef SGRH', '101231101999', 347318731, 'PRES LOT 018 F SUD CASERNE '),
('301238', 'RAZAFINTSALAMA ', 'OLGA LUCILE HANTANIRINA', 'RESPONSABLE DES  RETRAITES', '203992015952', 343793937, 'LOT 03A64 AMBOHIBARY'),
('326063', 'FENOSOA ', 'NORO MARIE', 'RESPONSABLE PERSONNELS ENCADRES', '201032008335', 344740797, 'LOT 18 D 40 MORAFENO '),
('329491', 'RASOANANTENAINA', 'ANGELINE MARIE LEONCE', 'RESPONSABLE PERSONNELS ENCADRES', '203012011239', 340398871, 'LOT13A6EST VINANY '),
('430621', 'RAKOTONDRAZAKA', 'RAYMOND', 'RESPONSABLE PERSONNEL CONTRACTUEL', '203191001583', 344004914, 'LOT 14IB IAJAKY'),
('335031', 'RAOLITIANA', 'ANITRA', 'RESPONSABLE CONGE', '205992011016', 0, 'rien'),
('1', 'VOLOLONA', 'HANITRINIAINA', 'RESPONSABLE ENF', '203012016184', 349236741, 'LOT 04 C 30 AMBOHIPIERENANA'),
('266775', 'RAKOTONIRINA HARIJAONA ', 'LOVANANTENAINA', 'Chef SAF', '117351000183', 344593651, 'LOT IH9 IAJAKY'),
('370685', 'SOLONANTENAINA', 'TAHINA HERIZO', 'BCAF', '203011020659', 348496127, 'LOT 10B 02 ANDREFANTSENA '),
('317052', 'RAVAONARIVO', 'ARIMANANA', 'COMPTABLE', '203992016868', 344006509, 'LOT 23D13-VOLAFOTSY'),
('379264', 'KENDRY TIANA ', 'BALSAM', 'DEPOSITAIRE COMPTABLE ', '204011012719', 341063344, 'LOT10A8 ANDREFATSENA'),
('379647', 'RALANTONIRINA', 'MARIE CLARISSE', 'RESPONSABLE SOLDE', '203012022875', 344300257, 'LOT 18 A 12 ANKENIHENY'),
('2', 'RAZAFINDRAKOTO', 'RIJA ANGELOS', 'AIDE COMPTABLE', '203011023805', 349394304, 'LOT 01 F 78 ALAKAMISY'),
('431959', 'RAZAFIMAHATRATRA', 'MAMPIONONA EMMANUEL', 'GARDIEN', '219 011 009 540', 34301146, 'AMBOHIPIERENANA AMBOSITRA'),
('406213', 'RALAHIANTENAINA', 'ANDRY HASINA', 'GARDIEN', '201091010658', 341718463, 'DREN  AMORON\'I MANIA'),
('335954', 'OLISOA NOMENJANAHARY', 'FRANCINE', 'FEMME DE MENAGE ET COURRIER', '203012009505', 348637488, 'LOGEMENT LYCEE TECHNIQUE'),
('317055', 'RAVELONARIVOTAHINA', 'SAHONDRA ESTHER', 'RESPONSABLE DES INTRANTS SCOLAIRES ET KIT', '203012012706', 345160887, 'LOT 14A7 IAJAKY'),
('316863', 'RAVOSOA', 'VOLOLONIAINA', 'RESPONSABLE COURRIER', '204032000102', 349236741, 'AMBOHIPIARENANA AMBOSITRA'),
('311827', 'RAFANOMEZANTSOA HARINIRIVO', 'LALAO VICTORINE', 'RESPONSABLE SANTE SCOLAIRE', '203012005971', 349881952, 'LOT 04A15A AMBOHIPIERENANA'),
('312734', 'RAFIDY', 'MARIE ANNICK VICTORIA', 'PRMP', '210012013930', 341985202, 'LOT 01B14 ALAKAMISY AMBOHIMIADANA'),
('301222', 'RAMAROSANDRATANA', 'JOSEPH', 'CHAUFFEUR', '204301012369', 345128045, 'LOT 10A8 ANDRREFATSENA'),
('303932', 'RAZAFINDRAFEHY LANTONANDRASANA', 'SYLVIA CHANTALE', 'RESPONSABLE COURRIER', '501112003310', 346708121, 'AMBOHIPIARENANA AMBOSITRA'),
('305751', 'HANTANIAINA', 'JOHANESA', 'CHAUFFEUR', '203011005824', 347106914, 'LOT 03A22 AMBOHIBARY'),
('310269', 'RAZANABOLOLONA RASOANANDRASANA', 'JULIENNE', 'ARCHIVISTE', '203992014182', 0, 'LOT 10E 44 ANDREFATSENA'),
('426962', 'RAHERIMANDIMBY', 'ANDRY TAHIRINIRINA', 'Chef SGES', '108011009737', 341523725, 'LOT 18 E 22 MORAFENO'),
('284301', 'RALAIVAO', 'BERTRAND HARISON', 'RESPONSABLE DES EXAMENS', '112131000198', 341358410, 'LOT 23 A 71 VOLAFOTSY'),
('277964', 'RAKOTOJOELINA', 'HANITRINIAINA ', 'RESPONSABLE SEPE', '208012001648', 341666799, 'ANDRIAMAMAHANA AMBOSITRA'),
('295934', 'ANDRIANASOLO', 'RUFIN ALPHONSE', 'RESPONSABLE  DE L\'EDUCATION FONDAMENTALE', '203011005478', 341076657, 'LOT 23C93 VOLAFOTSY'),
('371088', 'HARIVELO MIANDRISOA ', 'LAURENTIA EVELINE', 'RESPONSABLE DES EXAMENS', '204012010578', 349340134, 'LOT 9628 AMPIVAROTANOMBY'),
('284354', 'HAINGOKAJA', 'MISAINA LUCIE', 'CONSEILLER PEDAGOGIQUE', '203012009291', 345058328, 'LOT 03A16 AMBOAY-AMBOHIBARY'),
('425901', 'ANDRIANAIVOJAONA', 'NAMBININA TAHINA', 'RESPONSABLE DE L\'EDUCATION NON FORMELLE', '101231110719', 344082074, 'LOT 03D21 AMBOHIBARY'),
('390245', 'JAFETIMAROSOA', 'MBOLATIANA SARAH ANICHA', 'RESPONSABLE ENSEIGNEMENT SECONDAIRE', '111092011908', 349489643, 'LOT 18 B 32 MORAFENO'),
('329283', 'RAJAONARIVELO', 'VOLA MAMPIANINA', 'RESPONSABLE ETABLISSEMENTS PRIVES', '203012007306', 342967207, 'LOT 01A8 AMBOHIMIADANA'),
('297697', 'RANDRIANIRINA', 'ONJA LALAINA', 'RRSS', '101242092397', 348517185, 'LOT A 13 AMBOHIMIADANA'),
('356845', 'RASOARIMALALA', 'VOLATIANA', 'RESPONSABLE CIVISME', '508992015133', 346953219, 'LOT 01D26 NORD CASERNE ALAKAMISY'),
('309253', 'RATSIRAHONANA', 'ZO NIRINA', 'Chef SIEP', '204012006727', 346550529, 'LOT 10 C 28 ANDREFATSENA'),
('284341', 'RAHANTANIRINA', 'FARA ERNESTINE', 'RESPONSABLE ENCADREMENT ET PEDAGOGIQUE', '203992012926', 341731212, 'LOT 10 E 52 A ANDREFATSENA'),
('332090', 'ANDRIAMIARANA', 'BASILYS D\'ANTOINE', 'Chef SCAIIAF', '204011003599', 343748637, 'LOT 198 A BB/CU AMPASINJAZA FANDRIANA'),
('368771', 'ANDRIANARIMBOLA', 'ANDONOMENJANAHARY MPANDIMBIRAIBE', 'Chef SPR', '223011003954', 340108884, 'rien'),
('340344', 'RAZAFITSIAFAHY', 'ANDRIAMIHAJA CHRISTIAN', 'RESPONSABLE CARTE SCOLAIRE', '201011006127', 347675436, 'ID12 NORD CASERNE AMBOSITRA'),
('426683', 'RAKOTO ANDRIAMBALONARIVO', 'REMI CHRISTIAN', 'RESPONSABLE STATISTIQUE', '101221057813', 345093895, 'LOT 10B1 ANDREFATSENA'),
('272961', 'RAZAFINDRAMIALY', 'PRISQUETTE IRENE', 'Chef SFAP', '209012024015', 344593651, 'LOT 10 C 14 ANDREFATSENA'),
('406315', 'MIHARIMANANIRINA', 'AINA GERALD', 'Chef SIIPFAJ', '201071003342', 346403428, 'LOT10E9 ANDREFATSENA'),
('280724', 'POVENY', 'SOANANTENAINA', 'RESPONSABLE PATRIMOINE FONCIER ET INFRASTRUCTURE', '213012008339', 343120369, 'LOT 01C38 ALAKAMISY AMBOHIMIADANA'),
('280789', 'TSIRINIAINA', 'CINNA', 'Chef SSOR', '203011009284', 342696908, 'rien');

-- --------------------------------------------------------

--
-- Structure de la table `sortant`
--

DROP TABLE IF EXISTS `sortant`;
CREATE TABLE IF NOT EXISTS `sortant` (
  `numSortant` int(50) NOT NULL AUTO_INCREMENT,
  `dateSortant` date NOT NULL,
  `nature` varchar(50) NOT NULL,
  `observation` varchar(50) NOT NULL,
  `destination` varchar(50) NOT NULL,
  PRIMARY KEY (`numSortant`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `sortant`
--

INSERT INTO `sortant` (`numSortant`, `dateSortant`, `nature`, `observation`, `destination`) VALUES
(6, '2023-11-03', 'tara', 'sion', 'FOP'),
(7, '2023-11-05', '23', 'bien', 'ENS FIANARANTSOA'),
(3, '2023-10-25', 'dossier', 'non', 'ENS FIANARANTSOA'),
(4, '2023-10-05', 'cbcbcbb', 'hjhj', 'Veuillez selectionner'),
(5, '2023-10-30', 'WT', 'ETW', 'DRH MEN'),
(9, '2023-12-13', 'facture', 'ok', 'Finance'),
(10, '2023-11-09', 'dossier', 'non', 'DRH MEN'),
(11, '2023-12-04', 'taratasy', 'bien', 'ENS FIANARANTSOA'),
(12, '2023-12-05', 'dfs', 'fa', 'Finance');

-- --------------------------------------------------------

--
-- Structure de la table `traitement`
--

DROP TABLE IF EXISTS `traitement`;
CREATE TABLE IF NOT EXISTS `traitement` (
  `numTraitement` int(50) NOT NULL AUTO_INCREMENT,
  `numCourrier` int(50) NOT NULL,
  `dateTraitement` date NOT NULL,
  `nomPersonnel` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`numTraitement`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `traitement`
--

INSERT INTO `traitement` (`numTraitement`, `numCourrier`, `dateTraitement`, `nomPersonnel`, `status`) VALUES
(1, 12, '2023-10-22', 'ANDRIAMAHEFASOA JEAN RENE', 'envoyer'),
(2, 4, '2023-10-22', 'RAFIDY MARIE ANNICK VICTORIA', 'envoyer'),
(21, 118, '2023-11-22', 'KENDRY TIANA  BALSAM', 'envoyer'),
(4, 445, '2023-10-24', 'VOLOLONA HANITRINIAINA', 'non envoyer'),
(5, 87, '2023-10-24', 'RAKOTONDRAZAKA RAYMOND', 'non envoyer'),
(7, 25, '2023-10-25', 'RALANTONIRINA MARIE CLARISSE', 'non envoyer'),
(8, 544, '2023-10-25', 'RASOANANTENAINA ANGELINE MARIE LEONCE', 'non envoyer'),
(22, 1588, '2023-12-04', 'RAZAFINDRAKOTO RIJA ANGELOS', 'non envoyer'),
(18, 788, '2023-11-05', 'RAZAFIMAHATRATRA MAMPIONONA EMMANUEL', 'envoyer'),
(17, 11, '2023-11-04', 'RAKOTONIRINA HARIJAONA  LOVANANTENAINA', 'envoyer'),
(14, 78, '2023-11-03', 'RALANTONIRINA MARIE CLARISSE', 'envoyer');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `statuUser` varchar(20) NOT NULL,
  `telephone` int(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `statuUser`, `telephone`, `password`) VALUES
(6, 'ANDRIANAIVOMANJAKA', 'Brillant', 'bryanandriamanjaka12@gmail.com', 'Admin', 3466, '0000'),
(2, 'VOLAZARASOA', 'Elianne', 'elianevolazarasoa@gmail.com', 'Utilisateur', 345074999, 'Elianne1234'),
(3, 'rakoto', 'rojo', 'rojo@gmail.com', 'Utilisateur', 322545687, 'lolo'),
(4, 'toky', 'nilaina', 'tokynilaina@gmail.com', 'Admin', 34, '0000'),
(5, 'Sharon', 'Constancia', 'constanciasharon@gmail.com', 'Utilisateur', 348808020, '1234');
--
-- Base de données : `db_civue`
--
CREATE DATABASE IF NOT EXISTS `db_civue` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `db_civue`;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numeroCompte` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `solde` varchar(50) NOT NULL,
  `nbrjours` varchar(20) NOT NULL,
  `tarif` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `numeroCompte`, `nom`, `solde`, `nbrjours`, `tarif`) VALUES
(41, '328', 'test', 'test', '3', 40);
--
-- Base de données : `gestion_personnel`
--
CREATE DATABASE IF NOT EXISTS `gestion_personnel` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `gestion_personnel`;

-- --------------------------------------------------------

--
-- Structure de la table `conges`
--

DROP TABLE IF EXISTS `conges`;
CREATE TABLE IF NOT EXISTS `conges` (
  `numConge` varchar(50) NOT NULL,
  `numEmp_ref` varchar(50) DEFAULT NULL,
  `motif` varchar(50) DEFAULT NULL,
  `nombreJour` int(11) DEFAULT NULL,
  `dateDemande` date DEFAULT NULL,
  `dateRetour` date DEFAULT NULL,
  PRIMARY KEY (`numConge`),
  KEY `numEmp_ref` (`numEmp_ref`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `conges`
--

INSERT INTO `conges` (`numConge`, `numEmp_ref`, `motif`, `nombreJour`, `dateDemande`, `dateRetour`) VALUES
('1', '1', 'Malade', 10, '2023-07-05', '2023-07-15');

-- --------------------------------------------------------

--
-- Structure de la table `employes`
--

DROP TABLE IF EXISTS `employes`;
CREATE TABLE IF NOT EXISTS `employes` (
  `numEmp` varchar(50) NOT NULL,
  `nomEmp` varchar(50) DEFAULT NULL,
  `prenomEmp` varchar(50) DEFAULT NULL,
  `post` varchar(50) DEFAULT NULL,
  `salaire` int(11) DEFAULT NULL,
  PRIMARY KEY (`numEmp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `employes`
--

INSERT INTO `employes` (`numEmp`, `nomEmp`, `prenomEmp`, `post`, `salaire`) VALUES
('3', 'LIONOT', 'Naivo', 'PDG', 70000),
('2', 'BERTRAND', 'Victor', 'Conseiller', 500000),
('4', 'Aina', 'Mercia', 'Manjunga', 200000);

-- --------------------------------------------------------

--
-- Structure de la table `pointages`
--

DROP TABLE IF EXISTS `pointages`;
CREATE TABLE IF NOT EXISTS `pointages` (
  `IdPointage` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `numEmp_ref` varchar(50) DEFAULT NULL,
  `pointage` varchar(11) DEFAULT NULL,
  `datePointage` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`IdPointage`),
  UNIQUE KEY `IdPointage` (`IdPointage`),
  KEY `numEmp_ref` (`numEmp_ref`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `pointages`
--

INSERT INTO `pointages` (`IdPointage`, `numEmp_ref`, `pointage`, `datePointage`) VALUES
(5, '2', 'Oui', '2023-07-06 03:57:39'),
(8, '3', 'Non', '2023-03-03 08:33:33'),
(6, '2', 'Non', '2023-07-05 07:20:45'),
(10, '3', 'Oui', '2023-07-06 08:05:45');
--
-- Base de données : `java_data`
--
CREATE DATABASE IF NOT EXISTS `java_data` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `java_data`;

-- --------------------------------------------------------

--
-- Structure de la table `achat`
--

DROP TABLE IF EXISTS `achat`;
CREATE TABLE IF NOT EXISTS `achat` (
  `numAchat` varchar(50) NOT NULL,
  `numProd` varchar(50) NOT NULL,
  `nomClient` varchar(100) NOT NULL,
  `nbrLitre` int(50) NOT NULL,
  `dateAchat` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `achat`
--

INSERT INTO `achat` (`numAchat`, `numProd`, `nomClient`, `nbrLitre`, `dateAchat`) VALUES
('2', '3', 'Xavier', 5, '2023-07-10'),
('55', '5', 'rakoto', 99, '2023-10-06');

-- --------------------------------------------------------

--
-- Structure de la table `entree`
--

DROP TABLE IF EXISTS `entree`;
CREATE TABLE IF NOT EXISTS `entree` (
  `numEntree` varchar(50) NOT NULL,
  `numProd` varchar(50) NOT NULL,
  `stockEntree` int(50) NOT NULL,
  `dateEntree` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `entree`
--

INSERT INTO `entree` (`numEntree`, `numProd`, `stockEntree`, `dateEntree`) VALUES
('5', '5', 1000, '2023-10-06'),
('2', '3', 90, '2023-07-10');

-- --------------------------------------------------------

--
-- Structure de la table `entretien`
--

DROP TABLE IF EXISTS `entretien`;
CREATE TABLE IF NOT EXISTS `entretien` (
  `numEntr` varchar(50) NOT NULL,
  `numServ` varchar(50) NOT NULL,
  `Immatriculation_voiture` varchar(50) NOT NULL,
  `nomClient` varchar(100) NOT NULL,
  `dateEntretien` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `entretien`
--

INSERT INTO `entretien` (`numEntr`, `numServ`, `Immatriculation_voiture`, `nomClient`, `dateEntretien`) VALUES
('65', '3', '1234', 'floriaux', '2023-03-10'),
('46', '2', '1234', 'floriaux', '2023-07-10'),
('7', '1', '12', 'lolos', '2023-07-09'),
('45', '3', '1234', 'floriaux', '2023-07-10'),
('1455', '2', '1455', 'beloha', '2023-07-14'),
('2', '1', '1554', 'koko', '2023-07-08'),
('34', '1', '1234', 'floriaux', '2023-07-10'),
('4879', '3', '4466', 'Lovatina', '2023-05-16'),
('586', '4', '6847FE', 'Dollar', '2023-07-10'),
('587', '2', '6847FE', 'Dollar', '2023-07-10'),
('544', '1', '6624FD', 'RABE', '2023-10-06'),
('888', '4', '6624FD', 'RABE', '2023-10-06');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `numProd` varchar(50) NOT NULL,
  `design` varchar(50) NOT NULL,
  `stock` int(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`numProd`, `design`, `stock`) VALUES
('5', 'Liquide', 901),
('2', 'Gasoil', 0),
('3', 'Essence', 85);

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `numServ` varchar(50) NOT NULL,
  `service` varchar(50) NOT NULL,
  `prix` int(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`numServ`, `service`, `prix`) VALUES
('1', 'Lavage', 50000),
('3', 'Peint', 5000),
('2', 'Gonflage', 30),
('4', 'Loko', 35000);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `name` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`name`, `password`) VALUES
('', ''),
('admin', 'admin'),
('Bryan', '1234');
--
-- Base de données : `pointage`
--
CREATE DATABASE IF NOT EXISTS `pointage` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `pointage`;

-- --------------------------------------------------------

--
-- Structure de la table `congé`
--

DROP TABLE IF EXISTS `congé`;
CREATE TABLE IF NOT EXISTS `congé` (
  `NumConge` varchar(50) NOT NULL,
  `numEmp` varchar(50) NOT NULL,
  `Motif` varchar(50) NOT NULL,
  `Nbrjour` int(50) NOT NULL,
  `Datedemande` date NOT NULL,
  `Dateretour` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `congé`
--

INSERT INTO `congé` (`NumConge`, `numEmp`, `Motif`, `Nbrjour`, `Datedemande`, `Dateretour`) VALUES
('1', '1', 'Malade', 10, '2023-07-13', '2023-07-20'),
('3', '3', 'Vacance', 9, '2023-07-13', '2023-07-23');

-- --------------------------------------------------------

--
-- Structure de la table `emploié`
--

DROP TABLE IF EXISTS `emploié`;
CREATE TABLE IF NOT EXISTS `emploié` (
  `numEmp` varchar(50) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Poste` varchar(50) NOT NULL,
  `Salaire` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `emploié`
--

INSERT INTO `emploié` (`numEmp`, `Nom`, `Prenom`, `Poste`, `Salaire`) VALUES
('2', 'RAZAFIMANDIMBY', 'Lionot', 'Secretaire', 500000),
('1', 'LOVATIANA', 'Prisca', 'PDG', 1000000),
('3', 'Xav', 'Y', 'Z', 350000),
('fdsfafafafa', 'jkkff', 'asfaf', 'afaf', 66);

-- --------------------------------------------------------

--
-- Structure de la table `pointage`
--

DROP TABLE IF EXISTS `pointage`;
CREATE TABLE IF NOT EXISTS `pointage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Datepointage` date NOT NULL,
  `NumEmp` varchar(50) NOT NULL,
  `Pointage` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `pointage`
--

INSERT INTO `pointage` (`id`, `Datepointage`, `NumEmp`, `Pointage`) VALUES
(5, '2023-07-13', '3', 'NON'),
(4, '2023-07-13', '2', 'OUI');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
