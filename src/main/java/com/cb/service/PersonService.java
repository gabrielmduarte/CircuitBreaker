package com.cb.service;

import com.cb.client.SwapiClient;
import com.cb.domain.Person;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {

    private final SwapiClient swapiClient;

    private final CacheManager cacheManager;

    @CircuitBreaker(name = "personCB", fallbackMethod = "getFromCache")
    public Person getPerson(String id, Boolean fail) {
            if (fail) {
                System.out.println("erro no client");
                throw new RuntimeException("Erro");
            }
            var person = swapiClient.getPerson(id);
            saveToCache(id, person);
            return person;
    }

    public Person getFromCache(String id, Boolean fail, Exception ex) {
        System.out.println("executando fallback -> " + ex.getLocalizedMessage());
        var personFromCache = cacheManager.getCache("PersonCache").get(id, Person.class);
        if (personFromCache == null) {
            System.out.println("Erro ao buscar do cache, retornando padrao");
            return new Person("Test", "x", "y");
        }
        return personFromCache;
    }

    public void saveToCache(String id, Person value) {
        cacheManager.getCache("PersonCache").put(id, value);
    }

}
