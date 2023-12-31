package org.example.Sockets.Server;

import org.example.Core.Repositories.DBRepository;
import org.example.Core.Repositories.Repository;

import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) throws RemoteException {
        Repository innerRepository = new DBRepository("jdbc:postgresql://localhost:5432/dev", "postgres", "alex2704");
        RemoteServerRepository repository = new RemoteServerRepository(innerRepository, 8008);
        repository.start();
    }
}
