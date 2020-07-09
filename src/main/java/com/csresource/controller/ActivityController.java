package com.csresource.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
	public List<List<ActivityJson>> getActivity() {

		List<List<ActivityJson>> allActivitiesJson = new LinkedList<List<ActivityJson>>();

		List<ActivityJson> activitiesJson = new LinkedList<ActivityJson>();

		// Retrieve the first 20 elements
		Pageable sortedByDate = PageRequest.of(0, 20, Sort.by("date").descending());

		Page<Acitivity> activities = actRepo.findAll(sortedByDate);

		int numEntries = 1;
		for (Acitivity activity : activities) {

			// ActivityJson actJson = new
			// ActivityJson(activity.getType(),activity.getDate())

			String type = activity.getType();
			String typeID = activity.getTypeID();
			String resourceName = null;
			String username = null;

			ActivityJson actJson = new ActivityJson(activity.getType(), activity.getDate());

			if (type.equals(AppConstants.REVIEW) || type.equals(AppConstants.RATING)) {

				Optional<ResourceReview> resourceReviewVal = resourceReviewRepo.findById(typeID);

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

				Optional<ResourceQuestion> resourceQuesVal = resourceQuestionRepo.findById(typeID);

				if (resourceQuesVal.isPresent()) {

					ResourceQuestion resourceQues = resourceQuesVal.get();
					username = resourceQues.getUser().getUsername();
					resourceName = resourceQues.getResource().getName();
					actJson.setQuestion(resourceQues.getComment());

				}

			} else if (type.equals(AppConstants.REPLY)) {

				Optional<ResourceReply> resourceReplyVal = resourceReplyRepo.findById(typeID);

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

			// 4 entries allowed in each list. A list represents a row displayed on the GUI
			if (numEntries % 4 == 0) {
				allActivitiesJson.add(activitiesJson);

				activitiesJson = new LinkedList<ActivityJson>();
			}

			numEntries++;

		}

		if (!activitiesJson.isEmpty()) {
			allActivitiesJson.add(activitiesJson);

		}

		return allActivitiesJson;
	}

}
