-- First to create my keyspace:
CREATE KEYSPACE IF NOT EXISTS cbd_103530_ex2 WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': 1};
-- Use created keyspace:
USE cbd_103530_ex2;

-- Create users table:

CREATE TABLE users (
                  user_id int,
                  username text,
                  name text,
                  email text,
                  registered_on timestamp,
                  PRIMARY KEY(user_id)
                  );


-- Create videos table:
CREATE TABLE videos ( video_id int, author_id int, name text, description text, tags list<int>, shared_on timestamp, PRIMARY KEY(video_id));

-- Create tags table:
CREATE TABLE tags(
                  tag_id int,
                  name text,
                  video_id int,
                  PRIMARY KEY(name, video_id)
                  );

-- Create comments table:
CREATE TABLE comments(
                  comment_id int,
                  video_id int,
                  user_id int,
                  content text,
                  made_on timestamp,
                  PRIMARY KEY(comment_id)
                  );

-- Create videos_followers table:
CREATE TABLE videos_followers(
                  video_id int,
                  user_id int,
                  username text,
                  PRIMARY KEY(video_id, user_id)
                  );

-- Create videos_by_author table:
CREATE TABLE videos_by_author(
                  author_id int,
                  shared_on timestamp,
                  video_id int,
                  name text,
                  description text,
                  tags list<int>,
                  PRIMARY KEY(author_id, shared_on)
                  );

-- Create comments_by_user table:
CREATE TABLE comments_by_user(
                  user_id int,
                  made_on timestamp,
                  comment_id int,
                  video_id int,
                  content text,
                  PRIMARY KEY(user_id, made_on)
                  );

-- Create comments_by_video table:
CREATE TABLE comments_by_video(
                  video_id int,
                  made_on timestamp,
                  user_id int,
                  comment_id int,
                  content text,
                  PRIMARY KEY(video_id, made_on)
                  );

-- Create events_by_video_user table:
CREATE TABLE events_by_video_user(
                  video_id int,
                  user_id int,
                  occured_on timestamp,
                  event_id int,
                  type text,
                  on_video_seconds int,
                  PRIMARY KEY((video_id, user_id), occured_on)
                  );

-- Create ratings table:

CREATE TABLE ratings(
    video_id int,
    rating int,
    rating_id int,
    PRIMARY KEY(video_id, rating, rating_id)
);