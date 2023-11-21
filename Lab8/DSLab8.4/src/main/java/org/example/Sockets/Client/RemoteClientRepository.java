package org.example.Sockets.Client;

import org.example.Core.Models.Group;
import org.example.Core.Models.Student;
import org.example.Core.Repositories.Repository;
import org.example.Utils.Serialization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RemoteClientRepository implements Repository {
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    public RemoteClientRepository(String host, int port) throws IOException {
        socket = new Socket(host, port);

        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public int countGroups() {
        try {
            out.writeInt(0);
            return in.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countStudents() {
        try {
            out.writeInt(1);
            return in.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateGroup(Group group) {
        try {
            out.writeInt(2);
            String bytes = Serialization.toString(group);
            out.writeInt(bytes.length());
            out.writeBytes(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStudent(Student student) {
        try {
            out.writeInt(3);
            String bytes = Serialization.toString(student);
            out.writeInt(bytes.length());
            out.writeBytes(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void moveStudentToGroup(int studentId, int groupId) {
        try {
            out.writeInt(4);
            out.writeInt(studentId);
            out.writeInt(groupId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertGroup(Group group) {
        try {
            out.writeInt(5);
            String bytes = Serialization.toString(group);
            out.writeInt(bytes.length());
            out.writeBytes(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertStudent(int groupId, Student student) {
        try {
            out.writeInt(6);
            out.writeInt(groupId);
            String bytes = Serialization.toString(student);
            out.writeInt(bytes.length());
            out.writeBytes(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteGroup(int id) {
        try {
            out.writeInt(7);
            out.writeInt(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudent(int id) {
        try {
            out.writeInt(8);
            out.writeInt(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Group getGroup(int id) {
        try {
            out.writeInt(9);
            out.writeInt(id);

            int size = in.readInt();
            byte[] bytes = new byte[size];
            in.readFully(bytes);

            return (Group) Serialization.fromBytes(bytes);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student getStudent(int id) {
        try {
            out.writeInt(10);
            out.writeInt(id);

            int size = in.readInt();
            byte[] bytes = new byte[size];
            in.readFully(bytes);

            return (Student) Serialization.fromBytes(bytes);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Group> getGroups() {
        try {
            out.writeInt(11);

            int count = in.readInt();
            List<Group> groups = new ArrayList<>(count);

            for (int i = 0; i < count; ++i) {
                int size = in.readInt();
                byte[] bytes = new byte[size];
                in.readFully(bytes);
                groups.add((Group) Serialization.fromBytes(bytes));
            }

            return groups;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getStudents() {
        try {
            out.writeInt(12);

            int count = in.readInt();
            List<Student> students = new ArrayList<>(count);

            for (int i = 0; i < count; ++i) {
                int size = in.readInt();
                byte[] bytes = new byte[size];
                in.readFully(bytes);
                students.add((Student) Serialization.fromBytes(bytes));
            }

            return students;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
