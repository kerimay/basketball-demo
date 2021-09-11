create table player_average_stats
(
    id                      int    not null AUTO_INCREMENT,
    avg_played_minutes      double not null default 0,
    avg_points              double not null default 0,
    avg_rebounds            double not null default 0,
    avg_assists             double not null default 0,
    avg_steals              double not null default 0,
    avg_blocks              double not null default 0,
    avg_turnovers           double not null default 0,
    avg_field_goal_attempt  double not null default 0,
    avg_field_goal_made     double not null default 0,
    avg_three_point_attempt double not null default 0,
    avg_three_point_made    double not null default 0,
    avg_free_throw_attempt  double not null default 0,
    avg_free_throw_made     double not null default 0,
    player_id               int    not null,
    timestamp               TIMESTAMP,
    primary key (id)
);

alter table player_average_stats
    add constraint FKaveragestatplayer foreign key (player_id) references player (id);