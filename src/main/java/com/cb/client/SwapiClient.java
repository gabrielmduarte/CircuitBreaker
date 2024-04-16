package com.cb.client;

import com.cb.domain.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SwapiClient", url = "https://swapi.dev/api/")
public interface SwapiClient {

    @GetMapping("people/{id}")
    Person getPerson(@PathVariable String id);

}
