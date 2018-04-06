package at.refugeescode.mp12_social_network_fakebook.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Optional<Person> findById(Long Id);

}
