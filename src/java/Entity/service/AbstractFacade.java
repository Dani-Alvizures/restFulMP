/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author dannyalvizures
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> listarFiscalia() {
        StoredProcedureQuery spquery = getEntityManager().createStoredProcedureQuery("listarFiscalias");
        // execute SP
        spquery.execute();
        // get result
        try{
            return spquery.getResultList();
        }catch(Exception e){
            return null;
        }
        
    }

    public T buscarFiscalia(int id) {
        StoredProcedureQuery spquery = getEntityManager().createStoredProcedureQuery("buscarFiscalia");
        // set parameters
        spquery.registerStoredProcedureParameter("idFiscalia", Integer.class, ParameterMode.IN);
        spquery.setParameter("idFiscalia", id);
        // execute SP
        spquery.execute();
        // get result
        try{
            return (T) spquery.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    public boolean eliminarFiscalia(int id) {
        try {
            StoredProcedureQuery spquery = getEntityManager().createStoredProcedureQuery("eliminarFiscalia");
            // set parameters
            spquery.registerStoredProcedureParameter("idFiscalia", Integer.class, ParameterMode.IN);
            spquery.setParameter("idFiscalia", id);
            // execute SP
            spquery.execute();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean modificarFiscalia(int id, String nombre, String direccion, String telefono) {
        try {
            StoredProcedureQuery spquery = getEntityManager().createStoredProcedureQuery("modificarFiscalia");
            // set parameters
            spquery.registerStoredProcedureParameter("idFiscalia", Integer.class, ParameterMode.IN);
            spquery.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN);
            spquery.registerStoredProcedureParameter("direccion", String.class, ParameterMode.IN);
            spquery.registerStoredProcedureParameter("telefono", String.class, ParameterMode.IN);
            spquery.setParameter("idFiscalia", id);
            spquery.setParameter("nombre", nombre);
            spquery.setParameter("direccion", direccion);
            spquery.setParameter("telefono", telefono);
            // execute SP
            spquery.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean crearFiscalia(String nombre, String direccion, String telefono) {
        try {
            StoredProcedureQuery spquery = getEntityManager().createStoredProcedureQuery("insertarFiscalia");
            // set parameters
            spquery.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN);
            spquery.registerStoredProcedureParameter("direccion", String.class, ParameterMode.IN);
            spquery.registerStoredProcedureParameter("telefono", String.class, ParameterMode.IN);
            spquery.setParameter("nombre", nombre);
            spquery.setParameter("direccion", direccion);
            spquery.setParameter("telefono", telefono);
            // execute SP
            spquery.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
