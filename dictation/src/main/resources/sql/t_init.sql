-- 听写历史临时表
CREATE TABLE dictation_his_tmp
(
  id BIGINT(20) NOT NULL ,
  create_time timestamp not null default CURRENT_TIMESTAMP ,
  group_id BIGINT(20),
  word VARCHAR(24) not null ,
  result int not null default 1,
  des VARCHAR(256)
);

