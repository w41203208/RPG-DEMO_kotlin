CREATE TABLE IF NOT EXISTS test.users (
    user_id VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    created_at date NOT NULL,
    updated_at date NOT NULL,
    equipment_body INTEGER,
    equipment_hand INTEGER,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
    CONSTRAINT fk_users_on_bag FOREIGN KEY (user_bag_bag_id) REFERENCES test.bag (bag_id);
);

CREATE TABLE test.users_user_bag (
    user_user_id VARCHAR(255) NOT NULL,
    user_bag_bag_id INTEGER NOT NULL
);

ALTER TABLE test.users_user_bag ADD CONSTRAINT fk_useusebag_on_bag FOREIGN KEY (user_bag_bag_id) REFERENCES test.bag (bag_id);

ALTER TABLE test.users_user_bag ADD CONSTRAINT fk_useusebag_on_user FOREIGN KEY (user_user_id) REFERENCES test.users (user_id);