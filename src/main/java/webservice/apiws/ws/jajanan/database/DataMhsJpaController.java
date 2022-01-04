/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webservice.apiws.ws.jajanan.database;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import webservice.apiws.ws.jajanan.database.exceptions.NonexistentEntityException;
import webservice.apiws.ws.jajanan.database.exceptions.PreexistingEntityException;

/**
 *
 * @author HP
 */

//path variable untuk fokus ke nim?
@RestController
public class DataMhsJpaController implements Serializable {

    public DataMhsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("webservice.apiws_ws.jajanan_jar_0.0.1-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DataMhsJpaController() {
    }

    //CREATE
    @ResponseBody
    @PostMapping(value = "/post")
    public void create(@RequestBody DataMhs dataMhs) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dataMhs);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDataMhs(dataMhs.getNim()) != null) {
                throw new PreexistingEntityException("DataMhs " + dataMhs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //UPDATE
    @ResponseBody
    @RequestMapping(value = "/put/{nim}", method = RequestMethod.PUT)
    public void edit(@RequestBody DataMhs dataMhs) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dataMhs = em.merge(dataMhs);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String nim = dataMhs.getNim();
                if (findDataMhs(nim) == null) {
                    throw new NonexistentEntityException("The dataMhs with id " + nim + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //DELETE
    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DataMhs dataMhs;
            try {
                dataMhs = em.getReference(DataMhs.class, id);
                dataMhs.getNim();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dataMhs with id " + id + " no longer exists.", enfe);
            }
            em.remove(dataMhs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DataMhs> findDataMhsEntities() {
        return findDataMhsEntities(true, -1, -1);
    }

    public List<DataMhs> findDataMhsEntities(int maxResults, int firstResult) {
        return findDataMhsEntities(false, maxResults, firstResult);
    }

    private List<DataMhs> findDataMhsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DataMhs.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    //READ (NIM)
    @ResponseBody
    @GetMapping(value = "/get/{nim}")
    public DataMhs findDataMhs(@PathVariable String nim) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataMhs.class, nim);
        } finally {
            em.close();
        }
    }

    public int getDataMhsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DataMhs> rt = cq.from(DataMhs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
