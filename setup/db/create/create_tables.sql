-- user sequence

CREATE SEQUENCE seq_user
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

ALTER TABLE seq_user OWNER TO "db-inline";


CREATE SEQUENCE seq_inline
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

ALTER TABLE seq_inline OWNER TO "db-inline";


CREATE SEQUENCE seq_user_data
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

ALTER TABLE seq_user_data OWNER TO "db-inline";


CREATE TABLE users
(
  user_id bigint NOT NULL DEFAULT nextval('seq_user') PRIMARY KEY,
  username character varying(200) NOT NULL,
  password character varying(60) NOT NULL,
  token character varying(200),
  enabled SMALLINT NOT NULL DEFAULT 1,
  UNIQUE (user_id, username)
) WITH (

OIDS = FALSE
);

ALTER TABLE users OWNER TO "db-inline";


CREATE TABLE user_roles
(
  user_role_id INTEGER NOT NULL DEFAULT nextval('seq_inline') PRIMARY KEY,
  user_id bigint NOT NULL,
  ROLE CHARACTER VARYING(45) NOT NULL,
  UNIQUE (user_id, ROLE),
  CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
  ON UPDATE CASCADE ON DELETE CASCADE
) WITH (

OIDS = FALSE
);

ALTER TABLE user_roles OWNER TO "db-inline";


CREATE TABLE user_data
(
  user_data_id INTEGER NOT NULL DEFAULT nextval('seq_user_data') PRIMARY KEY,
  user_id bigint NOT NULL,
  name CHARACTER VARYING(150) NOT NULL,
  surname CHARACTER VARYING(150) NOT NULL,
  phone CHARACTER VARYING(50),
  email CHARACTER VARYING(200) NOT NULL,
  CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
  ON UPDATE CASCADE ON DELETE CASCADE
) WITH (

OIDS = FALSE
);

ALTER TABLE user_data OWNER TO "db-inline";


























CREATE TABLE user_role
(
  id bigint NOT NULL DEFAULT nextval('seq_wch'::regclass),
  "name" character varying(50) NOT NULL,
  user_id bigint NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (id),
  CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
  REFERENCES "user" (id) MATCH SIMPLE
  ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
OIDS=FALSE
);





-- common sequence

CREATE SEQUENCE seq_wch
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE seq_wch OWNER TO "db-wch";

--
-- configuration
--

CREATE TABLE config_airport
(
  code CHARACTER (3) PRIMARY KEY,
  country CHARACTER (2) NOT NULL,
  timezone CHARACTER VARYING (50) NOT NULL
) WITH (
  OIDS = FALSE
);

ALTER TABLE config_airport OWNER TO "db-wch";

CREATE TABLE config_aircraft
(
  code CHARACTER (3) PRIMARY KEY,
  manufacturer CHARACTER VARYING (50) NOT NULL,
  model CHARACTER VARYING (50) NOT NULL
) WITH (
  OIDS = FALSE
);

ALTER TABLE config_aircraft OWNER TO "db-wch";

CREATE TABLE config_booking_class
(
  rbd CHARACTER PRIMARY KEY,
  booking_class CHARACTER NOT NULL
) WITH (
  OIDS = FALSE
);

ALTER TABLE config_booking_class OWNER TO "db-wch";

CREATE TABLE config_checkin
(
  parameter_key CHARACTER VARYING (50) PRIMARY KEY,
  parameter_value text NOT NULL,
  parameter_type CHARACTER VARYING (50) NOT NULL
) WITH (
  OIDS = FALSE
);

ALTER TABLE config_checkin OWNER TO "db-wch";

CREATE TABLE config_webservice
(
  parameter_key CHARACTER VARYING (50) PRIMARY KEY,
  parameter_value text NOT NULL,
  parameter_type CHARACTER VARYING (50) NOT NULL
) WITH (
  OIDS = FALSE
);

ALTER TABLE config_webservice OWNER TO "db-wch";

CREATE TABLE config_webapp
(
  parameter_key CHARACTER VARYING (50) PRIMARY KEY,
  parameter_value text NOT NULL,
  parameter_type CHARACTER VARYING (50) NOT NULL
) WITH (
  OIDS = FALSE
);

ALTER TABLE config_webapp OWNER TO "db-wch";

CREATE TABLE message_source
(
  code CHARACTER VARYING (100) NOT NULL,
  lang CHARACTER (2) NOT NULL,
  bundle_name CHARACTER VARYING (30) NOT NULL,
  message_value TEXT,
  PRIMARY KEY (code, lang)
) WITH (
  OIDS = FALSE
);

ALTER TABLE message_source OWNER TO "db-wch";

CREATE INDEX ms_bundles_ind on message_source (bundle_name);
ALTER INDEX ms_bundles_ind OWNER  to "db-wch";

--
-- subscription
--

