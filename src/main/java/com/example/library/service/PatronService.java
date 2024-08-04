package com.example.library.service;

import com.example.library.model.Patron;

import java.util.List;

public interface PatronService {

    public List<Patron> getAllPatrons();
    public Patron getPatronById(Long id);

    public Patron addPatron(Patron patron);

    public Patron updatePatron(Long id, Patron patronDetails);

    public void deletePatron(Long id);
}
