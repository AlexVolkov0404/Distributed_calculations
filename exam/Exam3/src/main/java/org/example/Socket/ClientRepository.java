package org.example.Socket;

import org.example.Core.Contact;
import org.example.Core.RepositoryInterface;
import org.example.Utils.Serialization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements RepositoryInterface {
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    ClientRepository(String host, int port) throws IOException {
        socket = new Socket(host, port);

        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void create(Contact contact) {
        try {
            out.writeInt(1);

            String bytes = Serialization.toString(contact);
            out.writeInt(bytes.length());
            out.writeBytes(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contact get(int id) {
        try {
            out.writeInt(2);
            out.writeInt(id);

            int length = in.readInt();
            byte[] bytes = new byte[length];
            in.readFully(bytes);

            return (Contact) Serialization.fromBytes(bytes);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Contact contact) {
        try {
            out.writeInt(3);

            String bytes = Serialization.toString(contact);
            out.writeInt(bytes.length());
            out.writeBytes(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            out.writeInt(4);
            out.writeInt(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contact> findAllByName(String name) {
        try {
            out.writeInt(5);

            String outBytes = Serialization.toString(name);
            out.writeInt(outBytes.length());
            out.writeBytes(outBytes);

            List<Contact> contacts = new ArrayList<>();
            int count = in.readInt();
            for (int i = 0; i < count; ++i) {
                int length = in.readInt();
                byte[] bytes = new byte[length];
                in.readFully(bytes);

                contacts.add((Contact) Serialization.fromBytes(bytes));
            }

            return contacts;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contact> findAllByPhone(String phone) {
        try {
            out.writeInt(6);

            String outBytes = Serialization.toString(phone);
            out.writeInt(outBytes.length());
            out.writeBytes(outBytes);

            List<Contact> contacts = new ArrayList<>();
            int count = in.readInt();
            for (int i = 0; i < count; ++i) {
                int length = in.readInt();
                byte[] bytes = new byte[length];
                in.readFully(bytes);

                contacts.add((Contact) Serialization.fromBytes(bytes));
            }

            return contacts;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
