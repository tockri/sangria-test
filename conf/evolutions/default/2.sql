# --- !Ups
INSERT INTO space (`id`, `name`, `nulab_apps_space_key`, `created`)
    VALUES (1, 'system', '', NOW());

INSERT INTO `user` (`id`, `role`, `name`, `space_id`, `nulab_id`, `created`, `creator_id`, `updated`, `updator_id`)
    VALUES (1, 0, 'system', 1, '', NOW(), 1, NOW(), 1);

# --- !Downs
DELETE FROM `user` WHERE id = 1;
DELETE FROM `space` WHERE id = 1;
