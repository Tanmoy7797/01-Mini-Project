package in.tanmoy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.tanmoy.entity.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer> {

}
