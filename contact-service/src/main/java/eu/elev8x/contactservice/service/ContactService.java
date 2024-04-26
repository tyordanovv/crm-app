package eu.elev8x.contactservice.service;

import eu.elex8x.apicore.core.contact.ContactDTO;
import eu.elex8x.apicore.core.contact.ContactRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContactService
{
    ContactDTO createContact(ContactRequest request);

    void updateContact(ContactDTO request);

    void deleteContact(String contactId);

    List<ContactDTO> getUserContacts(String userId);

    List<ContactDTO> uploadCSVFile(MultipartFile file);
}
