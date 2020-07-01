package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl uSImpl = new UserServiceImpl();
        uSImpl.createUsersTable();
        System.out.println(uSImpl.getAllUsers().size());
        uSImpl.saveUser("Ivan", "Ivanov", (byte) 15);
        System.out.println(uSImpl.getAllUsers().size());
        uSImpl.saveUser("Petr", "Petrov", (byte) 20);
        uSImpl.saveUser("Vasily", "Vasechkin", (byte) 25);
        uSImpl.saveUser("Feodor", "Feodorov", (byte) 30);
        System.out.println(uSImpl.getAllUsers().size());
        uSImpl.createUsersTable();
        uSImpl.dropUsersTable();
    }
}
