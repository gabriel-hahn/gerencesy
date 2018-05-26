package com.gabrielhahn.gerencesy.services;

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
public class CartaoService extends AbstractCrudService<Cartao> {

    @Inject
    private GenericDao<Cartao> dao;

    @Override
    protected GenericDao<Cartao> getDao() {
        return dao;
    }

    public List<Cartao> findAllBoardActive() {
        return dao.findByQuery("SELECT c FROM Cartao AS c join Board as b ON (c.idBoard = b.id) where b.status = 'S'");
    }
}
