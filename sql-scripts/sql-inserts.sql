USE atrack;

INSERT INTO users (name, login, password, role, wallet)
values ("username", "user", "$2a$10$yeNX.Afve4Mesa3mivSnQu60/JrM3cmEDNheP5Uf8ZjkIWNLid.se", "USER", "79.657");
INSERT INTO users (name, login, password, role, wallet)
values ("AdminName", "admin", "$2a$10$ut0cZEqGjr7EBAykTJBrAexHmyi72V5MeOqCWSzTcg/S.cPKnqU8C", "ADMIN", "0");
INSERT INTO users (name, login, password, role, wallet)
values ("banedUser", "baned", "$2a$10$OL8wUsYnhzmYexqRDlpqB.Xmh3jHlvJTN3xKcU/MztGc5RMuWdzx2", "USER", "89.3");

INSERT INTO tracks (name, artist, date, price)
values ("Don’t Forget About Us", "Mariah Carey", "2006-02-10", "4.3");
INSERT INTO tracks (name, artist, date, price)
values ("Let Me Love You", "Mario", "2005-02-10", "3.9");
INSERT INTO tracks (name, artist, date, price)
values ("Lose Yourself", "Eminem", "2003-02-10", "5.1");
INSERT INTO tracks (name, artist, date, price)
values ("TiK ToK", "Ke$ha", "2010-02-10", "6.6");
INSERT INTO tracks (name, artist, date, price)
values ("Firework", "Katy Perry", "2011-02-10", "4.8");
INSERT INTO tracks (name, artist, date, price)
values ("Locked Out Of Heaven", "Bruno Mars", "2013-02-10", "3.7");
INSERT INTO tracks (name, artist, date, price)
values ("The Monster", "Eminem", "2014-02-10", "7.9");
INSERT INTO tracks (name, artist, date, price)
values ("Hello", "Adele", "2016-02-10", "7.7");
INSERT INTO tracks (name, artist, date, price)
values ("All I Want for Christmas Is You", "Mariah Carey", "2020-02-10", "10");
INSERT INTO tracks (name, artist, date, price)
values ("Killshot", "Eminem", "2018-02-10", "5.5");

INSERT INTO albums (name, artist, date)
values ("The best of Eminem", "Eminem", "2018-02-10");

INSERT INTO playlists (name, date)
values ("Good mood", "2020-02-10");

INSERT INTO credits (credit, date_end, user_id)
values ("9", "2020-02-21", "1");
INSERT INTO credits (credit, date_end, user_id)
values ("89.3", "2020-01-22", "3");

INSERT INTO comments (user_id, date, track_id, text)
values ("1", "2020-02-10 18:17:47", "4", "Like this song");
INSERT INTO comments (user_id, date, track_id, text)
values ("1", "2020-02-10 18:17:47", "1", "sounds good ^_^");
INSERT INTO comments (user_id, date, track_id, text)
values ("1", "2020-02-10 18:17:47", "8", "<3");
INSERT INTO comments (user_id, date, track_id, text)
values ("1", "2020-02-10 18:17:47", "5", "Wow");

INSERT INTO bonuses (name, discount, start_date, end_date, user_id)
values ("Birthday sale", "7", "2020-02-09", "2020-02-15", "1");

INSERT INTO user_track (user_id, track_id)
values ("1", "3");
INSERT INTO user_track (user_id, track_id)
values ("1", "4");
INSERT INTO user_track (user_id, track_id)
values ("1", "7");
INSERT INTO user_track (user_id, track_id)
values ("1", "10");

INSERT INTO user_album (user_id, album_id)
values ("1", "1");

INSERT INTO track_album (track_id, album_id)
values ("3", "1");
INSERT INTO track_album (track_id, album_id)
values ("7", "1");
INSERT INTO track_album (track_id, album_id)
values ("10", "1");

INSERT INTO track_playlist (track_id, playlist_id)
values ("1", "1");
INSERT INTO track_playlist (track_id, playlist_id)
values ("5", "1");
INSERT INTO track_playlist (track_id, playlist_id)
values ("8", "1");
INSERT INTO track_playlist (track_id, playlist_id)
values ("10", "1");

