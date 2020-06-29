package com.csresource.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csresource.jpa.Resource;
import com.csresource.jpa.ResourceQuestion;
import com.csresource.jpa.ResourceReply;
import com.csresource.jpa.ResourceReview;
import com.csresource.jpa.ResourceTag;
import com.csresource.repositories.ResourceQuestionRepository;
import com.csresource.repositories.ResourceReplyRepository;
import com.csresource.repositories.ResourceRepository;
import com.csresource.repositories.ResourceReviewRepository;
import com.resource.json.ResourceJson;
import com.resource.json.ResourceQuestionJson;
import com.resource.json.ResourceReplyJson;
import com.resource.json.ResourceReviewJson;
import com.resource.json.ResourceTagJson;

@RequestMapping("/resourceDetails")
@RestController
public class ResourceDetailsController {

	@Autowired
	ResourceReviewRepository resourceReviewRepo;

	@Autowired
	ResourceQuestionRepository resourceQuestionRepo;

	@Autowired
	ResourceReplyRepository resourceReplyRepo;

	@Autowired
	ResourceRepository resourceRepo;

	@GetMapping("/getDetails")
	public ResourceJson getResourceDetails(@RequestBody String resourceName) {

		ResourceJson resourceJson = null;

		var resourceVal = resourceRepo.findById(resourceName);

		if (resourceVal.isPresent()) {

			Resource resource = resourceVal.get();

			resourceJson = new ResourceJson(resourceName, resource.getDescription(), resource.getNumRatings(),
					resource.getRating(), resource.getUrl());

			// get the questions and replies
			for (ResourceQuestion question : resource.getResourceQuestions()) {

				ResourceQuestionJson questionJson = new ResourceQuestionJson(question.getId(), question.getComment(),
						question.getDate(), question.getLikes(), question.getUser().getUsername());

				for (ResourceReply reply : question.getResourceReplies()) {

					ResourceReplyJson replyJson = new ResourceReplyJson(reply.getId(), reply.getAccepted(),
							reply.getComment(), reply.getDate(), reply.getUser().getUsername(), reply.getLikes());
					questionJson.getReplies().add(replyJson);

				}

				resourceJson.getQuestions().add(questionJson);
			}

			// Get the reviews
			for (ResourceReview review : resource.getResourceReviews()) {

				ResourceReviewJson reviewJson = new ResourceReviewJson(review.getId(), review.getComment(),
						review.getDate(), review.getLikes(), review.getRating());

				resourceJson.getReviews().add(reviewJson);

			}

			// Get the tags
			for (ResourceTag tag : resource.getResourceTags()) {

				ResourceTagJson tagJson = new ResourceTagJson(tag.getId(), tag.getCount(), tag.getTag().getName());
				resourceJson.getTags().add(tagJson);
			}

		}

		return resourceJson;

	}

}
