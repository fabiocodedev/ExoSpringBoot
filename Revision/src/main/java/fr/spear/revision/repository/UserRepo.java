package fr.spear.revision.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spear.revision.Bean.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	User findByEmail (String email);
}
