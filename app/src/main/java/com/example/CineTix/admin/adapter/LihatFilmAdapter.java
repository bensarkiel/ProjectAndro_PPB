package com.example.CineTix.admin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CineTix.admin.model.LihatTiket;
import com.example.CineTix.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class LihatFilmAdapter extends FirebaseRecyclerAdapter<LihatTiket, LihatFilmAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public LihatFilmAdapter(@NonNull FirebaseRecyclerOptions<LihatTiket> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull LihatTiket model) {
        holder.Booking_Id.setText(model.getBookingId());
        holder.Movie_Id.setText(model.getMovieId());
        holder.Movie_Name.setText(model.getMovieName());
        holder.User_Name.setText(model.getUserName());
        holder.Show_Time.setText(model.getShowTime());
        holder.Seat.setText(model.getBookingSeat());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_lihat_tiket,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView Booking_Id, Movie_Id, Movie_Name, User_Name, Show_Time, Seat;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Booking_Id = itemView.findViewById(R.id.b_booking_id);
            Movie_Id = itemView.findViewById(R.id.b_movie_id);
            Movie_Name = itemView.findViewById(R.id.b_movie_name);
            User_Name = itemView.findViewById(R.id.b_user_name);
            Show_Time = itemView.findViewById(R.id.b_show_time);
            Seat = itemView.findViewById(R.id.b_seat);
        }
    }
}
