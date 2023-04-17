-- It doesn't make sense to create keyspace, since it must be already created from the DDL file
-- In order to be able to insert content into the several tables.
-- Use created keyspace:
USE cbd_103530_ex2;


-- insertions into users table

INSERT INTO users (user_id, username, name, email, registered_on)
    VALUES(
        1,
        'steve4videos',
        'Steve Griffin',
        'steve.griffen@american.us',
        '2022-02-11T14:20:49'
    );

INSERT INTO users (user_id, username, name, email, registered_on)
    VALUES(
        2,
        'mariachi1234',
        'Juan Pablito',
        'mariJuanPab@email.me',
        '2021-12-21T10:58:20'
    );

INSERT INTO users (user_id, username, name, email, registered_on)
    VALUES(
        3,
        'WinterIsComing',
        'Jon Snow',
        'youknownothingjonsnow@got.uk',
        '2008-08-05T16:20:49'
    );

INSERT INTO users (user_id, username, name, email, registered_on)
    VALUES(
        4,
        'smith',
        'Stan Smith',
        'american.dad@cia.us',
        '2021-05-14T12:12:12'
    );

INSERT INTO users (user_id, username, name, email, registered_on)
    VALUES(
        5,
        'cleopatra',
        'Queen Safira',
        'qsc@egy.pt',
        '2017-03-03T03:03:19'
    );

INSERT INTO users (user_id, username, name, email, registered_on)
    VALUES(
        6,
        'sirkazzio',
        'Sir Kazzio',
        'valiosa.como.pedra@safira.pt',
        '2022-11-07T00:00:00'
    );

INSERT INTO users (user_id, username, name, email, registered_on)
    VALUES(
        7,
        'pewds',
        'Felix Pewdiepie',
        'youtube@youtube.com',
        '2022-11-17T04:20:10'
    );


INSERT INTO users (user_id, username, name, email, registered_on)
    VALUES(
        8,
        'CR7',
        'Cristiano Ronaldo',
        'siuuuu@mundo.com',
        '2009-07-07T07:07:07'
    );


INSERT INTO users (user_id, username, name, email, registered_on)
    VALUES(
        9,
        'L10',
        'Lionel Messi',
        'maraloverdona@argentina.com',
        '2008-10-10T10:10:10'
    );


INSERT INTO users (user_id, username, name, email, registered_on)
    VALUES(
        10,
        'Ney10mar',
        'Neymar Junior',
        'neyney@tiktok.ch',
        '2022-01-01T00:00:00'
    );



-- insertions in videos table:
INSERT INTO videos (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        1,
        1,
        'How to join two comedy shows',
        'American Dad and Family Guy are two distinguish shows. However, Steve Griffen, son of Stan Smith and Peter Griffen, proves that the two shows can cohexist.',
        [1, 3, 5],
        '2022-03-13T13:30:02'
    );

INSERT INTO videos (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        2,
        8,
        'I am the best',
        'The oficial documentary of CR7, Cristiano Ronaldo',
        [6, 7],
        '2021-11-24T19:25:12'
    );


INSERT INTO videos (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        3,
        9,
        'Maradona: Inspiration',
        'Maradona Video Tribute by Lionel Messi',
        [6],
        '2020-12-24T10:30:00'
    );


INSERT INTO videos (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        4,
        7,
        'Minecraft Survival Mode Ep. 1',
        'First Episode of Minecraft Survival by Pewds',
        [1, 4, 10],
        '2019-06-1T22:10:00'
    );

INSERT INTO videos (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        5,
        7,
        'Minecraft Survival Mode Ep. 2',
        'Second Episode of Minecraft Survival by Pewds',
        [1, 4, 10],
        '2019-06-5T22:10:00'
    );

INSERT INTO videos (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        6,
        2,
        '24 hour Mariachi Compilation',
        'Mariachiiiiiiiiii',
        [8, 9],
        '2022-10-12T16:45:20'
    );

