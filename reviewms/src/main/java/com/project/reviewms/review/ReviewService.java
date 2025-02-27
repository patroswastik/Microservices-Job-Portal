package com.project.reviewms.review;

import java.util.List;

public interface ReviewService {
    public List<Review> getAllReviews(Long companyId);
    public Review getReviewById(Long reviewId);
    public boolean addReview(Long companyId, Review review);
    public boolean updateReview(Long reviewId, Review review);
    public boolean deleteReview(Long reviewId);
}
