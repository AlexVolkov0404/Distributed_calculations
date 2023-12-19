package org.example.Socket;

import org.example.Core.Contact;
import org.example.Core.Repository;
import org.example.Utils.Serialization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try {
            Repository repository = new Repository();

            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server listening on port 8080...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());

                // Create a new thread to handle the client
                Thread clientHandler = new Thread(new ClientHandler(clientSocket, repository));
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final Repository repository;

        public ClientHandler(Socket clientSocket, Repository repository) {
            this.clientSocket = clientSocket;
            this.repository = repository;
        }

        @Override
        public void run() {
            try {
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                boolean isAlive = true;
                while (isAlive && clientSocket.isConnected()) {
                    int operation = in.read();
                    switch (operation) {
                        case 0: isAlive = false; break;
                        case 1: {
                            int length = in.readInt();
                            byte[] bytes = new byte[length];
                            in.read(bytes);

                            Contact contact = (Contact) Serialization.fromBytes(bytes);
                            repository.create(contact);

                            break;
                        }
                        case 2: {
                            int id = in.readInt();

                            Contact contact = repository.get(id);

                            String bytes = Serialization.toString(contact);
                            out.writeInt(bytes.length());
                            out.writeBytes(bytes);

                            break;
                        }
                        case 3: {
                            int length = in.readInt();
                            byte[] bytes = new byte[length];
                            in.read(bytes);

                            Contact contact = (Contact) Serialization.fromBytes(bytes);
                            repository.update(contact);

                            break;
                        }
                        case 4: {
                            int id = in.readInt();
                            repository.delete(id);

                            break;
                        }
                        case 5: {
                            int length = in.readInt();
                            byte[] inBytes = new byte[length];
                            String name = (String) Serialization.fromBytes(inBytes);

                            List<Contact> contacts = repository.findAllByName(name);

                            out.writeInt(contacts.size());
                            for (Contact contact: contacts) {
                                String bytes = Serialization.toString(contact);
                                out.writeInt(bytes.length());
                                out.writeBytes(bytes);
                            }
                        }
                        case 6: {
                            int length = in.readInt();
                            byte[] inBytes = new byte[length];
                            String phone = (String) Serialization.fromBytes(inBytes);

                            List<Contact> contacts = repository.findAllByPhone(phone);

                            out.writeInt(contacts.size());
                            for (Contact contact: contacts) {
                                String bytes = Serialization.toString(contact);
                                out.writeInt(bytes.length());
                                out.writeBytes(bytes);
                            }
                        }
                    }
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}