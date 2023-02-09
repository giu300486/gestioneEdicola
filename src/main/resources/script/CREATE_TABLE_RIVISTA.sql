-- gestione_edicola.rivista definition

CREATE TABLE `rivista` (
  `id_rivista` bigint(20) NOT NULL DEFAULT 0,
  `bar_code` varchar(13) NOT NULL DEFAULT '1111111111111',
  `descrizione` varchar(100) NOT NULL,
  `prezzo` decimal(10,0) NOT NULL DEFAULT 0,
  `periodo` varchar(100) NOT NULL,
  `giacenze` int(11) DEFAULT 0,
  `numero_rivista` int(11) DEFAULT NULL,
  `data_creazione` datetime DEFAULT NULL,
  `data_aggiornamento` datetime DEFAULT NULL,
  PRIMARY KEY (`id_rivista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;