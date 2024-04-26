package eu.elev8x.ruleservice.service;

import eu.elex8x.apicore.composite.AggregatedRuleCreateRequest;
import eu.elex8x.apicore.composite.AggregatedRuleDTO;
import eu.elex8x.apicore.composite.AggregatedRuleUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface AggregatedRuleService {
    /**
     *
     * @param request
     * @return
     */
    AggregatedRuleDTO createAggregatedRule(AggregatedRuleCreateRequest request);

    /**
     *
     * @param request
     */
    void updateAggregatedRule(AggregatedRuleUpdateRequest request);

    /**
     *
     * @param ruleId
     */
    @Transactional
    void deleteAggregatedRule(Long ruleId);

    /**
     *
     * @param userId id of user
     * @return List of the scheduled user rules
     */
    List<AggregatedRuleDTO> getScheduledAggregatedRules(String userId);

    /**
     *
     * @return List of all the scheduled rules
     */
    List<AggregatedRuleDTO> getAllScheduledAggregatedRules();
}
