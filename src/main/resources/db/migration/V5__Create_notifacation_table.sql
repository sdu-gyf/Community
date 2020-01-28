create table notification
(
	id int auto_increment,
	notifier bigint not null,
	receiver bigint not null,
	outerId int not null,
	type int not null,
	gmt_create bigint not null,
	status int not null,
	constraint notification_pk
		primary key (id)
);

