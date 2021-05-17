- getCategoriasTarefa() 

select * from CategoriaTarefa;

- getOrganizacaoByEmailColaborador()
 
create or replace function getOrganizacaoByEmailColaborador (p_email Colaborador.Email%type) return int 
is
v_id int;
begin
select idOrganizacao into v_id from Colaborador where email = p_email;
return v_id;
end;
/

 
- createTarefa()

create or replace procedure createTarefa(
p_refTarefa Tarefa.referenciaTarefa%type,
p_designacao Tarefa.designacao%type,
p_descInformal Tarefa.descricaoInformal%type,
p_descTecnica Tarefa.descricaoTecnica%type,
p_duracao Tarefa.estimativaDuracao%type,
p_custo Tarefa.estimativaCusto%type,
p_idOrganizacao Organizacao.idOrganizacao%type,
p_idCategoria CategoriaTarefa.idCategoria%type)
is 
begin
    insert into Tarefa(referenciaTarefa, idOrganizacao, idCategoria, idEstadoTarefa, designacao, descricaoInformal, descricaoTecnica, estimativaDuracao, estimativaCusto) 
    values (p_refTarefa, p_idOrganizacao, p_idCategoria, 1, p_designacao, p_descInformal, p_descTecnica, p_duracao, p_custo);
end;
/


create or replace procedure createProcessoSeriacao(
    p_idAnuncio Anuncio.idAnuncio%type,
    p_idTipoRegimento TipoRegimento.idTipoRegimento%type,
    p_dataRealizacao ProcessoSeriacao.dataRealizacao%type
)
is
begin
    insert into ProcessoSeriacao(idAnuncio, idTipoRegimento, dataRealizacao)
    values (p_idAnuncio, p_idTipoRegimento, p_dataRealizacao);
end;
/

create or replace procedure createClassificacao(
    p_idAnuncio Anuncio.idAnuncio%type,
    p_idFreelancer Freelancer.idFreelancer%type,
    p_lugar Classificacao.lugar%type
)
is
begin
    insert into Classificacao(idAnuncio, idFreelancer, lugar)
    values (p_idAnuncio, p_idFreelancer, p_lugar);
end;
/

create or replace procedure createProcessoSeriacaoColaborador(
    p_idAnuncio Anuncio.idAnuncio%type,
    p_idColaborador Colaborador.idColaborador%type,
    p_idOrganizacao Organizacao.idOrganizacao%type
)
is
begin
    insert into ProcessoSeriacaoColaborador(idAnuncio, idColaborador, idOrganizacao)
    values (p_idAnuncio, p_idColaborador, p_idOrganizacao);
end;
/
