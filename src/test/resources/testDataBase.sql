CREATE DATABASE test2;
USE test2;

CREATE TABLE users
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(45) NOT NULL,
    `login`    VARCHAR(45) NOT NULL,
    `password` VARCHAR(20) NOT NULL,
    `role`     VARCHAR(20) NOT NULL,
    `wallet`   DOUBLE      NULL DEFAULT 0,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

CREATE TABLE tracks
(
    `id`     INT         NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(45) NOT NULL,
    `artist` VARCHAR(45) NOT NULL,
    `date`   DATE        NOT NULL,
    `price`  DOUBLE      NULL DEFAULT 0,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

CREATE TABLE albums
(
    `id`     INT         NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(45) NOT NULL,
    `artist` VARCHAR(45) NOT NULL,
    `date`   DATE        NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

CREATE TABLE playlists
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `date` DATE        NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

CREATE TABLE comments
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `userId`  INT         NOT NULL,
    `date`     DATETIME    NOT NULL,
    `trackId` INT         NOT NULL,
    `text`     VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (userId)
        REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (trackId)
        REFERENCES tracks (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

CREATE TABLE bonuses
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45) NOT NULL,
    `description` VARCHAR(45),
    `startDate`  DATETIME    NOT NULL,
    `endDate`    DATETIME    NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

create table us_tr
(
    userId  INT NOT NULL,
    trackId INT NOT NULL,
    PRIMARY KEY (userId, trackId),
    FOREIGN KEY (userId)
        REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (trackId)
        REFERENCES tracks (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table us_al
(
    userId  INT NOT NULL,
    album_id INT NOT NULL,
    PRIMARY KEY (userId, album_id),
    FOREIGN KEY (userId)
        REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (album_id)
        REFERENCES albums (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table us_pl
(
    userId     INT NOT NULL,
    playlist_id INT NOT NULL,
    PRIMARY KEY (userId, playlist_id),
    FOREIGN KEY (userId)
        REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (playlist_id)
        REFERENCES playlists (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table us_bon
(
    userId  INT NOT NULL,
    bonus_id INT NOT NULL,
    PRIMARY KEY (userId, bonus_id),
    FOREIGN KEY (userId)
        REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (bonus_id)
        REFERENCES bonuses (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table tr_al
(
    trackId INT NOT NULL,
    album_id INT NOT NULL,
    PRIMARY KEY (trackId, album_id),
    FOREIGN KEY (trackId)
        REFERENCES tracks (id) ON DELETE CASCADE,
    FOREIGN KEY (album_id)
        REFERENCES albums (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table tr_com
(
    trackId   INT NOT NULL,
    comment_id INT NOT NULL,
    PRIMARY KEY (trackId, comment_id),
    FOREIGN KEY (trackId)
        REFERENCES tracks (id) ON DELETE CASCADE,
    FOREIGN KEY (comment_id)
        REFERENCES comments (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table tr_pl
(
    playlist_id INT NOT NULL,
    trackId    INT NOT NULL,
    PRIMARY KEY (playlist_id, trackId),
    FOREIGN KEY (playlist_id)
        REFERENCES playlists (id) ON DELETE CASCADE,
    FOREIGN KEY (trackId)
        REFERENCES tracks (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;