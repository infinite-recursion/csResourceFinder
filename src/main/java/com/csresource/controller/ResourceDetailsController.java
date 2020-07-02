package com.csresource.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csresource.AppConstants;
import com.csresource.Util;
import com.csresource.jpa.Acitivity;
import com.csresource.jpa.Resource;
import com.csresource.jpa.ResourceQuestion;
import com.csresource.jpa.ResourceReply;
import com.csresource.jpa.ResourceReview;
import com.csresource.jpa.ResourceTag;
import com.csresource.jpa.Tag;
import com.csresource.jpa.User;
import com.csresource.repositories.ActivityRepository;
import com.csresource.repositories.ResourceQuestionRepository;
import com.csresource.repositories.ResourceReplyRepository;
import com.csresource.repositories.ResourceRepository;
import com.csresource.repositories.ResourceReviewRepository;
import com.csresource.repositories.ResourceTagRepository;
import com.csresource.repositories.TagRepository;
import com.csresource.repositories.UserRepository;
import com.resource.json.AcceptReplyJson;
import com.resource.json.ReplySubmissionJson;
import com.resource.json.ResourceJson;
import com.resource.json.ResourceQuestionJson;
import com.resource.json.ResourceQuestionSubmissionJson;
import com.resource.json.ResourceReplyJson;
import com.resource.json.ResourceReviewJson;
import com.resource.json.ResourceTagJson;
import com.resource.json.ReviewSubmissionJson;

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
	UserRepository userRepo;

	@Autowired
	ResourceRepository resourceRepo;

	@Autowired
	TagRepository tagRepo;

	@Autowired
	ResourceTagRepository resourceTagRepo;

	@Autowired
	ActivityRepository actRepo;

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
						review.getDate(), review.getLikes(), review.getRating(), review.getUser().getUsername());

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

	@PostMapping("/submitReview")
	public String submitReview(@RequestBody ReviewSubmissionJson reviewSubmit) {

		// Obtain the resource
		Resource resource = resourceRepo.findById(reviewSubmit.getResource()).get();
		User user = userRepo.findById(reviewSubmit.getUsername()).get();

		// Create the ResourceReview
		ResourceReview resourceReview = new ResourceReview();
		resourceReview.setComment(reviewSubmit.getComment());
		resourceReview.setDate(new Date());
		resourceReview.setLikes(0);
		resourceReview.setRating(reviewSubmit.getRating());
		resourceReview.setResource(resource);
		resourceReview.setUser(user);
		resourceReview.setId(Util.convertCurrentDateIntoString());
		resourceReviewRepo.save(resourceReview);

		// Handle the tag if there is one
		if (reviewSubmit.getTag() != null) {
			Tag tag = null;

			// convert tag to lowercase
			reviewSubmit.setTag(reviewSubmit.getTag().toLowerCase());

			// See if tag already exists. If not, create a new tag
			var tagExists = tagRepo.findById(reviewSubmit.getTag());

			if (tagExists.isPresent()) {

				tag = tagExists.get();
			}
			// Create a new tag
			else {
				tag = new Tag();
				tag.setName(reviewSubmit.getTag());
				tag = tagRepo.save(tag);
			}

			// Now see if there is already an instance of the tag for
			// this resource
			ResourceTag resourceTag = resourceTagRepo.findByTagAndResource(tag, resource);

			if (resourceTag == null) {

				resourceTag = new ResourceTag();
				resourceTag.setCount(1);
				resourceTag.setResource(resource);
				resourceTag.setTag(tag);
				resourceTag.setId(Util.convertCurrentDateIntoString());
				resourceTag = resourceTagRepo.save(resourceTag);
			}
			// Increment the count
			else {
				resourceTag.setCount(resourceTag.getCount() + 1);
				resourceTagRepo.save(resourceTag);
			}
		}

		// Now need to update the rating for the resource
		Float currRating = resource.getRating();
		Float numRatings = (float) (resource.getNumRatings());
		Float newTotatNumRatings = (float) (numRatings + 1);
		Float newRating = (currRating * (numRatings / newTotatNumRatings))
				+ (reviewSubmit.getRating() * (1 / newTotatNumRatings));

		resource.setNumRatings(resource.getNumRatings() + 1);
		resource.setRating(newRating);
		resourceRepo.save(resource);

		// Now create the activity
		Acitivity activity = new Acitivity();
		activity.setId(Util.convertCurrentDateIntoString());

		String type = null;
		if (reviewSubmit.getComment() != null) {
			type = AppConstants.REVIEW;
		} else {
			type = AppConstants.RATING;
		}
		activity.setType(type);
		activity.setTypeID(resourceReview.getId());
		activity.setDate(resourceReview.getDate());
		actRepo.save(activity);
		
		return resourceReview.getId();
	}

	@PostMapping("/submitQuestion")
	public String submitQuestion(@RequestBody ResourceQuestionSubmissionJson questionJson) {

		User user = userRepo.findById(questionJson.getUsername()).get();
		Resource resource = resourceRepo.findById(questionJson.getResource()).get();

		ResourceQuestion resourceQuestion = new ResourceQuestion();
		resourceQuestion.setId(Util.convertCurrentDateIntoString());
		resourceQuestion.setComment(questionJson.getComment());
		resourceQuestion.setDate(new Date());
		resourceQuestion.setUser(user);
		resourceQuestion.setResource(resource);
		resourceQuestion.setLikes(0);
		resourceQuestion = resourceQuestionRepo.save(resourceQuestion);

		// Now create the activity
		Acitivity activity = new Acitivity();
		activity.setId(Util.convertCurrentDateIntoString());
		activity.setType(AppConstants.QUESTION);
		activity.setTypeID(resourceQuestion.getId());
		activity.setDate(resourceQuestion.getDate());
		actRepo.save(activity);
		
		return resourceQuestion.getId();
	}
	
	@PostMapping("/submitReply")
	public String submitReply(@RequestBody ReplySubmissionJson replyJson) {

		User user = userRepo.findById(replyJson.getUsername()).get();
		Resource resource = resourceRepo.findById(replyJson.getResource()).get();

		ResourceQuestion resourceQuestion = resourceQuestionRepo.findById(replyJson.getQuestionId()).get();
		
		ResourceReply resourceReply = new ResourceReply();
		
		resourceReply.setId(Util.convertCurrentDateIntoString());
		resourceReply.setAccepted(false);
		resourceReply.setComment(replyJson.getComment());
		resourceReply.setDate(new Date());
		resourceReply.setLikes(0);
		resourceReply.setResource(resource);
		resourceReply.setUser(user);
		resourceReply.setResourceQuestion(resourceQuestion);
		resourceReply = resourceReplyRepo.save(resourceReply);

		// Now create the activity
		Acitivity activity = new Acitivity();
		activity.setId(Util.convertCurrentDateIntoString());
		activity.setType(AppConstants.REPLY);
		activity.setTypeID(resourceReply.getId());
		activity.setDate(resourceReply.getDate());
		actRepo.save(activity);
		
		return resourceReply.getId();
	}
	
	@PostMapping("/acceptReply")
	public void acceptReply(@RequestBody AcceptReplyJson acceptJson) {

		ResourceReply resourceReply = resourceReplyRepo.findById(acceptJson.getReplyId()).get();
		resourceReply.setAccepted(acceptJson.isAccepted());

		resourceReply = resourceReplyRepo.save(resourceReply);

	}

}