INSERT INTO videos (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        7,
        6,
        'Safira',
        'Best Song Ever',
        [8],
        '2022-12-12T16:45:20'
    );

INSERT INTO videos (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        8,
        3,
        'Dragons, Got Them?',
        'You know nothing Jon Snow',
        [2, 5],
        '2008-08-06T07:22:22'
    );

INSERT INTO videos (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        9,
        3,
        'Dragons, Got Them? Volume 2',
        'You know nothing Jon Snow vol.2',
        [2, 5],
        '2008-08-07T07:22:22'
    );

INSERT INTO videos (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        10,
        3,
        'Dragons, Got Them? Volume 3',
        'You know nothing Jon Snow vol.3',
        [2, 5],
        '2008-08-08T07:22:22'
    );

--insertion in tags tables:
INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        1,
        'Comedy',
        1
    );

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        1,
        'Comedy',
        4
    );

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        1,
        'Comedy',
        5
    );

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        2,
        'Aveiro',
        8
    );
INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        2,
        'Aveiro',
        9
    );
INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        2,
        'Aveiro',
        10
    );

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        3,
        'Animated',
        1
    );

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        4,
        'Children',
        4
    );
INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        4,
        'Children',
        5
    );

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        5,
        'Adventure',
        1
    );
INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        5,
        'Adventure',
        8
    );
INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        5,
        'Adventure',
        9
    );

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        5,
        'Adventure',
        10
    );  

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        6,
        'Sports',
        2
    );  
INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        6,
        'Sports',
        3
    );  

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        7,
        'Documentary',
        2
    );  

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        8,
        'Music',
        6
    );
INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        8,
        'Music',
        7
    );   

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        9,
        'Dance',
        6
    ); 

INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        10,
        'Gaming',
        4
    ); 
INSERT INTO tags (tag_id, name, video_id)
    VALUES(
        10,
        'Gaming',
        5
    ); 



INSERT INTO comments (comment_id, video_id, user_id, content, made_on)
    VALUES(
        1,
        1,
        5,
        'Ahahah that is so cool!',
        '2022-03-14T13:23:12'
    );

INSERT INTO comments (comment_id, video_id, user_id, content, made_on)
    VALUES(
        2,
        1,
        7,
        'Simpsons are way cooler...',
        '2022-03-16T21:11:42'
    );

INSERT INTO comments (comment_id, video_id, user_id, content, made_on)
    VALUES(
        3,
        6,
        6,
        'My song is better than this mariachi stuff!',
        '2022-10-16T22:01:02'
    );

INSERT INTO comments (comment_id, video_id, user_id, content, made_on)
    VALUES(
        4,
        6,
        2,
        'I could listen to this for 24 hours!!',
        '2022-10-15T02:11:27'
    );

INSERT INTO comments (comment_id, video_id, user_id, content, made_on)
    VALUES(
        5,
        6,
        5,
        'Juro que de mim ninguem te tira. Valiosa como pedra de SAFIRA',
        '2022-11-01T17:31:57'
    );

INSERT INTO comments (comment_id, video_id, user_id, content, made_on)
    VALUES(
        6,
        2,
        4,
        'Is this the soccer fellow?',
        '2022-01-25T10:21:07'
    );

INSERT INTO comments (comment_id, video_id, user_id, content, made_on)
    VALUES(
        7,
        6,
        8,
        'Inspiration <3',
        '2021-12-21T20:12:27'
    );

INSERT INTO comments (comment_id, video_id, user_id, content, made_on)
    VALUES(
        8,
        6,
        3,
        'For the Watch!',
        '2022-09-24T15:21:55'
    );

INSERT INTO comments (comment_id, video_id, user_id, content, made_on)
    VALUES(
        9,
        4,
        7,
        'Hey guys!!',
        '2019-06-5T22:10:05'
    );

