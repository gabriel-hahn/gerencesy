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
        cartaoBuilder.cartao.setTempo(1L);
        cartaoBuilder.cartao.setStatus("A");
        cartaoBuilder.cartao.setIdBoard(1L);
        return cartaoBuilder;
    }

    public CartaoBuilder concluido() {
        cartao.setStatus("C");
        return this;
    }

    public Cartao now() {
        return cartao;
    }

}
