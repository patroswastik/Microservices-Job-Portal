package com.project.reviewms.review.impl;

import com.project.reviewms.review.Review;
import com.project.reviewms.review.ReviewRepository;
import com.project.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(reviewId != null && updatedReview != null) {
            review.setRating(updatedReview.getRating());
            review.setDescription(updatedReview.getDescription());
            review.setTitle(updatedReview.getTitle());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        try{
            Review review = reviewRepository.findById(reviewId).orElse(null);
            if(review != null) {
                reviewRepository.deleteById(reviewId);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
