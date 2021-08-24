create table team
(
    id            int          not null AUTO_INCREMENT,
    name          varchar(255) not null,
    conference    varchar(255) not null,
    head_coach_id int      not null default 0,
    win_count     int,
    lose_count    int,
    primary key (id)
);

create table player
(
    id          int          not null AUTO_INCREMENT,
    name        varchar(255) not null,
    last_name   varchar(255) not null,
    nationality varchar(255),
    team_id     int          not null default 0,
    primary key (id)
);

create table head_coach
(
    id          int          not null AUTO_INCREMENT,
    name        varchar(255) not null,
    last_name   varchar(255) not null,
    nationality varchar(255),
    team_id     int,
    primary key (id)
);

alter table player
    add constraint FKplayerteamid foreign key (team_id) references team (id);