package eu.elex8x.contactservice.controller;

import eu.elex8x.apicore.core.contact.ContactRequest;
import eu.elex8x.contactservice.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "api/v1/clients")
@AllArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createContact(ContactRequest request){

    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateContact(ContactRequest request){

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteContact(Long contactId){

    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getUserContacts(){
        return null;
    }


}
