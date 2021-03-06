ALTER TABLE GSC_PAN
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_GSC_PAN;

/* 위원회관리 */
DROP TABLE GSC_PAN 
	CASCADE CONSTRAINTS;

/* 위원회관리 */
CREATE TABLE GSC_PAN (
	CM_ID VARCHAR2(10 BYTE) NOT NULL, /* 위원ID */
	NAME VARCHAR2(20 CHAR) NOT NULL, /* 이름 */
	PASSWORD VARCHAR2(20 CHAR) NOT NULL, /* 비번 */
	USE_YN CHAR(1) NOT NULL, /* 사용여부 */
	LEVELS NUMBER(2) NOT NULL, /* 레벨 */
	LOGIN NUMBER(7,0) NOT NULL, /* 로그인 */
	RECOMMEND NUMBER(7,0) NOT NULL /* 추천 */
)
	STORAGE (
		BUFFER_POOL DEFAULT
	)
	TABLESPACE HY_TBS
	LOGGING
	NOCOMPRESS
	NOCACHE
	NOPARALLEL
	NOROWDEPENDENCIES
	DISABLE ROW MOVEMENT;

COMMENT ON TABLE GSC_PAN IS '위원회관리';

COMMENT ON COLUMN GSC_PAN.CM_ID IS '위원ID';

COMMENT ON COLUMN GSC_PAN.NAME IS '이름';

COMMENT ON COLUMN GSC_PAN.PASSWORD IS '비번';

COMMENT ON COLUMN GSC_PAN.USE_YN IS '사용여부';

COMMENT ON COLUMN GSC_PAN.LEVELS IS '레벨';

COMMENT ON COLUMN GSC_PAN.LOGIN IS '로그인';

COMMENT ON COLUMN GSC_PAN.RECOMMEND IS '추천';

CREATE UNIQUE INDEX PK_GSC_PAN
	ON GSC_PAN (
		CM_ID ASC
	)
	STORAGE (
		BUFFER_POOL DEFAULT
	)
	NOLOGGING
	TABLESPACE HY_IX
	NOCOMPRESS
	NOSORT
	NOPARALLEL;

ALTER TABLE GSC_PAN
	ADD
		CONSTRAINT PK_GSC_PAN
		PRIMARY KEY (
			CM_ID
		);