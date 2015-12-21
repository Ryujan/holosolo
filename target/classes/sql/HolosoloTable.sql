CREATE TABLE member(
	id VARCHAR2(20) PRIMARY KEY,
	password VARCHAR2(20) NOT NULL,
	name VARCHAR2(30) NOT NULL,
	sex VARCHAR2(2) NOT NULL,
	email VARCHAR2(40) NOT NULL,
	authority NUMBER default 0,
	my_photo VARCHAR2(100) NOT NULL
);

CREATE TABLE contents(
	cno NUMBER PRIMARY KEY,
	title VARCHAR2(60) NOT NULL,
	content VARCHAR2(4000) NOT NULL,
	category VARCHAR2(20) NOT NULL,
	posted_time DATE NOT NULL,
	hits NUMBER default 0,
	writer VARCHAR2(30) NOT NULL,
	filename VARCHAR2(100) NOT NULL,
	CONSTRAINT fk_contents_writer FOREIGN KEY(writer) REFERENCES member(id) ON DELETE CASCADE
);
CREATE SEQUENCE contents_seq NOCACHE;

CREATE TABLE groups(
	gno NUMBER PRIMARY KEY,
	title VARCHAR2(60) NOT NULL,
	content VARCHAR2(4000) NOT NULL,
	category VARCHAR2(20) NOT NULL,
	promise_date DATE NOT NULL,
	sex_check VARCHAR(10) NOT NULL,
	current_num NUMBER default 1,
	max_num NUMBER default 2,
	status NUMBER default 0,
	writer VARCHAR2(30) NOT NULL,
	filename VARCHAR2(100) NOT NULL,
	latitude VARCHAR2(50),
	longitude VARCHAR2(50),
	promise_addr VARCHAR2(50),
	CONSTRAINT fk_group_writer FOREIGN KEY(writer) REFERENCES member(id) ON DELETE CASCADE
);
CREATE SEQUENCE groups_seq NOCACHE;

CREATE TABLE sharetips(
	sno NUMBER PRIMARY KEY,
	title VARCHAR2(60) NOT NULL,
	content VARCHAR2(4000) NOT NULL,
	category VARCHAR2(20) NOT NULL,
	posted_time DATE NOT NULL,
	hits NUMBER default 0,
	writer VARCHAR2(30) NOT NULL,
	filename VARCHAR2(100) NOT NULL,
	CONSTRAINT fk_sharetips_writer FOREIGN KEY(writer) REFERENCES member(id) ON DELETE CASCADE
);
CREATE SEQUENCE sharetips_seq NOCACHE;

CREATE TABLE sharetips_comment(
	rno NUMBER PRIMARY KEY,
	reply VARCHAR2(200) NOT NULL,
	posted_time DATE NOT NULL,
	sno NUMBER NOT NULL,
	writer VARCHAR2(30) NOT NULL,
	CONSTRAINT fk_sharetips_comment_sno FOREIGN KEY(sno) REFERENCES sharetips(sno) ON DELETE CASCADE,
	CONSTRAINT fk_sharetips_comment_writer FOREIGN KEY(writer) REFERENCES member(id) ON DELETE CASCADE
);
CREATE SEQUENCE sharetips_comment_seq NOCACHE;

CREATE TABLE contents_comment(
	rno NUMBER PRIMARY KEY,
	reply VARCHAR2(200) NOT NULL,
	posted_time DATE NOT NULL,
	cno NUMBER NOT NULL,
	writer VARCHAR2(30) NOT NULL,
	CONSTRAINT fk_contents_comment_sno FOREIGN KEY(cno) REFERENCES contents(cno) ON DELETE CASCADE,
	CONSTRAINT fk_contents_comment_writer FOREIGN KEY(writer) REFERENCES member(id) ON DELETE CASCADE
);
CREATE SEQUENCE contents_comment_seq NOCACHE;

CREATE TABLE groups_comment(
	rno NUMBER PRIMARY KEY,
	reply VARCHAR2(200) NOT NULL,
	posted_time DATE NOT NULL,
	gno NUMBER NOT NULL,
	writer VARCHAR2(30) NOT NULL,
	CONSTRAINT fk_groups_comment_gno FOREIGN KEY(gno) REFERENCES groups(gno) ON DELETE CASCADE,
	CONSTRAINT fk_groups_comment_writer FOREIGN KEY(writer) REFERENCES member(id) ON DELETE CASCADE
);
CREATE SEQUENCE groups_comment_seq NOCACHE;

CREATE TABLE member_groups(
	id VARCHAR2(30) NOT NULL,
	gno NUMBER NOT NULL,
	CONSTRAINT pk_member_groups PRIMARY KEY(id,gno),
	CONSTRAINT fk_member_groups_id FOREIGN KEY(id) REFERENCES member(id) ON DELETE CASCADE,
	CONSTRAINT fk_member_groups_gno FOREIGN KEY(gno) REFERENCES groups(gno) ON DELETE CASCADE
);

CREATE TABLE recommend_contents(
	id VARCHAR2(30) NOT NULL,
	cno NUMBER NOT NULL,
    CONSTRAINT pk_recommend_contents PRIMARY KEY(id, cno),
    CONSTRAINT fk_recommend_contents_cno FOREIGN KEY(cno) REFERENCES contents(cno) ON DELETE CASCADE,
    CONSTRAINT fk_recommend_contents_id FOREIGN KEY(id) REFERENCES member(id) ON DELETE CASCADE
);

CREATE TABLE recommend_sharetips(
	id VARCHAR2(30) NOT NULL,
	sno NUMBER NOT NULL,
   	CONSTRAINT pk_recommend_sharetips PRIMARY KEY(id, sno),
   	CONSTRAINT fk_recommend_sharetips_sno FOREIGN KEY(sno) REFERENCES sharetips(sno) ON DELETE CASCADE,
   	CONSTRAINT fk_recommend_sharetips_id FOREIGN KEY(id) REFERENCES member(id) ON DELETE CASCADE
);