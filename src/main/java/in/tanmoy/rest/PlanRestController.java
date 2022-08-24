package in.tanmoy.rest;

import java.util.List;
import java.util.Map;

import org.hibernate.annotations.ValueGenerationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.tanmoy.constants.AppConstants;
import in.tanmoy.entity.Plan;
import in.tanmoy.props.AppProperties;
import in.tanmoy.service.PlanService;

@RestController
public class PlanRestController {

	private PlanService planService;

	private Map<String, String> messages;

	public PlanRestController(PlanService planService, AppProperties appProps) {
		this.planService = planService;
		this.messages = appProps.getMessages();
		System.out.println(this.messages);
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {

		Map<Integer, String> planCategories = planService.getPlanCategories();

		return new ResponseEntity<>(planCategories, HttpStatus.OK);

	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String responseMsg = AppConstants.EMPTY_STR;
		boolean savePlan = planService.savePlan(plan);
		if (savePlan) {
			responseMsg = messages.get(AppConstants.PLAN_SAVE_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_SAVE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);

	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans() {
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan plan = planService.getPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);

	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		String responseMsg = AppConstants.EMPTY_STR;
		boolean isUpdated = planService.updatePlan(plan);
		if (isUpdated) {
			responseMsg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);

	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		String responseMsg = AppConstants.EMPTY_STR;
		boolean plan = planService.deletePlanById(planId);
		if (plan) {
			responseMsg = messages.get(AppConstants.PLAN_DELETE_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);

	}

	@PutMapping("/plan/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {
		String responseMsg = AppConstants.EMPTY_STR;
		boolean isChanged = planService.planStatusChange(planId, status);
		if (isChanged) {
			responseMsg = messages.get(AppConstants.PLAN_STATUS_CHANGE);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);

	}
}
