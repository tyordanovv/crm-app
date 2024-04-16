package eu.elex8x.messageservice.controller;

import eu.elex8x.apicore.composite.AggregateMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/api/v1/message")
public class MessageController {

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> createMessage(@RequestBody AggregateMessage body){
        return null;
    }
}
