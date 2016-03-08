# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table task (
  id                        integer not null,
  name                      varchar(255),
  user_id                   integer,
  constraint pk_task primary key (id))
;

create table user (
  id                        integer not null,
  username                  varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create sequence task_seq;

create sequence user_seq;

alter table task add constraint fk_task_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_task_user_1 on task (user_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists task;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists task_seq;

drop sequence if exists user_seq;

