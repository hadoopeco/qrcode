CREATE TABLE `Qrlog` (
  `id` bigint(20) NOT NULL,
  `qrid` bigint(20) NOT NULL,
  `uuid` varchar(50) not null,
  `userid` bigint(20) not null,  
  `query_dt` DATETIME not null,    
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;