package builders;

import com.gabrielhahn.gerencesy.model.Cartao;

public class CartaoBuilder {

    private Cartao cartao;

    private CartaoBuilder() {

    }

    public static CartaoBuilder umCartao() {
        CartaoBuilder cartaoBuilder = new CartaoBuilder();
        cartaoBuilder.cartao = new Cartao();
        cartaoBuilder.cartao.setNome("Cartão teste");
        cartaoBuilder.cartao.setTempo(2L);
        return cartaoBuilder;
    }

    public CartaoBuilder concluido() {
        cartao.setStatus("C");
        return this;
    }

    public CartaoBuilder naoConcluido() {
        cartao.setStatus("A");
        return this;
    }

    public Cartao now() {
        return cartao;
    }

}
