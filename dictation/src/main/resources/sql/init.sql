
-- 词语表
CREATE TABLE word
(
    id BIGINT(20) NOT NULL primary key ,
    word VARCHAR(24) not null UNIQUE ,
    type int default 1,
    voice_file VARCHAR(24) not null,
    last_result int not null default 0 ,
    level int ,
    level_time timestamp,
    total int,
    des VARCHAR(256)
);

-- 听写历史临时表
CREATE TABLE dictation_his_tmp
(
  id BIGINT(20) NOT NULL primary key  ,
  create_time timestamp not null default CURRENT_TIMESTAMP ,
  group_id BIGINT(20),
  word VARCHAR(24) not null ,
  result int not null default 1,
  des VARCHAR(256)
);


-- 听写历史表
CREATE TABLE dictation_his
(
  id BIGINT(20) NOT NULL primary key  ,
  create_time timestamp not null default CURRENT_TIMESTAMP ,
  group_id BIGINT(20),
  word VARCHAR(24) not null ,
  result int not null default 1,
  des VARCHAR(256)
);
CREATE INDEX index_dictation_his_word ON dictation_his (word);

-- 词语组
CREATE TABLE word_group
(
  id BIGINT(20) NOT NULL primary key  ,
  create_time timestamp not null default CURRENT_TIMESTAMP ,
  name VARCHAR(32) not null unique,
  type int not null default 1,
  word_total int ,
  pass_rate int ,
  good_rate int ,
  excellent_rate int ,
  result_time timestamp ,
  des VARCHAR(256)
);

-- 组包含的词语
CREATE TABLE word_group_list
(
  group_id BIGINT(20) NOT NULL,
  word VARCHAR(24) not null
);
CREATE INDEX index_word_group_list_groupId ON word_group_list (group_id);

--- ============== 题目
--drop table question;
--drop table question_his_tmp;
--drop table question_his;
--drop table question_group;
--drop table question_group_list;


-- 题目表
CREATE TABLE question
(
    id VARCHAR(32) NOT NULL primary key ,
    type int default 1,
    topic VARCHAR(256) not null,
    voice_need int default 0,
    voice_file VARCHAR(24),
    content VARCHAR(1024),
    answer VARCHAR(1024),
    last_result int not null default 0 ,
    level int ,
    level_time timestamp,
    total int,
    des VARCHAR(256)
);

-- 题目临时历史表
CREATE TABLE question_his_tmp
(
    id BIGINT(20) NOT NULL primary key  ,
    create_time timestamp not null default CURRENT_TIMESTAMP ,
    group_id BIGINT(20),
    question_id VARCHAR(32) not null ,
    topic VARCHAR(256) not null,
    result int not null default 1,
    des VARCHAR(256)
);


-- 题目历史表
CREATE TABLE question_his
(
    id BIGINT(20) NOT NULL primary key  ,
    create_time timestamp not null default CURRENT_TIMESTAMP ,
    group_id BIGINT(20),
    question_id VARCHAR(32) not null ,
    topic VARCHAR(256) not null,
    result int not null default 1,
    des VARCHAR(256)
);
CREATE INDEX index_dictation_his_topic_md5 ON question_his (id);

-- 题目组
CREATE TABLE question_group
(
    id BIGINT(20) NOT NULL primary key  ,
    create_time timestamp not null default CURRENT_TIMESTAMP ,
    name VARCHAR(32) not null unique,
    type int not null default 1,
    total int ,
    pass_rate int ,
    good_rate int ,
    excellent_rate int ,
    result_time timestamp ,
    status int ,
    des VARCHAR(256)
);

-- 组包含的词语
CREATE TABLE question_group_list
(
    group_id BIGINT(20) NOT NULL,
    question_id VARCHAR(32) not null
);
CREATE INDEX index_question_group_list_groupId ON question_group_list (group_id);



--- 临时================================
-- 设备分组和配置
CREATE TABLE group_config
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(64) not NULL COMMENT '名称',
	type ENUM('DEV_ONLINE', 'DEV_DATA') not null COMMENT '类型：1 dev_online组配置；2.dev_data组配置 ',
	create_time timestamp not null default CURRENT_TIMESTAMP COMMENT '添加时间',
	last_run_time timestamp    COMMENT '最后一次执行时间',

	dead_time timestamp    COMMENT '最后一次执行时间，超过此时间，则不再运行',
	next_interval int not null COMMENT '本次执行完毕后，下次执行时间间隔，单位秒',
	run_time_range VARCHAR(64) COMMENT '执行时间段： 08:00-20:00',
	enable int not null default 1 COMMENT '是否启用，0关闭，1启用',
	des VARCHAR(256)  COMMENT '备注',
	PRIMARY KEY (id)
);

-- 设备需要在线的设备
CREATE TABLE dev_online
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	imei VARCHAR(30) not NULL unique COMMENT '设备imei',
	group_id BIGINT(20) not null COMMENT '所属组id',
	create_time timestamp  not null default CURRENT_TIMESTAMP  COMMENT '添加时间',
	des VARCHAR(256)  COMMENT '备注',
	PRIMARY KEY (id)
);


-- 设备需要模拟上报哪些数据
CREATE TABLE dev_data
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	create_time timestamp not null default CURRENT_TIMESTAMP COMMENT '添加时间',
	imei VARCHAR(30) not NULL COMMENT '设备imei',
	dev_type ENUM('BF-1S') not null  COMMENT '设备类型',
	group_id BIGINT(20) not null  COMMENT '所属组id',
    open_sos int  COMMENT '是否开启sos, 0 不开启，1开启',
    gps_longitude double COMMENT '经度',
    gps_latitude double  COMMENT '维度',
    battery int  COMMENT '电量',
    step int  COMMENT '步数',
    wear int  COMMENT '穿戴状态：0 不戴着，1戴着',
    heartNum int  COMMENT '心率：如果为0，则随机值 60 - 90 ',
    blood_diastolic int  COMMENT '舒张压，如果为0，则随机值 60 - 80 ',
    blood_systolic int  COMMENT '收缩压，如果为0，则随机值 90 - 120 ',
    des VARCHAR(256)  COMMENT '备注',
	PRIMARY KEY (id)
);

