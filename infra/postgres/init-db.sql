-- keycloak
CREATE USER keycloak WITH ENCRYPTED PASSWORD 'keycloak';
-- CREATE USER postgres WITH ENCRYPTED PASSWORD 'postgres';
CREATE DATABASE keycloak;
CREATE DATABASE vneuron_clients;
GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak;
GRANT ALL PRIVILEGES ON DATABASE keycloak TO postgres;
GRANT ALL PRIVILEGES ON DATABASE vneuron_clients TO keycloak;
GRANT ALL PRIVILEGES ON DATABASE vneuron_clients TO postgres;
