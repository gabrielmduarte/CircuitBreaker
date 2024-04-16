package com.cb.controller;

import com.cb.domain.Person;
import com.cb.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("person/")
@AllArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping("{id}/{fail}")
    public Person getPerson(@PathVariable String id, @PathVariable Boolean fail) {
        return service.getPerson(id, fail);
    }

}
