package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.msg.learning.model.Revenue;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Integer> {

}
