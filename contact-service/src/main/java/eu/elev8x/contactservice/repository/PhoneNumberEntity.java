package eu.elev8x.contactservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import eu.elex8x.apicore.core.contact.PhoneAreaCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class PhoneNumberEntity {
    @DynamoDBAttribute
    private String code;

    @DynamoDBAttribute
    private Long number;

    public static PhoneNumberEntity validateAndFormatPhoneNumber(String number) {
        for (PhoneAreaCode code : PhoneAreaCode.values()) {
            if (number.startsWith(code.getValue())) {
                String codeValue = code.getValue();
                String phoneNumber = number.substring(codeValue.length());
                return new PhoneNumberEntity(codeValue, Long.parseLong(phoneNumber));
            }
        }

        throw new IllegalArgumentException("No matching area code found for the provided number: " + number);
    }
}
