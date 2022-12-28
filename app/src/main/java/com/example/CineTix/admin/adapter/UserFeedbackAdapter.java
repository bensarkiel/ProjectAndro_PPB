package com.example.CineTix.admin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CineTix.user.model.Userfeedback;
import com.example.CineTix.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class UserFeedbackAdapter extends FirebaseRecyclerAdapter<Userfeedback, UserFeedbackAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UserFeedbackAdapter(@NonNull FirebaseRecyclerOptions<Userfeedback> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Userfeedback model) {
        holder.TextView1.setText(model.getUsername());
        holder.TextView2.setText(model.getUlasan());
        holder.TextView3.setText(model.getFeedback());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_lihat_feedback,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView TextView1,TextView2,TextView3;
        public myViewHolder(@NonNull View listViewItem) {
            super(listViewItem);
            TextView1 = (TextView)listViewItem.findViewById(R.id.fb_userName);
            TextView2 = (TextView) listViewItem.findViewById(R.id.fb_rating);
            TextView3 =  (TextView)listViewItem.findViewById(R.id.fb_Feedback);

        }
    }
}
