package br.com.imarui.ima.identity.core.domain.enums.affiliation;

/**
 * Define os tipos de vínculo que uma pessoa pode possuir com uma organização.
 *
 * <p>Este enum não representa cargo, função operacional ou permissão de acesso.
 * Ele descreve somente a natureza do relacionamento entre o usuário e uma
 * organização.</p>
 *
 * <p>Uma mesma pessoa pode possuir mais de um vínculo, inclusive com a mesma
 * organização, desde que as regras do domínio permitam.</p>
 */
public enum AffiliationType {

    /**
     * Indica que a pessoa possui vínculo de trabalho com a organização.
     *
     * <p>Exemplos: colaborador contratado, funcionário administrativo,
     * motorista próprio, ajudante próprio ou gestor.</p>
     *
     * <p>O cargo específico da pessoa não deve ser representado por este valor.</p>
     */
    EMPLOYEE,

    /**
     * Indica que a pessoa está vinculada a uma organização cliente.
     *
     * <p>Exemplos: proprietário do estabelecimento, comprador, gerente,
     * responsável financeiro ou outro representante autorizado do cliente.</p>
     *
     * <p>A organização é o cliente; a pessoa é alguém vinculada a ela.</p>
     */
    CUSTOMER,

    PARTNER,

    /**
     * Indica que a pessoa está vinculada a uma organização fornecedora.
     *
     * <p>Exemplos: vendedor, consultor, responsável comercial, técnico
     * ou representante de uma empresa fornecedora.</p>
     *
     * <p>A organização é o fornecedor; a pessoa apenas a representa
     * ou atua em seu nome.</p>
     */
    SUPPLIER,

    /**
     * Indica que a pessoa está vinculada a uma organização transportadora.
     *
     * <p>Exemplos: motorista terceirizado, ajudante, gestor de frota
     * ou representante de uma transportadora.</p>
     *
     * <p>Este valor não representa a modalidade operacional do frete.
     * Conceitos como SPOT, frota fixa ou veículo agregado devem ser tratados
     * em outro contexto do domínio, como contrato, operação ou transporte.</p>
     */
    EXTERNAL
}