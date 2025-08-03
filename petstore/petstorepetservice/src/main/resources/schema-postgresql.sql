create table petstorepetservice_db.public.category
(
    id                bigserial     not null primary key,
    name              varchar(64)   not null unique
);

create table petstorepetservice_db.public.tag
(
    id                bigserial     not null primary key,
    name              varchar(64)   not null unique
);

create table petstorepetservice_db.public.pet
(
    id                bigserial     not null primary key,
    name              varchar(64)   not null unique,
    category_id       bigserial     not null,
    photoURL          varchar(255)  not null,
    status            varchar(64)   not null,
    constraint fk_category foreign key (category_id) references category(id)
);

create table petstorepetservice_db.public.pet_tag
(
    pet_id            bigserial     not null references pet (id) on delete cascade,
    tag_id            bigserial     not null references tag (id) on delete cascade,
    constraint uk_pet_tag unique (pet_id, tag_id)
);
