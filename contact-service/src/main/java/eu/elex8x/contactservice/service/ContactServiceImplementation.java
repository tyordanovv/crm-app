package eu.elex8x.contactservice.service;

import eu.elex8x.apicore.core.contact.ContactDTO;
import eu.elex8x.apicore.core.contact.ContactRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImplementation implements ContactService{

    @Override
    public void createContact(ContactRequest request) {

    }

    @Override
    public void updateContact(ContactRequest request) {

    }

    @Override
    public void deleteContact(Long contactId) {

    }

    @Override
    public List<ContactDTO> getUserContacts() {
        return null;
    }
}
