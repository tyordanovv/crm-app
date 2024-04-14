package eu.elex8x.contactservice.controller;

import eu.elex8x.contactservice.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "api/v1/clients")
@AllArgsConstructor
public class ContactController {
    private final ContactService contactService;

    public ResponseEntity<?> getUserContacts(){


        return ResponseEntity.ok("ok");
    }
}