INSERT INTO comments (comment_id, video_id, user_id, content, made_on)
    VALUES(
        10,
        4,
        10,
        '1vs1 PVP agora mesmo',
        '2019-06-6T17:31:57'
    );

-- possible types: play, pause, stop
INSERT INTO events_by_video_user (event_id, video_id, user_id, type, occured_on, on_video_seconds)
    VALUES(
        1,
        1,
        4,
        'play',
        '2022-03-13T14:30:02',
        0
    );

INSERT INTO events_by_video_user (event_id, video_id, user_id, type, occured_on, on_video_seconds)
    VALUES(
        2,
        1,
        4,
        'pause',
        '2022-03-13T14:30:12',
        10
    );

INSERT INTO events_by_video_user (event_id, video_id, user_id, type, occured_on, on_video_seconds)
    VALUES(
        3,
        1,
        4,
        'play',
        '2022-03-13T14:31:00',
        10
    );

INSERT INTO events_by_video_user (event_id, video_id, user_id, type, occured_on, on_video_seconds)
    VALUES(
        4,
        1,
        4,
        'pause',
        '2022-03-13T14:40:00',
        550
    );

INSERT INTO events_by_video_user (event_id, video_id, user_id, type, occured_on, on_video_seconds)
    VALUES(
        5,
        1,
        4,
        'play',
        '2022-03-13T14:41:00',
        550
    );

INSERT INTO events_by_video_user (event_id, video_id, user_id, type, occured_on, on_video_seconds)
    VALUES(
        6,
        1,
        4,
        'stop',
        '2022-03-13T14:42:00',
        610
    );

INSERT INTO events_by_video_user (event_id, video_id, user_id, type, occured_on, on_video_seconds)
    VALUES(
        7,
        4,
        8,
        'play',
        '2019-06-1T23:10:00',
        0
    );

INSERT INTO events_by_video_user (event_id, video_id, user_id, type, occured_on, on_video_seconds)
    VALUES(
        8,
        4,
        8,
        'pause',
        '2019-06-1T23:12:00',
        120
    );

INSERT INTO events_by_video_user (event_id, video_id, user_id, type, occured_on, on_video_seconds)
    VALUES(
        9,
        4,
        8,
        'play',
        '2019-06-1T23:13:00',
        120
    );

INSERT INTO events_by_video_user (event_id, video_id, user_id, type, occured_on, on_video_seconds)
    VALUES(
        10,
        4,
        8,
        'stop',
        '2019-06-1T23:20:00',
        540
    );


INSERT INTO videos_followers(video_id, user_id, username)
    VALUES(
        4,
        5,
        'cleopatra'
    );

INSERT INTO videos_followers(video_id, user_id, username)
    VALUES(
        4,
        8,
        'CR7'
    );

INSERT INTO videos_followers(video_id, user_id, username)
    VALUES(
        4,
        9,
        'L10'
    );

INSERT INTO videos_followers(video_id, user_id, username)
    VALUES(
        4,
        3,
        'WinterIsComing'
    );

INSERT INTO videos_followers(video_id, user_id, username)
    VALUES(
        4,
        7,
        'pewds'
    );

INSERT INTO videos_followers(video_id, user_id, username)
    VALUES(
        7,
        4,
        'smith'
    );

INSERT INTO videos_followers(video_id, user_id, username)
    VALUES(
        7,
        2,
        'mariachi1234'
    );

INSERT INTO videos_followers(video_id, user_id, username)
    VALUES(
        7,
        1,
        'steve4videos'
    );

INSERT INTO videos_followers(video_id, user_id, username)
    VALUES(
        7,
        3,
        'WinterIsComing'
    );

INSERT INTO videos_followers(video_id, user_id, username)
    VALUES(
        7,
        5,
        'cleopatra'
    );


