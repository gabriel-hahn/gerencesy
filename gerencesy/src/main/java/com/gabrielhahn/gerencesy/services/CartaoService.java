package com.gabrielhahn.gerencesy.services;

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
public class CartaoService {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Cartao> findAll() {
        Query query = em.createQuery("SELECT c FROM Cartao AS c");
        return query.getResultList();
    }
    
    public Cartao insert(Cartao cartao) {
        em.persist(cartao);
        return cartao;
    }
    
    public Cartao update(Cartao cartao) {
        return em.merge(cartao);
    }
    
    public Cartao findById(Long id) {
        return em.find(Cartao.class, id);
    }
    
    public void remove(Long id) {
        Cartao cartao = em.getReference(Cartao.class, id);
        em.remove(cartao);
    }
}
