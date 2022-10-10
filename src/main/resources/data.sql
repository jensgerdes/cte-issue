DELETE FROM article where 1=1;
INSERT INTO article (version, status_changed_at) VALUES (0, '2020-01-01T12:03:12Z');
INSERT INTO article (version, status_changed_at) VALUES (0, '2021-12-24T23:59:00Z');
INSERT INTO article (version, status_changed_at) VALUES (0, '2022-10-10T12:03:44+02:00');
INSERT INTO article (version, status_changed_at) VALUES (0, now());

DELETE FROM story where 1=1;
INSERT INTO story (version, status_changed_at) VALUES (0, '1970-01-01T00:00:00Z');
INSERT INTO story (version, status_changed_at) VALUES (0, '2000-12-31T23:59:17Z');
INSERT INTO story (version, status_changed_at) VALUES (0, now());