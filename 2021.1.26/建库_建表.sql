create database dttp_reward charset utf8mb4;
use dttp_reward;
create table onepiecereward (
  名号 varchar(100) not null unique,
  悬赏 varchar(100) not null
);

insert into onepiecereward values
  ('罗杰','5,564,800,000贝利'),
  ('白胡子', '5,046,000,000贝利'),
  ('凯多', '4,611,100,000贝利'),
  ('大妈', '4,388,000,000贝利'),
  ('红发', '4,048,900,000贝利'),
  ('黑胡子', '2,247,600,000贝利');