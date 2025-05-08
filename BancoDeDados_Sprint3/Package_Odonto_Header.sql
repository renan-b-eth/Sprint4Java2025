CREATE OR REPLACE PACKAGE ODONTO_PACKAGE AS
    -- Procedures CRUD para ODONTO_ENDERECO
    PROCEDURE INSERIR_ODONTO_ENDERECO(
        p_id_endereco IN NUMBER,
        p_logradouro IN VARCHAR2,
        p_numero IN VARCHAR2,
        p_complemento IN VARCHAR2,
        p_bairro IN VARCHAR2,
        p_cidade IN VARCHAR2,
        p_estado IN VARCHAR2,
        p_cep IN VARCHAR2
    );

    PROCEDURE ATUALIZAR_ODONTO_ENDERECO(
        p_id_endereco IN NUMBER,
        p_logradouro IN VARCHAR2,
        p_numero IN VARCHAR2,
        p_complemento IN VARCHAR2,
        p_bairro IN VARCHAR2,
        p_cidade IN VARCHAR2,
        p_estado IN VARCHAR2,
        p_cep IN VARCHAR2
    );

    PROCEDURE DELETAR_ODONTO_ENDERECO(p_id_endereco IN NUMBER);

    -- Procedures CRUD para ODONTO_PLANO
    PROCEDURE INSERIR_ODONTO_PLANO(
        p_id_plano IN NUMBER,
        p_nome_plano IN VARCHAR2,
        p_tipo_plano IN VARCHAR2,
        p_valor_plano IN NUMBER
    );

    PROCEDURE ATUALIZAR_ODONTO_PLANO(
        p_id_plano IN NUMBER,
        p_nome_plano IN VARCHAR2,
        p_tipo_plano IN VARCHAR2,
        p_valor_plano IN NUMBER
    );

    PROCEDURE DELETAR_ODONTO_PLANO(p_id_plano IN NUMBER);

    -- Procedures CRUD para ODONTO_PACIENTE
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
    );

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
    );

    PROCEDURE DELETAR_ODONTO_PACIENTE(p_id_paciente IN NUMBER);

    -- Procedures CRUD para ODONTO_DENTISTA
    PROCEDURE INSERIR_ODONTO_DENTISTA(
        p_id_dentista IN NUMBER,
        p_nome IN VARCHAR2,
        p_especialidade IN VARCHAR2,
        p_cro IN VARCHAR2,
        p_taxa_sinistros IN NUMBER,
        p_id_endereco IN NUMBER
    );

    PROCEDURE ATUALIZAR_ODONTO_DENTISTA(
        p_id_dentista IN NUMBER,
        p_nome IN VARCHAR2,
        p_especialidade IN VARCHAR2,
        p_cro IN VARCHAR2,
        p_taxa_sinistros IN NUMBER,
        p_id_endereco IN NUMBER
    );

    PROCEDURE DELETAR_ODONTO_DENTISTA(p_id_dentista IN NUMBER);

    -- Procedures CRUD para ODONTO_CLINICA
    PROCEDURE INSERIR_ODONTO_CLINICA(
        p_id_clinica IN NUMBER,
        p_nome_clinica IN VARCHAR2,
        p_tipo_atendimento IN VARCHAR2,
        p_id_endereco IN NUMBER
    );

    PROCEDURE ATUALIZAR_ODONTO_CLINICA(
        p_id_clinica IN NUMBER,
        p_nome_clinica IN VARCHAR2,
        p_tipo_atendimento IN VARCHAR2,
        p_id_endereco IN NUMBER
    );

    PROCEDURE DELETAR_ODONTO_CLINICA(p_id_clinica IN NUMBER);

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
    );

    -- Procedure para ODONTO_PROCEDIMENTO
    PROCEDURE INSERIR_ODONTO_PROCEDIMENTO(
        p_id_procedimento IN NUMBER,
        p_tipo_procedimento IN VARCHAR2,
        p_custo_procedimento IN NUMBER,
        p_id_consulta IN NUMBER
    );

    -- Procedure para ODONTO_SINISTRO
    PROCEDURE INSERIR_ODONTO_SINISTRO(
        p_id_sinistro IN NUMBER,
        p_data_sinistro IN DATE,
        p_valor_sinistro IN NUMBER,
        p_id_consulta IN NUMBER,
        p_causa IN VARCHAR2,
        p_status IN VARCHAR2
    );

    -- Procedure para ODONTO_SCORING
    PROCEDURE INSERIR_ODONTO_SCORING(
        p_id_scoring IN NUMBER,
        p_id_dentista IN NUMBER,
        p_scoring IN NUMBER,
        p_data_avaliacao IN DATE
    );

    -- Procedures para Relatórios
    PROCEDURE RELATORIO_SINISTROS_DENTISTA(
        p_data_inicio IN DATE,
        p_data_fim IN DATE,
        p_cursor OUT SYS_REFCURSOR
    );

    PROCEDURE RELATORIO_PACIENTES_ALTO_CUSTO(
        p_valor_limite IN NUMBER,
        p_cursor OUT SYS_REFCURSOR
    );
END ODONTO_PACKAGE;
/