CREATE TABLE subscription_flight
(
  id bigint DEFAULT nextval('seq_wch') PRIMARY KEY,
  operating_airline CHARACTER VARYING(2) NOT NULL,
  flight_number CHARACTER VARYING(4) NOT NULL,
  departure_airport CHARACTER VARYING(3) NOT NULL,
  arrival_airport CHARACTER VARYING(3) NOT NULL,
  departure_date TIMESTAMP NOT NULL,
  send_date TIMESTAMP NOT NULL,
  processed boolean DEFAULT FALSE NOT NULL,
  UNIQUE (operating_airline, flight_number, departure_date)
) WITH (
OIDS = FALSE
);

ALTER TABLE subscription_flight OWNER TO "db-wch";

CREATE INDEX subs_flight_ind on subscription_flight (operating_airline, flight_number, departure_date);
ALTER INDEX subs_flight_ind OWNER  to "db-wch";

CREATE INDEX subs_flight_processed_ind on subscription_flight (operating_airline, flight_number, departure_date, processed);
ALTER INDEX subs_flight_processed_ind OWNER  to "db-wch";

CREATE TABLE subscription
(
  id bigint DEFAULT nextval('seq_wch') PRIMARY KEY,
  flight_id bigint REFERENCES subscription_flight (id) ON DELETE CASCADE,
  pnr CHARACTER VARYING(6) NOT NULL,
  surname CHARACTER VARYING (50) NOT NULL,
  passenger_name CHARACTER VARYING(100) NOT NULL,
  booking_class CHARACTER NOT NULL,
  email CHARACTER VARYING(70) NOT NULL,
  locale CHARACTER VARYING(2) NOT NULL,
  sent boolean DEFAULT FALSE NOT NULL,
  attempts_count SMALLINT DEFAULT 0 NOT NULL
) WITH (
OIDS = FALSE
);

ALTER TABLE subscription OWNER TO "db-wch";

--
-- events
--

CREATE SEQUENCE seq_wch_event
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE seq_wch_event OWNER TO "db-wch";

CREATE TABLE event_booking
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  pnr CHARACTER VARYING (13) NOT NULL,
  surname CHARACTER VARYING (40) NOT NULL,
  eticket boolean NOT NULL,
  operator CHARACTER VARYING (20) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  description text
) WITH (
OIDS = FALSE
);

ALTER TABLE event_booking OWNER TO "db-wch";

CREATE TABLE event_subscription
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  pnr CHARACTER VARYING (6) NOT NULL,
  flight_number CHARACTER VARYING (7) NOT NULL,
  email CHARACTER VARYING (50) NOT NULL,
  operator CHARACTER VARYING (20) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  description text
) WITH (
OIDS = FALSE
);

ALTER TABLE event_subscription OWNER TO "db-wch";

CREATE TABLE event_subscription_send
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  pnr CHARACTER VARYING (6) NOT NULL,
  flight_number CHARACTER VARYING (8) NOT NULL,
  email CHARACTER VARYING (50) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  description text
) WITH (
OIDS = FALSE
);

ALTER TABLE event_subscription_send OWNER TO "db-wch";

CREATE TABLE event_ffp
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  pnr CHARACTER VARYING (6) NOT NULL,
  passenger_name CHARACTER VARYING (100) NOT NULL,
  old_number CHARACTER VARYING (20),
  new_number CHARACTER VARYING (20),
  operator CHARACTER VARYING (20) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  description text
) WITH (
OIDS = FALSE
);

ALTER TABLE event_ffp OWNER TO "db-wch";

CREATE TABLE event_doc
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  pnr CHARACTER VARYING (6) NOT NULL,
  passenger_name CHARACTER VARYING (100) NOT NULL,
  old_doc text,
  new_doc text,
  doc_type CHARACTER (4) NOT NULL,
  doc_status CHARACTER VARYING (8),
  operator CHARACTER VARYING (20) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  description text
) WITH (
OIDS = FALSE
);

ALTER TABLE event_doc OWNER TO "db-wch";

CREATE TABLE event_change_seat
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  pnr CHARACTER VARYING (6) NOT NULL,
  flight_number CHARACTER VARYING (8) NOT NULL,
  passenger_name CHARACTER VARYING (100) NOT NULL,
  old_seat character varying (5),
  new_seat character varying (5),
  operator CHARACTER VARYING (20) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  description text
) WITH (
OIDS = FALSE
);

ALTER TABLE event_change_seat OWNER TO "db-wch";

CREATE TABLE event_checkin
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  pnr CHARACTER VARYING (6) NOT NULL,
  flight_number CHARACTER VARYING (8) NOT NULL,
  passenger_name CHARACTER VARYING (100) NOT NULL,
  operator CHARACTER VARYING (20) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  through boolean NOT NULL DEFAULT FALSE,
  description text
) WITH (
OIDS = FALSE
);

ALTER TABLE event_checkin OWNER TO "db-wch";

CREATE TABLE event_boarding_pass
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  pnr CHARACTER VARYING (6) NOT NULL,
  flight_numbers text NOT NULL,
  passenger_names text NOT NULL,
  destination text NOT  NULL,
  dest_type CHARACTER VARYING (20) NOT NULL,
  operator CHARACTER VARYING (20) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  description text
) WITH (
OIDS = FALSE
);

