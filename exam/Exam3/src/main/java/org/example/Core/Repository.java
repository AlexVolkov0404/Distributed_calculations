package org.example.Core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Repository extends UnicastRemoteObject implements RepositoryInterface {
    private final List<Contact> list = new ArrayList<>();
    private int maxId = 0;

    public Repository() throws RemoteException {

    }

    @Override
    public synchronized void create(Contact contact) throws RemoteException {
        contact.setId(maxId);
        maxId++;

        list.add(contact);
    }

    @Override
    public synchronized Contact get(int id) throws RemoteException {
        Contact result = null;
        for (Contact contact: list) {
            result = contact;
        }
        return result;
    }

    @Override
    public synchronized void update(Contact contact) throws RemoteException {
        list.removeIf(x -> x.getId() == contact.getId());
        list.add(contact);
    }

    @Override
    public synchronized void delete(int id) throws RemoteException {
        list.removeIf(contact -> contact.getId() == id);
    }

    @Override
    public synchronized List<Contact> findAllByName(String name) throws RemoteException {
        return list.stream().filter(contact -> contact.getName().contains(name)).collect(Collectors.toList());
    }

    @Override
    public synchronized List<Contact> findAllByPhone(String phone) throws RemoteException {
        return list.stream().filter(contact -> contact.getName().equals(phone)).collect(Collectors.toList());
    }
}
