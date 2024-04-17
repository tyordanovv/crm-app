package eu.elev8x.ruleservice.persistance.condition;

import eu.elev8x.ruleservice.persistance.condition.ConditionEntity;
import eu.elex8x.apicore.core.condition.ConditionDTO;
import eu.elex8x.apicore.validator.Validator;

public class ConditionValidator implements Validator<ConditionEntity, ConditionDTO> {
    @Override
    public boolean validateEntity(ConditionEntity entity) {
        return false;
    }

    @Override
    public boolean validateDataTransferObject(ConditionDTO object) {
        return false;
    }
}
