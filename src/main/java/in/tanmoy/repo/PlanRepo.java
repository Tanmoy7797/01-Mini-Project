package in.tanmoy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.tanmoy.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
