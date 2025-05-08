-- =======================
-- TABELA: ODONTO_ENDERECO
-- =======================
CREATE OR REPLACE PROCEDURE INSERIR_ODONTO_ENDERECO(
    p_id_endereco IN NUMERIC,
    p_logradouro IN VARCHAR,
    p_numero IN VARCHAR,
    p_complemento IN VARCHAR,
    p_bairro IN VARCHAR,
    p_cidade IN VARCHAR,
    p_estado IN VARCHAR,
    p_cep IN VARCHAR
) IS
BEGIN
    INSERT INTO ODONTO_ENDERECO VALUES (p_id_endereco, p_logradouro, p_numero, p_complemento, p_bairro, p_cidade, p_estado, p_cep);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Erro ao inserir endereço: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE ATUALIZAR_ODONTO_ENDERECO(
    p_id_endereco IN NUMERIC,
    p_logradouro IN VARCHAR,
    p_numero IN VARCHAR,
    p_complemento IN VARCHAR,
    p_bairro IN VARCHAR,
    p_cidade IN VARCHAR,
    p_estado IN VARCHAR,
    p_cep IN VARCHAR
) IS
BEGIN
    UPDATE ODONTO_ENDERECO
    SET LOGRADOURO = p_logradouro, NUMERO = p_numero, COMPLEMENTO = p_complemento,
        BAIRRO = p_bairro, CIDADE = p_cidade, ESTADO = p_estado, CEP = p_cep
    WHERE ID_ENDERECO = p_id_endereco;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'Erro ao atualizar endereço: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE DELETAR_ODONTO_ENDERECO(p_id_endereco IN NUMERIC) IS
BEGIN
    DELETE FROM ODONTO_ENDERECO WHERE ID_ENDERECO = p_id_endereco;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20003, 'Erro ao deletar endereço: ' || SQLERRM);
END;
/

-- =======================
-- TABELA: ODONTO_PLANO
-- =======================
CREATE OR REPLACE PROCEDURE INSERIR_ODONTO_PLANO(
    p_id_plano IN NUMERIC,
    p_nome_plano IN VARCHAR,
    p_tipo_plano IN VARCHAR,
    p_valor_plano IN NUMERIC
) IS
BEGIN
    INSERT INTO ODONTO_PLANO VALUES (p_id_plano, p_nome_plano, p_tipo_plano, p_valor_plano);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'Erro ao inserir plano: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE ATUALIZAR_ODONTO_PLANO(
    p_id_plano IN NUMERIC,
    p_nome_plano IN VARCHAR,
    p_tipo_plano IN VARCHAR,
    p_valor_plano IN NUMERIC
) IS
BEGIN
    UPDATE ODONTO_PLANO
    SET NOME_PLANO = p_nome_plano, TIPO_PLANO = p_tipo_plano, VALOR_PLANO = p_valor_plano
    WHERE ID_PLANO = p_id_plano;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20005, 'Erro ao atualizar plano: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE DELETAR_ODONTO_PLANO(p_id_plano IN NUMERIC) IS
BEGIN
    DELETE FROM ODONTO_PLANO WHERE ID_PLANO = p_id_plano;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20006, 'Erro ao deletar plano: ' || SQLERRM);
END;
/

