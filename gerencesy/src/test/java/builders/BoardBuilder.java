package builders;

import com.gabrielhahn.gerencesy.model.Board;
import com.gabrielhahn.gerencesy.model.Cartao;

import java.util.ArrayList;
import java.util.List;

public class BoardBuilder {

    private Board board;

    private List<Board> boards;

    private BoardBuilder() {

    }

    public static BoardBuilder umBoard() {
        BoardBuilder builder = new BoardBuilder();
        builder.board = new Board();
        builder.board.setNome("Board teste");
        builder.board.setProgressoTempo(22.50);
        builder.board.setProgresso(80.00);
        builder.board.setStatus("N");
        builder = comCartoes(builder, false);
        return builder;
    }

    public static BoardBuilder variosBoards() {
        BoardBuilder builder = new BoardBuilder();
        builder.boards = new ArrayList<>();
        for (int i = 0 ; i < 5; i ++) {
            builder.boards.add(umBoard().nowBoard());
            builder = comCartoes(builder, true);
        }
        return builder;
    }

    private static BoardBuilder comCartoes(BoardBuilder builder, boolean isList) {
        List<Cartao> cartoes = new ArrayList<>();
        for (int i = 0 ; i < 5; i++) {
            cartoes.add(CartaoBuilder.umCartao().now());
        }

        if (isList) {
            builder.boards.forEach(x -> x.setCartoes(cartoes));
        } else {
            builder.board.setCartoes(cartoes);
        }

        return builder;
    }

    public BoardBuilder umBoardAtivo() {
        board.setStatus("S");
        return this;
    }

    public Board nowBoard() {
        return board;
    }

    public List<Board> nowBoards() {
        return boards;
    }

}