ALTER TABLE event_boarding_pass OWNER TO "db-wch";

CREATE TABLE event_availability
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  flight_number CHARACTER VARYING (8) NOT NULL,
  departure_airport CHARACTER VARYING (3) NOT NULL,
  arrival_airport CHARACTER VARYING (3) NOT NULL,
  departure_date TIMESTAMP NOT NULL,
  operator CHARACTER VARYING (20) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  description text
)
WITH (
  OIDS=FALSE
);

ALTER TABLE event_availability OWNER TO "db-wch";

CREATE TABLE event_seat_map
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  pnr CHARACTER VARYING (6) NOT NULL,
  flight_number CHARACTER VARYING (8) NOT NULL,
  operator CHARACTER VARYING (20) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  description text
)
WITH (
  OIDS=FALSE
);

ALTER TABLE event_seat_map OWNER TO "db-wch";

CREATE TABLE event_access_error
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  description text
)
WITH (
  OIDS=FALSE
);

ALTER TABLE event_access_error OWNER TO "db-wch";

CREATE TABLE event_social_user
(
  id bigint DEFAULT nextval('seq_wch_event') PRIMARY KEY,
  pnr CHARACTER VARYING (6) NOT NULL,
  operator CHARACTER VARYING (20) NOT NULL,
  passenger_name text,
  old_user text,
  new_user text,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  successful boolean NOT NULL,
  description text
)
WITH (
  OIDS=FALSE
);

ALTER TABLE event_social_user OWNER TO "db-wch";

CREATE TABLE social_user
(
  id bigint NOT NULL DEFAULT nextval('seq_wch'),
  pnr character varying (6) not null,
  passenger_rph character not null,
  network character (2) not null,
  social_id text,
  first_name text,
  last_name text,
  nickname text,
  gender character varying (10),
  photo_url text,
  profile_url text,
  birthday DATE,
  CONSTRAINT pk_social_user PRIMARY KEY (id)
) WITH (
  OIDS = FALSE
);
ALTER TABLE social_user OWNER TO "db-wch";

CREATE INDEX social_user_index1 on social_user(pnr);
CREATE INDEX social_user_index2 on social_user(pnr, passenger_rph);

CREATE TABLE social_flight
(
  id bigint NOT NULL DEFAULT nextval('seq_wch'),
  flight_number CHARACTER VARYING (6) NOT NULL,
  departure_date DATE NOT NULL,
  CONSTRAINT pk_social_flight PRIMARY KEY (id)
) WITH (
  OIDS = FALSE
);
ALTER TABLE social_flight OWNER TO "db-wch";

CREATE INDEX social_flight_index1 on social_flight(flight_number, departure_date);

CREATE TABLE social_mapping
(
  flight_id bigint REFERENCES social_flight (id) ON DELETE CASCADE,
  user_id bigint REFERENCES social_user (id) ON DELETE CASCADE,
  seat_row integer not null,
  seat_number character not null,
  CONSTRAINT pk_social_link PRIMARY KEY (flight_id,user_id)
) WITH (
  OIDS = FALSE
);

ALTER TABLE social_mapping OWNER TO "db-wch";

-- Table: event_change_config

CREATE TABLE event_change_config
(
  id bigint NOT NULL DEFAULT nextval('seq_wch_event'::regclass),
  description character varying(255),
  event_date date,
  event_time time without time zone,
  successful boolean,
  change_type character varying(7) NOT NULL,
  username character varying(50) NOT NULL,
  old_value text,
  new_value text,
  CONSTRAINT event_change_config_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE event_change_config OWNER TO "db-wch";

-- Table: event_admin_authenticate

CREATE TABLE event_admin_authenticate
(
  id bigint NOT NULL DEFAULT nextval('seq_wch_event'::regclass),
  event_date date NOT NULL,
  event_time time without time zone NOT NULL,
  successful boolean NOT NULL,
  description text,
  username character varying(50) NOT NULL,
  CONSTRAINT event_admin_authenticate_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE event_admin_authenticate OWNER TO "db-wch";

-- Table: "user"

CREATE TABLE "user"
(
  id bigint NOT NULL DEFAULT nextval('seq_wch'::regclass),
  "name" character varying(50) NOT NULL,
  password_hash character varying NOT NULL,
  enabled boolean NOT NULL DEFAULT true,
  CONSTRAINT user_pkey PRIMARY KEY (id),
  CONSTRAINT user_name_key UNIQUE (name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "user" OWNER TO "db-wch";

-- Table: user_role

CREATE TABLE user_role
(
  id bigint NOT NULL DEFAULT nextval('seq_wch'::regclass),
  "name" character varying(50) NOT NULL,
  user_id bigint NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (id),
  CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES "user" (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_role OWNER TO "db-wch";

-- Table: config_admin

CREATE TABLE config_admin
(
  parameter_key character varying(50) NOT NULL,
  parameter_value text NOT NULL,
  parameter_type character varying(50) NOT NULL,
  CONSTRAINT config_admin_webapp_pkey PRIMARY KEY (parameter_key)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE config_admin OWNER TO "db-wch";


