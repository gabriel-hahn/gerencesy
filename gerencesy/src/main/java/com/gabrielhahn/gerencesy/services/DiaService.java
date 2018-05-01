package com.gabrielhahn.gerencesy.services;

import com.gabrielhahn.gerencesy.model.Dia;
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
public class DiaService {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Dia> findAll() {
        Query query = em.createQuery("SELECT d FROM Dia AS d");
        return query.getResultList();
    }
    
    public Dia insert(Dia dia) {
        em.persist(dia);
        return dia;
    }
    
    public Dia update(Dia dia) {
        return em.merge(dia);
    }
    
    public Dia findById(Long id) {
        return em.find(Dia.class, id);
    }
    
    public void remove(Long id) {
        Dia dia = em.getReference(Dia.class, id);
        em.remove(dia);
    }
}
