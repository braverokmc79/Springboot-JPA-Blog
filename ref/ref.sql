drop database springbootblog;
create user `springbootblog`@`localhost` identified by '1234';
create database springbootblog CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
grant all privileges on springbootblog.* to `springbootblog`@`localhost` ;


-- 슈퍼 권한
GRANT SUPER ON *.* TO user1@localhost

--MYSQL 이모티콘 설정 utf8 로 하면 깨진다. 따라서,
--charset만 utf8이 아닌 utf8mb4로 적용해주면 간단히 처리가 가능

-- MySQL 한글 설정

-- my.ini 파일
[client]
default-character-set=utf8mb4

[mysql]
default-character-set=utf8mb4

[mysqld]
collation-server = utf8mb4_unicode_ci
init-connect='SET NAMES utf8mb4'
init_connect='SET collation_connection = utf8mb4_unicode_ci'
character-set-server=utf8mb4



-- 2. 한글 설정 확인

show variables like 'c%';

-- MySQL 재시작


> show variables like 'c%';
+----------------------------------+------------------------------------+
| Variable_name                    | Value                              |
+----------------------------------+------------------------------------+
| character_set_client             | euckr                              |
| character_set_connection         | euckr                              |
| character_set_database           | utf8mb4                            |
| character_set_filesystem         | binary                             |
| character_set_results            | euckr                              |
| character_set_server             | utf8mb4                            |
| character_set_system             | utf8                               |
| character_sets_dir               | C:\bitnami\mariadb\share\charsets\ |
| check_constraint_checks          | ON                                 |
| collation_connection             | euckr_korean_ci                    |
| collation_database               | utf8mb4_unicode_ci                 |
| collation_server                 | utf8mb4_unicode_ci                 |
| column_compression_threshold     | 100                                |
| column_compression_zlib_level    | 6                                  |
| column_compression_zlib_strategy | DEFAULT_STRATEGY                   |
| column_compression_zlib_wrap     | OFF                                |
| completion_type                  | NO_CHAIN                           |
| concurrent_insert                | AUTO                               |
| connect_timeout                  | 10                                 |
| core_file                        | ON                                 |










INSERT INTO reply (content, createDate, boardId, userId) VALUES('1번째 댓글', now(), 1, 6);
INSERT INTO reply (content, createDate, boardId, userId) VALUES('2번째 댓글', now(), 1, 6);
INSERT INTO reply (content, createDate, boardId, userId) VALUES('3첫번째 댓글', now(), 1, 6);
INSERT INTO reply (content, createDate, boardId, userId) VALUES('4첫번째 댓글', now(), 1, 6);
INSERT INTO reply (content, createDate, boardId, userId) VALUES('5첫번째 댓글', now(), 1, 6);
INSERT INTO reply (content, createDate, boardId, userId) VALUES('6첫번째 댓글', now(), 1, 6);
INSERT INTO reply (content, createDate, boardId, userId) VALUES('7첫번째 댓글', now(), 1, 6);
INSERT INTO reply (content, createDate, boardId, userId) VALUES('8첫번째 댓글', now(), 1, 6);
INSERT INTO reply (content, createDate, boardId, userId) VALUES('9첫번째 댓글', now(), 1, 6);
INSERT INTO reply (content, createDate, boardId, userId) VALUES('10번째 댓글', now(), 1, 6);