-- =======================
-- TABELA: ODONTO_PACIENTE
-- =======================
CREATE OR REPLACE PROCEDURE INSERIR_ODONTO_PACIENTE(
    p_id_paciente IN NUMERIC,
    p_nome_paciente IN VARCHAR,
    p_data_nascimento IN DATE,
    p_sexo IN VARCHAR,
    p_cpf IN VARCHAR,
    p_email IN VARCHAR,
    p_telefone IN VARCHAR,
    p_historico IN VARCHAR,
    p_id_endereco IN NUMERIC,
    p_id_plano IN NUMERIC
) IS
BEGIN
    INSERT INTO ODONTO_PACIENTE VALUES (p_id_paciente, p_nome_paciente, p_data_nascimento, p_sexo, p_cpf, p_email, p_telefone, p_historico, 0, p_id_endereco, p_id_plano);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20007, 'Erro ao inserir paciente: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE ATUALIZAR_ODONTO_PACIENTE(
    p_id_paciente IN NUMERIC,
    p_nome_paciente IN VARCHAR,
    p_data_nascimento IN DATE,
    p_sexo IN VARCHAR,
    p_cpf IN VARCHAR,
    p_email IN VARCHAR,
    p_telefone IN VARCHAR,
    p_historico IN VARCHAR,
    p_id_endereco IN NUMERIC,
    p_id_plano IN NUMERIC
) IS
BEGIN
    UPDATE ODONTO_PACIENTE
    SET NOME_PACIENTE = p_nome_paciente, DATA_NASCIMENTO_PACIENTE = p_data_nascimento,
        SEXO_PACIENTE = p_sexo, CPF_PACIENTE = p_cpf, EMAIL_PACIENTE = p_email,
        TELEFONE_PACIENTE = p_telefone, HISTORICO_ODONTOLOGICO = p_historico,
        ID_ENDERECO = p_id_endereco, ID_PLANO = p_id_plano
    WHERE ID_PACIENTE = p_id_paciente;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20008, 'Erro ao atualizar paciente: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE DELETAR_ODONTO_PACIENTE(p_id_paciente IN NUMERIC) IS
BEGIN
    DELETE FROM ODONTO_PACIENTE WHERE ID_PACIENTE = p_id_paciente;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20009, 'Erro ao deletar paciente: ' || SQLERRM);
END;
/

-- =======================
-- TABELA: ODONTO_DENTISTA
-- =======================
CREATE OR REPLACE PROCEDURE INSERIR_ODONTO_DENTISTA(
    p_id_dentista IN NUMERIC,
    p_nome IN VARCHAR,
    p_especialidade IN VARCHAR,
    p_cro IN VARCHAR,
    p_taxa_sinistros IN NUMERIC,
    p_id_endereco IN NUMERIC
) IS
BEGIN
    INSERT INTO ODONTO_DENTISTA VALUES (p_id_dentista, p_nome, p_especialidade, p_cro, p_taxa_sinistros, p_id_endereco);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20010, 'Erro ao inserir dentista: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE ATUALIZAR_ODONTO_DENTISTA(
    p_id_dentista IN NUMERIC,
    p_nome IN VARCHAR,
    p_especialidade IN VARCHAR,
    p_cro IN VARCHAR,
    p_taxa_sinistros IN NUMERIC,
    p_id_endereco IN NUMERIC
) IS
BEGIN
    UPDATE ODONTO_DENTISTA
    SET NOME = p_nome, ESPECIALIDADE = p_especialidade, CRO = p_cro,
        TAXA_SINISTROS = p_taxa_sinistros, ID_ENDERECO = p_id_endereco
    WHERE ID_DENTISTA = p_id_dentista;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20011, 'Erro ao atualizar dentista: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE DELETAR_ODONTO_DENTISTA(p_id_dentista IN NUMERIC) IS
BEGIN
    DELETE FROM ODONTO_DENTISTA WHERE ID_DENTISTA = p_id_dentista;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20012, 'Erro ao deletar dentista: ' || SQLERRM);
END;
/

