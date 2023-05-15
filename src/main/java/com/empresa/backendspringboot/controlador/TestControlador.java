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
        String urlSwagger = "http://localhost:8081/swagger-ui/index.html";
        // String urlSwagger = "https://ms-examen.azurewebsites.net/swagger-ui/index.html";
        String content = "<body>"
                        + "<h2><span>Esto es una REST API en </span> SpringBoot</h2>"
                        + "<p>Para realizar pruebas a la API: <a href=\"" + urlSwagger + "\">Documentacion SWAGGER UI</a></p>"
                        + "</body>";
        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}
