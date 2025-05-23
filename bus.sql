-- Create PostgreSQL user for the application
-- The user is already created via POSTGRES_USER environment variable
-- ALTER USER cityservice WITH PASSWORD 'cityservice';

-- Grant necessary privileges
ALTER USER cityservice WITH SUPERUSER;

-- Create city service database
-- Database is already created via POSTGRES_DB environment variable
-- CREATE DATABASE city_service;

-- Grant privileges to the user
GRANT ALL PRIVILEGES ON DATABASE city_service TO cityservice;

-- Connect to city service database
\c city_service;

-- Grant schema privileges
GRANT ALL ON SCHEMA public TO cityservice;
GRANT ALL ON ALL TABLES IN SCHEMA public TO cityservice;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO cityservice;

-- Create tables
DROP TABLE IF EXISTS cities;

CREATE TABLE cities (
    uuid TEXT PRIMARY KEY,
    shortName TEXT NOT NULL,
    properName TEXT NOT NULL,
    service TEXT NOT NULL
);

-- Insert initial data
INSERT INTO cities (uuid, shortName, properName, service)
VALUES
  ('40e19e98-8646-11e6-9066-549f350fcb0c', 'torun', 'Toruń', 'flixbus'),
  ('40de6982-8646-11e6-9066-549f350fcb0c', 'gdansk', 'Gdańsk', 'flixbus'),
  ('40e19c59-8646-11e6-9066-549f350fcb0c', 'warsaw', 'Warsaw', 'flixbus'),
  ('40de8b67-8646-11e6-9066-549f350fcb0c', 'poznan', 'Poznań', 'flixbus'),
  ('40de575f-8646-11e6-9066-549f350fcb0c', 'wroclaw', 'Wrocław', 'flixbus'),
  ('40e19d09-8646-11e6-9066-549f350fcb0c', 'gdynia', 'Gdynia', 'flixbus'),
  ('40de7eb5-8646-11e6-9066-549f350fcb0c', 'krakow', 'Kraków', 'flixbus'),
  ('40de6094-8646-11e6-9066-549f350fcb0c', 'bydgoszcz', 'Bydgoszcz', 'flixbus'),
  ('40e279fe-8646-11e6-9066-549f350fcb0c', 'rzeszow', 'Rzeszów', 'flixbus'),
  ('40d8f682-8646-11e6-9066-549f350fcb0c', 'berlin', 'Berlin', 'flixbus'),
  ('40d901a5-8646-11e6-9066-549f350fcb0c', 'munich', 'Munich', 'flixbus'),
  ('40db219f-8646-11e6-9066-549f350fcb0c', 'dresden', 'Dresden', 'flixbus');
