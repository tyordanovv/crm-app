package eu.elev8x.ruleservice.persistance.rule;

import eu.elex8x.apicore.core.rule.RuleDTO;
import eu.elex8x.apicore.validator.Validator;

public class RoleValidator implements Validator<RuleEntity, RuleDTO> {
    @Override
    public boolean validateEntity(RuleEntity entity) {
        return false;
    }

    @Override
    public boolean validateDataTransferObject(RuleDTO object) {
        return false;
    }
}
