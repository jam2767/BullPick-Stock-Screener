package com.bullpick.stock.screener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    //custom query to get unique email from each user
    @Query("SELECT u from User u WHERE u.email = ?1")
    User findByEmail(String email);

}
