package com.gabrielhahn.gerencesy.services;

import com.gabrielhahn.gerencesy.model.Board;
import com.gabrielhahn.gerencesy.model.Cartao;
import com.gabrielhahn.gerencesy.utils.GenericDao;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author gabrielhahnschaeffer
 */
@Stateless
public class BoardService extends AbstractCrudService<Board> {

    @Inject
    private GenericDao<Board> dao;

    @Override
    protected GenericDao<Board> getDao() {
        return dao;
    }
    
    public List<Board> findAll() {
        List<Board> boards = dao.findAll();

        //Calcula o progresso de cada board.
        boards = getProcessoBoards(boards);

        //Calcula o progresso em tempo de cada board.
        boards = getTempoBoards(boards);

        return boards;
    }

    public Long getIdBoardCheck() {
        List<Board> boards = dao.findByQuery("SELECT b FROM Board AS b where b.status = 'S'");
        return boards.get(0).getId();
    }
    
    public Board insert(Board board) {
        List<Board> boards = findAll();
        board = verifyStatus(boards, board);

        dao.insert(board);

        return board;
    }

    public Board changeBoardActive(Long id) {

        //Atualiza o board ativo atual
        List<Board> boards = dao.findByQuery("SELECT b FROM Board AS b where b.status = 'S'");
        Board novoBoardAtivo = findById(id);
        if(boards.size() > 0) {
            boards.get(0).setStatus("N");
            dao.update(boards.get(0));

            //Seta o novo board ativo
            novoBoardAtivo.setStatus("S");
            dao.update(novoBoardAtivo);
        }

        return novoBoardAtivo;
    }

    //Remove o board selecionado e seta um novo para padrão.
    public void remove(Long id) {
        dao.delete(id);
        List<Board> boards = dao.findAll();
        if(boards.size() > 0) {
            boards.get(0).setStatus("S");
            dao.update(boards.get(0));
        }
    }

    //Retorna o progresso de conclusão de cada board
    private List<Board> getProcessoBoards(List<Board> boards) {
        boards.forEach(x -> {
            List<Cartao> cartoes = x.getCartoes();
            Long total = new Long(cartoes.size());

            if(cartoes.size() > 0) {
                final Long[] totalConcluidos = {0L};

                cartoes.forEach(y -> {
                    if(y.getStatus().equals("C")){
                        totalConcluidos[0]+= 1;
                    }
                });

                x.setProgresso(((double) totalConcluidos[0] / total) * 100);
            }
            else {
                x.setProgresso(0);
            }
        });

        return boards;
    }

    //Retorna o tempo de conclusão de cada board
    private List<Board> getTempoBoards(List<Board> boards) {
        boards.forEach(x -> {
            List<Cartao> cartoes = x.getCartoes();
            Long[] total = {0L};

            if(cartoes.size() > 0) {
                final Long[] totalConcluidos = {0L};

                cartoes.forEach(y -> {
                    if(y.getStatus().equals("C")){
                        totalConcluidos[0]+= y.getTempo();
                    }
                    total[0] += y.getTempo();
                });

                x.setProgressoTempo(((double) totalConcluidos[0] / total[0]) * 100);
            }
            else {
                x.setProgressoTempo(0);
            }
        });

        return boards;
    }

    //Antes de inserir, verifica se existe outro board no sistema. Caso não existir, seta este primeiro board para ativo automaticamente.
    private Board verifyStatus(List<Board> boards, Board board) {
        if(boards.size() == 0) {
            board.setStatus("S");
        }

        return board;
    }
}
