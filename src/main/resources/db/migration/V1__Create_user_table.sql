create table USER
(
    ID bigint auto_increment primary key,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    BIO          varchar(256),
    avatar_url   varchar(100)
);