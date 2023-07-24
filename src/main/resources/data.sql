--insert into member(`id`, `name`, `email`, `create_at`, `update_at`) values (1, '홍길동', 'mars@thejoeun.com', now(), null);
--insert into member(`id`, `name`, `email`, `create_at`, `update_at`) values (2, '박남순', 'namsun@thejoeun.com', now(), null);
--insert into member(`id`, `name`, `email`, `create_at`, `update_at`) values (3, '이순신', 'leesunsin@gmail.com', now(), null);
--insert into member(`id`, `name`, `email`, `create_at`, `update_at`) values (4, '강감찬', 'kangkamchan@thejoeun.com', now(), null);
--insert into member(`id`, `name`, `email`, `create_at`, `update_at`) values (5, '홍길동', 'ryukwansun@gmail.com', now(), null);
--
--insert into users(`id`, `name`, `email`) values (1, '홍길동', 'test1@test.com');
--insert into users(`id`, `name`, `email`) values (2, '고길동', 'test2@test.com');
--insert into users(`id`, `name`, `email`) values (3, '박길동', 'test3@test.com');
--
--insert into publisher(`id`, `name`, `create_at`, `update_at`) values (1, '더조은아카데미', now(), now());
--
--insert into book(`id`, `name`, `publisher_id`, `create_at`, `update_at`) values (1, '재미있는 자바책', 1, now(), now());
--insert into book(`id`, `name`, `publisher_id`, `create_at`, `update_at`) values (2, '어려운 데이터베이스', 1, now(), now());
--insert into book(`id`, `name`, `publisher_id`, `create_at`, `update_at`) values (3, '텍스로 디자인 하는 css', 1, now(), now());

--INSERT INTO article(`title`, `content`) VALUES ("제목1", "내용1");
--INSERT INTO article(`title`, `content`) VALUES ("제목2", "내용2");
--INSERT INTO article(`title`, `content`) VALUES ("제목3", "내용3");

INSERT INTO article(`title`, `content`, `author`) VALUES ("제목1", "내용1", "user1");
INSERT INTO article(`title`, `content`, `author`) VALUES ("제목2", "내용2", "user2");
INSERT INTO article(`title`, `content`, `author`) VALUES ("제목3", "내용3", "user3");
