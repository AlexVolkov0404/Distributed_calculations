package com.example.dslab941;

import com.example.dslab941.Core.Models.Group;
import com.example.dslab941.Core.Models.Student;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class GroupsListXMLWriter implements MessageBodyWriter<List<Group>> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return List.class.isAssignableFrom(type) && genericType.getTypeName().contains("Group");
    }

    @Override
    public void writeTo(List<Group> groups, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream out) throws IOException, WebApplicationException {
        PrintWriter writer = new PrintWriter(out);

        try {
            Document doc = createDocument(groups);

            Source source = new DOMSource(doc);
            Result result = new StreamResult(writer);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(source, result);
        } catch (TransformerException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        writer.flush();
        writer.close();
    }

    Document createDocument(List<Group> groups) throws ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        Element root = doc.createElement(Group.GROUPS);
        for (Group item: groups) {
            Element group = doc.createElement(Group.GROUP);

            group.setAttribute(Group.ID, String.valueOf(item.getId()));
            group.setAttribute(Group.NAME, item.getName());
            group.setAttribute(Group.YEAR, String.valueOf(item.getYear()));

            for (Student subitem: item.getStudents()) {
                Element student = doc.createElement(Student.STUDENT);
                student.setAttribute(Student.ID, String.valueOf(subitem.getId()));
                student.setAttribute(Student.NAME, subitem.getName());
                student.setAttribute(Student.AGE, String.valueOf(subitem.getAge()));
                group.appendChild(student);
            }

            root.appendChild(group);
        }

        doc.appendChild(root);

        return doc;
    }
}
