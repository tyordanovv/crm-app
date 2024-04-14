package eu.elex8x.notificationservice.controller;

import eu.elex8x.notificationservice.service.implementation.DefaultConditionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/api/v1/condition/")
@AllArgsConstructor
public class ConditionController {
    private final DefaultConditionService conditionService;


}
