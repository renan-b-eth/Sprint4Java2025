CREATE OR REPLACE PACKAGE BODY ODONTO_PACKAGE AS
    -- Procedure para ODONTO_ENDERECO
    PROCEDURE INSERIR_ODONTO_ENDERECO(
        p_id_endereco IN NUMBER,
        p_logradouro IN VARCHAR2,
        p_numero IN VARCHAR2,
        p_complemento IN VARCHAR2,
        p_bairro IN VARCHAR2,
        p_cidade IN VARCHAR2,
        p_estado IN VARCHAR2,
        p_cep IN VARCHAR2
    ) IS
    BEGIN
        INSERT INTO ODONTO_ENDERECO (ID_ENDERECO, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP)
        VALUES (p_id_endereco, p_logradouro, p_numero, p_complemento, p_bairro, p_cidade, p_estado, p_cep);
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20001, 'Erro: ID de endereço já existe.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20002, 'Erro ao inserir endereço: ' || SQLERRM);
    END;

    PROCEDURE ATUALIZAR_ODONTO_ENDERECO(
        p_id_endereco IN NUMBER,
        p_logradouro IN VARCHAR2,
        p_numero IN VARCHAR2,
        p_complemento IN VARCHAR2,
        p_bairro IN VARCHAR2,
        p_cidade IN VARCHAR2,
        p_estado IN VARCHAR2,
        p_cep IN VARCHAR2
    ) IS
    BEGIN
        UPDATE ODONTO_ENDERECO
        SET LOGRADOURO = p_logradouro,
            NUMERO = p_numero,
            COMPLEMENTO = p_complemento,
            BAIRRO = p_bairro,
            CIDADE = p_cidade,
            ESTADO = p_estado,
            CEP = p_cep
        WHERE ID_ENDERECO = p_id_endereco;
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20003, 'Erro: Endereço não encontrado.');
        END IF;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20004, 'Erro ao atualizar endereço: ' || SQLERRM);
    END;

    PROCEDURE DELETAR_ODONTO_ENDERECO(p_id_endereco IN NUMBER) IS
    BEGIN
        DELETE FROM ODONTO_ENDERECO WHERE ID_ENDERECO = p_id_endereco;
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20005, 'Erro: Endereço não encontrado.');
        END IF;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20006, 'Erro ao deletar endereço: ' || SQLERRM);
    END;

    -- Procedure para ODONTO_PLANO
    PROCEDURE INSERIR_ODONTO_PLANO(
        p_id_plano IN NUMBER,
        p_nome_plano IN VARCHAR2,
        p_tipo_plano IN VARCHAR2,
        p_valor_plano IN NUMBER
    ) IS
    BEGIN
        INSERT INTO ODONTO_PLANO (ID_PLANO, NOME_PLANO, TIPO_PLANO, VALOR_PLANO)
        VALUES (p_id_plano, p_nome_plano, p_tipo_plano, p_valor_plano);
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20007, 'Erro: ID de plano já existe.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20008, 'Erro ao inserir plano: ' || SQLERRM);
    END;

    PROCEDURE ATUALIZAR_ODONTO_PLANO(
        p_id_plano IN NUMBER,
        p_nome_plano IN VARCHAR2,
        p_tipo_plano IN VARCHAR2,
        p_valor_plano IN NUMBER
    ) IS
    BEGIN
        UPDATE ODONTO_PLANO
        SET NOME_PLANO = p_nome_plano,
            TIPO_PLANO = p_tipo_plano,
            VALOR_PLANO = p_valor_plano
        WHERE ID_PLANO = p_id_plano;
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20009, 'Erro: Plano não encontrado.');
        END IF;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20010, 'Erro ao atualizar plano: ' || SQLERRM);
    END;

    PROCEDURE DELETAR_ODONTO_PLANO(p_id_plano IN NUMBER) IS
    BEGIN
        DELETE FROM ODONTO_PLANO WHERE ID_PLANO = p_id_plano;
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20011, 'Erro: Plano não encontrado.');
        END IF;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20012, 'Erro ao deletar plano: ' || SQLERRM);
    END;

    -- Procedure para ODONTO_PACIENTE
    PROCEDURE INSERIR_ODONTO_PACIENTE(
        p_id_paciente IN NUMBER,
        p_nome_paciente IN VARCHAR2,
        p_data_nascimento IN DATE,
        p_sexo IN VARCHAR2,
        p_cpf IN VARCHAR2,
        p_email IN VARCHAR2,
        p_telefone IN VARCHAR2,
        p_historico IN VARCHAR2,
        p_id_endereco IN NUMBER,
        p_id_plano IN NUMBER
    ) IS
    BEGIN
        INSERT INTO ODONTO_PACIENTE (
            ID_PACIENTE, NOME_PACIENTE, DATA_NASCIMENTO_PACIENTE, SEXO_PACIENTE, 
            CPF_PACIENTE, EMAIL_PACIENTE, TELEFONE_PACIENTE, HISTORICO_ODONTOLOGICO, 
            ID_ENDERECO, ID_PLANO
        ) VALUES (
            p_id_paciente, p_nome_paciente, p_data_nascimento, p_sexo, 
            p_cpf, p_email, p_telefone, p_historico, 
            p_id_endereco, p_id_plano
        );
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20013, 'Erro: ID ou CPF de paciente já existe.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20014, 'Erro ao inserir paciente: ' || SQLERRM);
    END;

    PROCEDURE ATUALIZAR_ODONTO_PACIENTE(
        p_id_paciente IN NUMBER,
        p_nome_paciente IN VARCHAR2,
        p_data_nascimento IN DATE,
        p_sexo IN VARCHAR2,
        p_cpf IN VARCHAR2,
        p_email IN VARCHAR2,
        p_telefone IN VARCHAR2,
        p_historico IN VARCHAR2,
        p_id_endereco IN NUMBER,
        p_id_plano IN NUMBER
    ) IS
    BEGIN
        UPDATE ODONTO_PACIENTE
        SET NOME_PACIENTE = p_nome_paciente,
            DATA_NASCIMENTO_PACIENTE = p_data_nascimento,
            SEXO_PACIENTE = p_sexo,
            CPF_PACIENTE = p_cpf,
            EMAIL_PACIENTE = p_email,
            TELEFONE_PACIENTE = p_telefone,
            HISTORICO_ODONTOLOGICO = p_historico,
            ID_ENDERECO = p_id_endereco,
            ID_PLANO = p_id_plano
        WHERE ID_PACIENTE = p_id_paciente;
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20015, 'Erro: Paciente não encontrado.');
        END IF;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20016, 'Erro ao atualizar paciente: ' || SQLERRM);
    END;

    PROCEDURE DELETAR_ODONTO_PACIENTE(p_id_paciente IN NUMBER) IS
    BEGIN
        DELETE FROM ODONTO_PACIENTE WHERE ID_PACIENTE = p_id_paciente;
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20017, 'Erro: Paciente não encontrado.');
        END IF;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20018, 'Erro ao deletar paciente: ' || SQLERRM);
    END;

    -- Procedure para ODONTO_DENTISTA
    PROCEDURE INSERIR_ODONTO_DENTISTA(
        p_id_dentista IN NUMBER,
        p_nome IN VARCHAR2,
        p_especialidade IN VARCHAR2,
        p_cro IN VARCHAR2,
        p_taxa_sinistros IN NUMBER,
        p_id_endereco IN NUMBER
    ) IS
    BEGIN
        INSERT INTO ODONTO_DENTISTA (ID_DENTISTA, NOME, ESPECIALIDADE, CRO, TAXA_SINISTROS, ID_ENDERECO)
        VALUES (p_id_dentista, p_nome, p_especialidade, p_cro, p_taxa_sinistros, p_id_endereco);
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20019, 'Erro: ID ou CRO de dentista já existe.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20020, 'Erro ao inserir dentista: ' || SQLERRM);
    END;

    PROCEDURE ATUALIZAR_ODONTO_DENTISTA(
        p_id_dentista IN NUMBER,
        p_nome IN VARCHAR2,
        p_especialidade IN VARCHAR2,
        p_cro IN VARCHAR2,
        p_taxa_sinistros IN NUMBER,
        p_id_endereco IN NUMBER
    ) IS
    BEGIN
        UPDATE ODONTO_DENTISTA
        SET NOME = p_nome,
            ESPECIALIDADE = p_especialidade,
            CRO = p_cro,
            TAXA_SINISTROS = p_taxa_sinistros,
            ID_ENDERECO = p_id_endereco
        WHERE ID_DENTISTA = p_id_dentista;
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20021, 'Erro: Dentista não encontrado.');
        END IF;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20022, 'Erro ao atualizar dentista: ' || SQLERRM);
    END;

    PROCEDURE DELETAR_ODONTO_DENTISTA(p_id_dentista IN NUMBER) IS
    BEGIN
        DELETE FROM ODONTO_DENTISTA WHERE ID_DENTISTA = p_id_dentista;
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20023, 'Erro: Dentista não encontrado.');
        END IF;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20024, 'Erro ao deletar dentista: ' || SQLERRM);
    END;

    -- Procedure para ODONTO_CLINICA
    PROCEDURE INSERIR_ODONTO_CLINICA(
        p_id_clinica IN NUMBER,
        p_nome_clinica IN VARCHAR2,
        p_tipo_atendimento IN VARCHAR2,
        p_id_endereco IN NUMBER
    ) IS
    BEGIN
        INSERT INTO ODONTO_CLINICA (ID_CLINICA, NOME_CLINICA, TIPO_ATENDIMENTO, ID_ENDERECO)
        VALUES (p_id_clinica, p_nome_clinica, p_tipo_atendimento, p_id_endereco);
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20025, 'Erro: ID de clínica já existe.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20026, 'Erro ao inserir clínica: ' || SQLERRM);
    END;

    PROCEDURE ATUALIZAR_ODONTO_CLINICA(
        p_id_clinica IN NUMBER,
        p_nome_clinica IN VARCHAR2,
        p_tipo_atendimento IN VARCHAR2,
        p_id_endereco IN NUMBER
    ) IS
    BEGIN
        UPDATE ODONTO_CLINICA
        SET NOME_CLINICA = p_nome_clinica,
            TIPO_ATENDIMENTO = p_tipo_atendimento,
            ID_ENDERECO = p_id_endereco
        WHERE ID_CLINICA = p_id_clinica;
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20027, 'Erro: Clínica não encontrada.');
        END IF;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20028, 'Erro ao atualizar clínica: ' || SQLERRM);
    END;

    PROCEDURE DELETAR_ODONTO_CLINICA(p_id_clinica IN NUMBER) IS
    BEGIN
        DELETE FROM ODONTO_CLINICA WHERE ID_CLINICA = p_id_clinica;
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20029, 'Erro: Clínica não encontrada.');
        END IF;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20030, 'Erro ao deletar clínica: ' || SQLERRM);
    END;

    -- Procedure para ODONTO_CONSULTA
    PROCEDURE INSERIR_ODONTO_CONSULTA(
        p_id_consulta IN NUMBER,
        p_data_consulta IN DATE,
        p_hora_consulta IN VARCHAR2,
        p_motivo_consulta IN VARCHAR2,
        p_tipo_consulta IN VARCHAR2,
        p_status_consulta IN VARCHAR2,
        p_tempo_espera IN NUMBER,
        p_id_paciente IN NUMBER,
        p_id_dentista IN NUMBER
    ) IS
    BEGIN
        INSERT INTO ODONTO_CONSULTA (
            ID_CONSULTA, DATA_CONSULTA, HORA_CONSULTA, MOTIVO_CONSULTA, 
            TIPO_CONSULTA, STATUS_CONSULTA, TEMPO_MEDIO_ESPERA, 
            ID_PACIENTE, ID_DENTISTA
        ) VALUES (
            p_id_consulta, p_data_consulta, p_hora_consulta, p_motivo_consulta, 
            p_tipo_consulta, p_status_consulta, p_tempo_espera, 
            p_id_paciente, p_id_dentista
        );
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20031, 'Erro: ID de consulta já existe.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20032, 'Erro ao inserir consulta: ' || SQLERRM);
    END;

    -- Procedure para ODONTO_PROCEDIMENTO
    PROCEDURE INSERIR_ODONTO_PROCEDIMENTO(
        p_id_procedimento IN NUMBER,
        p_tipo_procedimento IN VARCHAR2,
        p_custo_procedimento IN NUMBER,
        p_id_consulta IN NUMBER
    ) IS
    BEGIN
        INSERT INTO ODONTO_PROCEDIMENTO (
            ID_PROCEDIMENTO, TIPO_PROCEDIMENTO, CUSTO_PROCEDIMENTO, ID_CONSULTA
        ) VALUES (
            p_id_procedimento, p_tipo_procedimento, p_custo_procedimento, p_id_consulta
        );
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20033, 'Erro: ID de procedimento já existe.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20034, 'Erro ao inserir procedimento: ' || SQLERRM);
    END;

    -- Procedure para ODONTO_SINISTRO
    PROCEDURE INSERIR_ODONTO_SINISTRO(
        p_id_sinistro IN NUMBER,
        p_data_sinistro IN DATE,
        p_valor_sinistro IN NUMBER,
        p_id_consulta IN NUMBER,
        p_causa IN VARCHAR2,
        p_status IN VARCHAR2
    ) IS
    BEGIN
        INSERT INTO ODONTO_SINISTRO (
            ID_SINISTRO, DATA_SINISTRO, VALOR_SINISTRO, ID_CONSULTA, 
            CAUSA_SINISTRO, STATUS_SINISTRO
        ) VALUES (
            p_id_sinistro, p_data_sinistro, p_valor_sinistro, p_id_consulta, 
            p_causa, p_status
        );
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20035, 'Erro: ID de sinistro já existe.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20036, 'Erro ao inserir sinistro: ' || SQLERRM);
    END;

    -- Procedure para ODONTO_SCORING
    PROCEDURE INSERIR_ODONTO_SCORING(
        p_id_scoring IN NUMBER,
        p_id_dentista IN NUMBER,
        p_scoring IN NUMBER,
        p_data_avaliacao IN DATE
    ) IS
    BEGIN
        INSERT INTO ODONTO_SCORING (
            ID_SCORING, ID_DENTISTA, SCORING, DATA_AVALIACAO
        ) VALUES (
            p_id_scoring, p_id_dentista, p_scoring, p_data_avaliacao
        );
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20037, 'Erro: ID de scoring já existe.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20038, 'Erro ao inserir scoring: ' || SQLERRM);
    END;

    -- Procedure para RELATORIO_SINISTROS_DENTISTA
    PROCEDURE RELATORIO_SINISTROS_DENTISTA(
        p_data_inicio IN DATE,
        p_data_fim IN DATE,
        p_cursor OUT SYS_REFCURSOR
    ) IS
    BEGIN
        OPEN p_cursor FOR
            SELECT 
                d.NOME AS NOME_DENTISTA,
                d.ESPECIALIDADE,
                COUNT(s.ID_SINISTRO) AS TOTAL_SINISTROS,
                SUM(s.VALOR_SINISTRO) AS VALOR_TOTAL,
                NVL(TO_CHAR(sc.SCORING, 'FM999.00'), 'N/A') AS ULTIMO_SCORING
            FROM ODONTO_DENTISTA d
            INNER JOIN ODONTO_CONSULTA c ON d.ID_DENTISTA = c.ID_DENTISTA
            INNER JOIN ODONTO_SINISTRO s ON c.ID_CONSULTA = s.ID_CONSULTA
            LEFT JOIN ODONTO_SCORING sc ON d.ID_DENTISTA = sc.ID_DENTISTA
                AND sc.DATA_AVALIACAO = (SELECT MAX(DATA_AVALIACAO) 
                                         FROM ODONTO_SCORING 
                                         WHERE ID_DENTISTA = d.ID_DENTISTA)
            WHERE s.DATA_SINISTRO BETWEEN p_data_inicio AND p_data_fim
            GROUP BY d.NOME, d.ESPECIALIDADE, sc.SCORING
            ORDER BY VALOR_TOTAL DESC;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20039, 'Erro no relatório de sinistros por dentista: ' || SQLERRM);
    END;

    -- Procedure para RELATORIO_PACIENTES_ALTO_CUSTO
    PROCEDURE RELATORIO_PACIENTES_ALTO_CUSTO(
        p_valor_limite IN NUMBER,
        p_cursor OUT SYS_REFCURSOR
    ) IS
    BEGIN
        OPEN p_cursor FOR
            SELECT 
                p.NOME_PACIENTE,
                NVL(pl.NOME_PLANO, 'Sem Plano') AS NOME_PLANO,
                COUNT(c.ID_CONSULTA) AS TOTAL_CONSULTAS,
                SUM(pr.CUSTO_PROCEDIMENTO) AS CUSTO_TOTAL
            FROM ODONTO_PACIENTE p
            INNER JOIN ODONTO_CONSULTA c ON p.ID_PACIENTE = c.ID_PACIENTE
            INNER JOIN ODONTO_PROCEDIMENTO pr ON c.ID_CONSULTA = pr.ID_CONSULTA
            LEFT JOIN ODONTO_PLANO pl ON p.ID_PLANO = pl.ID_PLANO
            GROUP BY p.NOME_PACIENTE, pl.NOME_PLANO
            HAVING SUM(pr.CUSTO_PROCEDIMENTO) > p_valor_limite
            ORDER BY CUSTO_TOTAL DESC;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20040, 'Erro no relatório de pacientes com alto custo: ' || SQLERRM);
    END;
END ODONTO_PACKAGE;
/