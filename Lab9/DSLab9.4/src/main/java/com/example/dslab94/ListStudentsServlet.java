package com.example.dslab94;

import com.example.dslab94.Core.Models.Group;
import com.example.dslab94.Core.Models.Student;
import com.example.dslab94.Core.Repositories.DBRepository;
import com.example.dslab94.Core.Repositories.Repository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListAlbumsServlet", value = "/")
public class ListStudentsServlet extends HttpServlet {
    Repository repository;

    @Override
    public void init() {
        repository = new DBRepository("jdbc:postgresql://localhost:5432/dev", "postgres", "alex2704");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        List<Group> groups = repository.getGroups();

        PrintWriter out = response.getWriter();
        out.println("<html><style>table, td, th { border: 1px solid #333; } thead, tfoot { background-color: #333; color: #fff; } </style>");
        out.println("<body><table>");
        out.println("<tr><th>Id</th><th>Name</th><th>Age</th><th>Group Name</th></tr>");

        for (Group group: groups) {
            printGroups(out, group);
        }

        out.println("</table></body></html>");
    }

    void printGroups(PrintWriter out, Group group) {
        for (Student student: group.getStudents()) {
            out.print("<tr>");
            out.print("<td>" + student.getId() + "</td>");
            out.print("<td>" + student.getName() + "</td>");
            out.print("<td>" + student.getAge() + "</td>");
            out.print("<td>" + group.getName() + "</td>");
            out.print("</tr>");
        }
    }

    public void destroy() {
    }
}