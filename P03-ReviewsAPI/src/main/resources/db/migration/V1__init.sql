create table products(
    id int AUTO_INCREMENT,
    name varchar(100),
    description varchar(200),
    constraint product_pk primary key (id)
);

create table reviews(
    id int AUTO_INCREMENT,
    content varchar(1000),
    product_id int,
    constraint review_pk primary key (id),
    constraint review_fk foreign key (product_id) references products (id)
);

create table comments(
    id int AUTO_INCREMENT,
    content varchar(1000),
    review_id int,
    constraint comment_pk primary key (id),
    constraint comment_fk foreign key (review_id) references reviews (id)
);