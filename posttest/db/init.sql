

CREATE TABLE public.lottery (
	ticket varchar(6) NOT null,
	price NUMERIC(3,0) NOT NULL,
	amount NUMERIC(3,0) null,
	primary key (ticket)
);

CREATE TABLE public.user_ticket (
	id serial NOT NULL,
	user_id varchar(10) NOT NULL,
	ticket varchar(6) NOT NULL
);