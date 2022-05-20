package com.alpha.api_for_test_automation.Repository;

import com.alpha.api_for_test_automation.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
