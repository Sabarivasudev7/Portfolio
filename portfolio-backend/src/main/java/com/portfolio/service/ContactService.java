package com.portfolio.service;

import org.springframework.stereotype.Service;
import com.portfolio.entity.Contact;
import com.portfolio.repository.ContactRepository;

@Service
public class ContactService {
    private final ContactRepository repository;
    public ContactService(ContactRepository repository) { this.repository = repository; }
    public Contact saveContact(Contact contact) { return repository.save(contact); }
}