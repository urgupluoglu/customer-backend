package com.example.demo.rest;

import com.example.demo.entity.Customer;
import com.example.demo.serializable.CustomerObject;
import com.example.demo.serializable.NoteObject;
import com.example.demo.service.NoteService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yusuf on 4/2/2018.
 *
 * All rest api mappings associated with Note are implemented here
 * This is one way to handle api links
 * This suits well when you have a few entry points for a service
 * (it is great when GET, POST, PUT, DELETE with a single url)
 *
 */
@RestController
public class NoteRest {

    private Logger LOGGER = LogManager.getLogger(NoteRest.class);

    @Autowired
    NoteService noteService;

    @RequestMapping(value = "note", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addNewNote(@RequestBody NoteObject note) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mapper.writeValueAsString(noteService.addNote(note)));
        } catch (Exception e) {
            LOGGER.error(e);
            // It is not best practice to show error message to end user for security reasons!
            // Use custom error messages instead.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @RequestMapping(value = "note", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateNote(@RequestBody NoteObject note) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mapper.writeValueAsString(noteService.updateNote(note)));
        } catch (Exception e) {
            LOGGER.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @RequestMapping(value = "note", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteNote(@RequestBody NoteObject note) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mapper.writeValueAsString(noteService.deleteNote(note)));
        } catch (Exception e) {
            LOGGER.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @RequestMapping(value = "noteList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getNotesByCustomer(@RequestBody CustomerObject customerObject) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mapper.writeValueAsString(noteService.getNoteListByCustomer(customerObject)));
        } catch (Exception e) {
            LOGGER.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

}
