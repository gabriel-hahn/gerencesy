package com.gabrielhahn.gerencesy.services;

import com.gabrielhahn.gerencesy.model.Board;

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
        Query query = em.createQuery("SELECT d FROM Board AS d");
        return query.getResultList();
    }
    
    public Board insert(Board board) {
        em.persist(board);
        return board;
    }
    
    public Board update(Board board) {
        return em.merge(board);
    }
    
    public Board findById(Long id) {
        return em.find(Board.class, id);
    }
    
    public void remove(Long id) {
        Board board = em.getReference(Board.class, id);
        em.remove(board);
    }
}
