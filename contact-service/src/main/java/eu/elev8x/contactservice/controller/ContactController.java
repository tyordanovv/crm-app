package eu.elev8x.contactservice.controller;

import eu.elex8x.apicore.core.contact.ContactDTO;
import eu.elex8x.apicore.core.contact.ContactRequest;
import eu.elev8x.contactservice.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/contacts")
@AllArgsConstructor
public class ContactController
{
    private final ContactService contactService;

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ContactDTO createContact(
            @RequestBody ContactRequest request
    ) {
        System.out.println("in");
        return contactService.createContact(request);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateContact(
            @RequestBody ContactDTO request
    ) {
        contactService.updateContact(request);
    }

    @DeleteMapping(value = "/{contactId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteContact(
            @PathVariable String contactId
    ) {
        contactService.deleteContact(contactId);
    }

    @GetMapping(
            produces = "application/json",
            value = "/{userId}")
    public ResponseEntity<List<ContactDTO>> getUserContacts(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok(contactService.getUserContacts(userId));
    }

    @PostMapping(
            value = "/upload",
            consumes = "multipart/form-data",
            produces = "application/json")
    public ResponseEntity<List<ContactDTO>> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(contactService.uploadCSVFile(file));
    }

    @GetMapping(
            produces = "application/json")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("ok");
    }
}
