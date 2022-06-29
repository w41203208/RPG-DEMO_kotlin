CREATE TABLE IF NOT EXISTS test.equipments(
    equipment_id INTEGER NOT NULL,
    equipment_type VARCHAR(255) NOT NULL,
    equipment_name VARCHAR(255) NOT NULL,
    equipment_attribute VARCHAR(255) NOT NULL,
    equipment_value INTEGER NOT NULL,
    CONSTRAINT pk_equipments PRIMARY KEY (equipment_id)
);

CREATE TABLE IF NOT EXISTS test.bag(
    bag_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    equipment_id INTEGER NOT NULL,
    CONSTRAINT pk_bag PRIMARY KEY (bag_id),
    CONSTRAINT fk_bag_on_equipments FOREIGN KEY (equipment_id) REFERENCES test.equipments (equipment_id) ON DELETE CASCADE,
    CONSTRAINT fk_bag_on_users FOREIGN KEY (user_id) REFERENCES test.users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS test.users(
    user_id VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    created_at date NOT NULL,
    updated_at date NOT NULL,
    equipment_body INTEGER,
    equipment_hand INTEGER,
    CONSTRAINT pk_users PRIMARY KEY (user_id),
    CONSTRAINT fk_users_on_equipments_1 FOREIGN KEY (equipment_body) REFERENCES test.equipments (equipment_id) ON DELETE SET NULL,
    CONSTRAINT fk_users_on_equipments_2 FOREIGN KEY (equipment_hand) REFERENCES test.equipments (equipment_id) ON DELETE SET NULL
);







