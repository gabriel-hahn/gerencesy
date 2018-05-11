package com.gabrielhahn.gerencesy.services;

import com.gabrielhahn.gerencesy.model.Board;
import com.gabrielhahn.gerencesy.model.Cartao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gabrielhahnschaeffer
 */
@Stateless
public class BoardService {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Board> findAll() {
        Query query = em.createQuery("SELECT b FROM Board AS b");
        List<Board> boards = query.getResultList();

        //Calcula o progresso de cada board.
        boards.forEach(x -> {
            List<Cartao> cartoes = x.getCartoes();
            Long total = (long) cartoes.size();

            if(total > 0) {
                final Long[] totalConcluidos = {0L};

                cartoes.forEach(y -> {
                    if(y.getStatus().equals("C")){
                        totalConcluidos[0]++;
                    }
                });

                x.setProgresso(totalConcluidos[0] / total * 100);
            }
            else {
                x.setProgresso(0L);
            }
        });

        return boards;
    }

    public Long getIdBoardCheck() {
        Query query = em.createQuery("SELECT b FROM Board AS b where b.status = 'S'");
        List<Board> boards = query.getResultList();
        return boards.get(0).getId();
    }
    
    public Board insert(Board board) {
        em.persist(board);
        return board;
    }
    
    public Board update(Board board) {
        return em.merge(board);
    }

    public Board changeBoardActive(Long id) {

        //Atualiza o board ativo atual
        Query query = em.createQuery("SELECT b FROM Board AS b where b.status = 'S'");
        List<Board> boards = query.getResultList();
        boards.get(0).setStatus("N");
        em.merge(boards.get(0));

        //Seta o novo board ativo
        Board novoBoardAtivo = findById(id);
        novoBoardAtivo.setStatus("S");
        em.merge(novoBoardAtivo);

        return novoBoardAtivo;
    }
    
    public Board findById(Long id) {
        return em.find(Board.class, id);
    }
    
    public void remove(Long id) {
        Board board = em.getReference(Board.class, id);
        em.remove(board);
    }
}
