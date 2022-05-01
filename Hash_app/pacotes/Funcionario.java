package Hash_app.pacotes;

import java.util.Calendar;

import Hash_app.pacotes.Dados.validade;

public class Funcionario extends Pessoa {
    private Dados cartao= new Dados();

    public Funcionario(String nome, String cpf, Integer id, Integer contrato_de, Integer contrato_ate) {
        setNome(nome);
        setCpf(cpf);
        setCartao(id);
        setContrato(contrato_de, contrato_ate);
    }

    public void setCartao(Integer cartao) {
        this.cartao.numero_cartao = cartao;
    }

    public String getCartao() {
       return cartao.numero_cartao.toString();
    }

    public void setContrato(int inicio,int fim) {
        Calendar hoje= Calendar.getInstance();
        int ano= hoje.get(Calendar.YEAR);

        if(ano<inicio) {
            this.cartao.validade_contrato= validade.a_contratar;
        }

        else if(ano>fim) {
            this.cartao.validade_contrato= validade.contrato_vencido;
        }

        else if(ano>=inicio && ano<=fim) {
            this.cartao.validade_contrato= validade.em_vigor;
        }
    }

    public String getContrato() {
        return "status da contratacao: "+cartao.validade_contrato;
    }

    public void getDados() {
        getNome();
        getCpf();
        getCartao();
        getContrato();
    }
}
