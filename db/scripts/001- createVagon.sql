
create table port_station.vagon (
id bigint NOT NULL,
max_capacity_in_tons integer,
current_capacity_in_tons integer,
train bigint,
serial_number character varying (255),
cargo_type integer,
is_empty boolean
);



