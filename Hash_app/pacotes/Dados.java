package Hash_app.pacotes;

public class Dados {
    enum validade{em_vigor, contrato_vencido, a_contratar}

    protected validade validade_contrato;
    protected String cargo;
    protected Integer numero_cartao;
}
