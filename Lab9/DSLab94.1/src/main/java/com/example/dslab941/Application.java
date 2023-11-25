package com.example.dslab941;

import com.example.dslab941.Core.Repositories.DBRepository;
import com.example.dslab941.Core.Repositories.Repository;
import jakarta.ws.rs.ApplicationPath;
import lombok.Getter;

@ApplicationPath("/")
public class Application extends jakarta.ws.rs.core.Application {
    @Getter
    private static Application instance;

    @Getter
    private final Repository repository;

    public Application() {
        repository = new DBRepository("jdbc:postgresql://localhost:5432/dev", "postgres", "alex2704");
        instance = this;
    }

}