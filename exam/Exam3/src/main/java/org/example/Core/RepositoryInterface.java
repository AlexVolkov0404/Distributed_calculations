package org.example.Core;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RepositoryInterface extends Remote {
    void create(Contact contact) throws RemoteException;
    Contact get(int id) throws RemoteException;
    void update(Contact contact) throws RemoteException;
    void delete(int id) throws RemoteException;
    List<Contact> findAllByName(String name) throws RemoteException;
    List<Contact> findAllByPhone(String phone) throws RemoteException;
}
