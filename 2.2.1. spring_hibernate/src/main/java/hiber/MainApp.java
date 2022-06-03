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
      User user1 = new User("User1", "Lastname1", "userxxx@mail.ru", 132, "sf"),
              user2 = new User("User2", "Lastname2", "user2@mail.ru", 112, "sf"),
              user3 = new User("User3", "Lastname3", "user3@mail.ru", 114, "sf"),
              user4 = new User("User4", "Lastname4", "user4@mail.ru", 2244, "abc"),
              userWithoutCar = new User("User5", "Lastname5", "user5@mail.ru");
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);
      userService.add(userWithoutCar);

      List<User> users = userService.listUsers();
      Car car;
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+ user.getFirstName());
         System.out.println("Last Name = "+ user.getLastName());
         System.out.println("Email = "+ user.getEmail());
         if ((car = user.getCarID()) != null) {
            System.out.println("Car = " + user.getCarID().getModel() + " : " + user.getCarID().getSeries());
         }
         System.out.println();
      }
      User userByCar = userService.getUserByCar("sf", 112);
      System.out.println(userByCar.getFirstName() + " владеет машиной " + userByCar.getCarID().getModel()
              + " с серийным номером " + userByCar.getCarID().getSeries());
      context.close();
   }
}
