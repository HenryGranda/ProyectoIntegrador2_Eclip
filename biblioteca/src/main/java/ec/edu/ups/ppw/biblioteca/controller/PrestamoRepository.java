package ec.edu.ups.ppw.biblioteca.controller;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

import ec.edu.ups.ppw.biblioteca.model.Prestamo;

@Stateless
public class PrestamoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Prestamo> findLoansNearDueDate() {
        System.out.println("Ejecutando findLoansNearDueDate...");
        String jpql = "SELECT p FROM Prestamo p WHERE p.fechaDevolucion BETWEEN :startDate AND :endDate";
        TypedQuery<Prestamo> query = entityManager.createQuery(jpql, Prestamo.class);
        query.setParameter("startDate", new Date());
        query.setParameter("endDate", new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)); // 7 días después
        return query.getResultList();
    }
}
