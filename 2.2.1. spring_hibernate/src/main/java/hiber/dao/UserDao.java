package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();

   User findCarUser(String model, int series);

   void addCar(Car car);

   void updateUser(User user);
}
