create table NOTIFICATION
(
    ID            BIGINT NOT NULL auto_increment,
    NOTIFIER      BIGINT  not null,
    RECEIVER      BIGINT  not null,
    OUTERID       BIGINT  not null,
    TYPE          INTEGER not null,
    GMT_CREATE    BIGINT  not null,
    STATUS        INTEGER not null,
    NOTIFIER_NAME VARCHAR(100),
    OUTER_TITLE   VARCHAR(256),
    constraint NOTIFICATION_PK
        primary key (ID)
);

