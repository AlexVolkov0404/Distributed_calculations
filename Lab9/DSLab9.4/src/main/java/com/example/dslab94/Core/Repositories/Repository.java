package com.example.dslab94.Core.Repositories;

import com.example.dslab94.Core.Models.Student;
import com.example.dslab94.Core.Models.Group;
import java.util.List;

public interface Repository{
    int countGroups();
    int countStudents();

    void updateGroup(Group group);
    void updateStudent(Student student);

    void moveStudentToGroup(int studentId, int groupId);

    void insertGroup(Group group);
    void insertStudent(int groupId, Student student);

    void deleteGroup(int id);
    void deleteStudent(int id);

    Group getGroup(int id);
    Student getStudent(int id);

    List<Group> getGroups();
    List<Student> getStudents();
}
