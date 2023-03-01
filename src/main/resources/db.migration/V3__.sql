alter table player_street_stats add accepted BOOLEAN;

alter table player_street_stats add street_game_id BIGINT;

alter table player_street_stats add CONSTRAINT FK_PLAYER_STREET_STATS_ON_STREET_GAME FOREIGN KEY (street_game_id) REFERENCES street_game (id);

alter table player_street_games drop constraint fkc12ujnao28io827cavcvbhkkf;

alter table player drop constraint fkcbktjn3objnquauot7amb1g82;

alter table player_street_games drop constraint fkfghncetl7jefa3xq0hahq118i;

drop table player_street_games cascade;

alter table player_chats drop constraint player_chats_pkey;

alter table player drop COLUMN friendship_id;