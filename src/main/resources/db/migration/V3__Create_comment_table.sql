create table COMMENT
(
    ID            BIGINT  NOT NULL auto_increment,
    PARENT_ID     BIGINT        not null,
    TYPE          INTEGER       not null,
    COMMENTATOR   BIGINT        not null,
    GMT_CREATE    BIGINT        not null,
    GMT_MODIFIED  BIGINT        not null,
    LIKE_COUNT    BIGINT,
    CONTENT       VARCHAR(1024) not null,
    COMMENT_COUNT INTEGER default 0,
    constraint COMMENT_PK
        primary key (ID)
);

