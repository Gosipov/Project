drop schema if exists inquizition;
create schema inquizition;
use inquizition;

create table users(
	id int not null auto_increment primary key,
	name varchar(20) unique,
	password varchar(40),
	admin boolean default false
);

create table quizzes(
	id int not null auto_increment primary key,
	name varchar(64),
	descript text,
	time_created datetime default now(),
	creator_id int,
	times_taken int default 0,
	one_page boolean,
	shuffle boolean,
	foreign key(creator_id) 
		references users(id)
		on delete set null
);

create table question_types(
	id int not null auto_increment primary key,
	name varchar(32)
);

create table questions(
	id int not null auto_increment primary key,
	question text,
	imageurl varchar(128),
	quiz_id int,
	type int, 
	foreign key(quiz_id) 
		references quizzes(id)
		on delete cascade,
	foreign key(type)
		references question_types(id)
		on delete cascade
);

create table answers(
	id int not null auto_increment primary key,
	answer text,
	question_id int, 
	yes boolean default true,
	foreign key(question_id) 
		references questions(id)
		on delete cascade 
);

create table friendship(
	id int not null auto_increment primary key,
	first_id int,
	second_id int,
	foreign key(first_id) 
		references users(id)
		on delete cascade,
	foreign key(second_id) 
		references users(id)
		on delete cascade	
);

create table history(
	id int not null auto_increment primary key,
	user_id int,
	quiz_id int,
	type enum('create', 'solve') default 'solve',
	time_elapsed int, -- in milliseconds
	score int,
	tdate datetime default now(),
	foreign key(user_id) 
		references users(id)
		on delete cascade,
	foreign key(quiz_id) 
		references quizzes(id)
		on delete cascade
);

create table best_score(
	quiz_id int,
	entry_id int,
	foreign key(quiz_id)
		references quizzes(id)
		on delete cascade,
	foreign key(entry_id)
		references history(id)
		on delete cascade
);

create table messages(
	id int not null auto_increment primary key,
	type enum('message', 'challenge', 'frequest') default 'message', 
	sender_id int,
	receiver_id int,
	subject text,
	message text,
	dtime datetime default now(),
	unread boolean default true,
	foreign key(sender_id) 
		references users(id)
		on delete cascade,
	foreign key(receiver_id) 
		references users(id)
		on delete cascade
);

create table wall(
	id int not null auto_increment primary key,
	adminID int not null,
	foreign key(adminId)
		references users(id)
		on delete cascade,
	message text,
	dtime datetime default now()
);

insert into question_types(name)
	value("qr");

insert into question_types(name)
	value("blank");

insert into question_types(name)
	value("mcq");

insert into question_types(name)
	value("prq");


delimiter //
create trigger upd_history after insert on history
	for each row begin
		if new.type = 'solve' then
			update quizzes 
				set quizzes.times_taken = quizzes.times_taken + 1 
				where new.quiz_id = quizzes.id;
		end if;
	end//

create procedure add_quiz(qname varchar(64), qdescript text, creator int)
	begin
		insert into quizzes(name, descript, time_created, creator_id)
			values(qname, qdescript, now(), creator);
		insert into history(user_id, quiz_id, type, tdate)
			values(creator, (select id from quizzes order by id desc limit 1), 'create', now());  
	end//

