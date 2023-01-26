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
        return new ResponseEntity<>("Hola desde el CONTROLADOR", HttpStatus.OK);
    }
}
