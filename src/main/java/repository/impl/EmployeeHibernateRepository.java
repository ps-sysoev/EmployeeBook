package repository.impl;

import lombok.extern.slf4j.Slf4j;
import model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
public class EmployeeHibernateRepository implements Repository<Long, Employee> {
    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;

    @Override
    public void init() {
        registry = new StandardServiceRegistryBuilder().configure().build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public Employee save(Employee employee) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();

        return employee;
    }

    @Override
    public boolean update(Long id, Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Employee> query = session.createQuery("UPDATE Employee SET name = :name, country = :country WHERE id = :id");
            query.setParameter("name", employee.getName());
            query.setParameter("country", employee.getCountry());
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("Error in the update method {}, {}", e.getClass().getCanonicalName(), e.getMessage());

            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(Employee.builder()
                    .id(id)
                    .build());
            session.getTransaction().commit();

        } catch (HibernateException e) {
            log.error("Error in the delete method {}, {}", e.getClass().getCanonicalName(), e.getMessage());

            return false;
        }

        return true;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.beginTransaction().commit();

        return Optional.ofNullable(employee);
    }

    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        List<Employee> employeeList = session.createQuery("from Employee", Employee.class).getResultList();
        session.getTransaction().commit();

        return employeeList;
    }

    @Override
    public List<Employee> findByName(String name) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Query<Employee> employeeQuery = session.createQuery("from Employee where name = :name", Employee.class);
        employeeQuery.setParameter("name", name);
        session.getTransaction().commit();

        return employeeQuery.getResultList();
    }

    @Override
    public List<Employee> findByCreatedDateInterval(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public void close() {
        try {
            StandardServiceRegistryBuilder.destroy(registry);

            log.info("Session is closed.");
        } catch (Exception e) {
            log.error("Close session: SQLException in the close method {} {}", e.getClass().getCanonicalName(), e.getMessage());

            e.printStackTrace();
        }
    }
}
