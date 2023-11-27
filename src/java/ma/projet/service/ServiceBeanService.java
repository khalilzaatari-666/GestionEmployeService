package ma.projet.service;

import java.util.List;
import ma.projet.beans.ServiceBean;  // Update the import to use the correct class
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

public class ServiceBeanService implements IDao<ServiceBean> {  // Update the class type to use the correct class
    @Override
    public boolean create(ServiceBean o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(ServiceBean o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(ServiceBean o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public ServiceBean getById(int id) {
        ServiceBean service = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        service = (ServiceBean) session.get(ServiceBean.class, id);
        session.getTransaction().commit();
        return service;
    }

    @Override
    public List<ServiceBean> getAll() {
        List<ServiceBean> services = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        services = session.createQuery("from ServiceBean").list();
        session.getTransaction().commit();
        return services;
    }
}
