package com.learn.security;

import com.learn.domain.Person;
import com.learn.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<Person> {

    @Autowired
    private PersonRepository repository;

    public PersonRepository getRepository() { return repository; }

    public void setRepository(PersonRepository repository) { this.repository = repository; }

    @Override
    public Person getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            // return system user if authentication isn't present*/
            return repository.getEntityManager().getReference(Person.class, 1);
        }
        return repository.getEntityManager().getReference(Person.class, Integer.parseInt(authentication.getName()));
    }
}
