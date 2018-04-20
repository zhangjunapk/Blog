create table blog(
	blog_id int(10),
	title varchar(50),
	content text(999999),
	date DATE);

create table comment(
	comment_id int(10),
	blog_id int(10),
	content text(200),
	date DATE);

create table type(
	type_id int(10),
	title varchar(50),
	info varchar(200));

create table tag(
	tag_id int(10),
	content varchar(50));

create table read_hiistory(
	blog_id int(10),
	date DATE);

create table tag_set_history(
	history_id int(10),
	blog_id int(10),
	tag_id int(10),
	date DATE);

create type_set_history(
	history_id int(10),
	type_id int(10),
	blog_id int(10),
	date DATE);
