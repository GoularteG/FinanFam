create table receitas(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    descricao varchar(100),
    valor varchar(100) not null,
    data varchar(100) not null,

    primary key(id)

);