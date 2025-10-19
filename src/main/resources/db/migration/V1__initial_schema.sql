/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-12.0.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: EstacionamentoAPIJava
-- ------------------------------------------------------
-- Server version	12.0.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Current Database: `EstacionamentoAPIJava`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `EstacionamentoAPIJava` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `EstacionamentoAPIJava`;

--
-- Table structure for table `caixa`
--

DROP TABLE IF EXISTS `caixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `caixa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dia` datetime(6) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `valor` decimal(38,2) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `condicao_pagamento_id` int(11) DEFAULT NULL,
  `controle_fluxo_id` int(11) DEFAULT NULL,
  `forma_pagamento_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKkgt4y4oeetc5ev9ovy10oftfa` (`controle_fluxo_id`),
  KEY `FK8gcu61fcm9rcgvpxu07ta1dwt` (`cliente_id`),
  KEY `FK7e4pnqjp1d790ex2deqob3iwe` (`condicao_pagamento_id`),
  KEY `FKae1encxm5glqu88jgufmj30oa` (`forma_pagamento_id`),
  CONSTRAINT `FK7e4pnqjp1d790ex2deqob3iwe` FOREIGN KEY (`condicao_pagamento_id`) REFERENCES `condicaopagamento` (`id`),
  CONSTRAINT `FK8gcu61fcm9rcgvpxu07ta1dwt` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKae1encxm5glqu88jgufmj30oa` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `formapagamento` (`id`),
  CONSTRAINT `FKbd8c1kawi6bc8svg8r8j1acbf` FOREIGN KEY (`controle_fluxo_id`) REFERENCES `controle_fluxo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `data_nasc` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `limite_de_compra` decimal(10,2) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `end_id` int(11) DEFAULT NULL,
  `plano` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK91caud4h58x4bidmo57hqfj4h` (`end_id`),
  CONSTRAINT `FKmnasw9rpnl9uhnvmobx8kf57x` FOREIGN KEY (`end_id`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente_veiculo`
--

DROP TABLE IF EXISTS `cliente_veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_veiculo` (
  `cliente_id` int(11) NOT NULL,
  `veiculo_id` int(11) NOT NULL,
  UNIQUE KEY `UKcvgt5eeoorgfvxnhxghcjr7s6` (`veiculo_id`),
  KEY `FKtm59nihsyv2lfwbfbr8ypgytn` (`cliente_id`),
  CONSTRAINT `FK6cwm4kpaw9lgk4i73kqpr20f0` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`),
  CONSTRAINT `FKtm59nihsyv2lfwbfbr8ypgytn` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `condicaopagamento`
--

DROP TABLE IF EXISTS `condicaopagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `condicaopagamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `conditens`
--

DROP TABLE IF EXISTS `conditens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `conditens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parcela` int(11) DEFAULT NULL,
  `percentual` decimal(38,2) DEFAULT NULL,
  `condicao_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg1f0bqr3syr9rv6nb7w6mfvq6` (`condicao_id`),
  CONSTRAINT `FKg1f0bqr3syr9rv6nb7w6mfvq6` FOREIGN KEY (`condicao_id`) REFERENCES `condicaopagamento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `controle_fluxo`
--

DROP TABLE IF EXISTS `controle_fluxo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `controle_fluxo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `entrada` datetime(6) DEFAULT NULL,
  `saida` datetime(6) DEFAULT NULL,
  `valor_cobrado` decimal(38,2) DEFAULT NULL,
  `tabela_preco_id` int(11) DEFAULT NULL,
  `vaga_id` int(11) DEFAULT NULL,
  `veiculo_id` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKeq1s6xjmi8rp60j9v2h4jh04o` (`cliente_id`),
  KEY `FKagxvv8i6awjoivkqu4ag84w3r` (`tabela_preco_id`),
  KEY `FKfsfa2t8cmsb9ojkkfldqlep4a` (`vaga_id`),
  KEY `FK93xqwb2b1wuuum7njhkfd14yp` (`veiculo_id`),
  CONSTRAINT `FK93xqwb2b1wuuum7njhkfd14yp` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`),
  CONSTRAINT `FKagxvv8i6awjoivkqu4ag84w3r` FOREIGN KEY (`tabela_preco_id`) REFERENCES `tabelapreco` (`id`),
  CONSTRAINT `FKfsfa2t8cmsb9ojkkfldqlep4a` FOREIGN KEY (`vaga_id`) REFERENCES `vagas` (`id`),
  CONSTRAINT `FKneg1t9w732xk5maepqjf3l5i` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `financeiro`
--

DROP TABLE IF EXISTS `financeiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `financeiro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_emissao` datetime DEFAULT NULL,
  `data_pagamento` datetime DEFAULT NULL,
  `data_vencimento` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `valor_desconto` decimal(10,2) DEFAULT NULL,
  `valor_despesas` decimal(10,2) DEFAULT NULL,
  `valor_juros` decimal(10,2) DEFAULT NULL,
  `valor_original` decimal(10,2) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `cond_pagamento_id` int(11) DEFAULT NULL,
  `forma_pagamento_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhnu2rwnx514wpeumps8b4fawb` (`cliente_id`),
  KEY `FKbt6hj0ikykxhn6d87qo8rf44a` (`cond_pagamento_id`),
  KEY `FKe7kll5a5xkud74jhe149lku8f` (`forma_pagamento_id`),
  CONSTRAINT `FKbt6hj0ikykxhn6d87qo8rf44a` FOREIGN KEY (`cond_pagamento_id`) REFERENCES `condicaopagamento` (`id`),
  CONSTRAINT `FKe7kll5a5xkud74jhe149lku8f` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `formapagamento` (`id`),
  CONSTRAINT `FKhnu2rwnx514wpeumps8b4fawb` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formapagamento`
--

DROP TABLE IF EXISTS `formapagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `formapagamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tabelapreco`
--

DROP TABLE IF EXISTS `tabelapreco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabelapreco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `preco` decimal(10,2) DEFAULT NULL,
  `tipo_contrato` varchar(255) DEFAULT NULL,
  `tipo_veiculo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vagas`
--

DROP TABLE IF EXISTS `vagas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `vagas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `descrisao` varchar(255) DEFAULT NULL,
  `numero_vagas` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `veiculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `cor` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `placa` varchar(255) DEFAULT NULL,
  `quilometragem` int(11) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKny7f0cx7lnl40poaqcijjxyao` (`cliente_id`),
  CONSTRAINT `FKny7f0cx7lnl40poaqcijjxyao` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-10-09 21:00:05
