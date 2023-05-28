create table tbl_user(
    uuid UUID primary key,
    username varchar(100) not null unique,
    passwd varchar(255) not null,
    role varchar(255) not null
);

create table tbl_task(
    uuid UUID primary key,
    description text null,
    user_uuid UUID not null,
    is_valid boolean,
    created_at timestamp,
    updated_at timestamp
);

alter table tbl_task add constraint fk_user_uuid_task
foreign key (user_uuid) references tbl_user(uuid);