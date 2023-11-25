package com.example.dslab941;

import com.example.dslab941.Core.Models.Group;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/json")
public class JsonEndpoint {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Group> getJsonGroups() {
        return Application.getInstance().getRepository().getGroups();
    }
}