CREATE DATABASE atrack;
USE atrack;

CREATE TABLE users
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(45) NOT NULL,
    `login`    VARCHAR(45) NOT NULL,
    `password` VARCHAR(60) NOT NULL,
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
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `user_id`  INT          NOT NULL,
    `date`     DATETIME     NOT NULL,
    `track_id` INT          NOT NULL,
    `text`     VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (track_id)
        REFERENCES tracks (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

CREATE TABLE bonuses
(
    id        INT         NOT NULL AUTO_INCREMENT,
    name     VARCHAR(45) NOT NULL,
    discount  INT         NOT NULL,
    start_date DATE        NOT NULL,
    endDate   DATE        NOT NULL,
    user_id   INT         NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table user_track
(
    user_id  INT NOT NULL,
    track_id INT NOT NULL,
    PRIMARY KEY (user_id, track_id),
    CONSTRAINT FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (track_id)
        REFERENCES tracks (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table user_album
(
    user_id   INT NOT NULL,
    album_id INT NOT NULL,
    PRIMARY KEY (user_id, album_id),
    CONSTRAINT FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (album_id)
        REFERENCES albums (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table user_playlist
(
    user_id      INT NOT NULL,
    playlist_id INT NOT NULL,
    PRIMARY KEY (user_id, playlist_id),
    CONSTRAINT FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (playlist_id)
        REFERENCES playlists (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table track_album
(
    track_id  INT NOT NULL,
    album_id INT NOT NULL,
    PRIMARY KEY (track_id, album_id),
    CONSTRAINT FOREIGN KEY (track_id)
        REFERENCES tracks (id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (album_id)
        REFERENCES albums (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table track_comment
(
    track_id    INT NOT NULL,
    comment_id INT NOT NULL,
    PRIMARY KEY (track_id, comment_id),
    CONSTRAINT FOREIGN KEY (track_id)
        REFERENCES tracks (id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (comment_id)
        REFERENCES comments (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

create table track_playlist
(
    playlist_id INT NOT NULL,
    track_id     INT NOT NULL,
    PRIMARY KEY (playlist_id, track_id),
    CONSTRAINT FOREIGN KEY (playlist_id)
        REFERENCES playlists (id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (track_id)
        REFERENCES tracks (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;

CREATE TABLE credits
(
    `id`       INT    NOT NULL AUTO_INCREMENT,
    `credit`   DOUBLE NULL DEFAULT '0',
    `date_end` DATE   NOT NULL,
    `user_id`  INT    NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (user_id)
            REFERENCES users (id) ON DELETE CASCADE
)
    ENGINE = InnoDB;