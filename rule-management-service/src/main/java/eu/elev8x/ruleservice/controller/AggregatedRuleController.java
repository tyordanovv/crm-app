package eu.elev8x.ruleservice.controller;

import eu.elev8x.ruleservice.service.AggregatedRuleService;
import eu.elex8x.apicore.composite.AggregatedRuleCreateRequest;
import eu.elex8x.apicore.composite.AggregatedRuleDTO;
import eu.elex8x.apicore.composite.AggregatedRuleUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(name = "/api/v1/aggregated-rule")
@AllArgsConstructor
public class AggregatedRuleController {
    private final AggregatedRuleService aggregatedService;

    @PostMapping(
            consumes = "application/json")
    @ResponseStatus(
            HttpStatus.ACCEPTED)
    public ResponseEntity<AggregatedRuleDTO> createAggregatedRule(
            @RequestBody AggregatedRuleCreateRequest request
    ){
        return ResponseEntity.ok(aggregatedService.createAggregatedRule(request));
    }

    @PutMapping(
            consumes = "application/json")
    @ResponseStatus(
            HttpStatus.ACCEPTED)
    public void updateAggregatedRule(
            @RequestBody AggregatedRuleUpdateRequest request
    ){
        aggregatedService.updateAggregatedRule(request);
    }

    @DeleteMapping(
            value = "/{ruleId}")
    @ResponseStatus(
            HttpStatus.ACCEPTED)
    public void deleteAggregatedRule(
            @PathVariable Long ruleId
    ){
        aggregatedService.deleteAggregatedRule(ruleId);
    }

    @GetMapping(
            value = "/{userId}",
            produces = "application/json")
    public ResponseEntity<List<AggregatedRuleDTO>> getScheduledAggregatedRules(
            @PathVariable String userId
    ){
        return ResponseEntity.ok(
                aggregatedService.getScheduledAggregatedRules(userId)
        );
    }
}
