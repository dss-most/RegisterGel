create table hibernate_sequences (  sequence_name varchar(255), next_val bigint );


create table registration (
	id BIGINT not null,
	title varchar(100),
	first_name varchar(255), 
	last_name varchar(255),
	occupation varchar(255),
	phone varchar(255),
	email varchar(255),
	round int,
	workplace varchar(255),
	status varchar(100),
	created_time datetime, 
	
	primary key (id)
);