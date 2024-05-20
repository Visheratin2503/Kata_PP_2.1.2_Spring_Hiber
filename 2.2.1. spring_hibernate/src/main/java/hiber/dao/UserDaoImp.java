package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @Transactional(readOnly = true)
   public User findCarUser(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      Query<User> query = session.createQuery("SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.uniqueResult();
   }

   @Override
   @Transactional
   public void addCar(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public void updateUser(User user) {
      sessionFactory.getCurrentSession().update(user);
   }
}
