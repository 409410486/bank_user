INSERT INTO SYS_USER (USER_ID, PASSWORD, USER_NAME, BRANCH_ID, DEP_ID, EMAIL, ENABLED, ROLE_ID, TOKEN, LAST_LOGIN_IP, LAST_LOGIN_TIME) VALUES
('kenny', '$2a$10$8BVNZ8bgcNaHBbarQCiqxexfji6n1QKJkpJygEO3gfgLA/S/Uxr92', 'KennyLu', '', '', '', true, 'cc874d79-a3b3-4016-8cc3-327309473503', '', '', current_timestamp),
('mary', '$2a$10$8dwY6dV50fNeDiueIc.WbOhUbWfe7diQscgTpO.rtAH4jRCTiT8yS', 'MaryWu', '', '', '', true, '1a201d72-0972-40fc-aba3-45fc0921a800', '', '', current_timestamp),
('steve', '$2a$10$8BVNZ8bgcNaHBbarQCiqxexfji6n1QKJkpJygEO3gfgLA/S/Uxr92', 'SteveWang', '', '', '', true, '288dcdbd-261c-44ee-a669-37d8a8e455b9', '', '', current_timestamp);
INSERT INTO SYS_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESC, FUNCTIONS) VALUES
('cc874d79-a3b3-4016-8cc3-327309473503', 'teller', 'bank_teller(log in)', '{"folder.system":[],"system.users":["q","m"],"system.roles": ["q","m"],"folder.fisc":[],"fisc.notice":["x"]}'),
('288dcdbd-261c-44ee-a669-37d8a8e455b9', 'manager', 'bank_manager(release)', '{"folder.system":[],"system.users":["q","m"],"system.roles": ["q","m"],"system.permissions":["q","m"]}'),
('1a201d72-0972-40fc-aba3-45fc0921a800', 'manager', 'bank_manager(release)', '{"folder.system":[],"system.users":["q","m"],"system.roles": ["q","m"],"system.permissions":["q","m"]}');
INSERT INTO SYS_FUNC (FUNC_ID, FUNC_NAME, FUNC_URL, PARENT_ID, ORDER_NO, PERMISSION) VALUES
('folder.system', 'system setting', '#', 'root', 1, '[]'),
('system.users', 'user setting', '/user', 'folder.system', 1, '["q", "m"]'),
('system.roles', 'group setting', '/role', 'folder.system', 2, '["q", "m"]'),
('system.permissions', 'permission setting', '/permission', 'folder.system', 3, '["q", "m"]'),
('system.bank', 'bank setting', '/bank', 'folder.system', 4, '["q", "m"]'),
('folder.fisc', 'Financial category', '#', 'root', 2, '[]'),
('fisc.notice', 'Send a message to Finance', '/web/fisc/notice', 'folder.fisc', 1, '["q", "m"]');
