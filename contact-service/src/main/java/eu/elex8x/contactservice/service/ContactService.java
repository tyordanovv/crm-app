package eu.elex8x.contactservice.service;

import eu.elex8x.apicore.core.contact.ContactDTO;
import eu.elex8x.apicore.core.contact.ContactRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContactService {
    void createContact(ContactRequest request);

    void updateContact(ContactRequest request);

    void deleteContact(Long contactId);

    List<ContactDTO> getUserContacts();
}
