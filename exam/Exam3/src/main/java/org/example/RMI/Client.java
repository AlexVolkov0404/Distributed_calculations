package org.example.RMI;

import org.example.Core.Contact;
import org.example.Core.Repository;
import org.example.Core.RepositoryInterface;
import org.example.Socket.ClientRepository;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws NotBoundException, RemoteException {
        Registry registry = LocateRegistry.getRegistry(1099);
        RepositoryInterface repository = (RepositoryInterface) registry.lookup("repository");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("(0) to exit");
            System.out.println("(1) to create contact");
            System.out.println("(2) to get contact");
            System.out.println("(3) to update contact");
            System.out.println("(4) to delete contact");
            System.out.println("(5) to find all contacts by name");
            System.out.println("(6) to find all contacts by phone");
            int operation = scanner.nextInt();
            switch (operation) {
                case 0: return;
                case 1: {
                    Contact contact = new Contact();
                    System.out.println("Type name:");
                    contact.setName(scanner.nextLine());
                    contact.setName(scanner.nextLine());
                    System.out.println("Type phone:");
                    contact.setPhone(scanner.nextLine());
                    System.out.println("Type skype:");
                    contact.setSkype(scanner.nextLine());
                    System.out.println("Type email:");
                    contact.setEmail(scanner.nextLine());
                    System.out.println("Type fbProfile:");
                    contact.setFbProfile(scanner.nextLine());

                    repository.create(contact);
                    break;
                }
                case 2: {
                    System.out.println("Type id:");
                    int id = scanner.nextInt();
                    System.out.println(repository.get(id));
                    break;
                }
                case 3: {
                    Contact contact = new Contact();
                    System.out.println("Type id:");
                    contact.setId(scanner.nextInt());
                    System.out.println("Type name:");
                    contact.setName(scanner.nextLine());
                    contact.setName(scanner.nextLine());
                    System.out.println("Type phone:");
                    contact.setPhone(scanner.nextLine());
                    System.out.println("Type skype:");
                    contact.setSkype(scanner.nextLine());
                    System.out.println("Type email:");
                    contact.setEmail(scanner.nextLine());
                    System.out.println("Type fbProfile:");
                    contact.setFbProfile(scanner.nextLine());

                    repository.update(contact);
                    break;
                }
                case 4: {
                    System.out.println("Type id:");
                    int id = scanner.nextInt();
                    repository.delete(id);
                    break;
                }
                case 5: {
                    System.out.println("Type name:");
                    String name = scanner.nextLine();
                    name = scanner.nextLine();
                    System.out.println(repository.findAllByName(name));
                    break;
                }
                case 6: {
                    System.out.println("Type phone:");
                    String phone = scanner.nextLine();
                    phone = scanner.nextLine();
                    System.out.println(repository.findAllByPhone(phone));
                    break;
                }
            }
        }
    }
}
