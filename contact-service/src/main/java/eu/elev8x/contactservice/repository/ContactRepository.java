package eu.elev8x.contactservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository
{
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public ContactEntity save(ContactEntity contact)
    {
        dynamoDBMapper.save(contact);
        return contact;
    }

    public List<ContactEntity> saveList(List<ContactEntity> contacts) {
        dynamoDBMapper.batchSave(contacts);
        return contacts;
    }

    public ContactEntity findById(String id)
    {
        return dynamoDBMapper.load(ContactEntity.class, id);
    }

    public void deleteById(String id)
    {
        ContactEntity entity = dynamoDBMapper.load(ContactEntity.class, id);
        dynamoDBMapper.delete(entity);
    }

    public ContactEntity update(String id, ContactEntity contact)
    {
        dynamoDBMapper.save(contact,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("contactId", new ExpectedAttributeValue(
                                new AttributeValue().withS(id)
                        )));
        return contact;
    }
}