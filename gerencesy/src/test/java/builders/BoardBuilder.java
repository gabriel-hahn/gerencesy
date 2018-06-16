package builders;

import com.gabrielhahn.gerencesy.model.Board;
import com.gabrielhahn.gerencesy.model.Cartao;

import java.util.ArrayList;
import java.util.List;

public class BoardBuilder {

    private Board board;

    private BoardBuilder() {

    }

    public static BoardBuilder umBoard() {
        BoardBuilder builder = new BoardBuilder();
        builder.board = new Board();
        builder.board.setNome("Board teste");
        builder.board.setProgressoTempo(22.50);
        builder.board.setProgresso(80.00);
        return builder;
    }

    public BoardBuilder comCartoes() {
        List<Cartao> cartoes = new ArrayList<>();
        for (int i = 0 ; i < 5; i++) {
            cartoes.add(CartaoBuilder.umCartao().naoConcluido().now());
        }
        board.setCartoes(cartoes);
        return this;
    }

    public BoardBuilder umBoardAtivo() {
        board.setStatus("N");
        return this;
    }

    public BoardBuilder umBoardInativo() {
        board.setStatus("N");
        return this;
    }

    public Board now() {
        return board;
    }

}
