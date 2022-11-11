package com.maria.pastelhub.book_landing.viewmodel;

import androidx.lifecycle.ViewModel;

import com.maria.pastelhub.book_landing.model.BookLandingUser;

public class BookLandingViewModel extends ViewModel {

    public String id;
    public String name;
    public String rating;
    public String date_time;
    public String review;
    public String first_letter_review;

    public BookLandingViewModel() {
    }

    public BookLandingViewModel(BookLandingUser bookLandingUser) {
        this.id = bookLandingUser.id;
        this.name = bookLandingUser.name;
        this.rating = bookLandingUser.rating;
        this.date_time = bookLandingUser.date_time;
        this.review = bookLandingUser.review;
        this.first_letter_review = bookLandingUser.first_letter_review;
    }

    public Float getRatingFloat() {
        return Float.parseFloat(rating);
    }
}
