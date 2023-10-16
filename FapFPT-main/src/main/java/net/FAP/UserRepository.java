package net.FAP;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query(value = "SELECT * FROM Accounts u WHERE u.username = ?1", nativeQuery = true)
	public User getUserByUsername(String username);
}
