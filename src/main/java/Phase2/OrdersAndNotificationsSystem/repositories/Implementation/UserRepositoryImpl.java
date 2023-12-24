//package Phase2.OrdersAndNotificationsSystem.repositories.Implementation;
//
//import Phase2.OrdersAndNotificationsSystem.models.User;
//import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;
//import Phase2.OrdersAndNotificationsSystem.repositories.UsersRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//
//@Repository
//
//public class UserRepositoryImpl implements UsersRepository {
//
//    ArrayList<User> users = new ArrayList<>();
//
//    UserRepositoryImpl() {
//        users.add(new User("ranaessam", "123456", "ranaessam03@gmail.com", "01145303111", "Mokattam", 1000));
//    }
//
//
//    @Override
//    public ArrayList<User> getAllUsers() {
//        return null;
//    }
//
//    @Override
//    public User getUser(Credentials credentials)  {
//        for (User user : users) {
//            if (user.getUsername().equals(credentials.getUsername()) && user.getPassword().equals(credentials.getPassword())) {
//                return user;
//            }
//        }
//        System.out.println("Invalid Credentials");
//       return null;
//    }
//
//    @Override
//    public void addUser(User user) {
//
//    }
//
//    @Override
//    public void updateUser(User user) {
//
//    }
//    @Override
//    public void deleteUser(String username) {
//
//    }
//}
