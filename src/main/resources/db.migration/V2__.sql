alter table friendship add accepted BOOLEAN;

alter table friendship add friend_one_id BIGINT;

alter table friendship add friend_two_id BIGINT;

alter table friendship alter COLUMN  accepted SET NOT NULL;

alter table friendship add CONSTRAINT FK_FRIENDSHIP_ON_FRIEND_ONE FOREIGN KEY (friend_one_id) REFERENCES player (id);

alter table friendship add CONSTRAINT FK_FRIENDSHIP_ON_FRIEND_TWO FOREIGN KEY (friend_two_id) REFERENCES player (id);

alter table player drop constraint fkcbktjn3objnquauot7amb1g82;

alter table player_chats drop constraint player_chats_pkey;

alter table player drop COLUMN friendship_id;