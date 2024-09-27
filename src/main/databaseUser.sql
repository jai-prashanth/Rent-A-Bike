/*
 Use this file to create the database user
 */
 
/********************************* Database User *********************************/

DROP DATABASE IF EXISTS rentabike;
CREATE DATABASE rentabike;
DROP USER IF EXISTS 'dbuser'@'localhost';
CREATE USER 'dbuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON rentabike.* TO 'dbuser'@'localhost';
USE rentabike;
SET SQL_SAFE_UPDATES = 0;
/********************************* Create Tables *********************************/

CREATE TABLE IF NOT EXISTS users (
    `userId` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) UNIQUE NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(20),
    `userFirstName` VARCHAR(255),
    `userMiddleName` VARCHAR(255),
    `userLastName` VARCHAR(255),
    `userAddress` VARCHAR(255),
    `driversLicenseId` VARCHAR(50) unique not null,
    `numberOfAccidents` INT default 0,
    `role` varchar(255) default "user"
);

CREATE TABLE IF NOT EXISTS bikes (
    `registration_number` VARCHAR(255) PRIMARY KEY,
    `bike_model` VARCHAR(255),
    `bike_status` VARCHAR(255),
    `CBook_number` VARCHAR(255),
    `insurance` VARCHAR(255),
    `is_available` BOOLEAN,
    `rate_per_hour` BIGINT
);

CREATE TABLE IF NOT EXISTS bookings (
    `booking_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `customer_id` BIGINT,
    `registration_number` VARCHAR(255),
    `booking_time` DATETIME,
    `pickup_time` DATETIME,
    `return_time` DATETIME,
    `down_payment` BIGINT,
    `total_payment` BIGINT,
    `feedback` text,
    FOREIGN KEY (customer_id) REFERENCES users(userId)
);

CREATE TABLE Accidents(
    `accidentId` INT AUTO_INCREMENT PRIMARY KEY,
    `registration_number` VARCHAR(255),
    `userId` BIGINT,
    `accidentDate` DATE,
    `accidentLocation` VARCHAR(255),
    `accidentDescription` TEXT,
    FOREIGN KEY (userId) REFERENCES users(userId)
);

create table if not exists blocklist(
	`userId` Bigint primary key,
	`reason` varchar(255) default "Number of accidents more than 2",
	`blocktime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	foreign key (userId) references users(userId)
);


CREATE TABLE IF NOT EXISTS bikeupdatelogs (
    `logId` INT AUTO_INCREMENT PRIMARY KEY,
    `registration_number` VARCHAR(255),
    `is_available` BOOLEAN,
    `rate_per_hour` BIGINT,
    `changetime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS bikesdeletelogs (
    `registration_number` VARCHAR(255) PRIMARY KEY,
    `deletetime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

/********************************* Create Views *********************************/

/**** Run this from dbuser (don't know why this is failing to execute here) ****/

-- CREATE VIEW UsersView AS SELECT userId, phone, userFirstName, userMiddleName, userLastName, userAddress, numberOfAccidents FROM users;

/********************************* Create Triggers *********************************/

/**** 1. For inserting into bike-update-logs table before updation of bikes ****/

DELIMITER //

CREATE TRIGGER before_update_bikes
BEFORE UPDATE ON bikes
FOR EACH ROW
BEGIN
        INSERT INTO bikeupdatelogs (registration_number, is_available, rate_per_hour,changetime)
        VALUES (OLD.registration_number, OLD.is_available, OLD.rate_per_hour,now());
END;

//

DELIMITER ;

/**** 2. For inserting into bike-deletion-logs table before deleting from bikes ****/

DELIMITER //

CREATE TRIGGER before_delete_bikes
BEFORE DELETE ON bikes
FOR EACH ROW
BEGIN
    INSERT INTO bikesdeletelogs (registration_number,deletetime)
    VALUES (OLD.registration_number,now());
END;

//
DELIMITER ;

/**** 3. For updating the number of accidents in users table after inserting a new accident ****/

DELIMITER //

CREATE TRIGGER after_insert_accidents
AFTER INSERT ON accidents
FOR EACH ROW
BEGIN
    UPDATE Users SET Users.numberofAccidents = Users.numberofAccidents+1 WHERE USERID = NEW.USERID;
END;
//
DELIMITER ;

/**** 4. For inserting user into blocklist if number of accidents become greater than 2 after updation in users ****/

DELIMITER //
create trigger after_update_users
after update on users
for each row
begin
	if new.numberOfAccidents <> old.numberOfAccidents then
		if new.numberOfAccidents > 2 then
			INSERT INTO blocklist (userId, blocktime)
			VALUES (OLD.userId, now());
		end if;
	end if;
end;
//
DELIMITER ;

/********************************* Insert Dummy Data *********************************/

SELECT * FROM USERS;

UPDATE USERS SET ROLE="ADMIN" where userId <= 2;





