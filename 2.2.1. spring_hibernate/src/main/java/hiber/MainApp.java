package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Model S", 123);
        Car car2 = new Car("Mustang", 456);
        Car car3 = new Car("Lamborghini", 789);
        Car car4 = new Car("Galant Mitsubishi", 999);

        userService.addCar(car1);
        userService.addCar(car2);
        userService.addCar(car3);
        userService.addCar(car4);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        user1.setCar(car1);
        userService.updateUser(user1);

        user2.setCar(car2);
        userService.updateUser(user2);

        user3.setCar(car3);
        userService.updateUser(user3);

        user4.setCar(car4);
        userService.updateUser(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                System.out.println("Car Model = " + user.getCar().getModel());
                System.out.println("Car Series = " + user.getCar().getSeries());
            }
            System.out.println();
        }

        User foundUser = userService.findCarUser("Model S", 123);
        if (foundUser != null) {
            System.out.println("Найден пользователь с авто: " + foundUser.getFirstName()
                    + " " + foundUser.getLastName() + " авто: " + "(" + foundUser.getCar().getModel() + ")" + " "
                    + foundUser.getCar().getSeries());
        } else {
            System.out.println("Такой пользователь не найден");
        }
        context.close();
    }
}
