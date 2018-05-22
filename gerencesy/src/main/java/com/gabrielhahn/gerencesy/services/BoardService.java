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

        //Calcula o progresso em tempo de cada board.
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

    public Long getIdBoardCheck() {
        Query query = em.createQuery("SELECT b FROM Board AS b where b.status = 'S'");
        List<Board> boards = query.getResultList();
        return boards.get(0).getId();
    }
    
    public Board insert(Board board) {

        //Antes de inserir, verifica se existe outro board no sistema. Caso não existir, seta este primeiro board para ativo automaticamente.
        List<Board> boards = findAll();
        if(boards.size() > 0) {
            em.persist(board);
        }
        else {
            board.setStatus("S");
            em.persist(board);
        }

        return board;
    }
    
    public Board update(Board board) {
        return em.merge(board);
    }

    public Board changeBoardActive(Long id) {

        //Atualiza o board ativo atual
        Query query = em.createQuery("SELECT b FROM Board AS b where b.status = 'S'");
        List<Board> boards = query.getResultList();
        Board novoBoardAtivo = findById(id);
        if(boards.size() > 0) {
            boards.get(0).setStatus("N");
            em.merge(boards.get(0));

            //Seta o novo board ativo
            novoBoardAtivo.setStatus("S");
            em.merge(novoBoardAtivo);
        }

        return novoBoardAtivo;
    }
    
    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    //Remove o board selecionado e seta um novo para padrão.
    public void remove(Long id) {
        Board board = em.getReference(Board.class, id);
        em.remove(board);
        Query query = em.createQuery("SELECT b FROM Board AS b");
        List<Board> boards = query.getResultList();
        if(boards.size() > 0) {
            boards.get(0).setStatus("S");
            em.merge(boards.get(0));
        }
    }
}
