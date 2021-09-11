create table game
(
    id                    int not null AUTO_INCREMENT,
    home_team_id          int not null default 0,
    away_team_id          int not null default 0,
    home_team_score       int not null default 0,
    away_team_score       int not null default 0,
    player_of_the_game_id int not null default 0,
    timestamp             TIMESTAMP,
    primary key (id)
);

alter table game
    add constraint FKmanofthegame foreign key (player_of_the_game_id) references player (id);

alter table game
    add constraint FKteam1game foreign key (home_team_id) references team (id);

alter table game
    add constraint FKteam2game foreign key (away_team_id) references team (id);