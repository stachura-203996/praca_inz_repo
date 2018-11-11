CREATE TABLE ADDRESS
(
  ID BIGINT NOT NULL,
  CITY CHARACTER VARYING (255),
  STREET CHARACTER VARYING(255),
  BUILDING_NUMBER CHARACTER VARYING(255),
  FLAT_NUMBER CHARACTER VARYING (255),
  ZIP_CODE CHARACTER VARYING(255),
  CONSTRAINT PK_ADRESS PRIMARY KEY (ID)
)
WITH (
  OIDS=FALSE
);