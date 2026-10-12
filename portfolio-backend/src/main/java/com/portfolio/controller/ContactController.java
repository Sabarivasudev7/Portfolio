package com.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.portfolio.entity.Contact;
import com.portfolio.service.ContactService;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactService service;
    public ContactController(ContactService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Contact> submitContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(service.saveContact(contact));
    }
}