INSERT INTO videos_by_author (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        1,
        1,
        'How to join two comedy shows',
        'American Dad and Family Guy are two distinguish shows. However, Steve Griffen, son of Stan Smith and Peter Griffen, proves that the two shows can cohexist.',
        [1, 3, 5],
        '2022-03-13T13:30:02'
    );

INSERT INTO videos_by_author (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        2,
        8,
        'I am the best',
        'The oficial documentary of CR7, Cristiano Ronaldo',
        [6, 7],
        '2021-11-24T19:25:12'
    );


INSERT INTO videos_by_author (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        3,
        9,
        'Maradona: Inspiration',
        'Maradona Video Tribute by Lionel Messi',
        [6],
        '2020-12-24T10:30:00'
    );


INSERT INTO videos_by_author (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        4,
        7,
        'Minecraft Survival Mode Ep. 1',
        'First Episode of Minecraft Survival by Pewds',
        [1, 4, 10],
        '2019-06-1T22:10:00'
    );

INSERT INTO videos_by_author (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        5,
        7,
        'Minecraft Survival Mode Ep. 2',
        'Second Episode of Minecraft Survival by Pewds',
        [1, 4, 10],
        '2019-06-5T22:10:00'
    );

INSERT INTO videos_by_author (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        6,
        2,
        '24 hour Mariachi Compilation',
        'Mariachiiiiiiiiii',
        [8, 9],
        '2022-10-12T16:45:20'
    );

INSERT INTO videos_by_author (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        7,
        6,
        'Safira',
        'Best Song Ever',
        [8],
        '2022-12-12T16:45:20'
    );

INSERT INTO videos_by_author (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        8,
        3,
        'Dragons, Got Them?',
        'You know nothing Jon Snow',
        [2, 5],
        '2008-08-06T07:22:22'
    );

INSERT INTO videos_by_author (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        9,
        3,
        'Dragons, Got Them? Volume 2',
        'You know nothing Jon Snow vol.2',
        [2, 5],
        '2008-08-07T07:22:22'
    );

INSERT INTO videos_by_author (video_id, author_id, name, description, tags, shared_on)
    VALUES(
        10,
        3,
        'Dragons, Got Them? Volume 3',
        'You know nothing Jon Snow vol.3',
        [2, 5],
        '2008-08-08T07:22:22'
    );

INSERT INTO comments_by_user (comment_id, video_id, user_id, content, made_on)
    VALUES(
        1,
        1,
        5,
        'Ahahah that is so cool!',
        '2022-03-14T13:23:12'
    );

INSERT INTO comments_by_user (comment_id, video_id, user_id, content, made_on)
    VALUES(
        2,
        1,
        7,
        'Simpsons are way cooler...',
        '2022-03-16T21:11:42'
    );

INSERT INTO comments_by_user (comment_id, video_id, user_id, content, made_on)
    VALUES(
        3,
        6,
        6,
        'My song is better than this mariachi stuff!',
        '2022-10-16T22:01:02'
    );

INSERT INTO comments_by_user (comment_id, video_id, user_id, content, made_on)
    VALUES(
        4,
        6,
        2,
        'I could listen to this for 24 hours!!',
        '2022-10-15T02:11:27'
    );

INSERT INTO comments_by_user (comment_id, video_id, user_id, content, made_on)
    VALUES(
        5,
        6,
        5,
        'Juro que de mim ninguem te tira. Valiosa como pedra de SAFIRA',
        '2022-11-01T17:31:57'
    );

INSERT INTO comments_by_user (comment_id, video_id, user_id, content, made_on)
    VALUES(
        6,
        2,
        4,
        'Is this the soccer fellow?',
        '2022-01-25T10:21:07'
    );

INSERT INTO comments_by_user (comment_id, video_id, user_id, content, made_on)
    VALUES(
        7,
        6,
        8,
        'Inspiration <3',
        '2021-12-21T20:12:27'
    );

INSERT INTO comments_by_user (comment_id, video_id, user_id, content, made_on)
    VALUES(
        8,
        6,
        3,
        'For the Watch!',
        '2022-09-24T15:21:55'
    );

