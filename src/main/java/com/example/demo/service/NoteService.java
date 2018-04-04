package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Note;
import com.example.demo.repo.NoteRepo;
import com.example.demo.serializable.CustomerObject;
import com.example.demo.serializable.NoteObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yusuf on 4/2/2018.
 *
 * Services can either return serializable objects
 * or entity so rest classes do the casting.
 *
 * This is an example of service always returns serializable objects.
 * It is a good practice if you don't intent to use these services other than rest classes
 *
 */
@Component
public class NoteService {

    @Autowired
    NoteRepo noteRepo;

    public NoteObject addNote(NoteObject noteObject) throws Exception {
        // cleaner to use Apache Commons StringUtils
        if (noteObject == null || noteObject.getText() == null ||
                noteObject.getText().length() == 0 || noteObject.getCustomerId() == 0) {
            // best practice here is to define custom exceptions
            throw new Exception("Custom Error Message");
        }
        // make sure id is accidentally not set!
        noteObject.setId(0);
        Customer customer = new Customer();
        customer.setId(noteObject.getCustomerId());
        Note note = new Note();
        note.setText(noteObject.getText());
        note.setCustomer(customer);

        return castNoteToNoteObject(noteRepo.save(note));
    }

    public NoteObject updateNote(NoteObject noteObject) throws Exception {

        if (noteObject == null || noteObject.getText() == null || noteObject.getId() == 0 ||
                noteObject.getText().length() == 0) {
            throw new Exception("Custom Error Message");
        }
        Note note = noteRepo.getById(noteObject.getId());
        if (note == null)
            throw new Exception("Another Custom Error Message");
        note.setText(noteObject.getText());
        return castNoteToNoteObject(noteRepo.save(note));
    }

    public boolean deleteNote(NoteObject noteObject) throws Exception {
        if (noteObject == null || noteObject.getId() == 0) {
            throw new Exception("Custom Error Message");
        }
        try {
            // no need to check if there is a note by this id as long as we handle it in the catch block below
            noteRepo.deleteById(noteObject.getId());
        } catch (Exception e) {
            throw new Exception("Invalid note by this id: " + noteObject.getId());
        }
        return true;
    }

    public List<NoteObject> getNoteListByCustomer(CustomerObject customerObject) throws Exception {
        if (customerObject == null || customerObject.getId() == 0) {
            throw new Exception("Custom Error Message");
        }
        return castNoteListToNoteObjectList(noteRepo.getAllByCustomer_IdOrderById(customerObject.getId()));
    }

    private NoteObject castNoteToNoteObject(Note note) {
        return new NoteObject(note.getId(), note.getText(), note.getCustomer().getId());
    }

    private List<NoteObject> castNoteListToNoteObjectList(List<Note> noteList) {
        List<NoteObject> noteObjectList = new ArrayList<>();
        noteList.forEach(note -> noteObjectList.add(castNoteToNoteObject(note)));
        return noteObjectList;
    }
}
