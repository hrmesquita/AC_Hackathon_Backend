package org.greytcoders.rent_a_bs.repositories;

import org.greytcoders.rent_a_bs.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
