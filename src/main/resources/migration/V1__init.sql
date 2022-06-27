CREATE TABLE IF NOT EXISTS Test.users (
    user_id VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    created_at date NOT NULL,
    updated_at date NOT NULL,
    equipment_body INTEGER,
    equipment_hand INTEGER,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);





--CREATE TABLE IF NOT EXISTS test.equipments(
--    equipment_id INTEGER NOT NULL,
--    equipment_name VARCHAR(255) NOT NULL,
--    CONSTRAINT pk_equipments PRIMARY KEY (equipment_id)
--)
--
--CREATE TABLE IF NOT EXISTS test.bag(
--)