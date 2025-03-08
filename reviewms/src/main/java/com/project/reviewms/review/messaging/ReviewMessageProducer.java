package com.project.reviewms.review.messaging;

import com.project.reviewms.review.Review;
import com.project.reviewms.review.dto.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Review review) {
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(review.getId());
        reviewMessage.setTitle(review.getTitle());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setCompanyId(review.getCompanyId());
        reviewMessage.setRating(review.getRating());
        rabbitTemplate.convertAndSend("CompanyRatingQueue", reviewMessage);
    }
}
