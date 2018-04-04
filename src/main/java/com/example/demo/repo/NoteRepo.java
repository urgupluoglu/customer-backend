package com.example.demo.repo;

import com.example.demo.entity.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by yusuf on 4/2/2018.
 */
public interface NoteRepo extends CrudRepository<Note, Long> {

    Note getById(Long id);
    List<Note> getAllByCustomer_IdOrderById(Long customerId);
}
