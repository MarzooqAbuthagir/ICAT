package com.maria.pastelhub.book_landing.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maria.pastelhub.book_landing.interfaces.ClickListener;
import com.maria.pastelhub.book_landing.viewmodel.BookLandingViewModel;
import com.maria.pastelhub.databinding.ReviewBinding;
import com.maria.pastelhub.log_files.LogFile;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewView> {

    List<BookLandingViewModel> bookLandingUserList;
    private LayoutInflater layoutInflater;

    public ReviewAdapter(List<BookLandingViewModel> bookLandingUserList) {
        this.bookLandingUserList = bookLandingUserList;
    }

    @NonNull
    @Override
    public ReviewView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ReviewBinding reviewBinding = ReviewBinding.inflate(layoutInflater, parent, false);
        reviewBinding.setPresenter(new ClickListener() {
            @Override
            public void onclickListener() {

                Log.d("click me ","click me "+reviewBinding.getReviewViewModel().id);
                Toast.makeText(parent.getContext(),""+reviewBinding.getReviewViewModel().id,Toast.LENGTH_LONG).show();

            }
        });
        return new ReviewView(reviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewView holder, int position) {
        BookLandingViewModel bookLandingViewModel = bookLandingUserList.get(position);
        holder.bind(bookLandingViewModel);
    }

    @Override
    public int getItemCount() {
        return bookLandingUserList.size();
    }

    public class ReviewView extends RecyclerView.ViewHolder {

        private ReviewBinding reviewBinding;

        public ReviewView(ReviewBinding reviewBinding) {
            super(reviewBinding.getRoot());
            this.reviewBinding = reviewBinding;
        }

        public void bind(BookLandingViewModel bookLandingViewModel) {
            this.reviewBinding.setReviewViewModel(bookLandingViewModel);
        }

        public ReviewBinding getBookBinding() {
            return reviewBinding;
        }

    }

}
