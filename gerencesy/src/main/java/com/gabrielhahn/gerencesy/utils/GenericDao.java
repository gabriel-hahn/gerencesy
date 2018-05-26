package com.gabrielhahn.gerencesy.utils;

import com.gabrielhahn.gerencesy.model.Entidade;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

/**
 * @param <T> Tipo da Entidade no GenericDao
 */
public class GenericDao<T extends Entidade> {

    private final EntityManager em;
    private final Class<T> type;

    public GenericDao(EntityManager em, Class<T> type) {
        this.em = em;
        this.type = type;
    }

    public T insert(T bean) {
        em.persist(bean);
        return bean;
    }

    public T update(T bean) {
        T oldBean = em.find(type, bean.getId());
        if (oldBean == null) {
            throw new EntityNotFoundException();
        }
        return em.merge(bean);
    }

    public void delete(Long id) {
        T bean = em.getReference(type, id);
        if (bean == null) {
            throw new EntityNotFoundException();
        }
        em.remove(bean);
    }

    private boolean nomEmpty(String... values) {
        for (String val : values) {
            if (val == null || val.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private List<String> asListString(String value) {
        return Arrays.asList(value.split("\\."));
    }

    public List<T> findAll(String filterField, String filterData, String order) {
        return findAll(null, null, filterField, filterData, order);
    }

    public List<T> findAll() {
        JpaCriteriaHelper helper = JpaCriteriaHelper.select(em, type);
        return helper.getResults();
    }

    public Long getCount(String filterField, String filterData) {
        JpaCriteriaHelper helper = JpaCriteriaHelper.select(em, type);
        if (nomEmpty(filterField, filterData)) {
            helper.where(asListString(filterField), JpaCriteriaHelper.ComparatorOperator.LIKE_IGNORE_CASE, filterData.replaceAll("\\b", "%"));
        }
        return helper.count();
    }

    public List<T> findAll(Integer pageSize, Integer pageNumber, String filterField, String filterData, String order) {
        JpaCriteriaHelper helper = JpaCriteriaHelper.select(em, type);
        if (pageSize != null && pageNumber != null) {
            helper.setPageSize(pageSize)
                    .page(pageNumber);
        }
        if (nomEmpty(filterField, filterData)) {
            helper.where(asListString(filterField), JpaCriteriaHelper.ComparatorOperator.LIKE_IGNORE_CASE, filterData.replaceAll("\\b", "%"));
        }
        if (nomEmpty(order)) {
            String[] parts = order.split("\\+");
            helper.orderBy(parts[0]);
            if (parts.length > 1 && parts[1].equalsIgnoreCase("desc")) {
                helper.desc();
            }
        }
        return helper.getResults();
    }

    public List<T> findByQuery(String query) {
        return em.createQuery(query).getResultList();
    }

    public T find(Long id) {
        T bean = em.find(type, id);
        if (bean == null) {
            throw new EntityNotFoundException();
        }
        return bean;
    }

}
