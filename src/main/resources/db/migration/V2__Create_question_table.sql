create table QUESTION
(
    ID            BIGINT  NOT NULL auto_increment,
    TITLE         VARCHAR(50),
    DESCRIPTION   LONGTEXT,
    GMT_CREATE    BIGINT,
    GMT_MODIFIED  BIGINT,
    CREATOR       BIGINT,
    COMMENT_COUNT INTEGER default 0,
    VIEW_COUNT    INTEGER default 0,
    LIKE_COUNT    INTEGER default 0,
    TAG           VARCHAR(256),
    constraint QUESTION_PK
        primary key (ID)
);