-- =======================
-- TABELA: ODONTO_CLINICA
-- =======================
CREATE OR REPLACE PROCEDURE INSERIR_ODONTO_CLINICA(
    p_id_clinica IN NUMERIC,
    p_nome_clinica IN VARCHAR,
    p_tipo_atendimento IN VARCHAR,
    p_id_endereco IN NUMERIC
) IS
BEGIN
    INSERT INTO ODONTO_CLINICA VALUES (p_id_clinica, p_nome_clinica, p_tipo_atendimento, p_id_endereco);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20013, 'Erro ao inserir clínica: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE ATUALIZAR_ODONTO_CLINICA(
    p_id_clinica IN NUMERIC,
    p_nome_clinica IN VARCHAR,
    p_tipo_atendimento IN VARCHAR,
    p_id_endereco IN NUMERIC
) IS
BEGIN
    UPDATE ODONTO_CLINICA
    SET NOME_CLINICA = p_nome_clinica, TIPO_ATENDIMENTO = p_tipo_atendimento, ID_ENDERECO = p_id_endereco
    WHERE ID_CLINICA = p_id_clinica;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20014, 'Erro ao atualizar clínica: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE DELETAR_ODONTO_CLINICA(p_id_clinica IN NUMERIC) IS
BEGIN
    DELETE FROM ODONTO_CLINICA WHERE ID_CLINICA = p_id_clinica;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20015, 'Erro ao deletar clínica: ' || SQLERRM);
END;
/

-- =======================
-- TABELA: ODONTO_CONSULTA
-- =======================
CREATE OR REPLACE PROCEDURE INSERIR_ODONTO_CONSULTA(
    p_id_consulta IN NUMERIC,
    p_data_consulta IN DATE,
    p_hora_consulta IN VARCHAR,
    p_motivo_consulta IN VARCHAR,
    p_tipo_consulta IN VARCHAR,
    p_status_consulta IN VARCHAR,
    p_tempo_espera IN NUMERIC,
    p_id_paciente IN NUMERIC,
    p_id_dentista IN NUMERIC
) IS
BEGIN
    INSERT INTO ODONTO_CONSULTA VALUES (p_id_consulta, p_data_consulta, p_hora_consulta, p_motivo_consulta, p_tipo_consulta, p_status_consulta, p_tempo_espera, p_id_paciente, p_id_dentista);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20016, 'Erro ao inserir consulta: ' || SQLERRM);
END;
/

-- =======================
-- TABELA: ODONTO_PROCEDIMENTO
-- =======================
CREATE OR REPLACE PROCEDURE INSERIR_ODONTO_PROCEDIMENTO(
    p_id_procedimento IN NUMERIC,
    p_tipo_procedimento IN VARCHAR,
    p_custo_procedimento IN NUMERIC,
    p_id_consulta IN NUMERIC
) IS
BEGIN
    INSERT INTO ODONTO_PROCEDIMENTO VALUES (p_id_procedimento, p_tipo_procedimento, p_custo_procedimento, p_id_consulta);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20017, 'Erro ao inserir procedimento: ' || SQLERRM);
END;
/

-- =======================
-- TABELA: ODONTO_SINISTRO
-- =======================
CREATE OR REPLACE PROCEDURE INSERIR_ODONTO_SINISTRO(
    p_id_sinistro IN NUMERIC,
    p_data_sinistro IN DATE,
    p_valor_sinistro IN NUMERIC,
    p_id_consulta IN NUMERIC,
    p_causa IN VARCHAR,
    p_status IN VARCHAR
) IS
BEGIN
    INSERT INTO ODONTO_SINISTRO VALUES (p_id_sinistro, p_data_sinistro, p_valor_sinistro, p_id_consulta, p_causa, p_status);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20018, 'Erro ao inserir sinistro: ' || SQLERRM);
END;
/

-- =======================
-- TABELA: ODONTO_SCORING
-- =======================
CREATE OR REPLACE PROCEDURE INSERIR_ODONTO_SCORING(
    p_id_scoring IN NUMERIC,
    p_id_dentista IN NUMERIC,
    p_scoring IN NUMERIC,
    p_data_avaliacao IN DATE
) IS
BEGIN
    INSERT INTO ODONTO_SCORING VALUES (p_id_scoring, p_id_dentista, p_scoring, p_data_avaliacao);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20019, 'Erro ao inserir scoring: ' || SQLERRM);
END;
/
