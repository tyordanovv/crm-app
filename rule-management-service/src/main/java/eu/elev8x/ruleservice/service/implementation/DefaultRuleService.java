package eu.elev8x.ruleservice.service.implementation;

import eu.elev8x.ruleservice.persistance.rule.RuleEntity;
import eu.elev8x.ruleservice.persistance.rule.RuleRepository;
import eu.elev8x.ruleservice.service.RuleService;
import eu.elex8x.apicore.core.rule.RuleCreateRequest;
import eu.elex8x.apicore.core.rule.RuleType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DefaultRuleService implements RuleService {
    private final RuleRepository repository;

    @Override
    public RuleEntity createRule(RuleCreateRequest request) {
        RuleEntity rule = new RuleEntity();
        rule.setName(request.name());
        rule.setDescription(request.description());
        rule.setUpdatedAt(LocalDateTime.now());

        return repository.save(rule);
    }

    @Override
    public void deleteConditionByRuleID(Long ruleId) {
        repository.deleteAllByRuleId(ruleId);
    }

    @Override
    public List<RuleEntity> fetchUserRulesByType(String userId, RuleType ruleType) {
        return repository.findByUserIdAndType(userId, ruleType);
    }
}
