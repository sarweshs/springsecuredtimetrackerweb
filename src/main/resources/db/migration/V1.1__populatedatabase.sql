INSERT INTO users(username,password,enabled)
VALUES ('admin','$2a$08$rtrf6YicjJGtkFQXipHiM.GHSvDH.1UWiayaqi95SXUw2DNtOhcsK', true);
INSERT INTO users(username,password,enabled)
VALUES ('viewer','$2a$08$44fj0frLc3cKjnt0RJpsDOHwafrglg4js8qisng/.xsPwvcJYYy7K', true);
 
INSERT INTO user_roles (username, role)
VALUES ('viewer', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_USER');