INSERT INTO comments_by_user (comment_id, video_id, user_id, content, made_on)
    VALUES(
        9,
        4,
        7,
        'Hey guys!!',
        '2019-06-5T22:10:05'
    );

INSERT INTO comments_by_user (comment_id, video_id, user_id, content, made_on)
    VALUES(
        10,
        4,
        10,
        '1vs1 PVP agora mesmo',
        '2019-06-6T17:31:57'
    );

INSERT INTO comments_by_video (comment_id, video_id, user_id, content, made_on)
    VALUES(
        1,
        1,
        5,
        'Ahahah that is so cool!',
        '2022-03-14T13:23:12'
    );

INSERT INTO comments_by_video (comment_id, video_id, user_id, content, made_on)
    VALUES(
        2,
        1,
        7,
        'Simpsons are way cooler...',
        '2022-03-16T21:11:42'
    );

INSERT INTO comments_by_video (comment_id, video_id, user_id, content, made_on)
    VALUES(
        3,
        6,
        6,
        'My song is better than this mariachi stuff!',
        '2022-10-16T22:01:02'
    );

INSERT INTO comments_by_video (comment_id, video_id, user_id, content, made_on)
    VALUES(
        4,
        6,
        2,
        'I could listen to this for 24 hours!!',
        '2022-10-15T02:11:27'
    );

INSERT INTO comments_by_video (comment_id, video_id, user_id, content, made_on)
    VALUES(
        5,
        6,
        5,
        'Juro que de mim ninguem te tira. Valiosa como pedra de SAFIRA',
        '2022-11-01T17:31:57'
    );

INSERT INTO comments_by_video (comment_id, video_id, user_id, content, made_on)
    VALUES(
        6,
        2,
        4,
        'Is this the soccer fellow?',
        '2022-01-25T10:21:07'
    );

INSERT INTO comments_by_video (comment_id, video_id, user_id, content, made_on)
    VALUES(
        7,
        6,
        8,
        'Inspiration <3',
        '2021-12-21T20:12:27'
    );

INSERT INTO comments_by_video (comment_id, video_id, user_id, content, made_on)
    VALUES(
        8,
        6,
        3,
        'For the Watch!',
        '2022-09-24T15:21:55'
    );

INSERT INTO comments_by_video (comment_id, video_id, user_id, content, made_on)
    VALUES(
        9,
        4,
        7,
        'Hey guys!!',
        '2019-06-5T22:10:05'
    );

INSERT INTO comments_by_video (comment_id, video_id, user_id, content, made_on)
    VALUES(
        10,
        4,
        10,
        '1vs1 PVP agora mesmo',
        '2019-06-6T17:31:57'
    );

INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        1,
        3,
        1
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        1,
        4,
        2
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        1,
        3,
        3
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        1,
        2,
        4
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        1,
        5,
        5
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        1,
        5,
        6
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        1,
        4,
        7
    );


INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        2,
        5,
        8
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        2,
        5,
        9
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        2,
        5,
        10
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        2,
        1,
        11
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        2,
        2,
        12
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        2,
        2,
        13
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        2,
        4,
        14
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        2,
        5,
        15
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        2,
        3,
        16
    );


INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        3,
        5,
        17
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        3,
        5,
        18
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        3,
        5,
        19
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        3,
        5,
        20
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        3,
        5,
        21
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        3,
        5,
        22
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        3,
        5,
        23
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        3,
        5,
        24
    );



INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        4,
        5,
        25
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        4,
        5,
        26
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        4,
        4,
        27
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        4,
        5,
        28
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        4,
        3,
        29
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        4,
        2,
        30
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        4,
        3,
        31
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        4,
        4,
        32
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        4,
        5,
        33
    );
INSERT INTO ratings(video_id, rating, rating_id)
    VALUES(
        4,
        4,
        34
    );