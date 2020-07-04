package com.example.Unsplash.adapters;

    import android.content.Context;

    import android.content.Intent;
    import android.util.Log;
    import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.Unsplash.Activities.FullscreenPhotoActivity;
    import com.example.Unsplash.R;
    import com.example.Unsplash.Utils.GlideApp;
    import com.example.Unsplash.Utils.Square_Image;
    import com.example.Unsplash.models.Photo;

    import java.util.List;

    import butterknife.BindView;
import butterknife.ButterKnife;
    import butterknife.OnClick;
    import de.hdodenhof.circleimageview.CircleImageView;

    /**
     * Created by sontbv on 12/3/17.
     */

    public class PhotosAdatper extends RecyclerView.Adapter<PhotosAdatper.ViewHolder> {
        private final String TAG = PhotosAdatper.class.getSimpleName();
        private List<Photo> photos;
        private Context context;

        public PhotosAdatper(Context context, List<Photo> photos){
            this.photos = photos;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_photo_layout, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Photo photo = photos.get(position);
            holder.username.setText(photo.getUser().getUsername());
            GlideApp
                    .with(context)
                    .load(photo.getUrl().getRegular())
                    .placeholder(R.drawable.placeholder)
                    .override(600, 600)
                    .into(holder.photo);

            GlideApp
                    .with(context)
                    .load(photo.getUser().getProfileImage().getSmall())
                    .into(holder.userAvatar);

        }

        @Override
        public int getItemCount() {
            return photos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.item_photo_username)
            TextView username;
            @BindView(R.id.item_photo_photo)
            Square_Image photo;
            @BindView(R.id.item_photo_user_avatar)
            CircleImageView userAvatar;
            @BindView(R.id.item_photo_layout)
            FrameLayout frameLayout;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }


            @OnClick(R.id.item_photo_layout)
            public void handleOnClick(){
                Log.d(TAG, "dmmmmmmmmm");
                int position = getAdapterPosition();
                String photoId = photos.get(position).getId();
                Intent intent = new Intent(context, FullscreenPhotoActivity.class);
                intent.putExtra("photoId", photoId);
                context.startActivity(intent);
            }











        }
}
