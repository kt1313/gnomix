CREATE TABLE room (id bigint not null auto_increment, description varchar(255), number varchar(255), size integer not null, primary key (id));

CREATE TABLE room_beds (room_id bigint not null, beds integer);

CREATE TABLE room_photos_urls (room_id bigint not null, photos_urls varchar(255));

ALTER TABLE room_beds ADD constraint FKmgdse5awswl23tm83cvmaerh6 foreign key (room_id) references room;

ALTER TABLE room_photos_urls ADD constraint FK8m7edvc6gfyryjne6xluj5stv foreign key (room_id) references room;

CREATE TABLE guest (id bigint not null auto_increment, birth_date date, customer_id varchar(255), first_name varchar(255), gender integer, last_name varchar(255), vip boolean not null,  primary key (id))
