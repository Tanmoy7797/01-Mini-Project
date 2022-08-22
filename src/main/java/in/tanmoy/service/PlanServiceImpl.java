package in.tanmoy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tanmoy.entity.Plan;
import in.tanmoy.entity.PlanCategory;
import in.tanmoy.repo.PlanCategoryRepo;
import in.tanmoy.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private PlanCategoryRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> categories = planCategoryRepo.findAll();
		Map<Integer, String> categoryMap = new HashMap<>();

		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan saved = planRepo.save(plan);

		return saved.getPlanId() != null;
	}

	@Override
	public List<Plan> getAllPlans() {

		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		// Optional is a container where objects may available or may not be available
		Optional<Plan> planObject = planRepo.findById(planId);
		if (planObject.isPresent()) {
			return planObject.get();
		}

		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		Plan save = planRepo.save(plan);// upsert

		return save.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String activeSw) {
		Optional<Plan> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSw(activeSw);
			planRepo.save(plan);
			return true;

		}

		return false;
	}

}
