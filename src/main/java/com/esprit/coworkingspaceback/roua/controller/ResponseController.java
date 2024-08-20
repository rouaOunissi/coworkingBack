package com.esprit.coworkingspaceback.roua.controller;

import com.esprit.coworkingspaceback.roua.entities.Response;
import com.esprit.coworkingspaceback.roua.services.IResponseService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@RestController
@AllArgsConstructor
@RequestMapping("/Response")
@CrossOrigin(origins = "http://localhost:4200")
public class ResponseController {
    IResponseService iResponseService;

}
