package eu.elev8x.contactservice.service.implementation;

import eu.elev8x.contactservice.repository.ContactEntity;
import eu.elev8x.contactservice.repository.ContactRepository;
import eu.elev8x.contactservice.repository.PhoneNumberEntity;
import eu.elex8x.apicore.core.contact.ContactDTO;
import eu.elex8x.apicore.core.contact.ContactRequest;
import eu.elev8x.contactservice.service.ContactService;
import eu.elex8x.apicore.exception.BadRequestException;
import eu.elex8x.apicore.exception.InvalidInputException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContactServiceImplementation implements ContactService
{
    private ContactRepository repository;

    @Override
    public ContactDTO createContact(ContactRequest request)
    {
        return repository
                .save(ContactEntity.builder()
                        .email(request.email())
                        .number(new PhoneNumberEntity(request.number().code().getValue(), request.number().number()))
//                        .dynamicAttributes(request.attributes())
                        .build())
                .entityToDTO();
    }

    @Override
    public void updateContact(ContactDTO request)
    {
        ContactEntity entity = repository.findById(request.id());

        entity.setNumber(new PhoneNumberEntity(request.number().code().getValue(), request.number().number()));
        entity.setEmail(request.email());
//        entity.setDynamicAttributes(request.attributes());
        repository.update(entity.getContactId(), entity);
    }

    @Override
    public void deleteContact(String contactId)
    {
        repository.deleteById(contactId);
    }

    @Override
    public List<ContactDTO> getUserContacts(String userId)
    {
        return null;
    }

    @Override
    public List<ContactDTO> uploadCSVFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new InvalidInputException("Received file is empty.");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<ContactEntity> contacts = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                String firstName = fields[0];
                String lastName = fields[1];
                String email = fields[2];
                String number = fields[3];

                ContactEntity contactEntity = ContactEntity.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .number(PhoneNumberEntity.validateAndFormatPhoneNumber(number))
                        .build();
                contacts.add(contactEntity);

            }

            return repository.saveList(contacts).stream()
                    .map(ContactEntity::entityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new BadRequestException("Failed to process the CSV file.");
        }
    }
}
