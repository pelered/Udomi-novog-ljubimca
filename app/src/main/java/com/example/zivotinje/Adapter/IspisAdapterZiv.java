package com.example.zivotinje.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zivotinje.PrikazZiv;
import com.example.zivotinje.R;
import com.example.zivotinje.Model.ZivUpload;

import java.util.List;

public class IspisAdapterZiv extends RecyclerView.Adapter<IspisAdapterZiv.ImageViewHolder>{
    private Context mContext;
    private List<ZivUpload> mUploads;
    public IspisAdapterZiv(Context context, List<ZivUpload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    public IspisAdapterZiv.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //todo promjenit layout za zivotinje bas
        View v = LayoutInflater.from(mContext).inflate(R.layout.card_view_ziv, parent, false);
        return new IspisAdapterZiv.ImageViewHolder(v);
    }

    public void onBindViewHolder(@NonNull final IspisAdapterZiv.ImageViewHolder holder, final int position) {
        final ZivUpload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getNaziv());
        holder.skl.setText(uploadCurrent.getNaz_skl());
        holder.status.setText(uploadCurrent.getStatus());
        holder.izmjena.setText(uploadCurrent.getLast_date());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "This is item in position " + position, Toast.LENGTH_SHORT).show();
                //EditZiv fragment=new EditZiv();
                PrikazZiv fragment=new PrikazZiv();
                Bundle args = new Bundle();
                args.putString("oznaka", uploadCurrent.getOznaka());
                fragment.setArguments(args);
                FragmentTransaction ft =((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack("tag_ispis");
                ft.commit();
            }
        });
        //Log.d("Pisem",uploadCurrent.getUrl().toString());
        if(uploadCurrent.getUrl()!=null){
            Glide.with(mContext)
                    .load(uploadCurrent.getUrl().get("0_key"))
                    //.centerCrop()
                    .optionalFitCenter()
                    .into(holder.imageView);

        }
    }
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName,skl,status,izmjena;
        public ImageView imageView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            skl= itemView.findViewById(R.id.card_grad);
            status= itemView.findViewById(R.id.status);
            izmjena= itemView.findViewById(R.id.izmjena);
            textViewName = itemView.findViewById(R.id.textView_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
        }
    }
}
