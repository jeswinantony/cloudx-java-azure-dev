create table petstoreproductservice_db.public.category
(
    id                bigserial     not null primary key,
    name              varchar(64)   not null unique
);

create table petstoreproductservice_db.public.tag
(
    id                bigserial     not null primary key,
    name              varchar(64)   not null unique
);

create table petstoreproductservice_db.public.product
(
    id                bigserial     not null primary key,
    name              varchar(64)   not null unique,
    category_id       bigserial     not null,
    photoURL          varchar(255)  not null,
    status            varchar(64)   not null,
    constraint fk_category foreign key (category_id) references category(id)
);

create table petstoreproductservice_db.public.product_tag
(
    product_id        bigserial     not null references product (id) on delete cascade,
    tag_id            bigserial     not null references tag (id) on delete cascade,
    constraint uk_product_tag unique (product_id, tag_id)
);
