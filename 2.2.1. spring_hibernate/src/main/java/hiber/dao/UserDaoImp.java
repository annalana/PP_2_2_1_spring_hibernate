package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      Transaction transaction = null;
      User user = null;
      try (Session session = sessionFactory.openSession();) {
         transaction = session.beginTransaction();
         Query query = session.createQuery("from Car where series=:seriesNum and model=:modelName");
         query.setParameter("modelName", model);
         query.setParameter("seriesNum", series);
         Car car = (Car)(query.getResultList().get(0));
         user = car.getOwner();
         transaction.commit();
      } catch (HibernateException e) {
         if (transaction != null) {
            transaction.rollback();
         }
         e.printStackTrace();
      }
      return user;
   }
}
