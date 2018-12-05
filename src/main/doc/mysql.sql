create table author(
  id int not null auto_increment,
  account varchar(50) not null,
  name varchar(50) not null ,
  pwd varchar(50) not null,
  sex int(1) not null ,
  address int(1) not null,
  introduce varchar(200) not null ,
  img varchar(64) default '0.jpg' not null ,
  primary key (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table articleDao(
    id int not null auto_increment,
    authorid int not null ,
    subtime DATETIME not null ,
    numb int not null default 0,
    commentnumb int not null default 0,
    title varchar(50) not null ,
    htmltext text not null,
    mkdtext text not null,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table  comment(
    id int not null auto_increment,
    articleid int not null,
    authorid  int not null,
    time datetime,
    main text,
    primary key (id)
)