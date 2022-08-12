set client_encoding = 'UTF8';

create sequence port_station.vagon_id_seq
start with 1
increment by 1
no minvalue
no maxvalue
cache 1;

create table port_station.vagon (
id bigint NOT NULL,
max_capacity_in_tons integer,
current_capacity_in_tons integer,
train bigint,
serial_number character varying (255),
cargo_type integer,
is_empty boolean
);

