package com.example.library.service;

import com.example.library.exceptions.ResourceNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.Patron;
import com.example.library.repository.PatronRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronServiceImp implements PatronService{

    @Autowired
    PatronRepository patronRepository;
    @Override
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Override
    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));
    }

    @Override
    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Override
    public Patron updatePatron(Long id, Patron patronDetails) {

        Patron patron = getPatronById(id);
        if (patron !=null){
            BeanUtils.copyProperties(patronDetails , patron , "id");
            return patronRepository.save(patron);
        }
        throw  new ResourceNotFoundException("There is no Patron to update with this id  :" + id);
    }

    @Override
    public void deletePatron(Long id) {
        Patron patron = getPatronById(id);
        if (patron != null) {
            patronRepository.deleteById(id);
        }
        throw  new ResourceNotFoundException("There is no Patron to delete with this id :" + id);

    }
}
