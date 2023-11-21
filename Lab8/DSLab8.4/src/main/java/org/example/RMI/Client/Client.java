package org.example.RMI.Client;

import org.example.Core.Models.Group;
import org.example.Core.Models.Student;
import org.example.Core.Repositories.Repository;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            Repository repository = (Repository) registry.lookup("repository");

            System.out.println("Groups: " + repository.countGroups());
            System.out.println("Students: " + repository.countStudents());

            Group group = new Group();
            group.setName("Calculus");
            group.setYear(20);

            //repository.insertGroup(group);

            Student student = new Student();
            student.setName("Andrew");
            //repository.insertStudent(2, student);

            //repository.deleteGroup(1);
            //repository.deleteStudent(10);

            System.out.println("Group: " + repository.getGroup(0));
            System.out.println("Student: " + repository.getStudent(13));

            System.out.println("Groups: " + repository.getGroups());
            System.out.println("Students: " + repository.getStudents());
        } catch (NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
