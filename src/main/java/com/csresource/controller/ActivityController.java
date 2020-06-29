package com.csresource.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csresource.AppConstants;
import com.csresource.jpa.Acitivity;
import com.csresource.jpa.ResourceQuestion;
import com.csresource.jpa.ResourceReply;
import com.csresource.jpa.ResourceReview;
import com.csresource.repositories.ActivityRepository;
import com.csresource.repositories.ResourceQuestionRepository;
import com.csresource.repositories.ResourceReplyRepository;
import com.csresource.repositories.ResourceReviewRepository;
import com.resource.json.ActivityJson;

@RestController
public class ActivityController {

	@Autowired
	ActivityRepository actRepo;

	@Autowired
	ResourceReviewRepository resourceReviewRepo;

	@Autowired
	ResourceQuestionRepository resourceQuestionRepo;

	@Autowired
	ResourceReplyRepository resourceReplyRepo;

	@GetMapping("/getActivity")
	public List<ActivityJson> getActivity() {

		List<ActivityJson> activitiesJson = new LinkedList<ActivityJson>();

		// Retrieve the first 15 elements
		Pageable sortedByDate = PageRequest.of(0, 15, Sort.by("date").descending());

		Page<Acitivity> activities = actRepo.findAll(sortedByDate);

		for (Acitivity activity : activities) {

			// ActivityJson actJson = new
			// ActivityJson(activity.getType(),activity.getDate())

			String type = activity.getType();
			String typeID = activity.getTypeID();
			String resourceName = null;
			String username = null;

			ActivityJson actJson = new ActivityJson(activity.getType(), activity.getDate());

			if (type.equals(AppConstants.REVIEW) || type.equals(AppConstants.RATING)) {

				var resourceReviewVal = resourceReviewRepo.findById(typeID);

				if (resourceReviewVal.isPresent()) {

					ResourceReview resourceReview = resourceReviewVal.get();
					username = resourceReview.getUser().getUsername();

					if (type.equals("review")) {
						actJson.setReview(resourceReview.getComment());
					}
					actJson.setRating(resourceReview.getRating());

					resourceName = resourceReview.getResource().getName();
				}

			} else if (type.equals(AppConstants.QUESTION)) {

				var resourceQuesVal = resourceQuestionRepo.findById(typeID);

				if (resourceQuesVal.isPresent()) {

					ResourceQuestion resourceQues = resourceQuesVal.get();
					username = resourceQues.getUser().getUsername();
					resourceName = resourceQues.getResource().getName();
					actJson.setQuestion(resourceQues.getComment());

				}

			} else if (type.equals(AppConstants.REPLY)) {

				var resourceReplyVal = resourceReplyRepo.findById(typeID);

				if (resourceReplyVal.isPresent()) {

					ResourceReply resourceReply = resourceReplyVal.get();
					username = resourceReply.getUser().getUsername();
					resourceName = resourceReply.getResource().getName();

					actJson.setQuestionReply(resourceReply.getComment());

					// Get the associated question

					ResourceQuestion resourceQues = resourceReply.getResourceQuestion();
					actJson.setQuestion(resourceQues.getComment());

				}

			}

			actJson.setUsername(username);
			actJson.setResourceName(resourceName);

			activitiesJson.add(actJson);

		}

		return activitiesJson;
	}

}
