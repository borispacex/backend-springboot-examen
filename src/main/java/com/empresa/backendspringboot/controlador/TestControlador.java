package com.empresa.backendspringboot.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestControlador {
    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> function() {
        String content = "<body>"
                        + "<h1><span>Esto es una REST API en </span> SpringBoot</h1>"
                        + "<p>Para realizar pruebas a la API: <a href=\"http://localhost:8081/swagger-ui/index.html\">Documentacion SWAGGER UI</a></p>"
                        + "</body>";
        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}
