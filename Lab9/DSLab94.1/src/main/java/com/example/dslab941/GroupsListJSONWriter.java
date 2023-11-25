package com.example.dslab941;

import com.example.dslab941.Core.Models.Group;
import com.example.dslab941.Core.Models.Student;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Provider
public class GroupsListJSONWriter implements MessageBodyWriter<List<Group>> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return List.class.isAssignableFrom(type) && genericType.getTypeName().contains("Group");
    }

    @Override
    public void writeTo(List<Group> groups, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream out) throws IOException, WebApplicationException {
        PrintWriter writer = new PrintWriter(out);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        for (Group item: groups) {
            stringBuilder.append("{");
            stringBuilder.append("\"" + Group.ID + "\":").append(item.getId()).append(",");
            stringBuilder.append("\"" + Group.NAME + "\":\"").append(item.getName()).append("\",");
            stringBuilder.append("\"" + Group.YEAR + "\":").append(item.getYear()).append(",");

            stringBuilder.append("\"Students\": [");
            for (Student subitem: item.getStudents()) {
                stringBuilder.append("{");
                stringBuilder.append("\"" + Student.ID + "\":").append(subitem.getId()).append(",");
                stringBuilder.append("\"" + Student.NAME + "\":\"").append(subitem.getName()).append("\",");
                stringBuilder.append("\"" + Student.AGE + "\":").append(subitem.getAge());
                stringBuilder.append("},");
            }

            if (item.hasStudents()) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }

            stringBuilder.append("]},");
        }

        if (!groups.isEmpty()) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        stringBuilder.append("]");

        writer.print(stringBuilder);

        writer.flush();
        writer.close();
    }
}
