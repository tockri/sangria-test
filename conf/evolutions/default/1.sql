# --- !Ups

CREATE TABLE `space` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `nulab_apps_space_key` varchar(20) DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `name` varchar(256) NOT NULL DEFAULT '',
  `space_id` bigint(20) unsigned NOT NULL,
  `nulab_id` varchar(40) NOT NULL DEFAULT '',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_id` bigint(20) unsigned NOT NULL,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updator_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `space_id` (`space_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`space_id`) REFERENCES `space` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `space_id` bigint(20) unsigned DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_id` bigint(20) unsigned NOT NULL,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updator_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `space_id` (`space_id`),
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`space_id`) REFERENCES `space` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) unsigned NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_id` bigint(20) unsigned NOT NULL,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updator_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `group_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `group_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `order` int(11) unsigned NOT NULL DEFAULT '0',
  `role` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `label` varchar(255) NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `group_user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

# --- !Downs

DROP TABLE group_user CASCADE ;
DROP TABLE `group` CASCADE ;
DROP TABLE category CASCADE ;
DROP TABLE user CASCADE ;
DROP TABLE space CASCADE ;