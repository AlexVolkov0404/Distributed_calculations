package org.example.Core.Repositories;

import org.example.Core.Models.Student;
import org.example.Core.Models.Group;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Repository extends Remote {
    int countGroups() throws RemoteException;
    int countStudents() throws RemoteException;

    void updateGroup(Group group) throws RemoteException;
    void updateStudent(Student student) throws RemoteException;

    void moveStudentToGroup(int studentId, int groupId) throws RemoteException;

    void insertGroup(Group group) throws RemoteException;
    void insertStudent(int groupId, Student student) throws RemoteException;

    void deleteGroup(int id) throws RemoteException;
    void deleteStudent(int id) throws RemoteException;

    Group getGroup(int id) throws RemoteException;
    Student getStudent(int id) throws RemoteException;

    List<Group> getGroups() throws RemoteException;
    List<Student> getStudents() throws RemoteException;
}
