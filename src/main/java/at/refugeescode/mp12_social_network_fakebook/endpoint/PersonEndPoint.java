package at.refugeescode.mp12_social_network_fakebook.endpoint;

import at.refugeescode.mp12_social_network_fakebook.persistence.Person;
import at.refugeescode.mp12_social_network_fakebook.persistence.PersonRepository;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class PersonEndPoint {

    private PersonRepository personRepository;

    public PersonEndPoint(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    List<Person> showFirends() {

        return personRepository.findAll();
    }

    @PostMapping("/persons")
    void addPerson(@RequestBody Person person){
        personRepository.save(person);
    }

    @GetMapping("/persons/{id1}/friend/{id2}")
    void addFriend(@PathVariable Long id1 ,@PathVariable Long id2){

        Optional<Person> person1 = personRepository.findById(id1);
        Optional<Person> friend = personRepository.findById(id2);
        if (!person1.isPresent() || !friend.isPresent()){

        }
        person1.get().getFriends().add(friend.get());
        personRepository.save(person1.get());
    }

    @GetMapping("/persons/{id1}/unfriend/{id2}")
    void deleteFriend(@PathVariable Long id1 , @PathVariable Long id2){
        Optional<Person> person1 = personRepository.findById(id1);
        Optional<Person> friend = personRepository.findById(id2);
        if (!person1.isPresent() || !friend.isPresent()){

        }

        personRepository.getOne(id1).getFriends().remove(friend.get());
        personRepository.save(person1.get());
    }


}
