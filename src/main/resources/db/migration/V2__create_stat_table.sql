create table stat
(
    id                  int    not null AUTO_INCREMENT,
    played_minutes      double not null default 0,
    points              double not null default 0,
    rebounds            double not null default 0,
    assists             double not null default 0,
    steals              double not null default 0,
    blocks              double not null default 0,
    turnovers           double not null default 0,
    field_goal_attempt  double not null default 0,
    field_goal_made     double not null default 0,
    three_point_attempt double not null default 0,
    three_point_made    int    not null default 0,
    free_throw_attempt  double not null default 0,
    free_throw_made     double not null default 0,
    player_id           int    not null,
    timestamp           TIMESTAMP,
    primary key (id)
);

alter table stat
    add constraint FKstatplayer foreign key (player_id) references player (id);