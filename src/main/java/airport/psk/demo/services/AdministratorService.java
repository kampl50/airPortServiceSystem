package airport.psk.demo.services;


import airport.psk.demo.models.people.Employer;
import airport.psk.demo.repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {

    @Autowired
    EmployerRepository repository;

    public void save(Employer employer) {
        repository.save(employer);
    }

    public void deleteEmployer(String id) {
        repository.deleteById(id);
    }

    public List<Employer> getAllEmployers() {
        return repository.findAll();
    }

}
