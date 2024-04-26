package eu.elev8x.contactservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import eu.elex8x.apicore.core.contact.ContactDTO;
import eu.elex8x.apicore.core.contact.PhoneAreaCode;
import eu.elex8x.apicore.core.contact.PhoneNumber;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Map;

@DynamoDBTable(tableName = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ContactEntity
{
    @DynamoDBHashKey(attributeName = "contactId")
    @DynamoDBAutoGeneratedKey
    private String contactId;
    @DynamoDBAttribute
    @NotNull
    private String firstName;
    @DynamoDBAttribute
    @NotNull
    private String lastName;
    @DynamoDBAttribute
    private String email;
    @DynamoDBAttribute
    @NotNull
    private PhoneNumberEntity number;
//    @DynamoDBAttribute
//    private Map<String, Object> dynamicAttributes;

    public ContactDTO entityToDTO(){
        return new ContactDTO(
                this.contactId,
                this.firstName,
                this.lastName,
                this.email,
                new PhoneNumber(
                        PhoneAreaCode.fromString(this.number.getCode()),
                        this.number.getNumber()
                )
//                ,
//                this.dynamicAttributes
        );
    }
}