-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema faculty1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema faculty1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `faculty1`
  DEFAULT CHARACTER SET utf8;
USE `faculty1`;

-- -----------------------------------------------------
-- Table `faculty1`.`human`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty1`.`human` (
  `id`         INT(11)                              NOT NULL AUTO_INCREMENT
  COMMENT '',
  `login`      VARCHAR(20)                          NOT NULL
  COMMENT '',
  `password`   VARCHAR(50)                          NOT NULL
  COMMENT '',
  `name`       VARCHAR(20)                          NOT NULL
  COMMENT '',
  `last_name`  VARCHAR(20)                          NOT NULL
  COMMENT '',
  `patronymic` VARCHAR(25)                          NOT NULL
  COMMENT '',
  `role`       ENUM ('STUDENT', 'ADMIN', 'TEACHER') NOT NULL
  COMMENT '',
  PRIMARY KEY (`id`)
    COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
    COMMENT '',
  UNIQUE INDEX `login_UNIQUE` (`login` ASC)
    COMMENT ''
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 33
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `faculty1`.`teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty1`.`teacher` (
  `position` ENUM ('ASSISTANT_PROFESSOR', 'ASSOCIATE_PROFESSOR', 'PROFESSOR') NOT NULL
  COMMENT '',
  `human_id` INT(11)                                                          NOT NULL
  COMMENT '',
  PRIMARY KEY (`human_id`)
    COMMENT '',
  CONSTRAINT `fk_teacher_human1`
  FOREIGN KEY (`human_id`)
  REFERENCES `faculty1`.`human` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `faculty1`.`discipline`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty1`.`discipline` (
  `id`               INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT '',
  `title`            VARCHAR(70) NOT NULL
  COMMENT '',
  `teacher_human_id` INT(11)     NOT NULL
  COMMENT '',
  PRIMARY KEY (`id`)
    COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
    COMMENT '',
  UNIQUE INDEX `title_UNIQUE` (`title` ASC)
    COMMENT '',
  INDEX `fk_discipline_teacher1_idx` (`teacher_human_id` ASC)
    COMMENT '',
  CONSTRAINT `fk_discipline_teacher1`
  FOREIGN KEY (`teacher_human_id`)
  REFERENCES `faculty1`.`teacher` (`human_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `faculty1`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty1`.`student` (
  `contract` TINYINT(1) NOT NULL
  COMMENT '',
  `human_id` INT(11)    NOT NULL
  COMMENT '',
  PRIMARY KEY (`human_id`)
    COMMENT '',
  CONSTRAINT `fk_student_human`
  FOREIGN KEY (`human_id`)
  REFERENCES `faculty1`.`human` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `faculty1`.`evaluation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty1`.`evaluation` (
  `id`                 INT(11)                                              NOT NULL AUTO_INCREMENT
  COMMENT '',
  `discipline_id`      INT(11)                                              NOT NULL
  COMMENT '',
  `student_human_id`   INT(11)                                              NOT NULL
  COMMENT '',
  `statusInDiscipline` ENUM ('DECLARED', 'REVOKED', 'CONFIRMED', 'DELETED') NOT NULL
  COMMENT '',
  `mark`               ENUM ('A', 'B', 'C', 'D', 'E', 'Fx', 'F')            NULL     DEFAULT NULL
  COMMENT '',
  `feedback`           MEDIUMTEXT                                           NULL     DEFAULT NULL
  COMMENT '',
  PRIMARY KEY (`id`)
    COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
    COMMENT '',
  INDEX `fk_evaluation_student1_idx` (`student_human_id` ASC)
    COMMENT '',
  INDEX `fk_evaluation_discipline1_idx` (`discipline_id` ASC)
    COMMENT '',
  CONSTRAINT `fk_evaluation_discipline1`
  FOREIGN KEY (`discipline_id`)
  REFERENCES `faculty1`.`discipline` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_evaluation_student1`
  FOREIGN KEY (`student_human_id`)
  REFERENCES `faculty1`.`student` (`human_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
