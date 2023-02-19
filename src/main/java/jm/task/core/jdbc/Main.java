package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService us = new UserServiceImpl();
        us.createUsersTable();
        User user1 = new User("Первый", "Первов", (byte) 11);
        User user2 = new User("Второй", "Вторников", (byte) 22);
        User user3 = new User("Третий", "Третьяков", (byte) 33);
        User user4 = new User("Четветрый", "Четвертов", (byte) 44);
        us.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        us.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        us.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        us.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        us.getAllUsers().forEach(System.out::println);
        us.cleanUsersTable();
        us.dropUsersTable();


    }
}
