package eu.elev8x.contactservice;

import eu.elev8x.contactservice.controller.ContactController;
import eu.elev8x.contactservice.repository.ContactRepository;
import eu.elev8x.contactservice.repository.PhoneNumberEntity;
import eu.elev8x.contactservice.service.implementation.ContactServiceImplementation;
import eu.elex8x.apicore.core.contact.ContactDTO;
import eu.elex8x.apicore.core.contact.PhoneAreaCode;
import eu.elex8x.apicore.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

public class ContactServiceTest {

    private ContactServiceImplementation contactServiceImplementation;

    @Mock
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contactServiceImplementation = new ContactServiceImplementation(contactRepository);
    }

    @Test
    public void testUploadCSVFile_Success() throws IOException {
        // Mock
        String csvContent = "John,Doe,john.doe@example.com,+359456789\n" +
                "Jane,Smith,jane.smith@example.com,+359654321\n";
        MultipartFile multipartFile = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

        when(contactRepository.saveList(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        // Test
        List<ContactDTO> result = contactServiceImplementation.uploadCSVFile(multipartFile);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("john.doe@example.com", result.get(0).email());
        assertEquals("John", result.get(0).firstName());
        assertEquals("Doe", result.get(0).lastName());
        assertEquals(PhoneAreaCode.BULGARIA, result.get(0).number().code());
        assertEquals(456789, result.get(0).number().number());
    }

    @Test
    public void testUploadCSVFile_EmptyFile() {
        // Mock
        MultipartFile emptyFile = new MockMultipartFile("file", "empty.csv", "text/csv", new byte[0]);

        // Test + Assert
        assertThrows(InvalidInputException.class, () -> contactServiceImplementation.uploadCSVFile(emptyFile));
    }
}