package eu.elev8x.contactservice.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import eu.elex8x.apicore.core.contact.PhoneAreaCode;
import eu.elex8x.apicore.core.contact.PhoneNumber;

public class PhoneNumberEntityConverter implements DynamoDBTypeConverter<String, PhoneNumberEntity> {
    @Override
    public String convert(PhoneNumberEntity object) {
        // Convert PhoneNumber to String representation for DynamoDB
        return object.getCode() + object.getNumber();
    }

    @Override
    public PhoneNumberEntity unconvert(String object) {
        String code = object.substring(0, 4);
        String number = object.substring(4);
        return new PhoneNumberEntity(code, Long.parseLong(number));
    }
}