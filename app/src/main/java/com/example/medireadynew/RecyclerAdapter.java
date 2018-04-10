package com.example.medireadynew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordanyep on 2018-03-19.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    static List<DatabaseModel> dbList;
    static Context context;


    DatabaseHelper helper;

    RecyclerAdapter(Context context, List<DatabaseModel> dbList ){
        this.dbList = new ArrayList<DatabaseModel>();
        this.context = context;
        this.dbList = dbList;

    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.person_card, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        /*
        values.put("first", first);
        values.put("last", last);
        values.put("age", age);
        values.put("gender", gender);
        values.put("relationship", relationship);
         */
        holder.first.setText(dbList.get(position).getFirstName());
        holder.last.setText(dbList.get(position).getLastName());
        holder.age.setText(dbList.get(position).getAge());
        holder.gender.setText(dbList.get(position).getGender());
        holder.relationship.setText(dbList.get(position).getRelationship());


        //holder.image.setImageBitmap(bitmap);
//        Bitmap bitmap = BitmapFactory.decodeByteArray(image2, 0, image2.length);
//        holder.image2.setImageBitmap(dbList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView first,last, age, gender, relationship;
        public ImageView image2;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            first = (TextView) itemLayoutView.findViewById(R.id.firstName);
            last = (TextView)itemLayoutView.findViewById(R.id.lastName);
            age = (TextView)itemLayoutView.findViewById(R.id.age);
            gender = (TextView)itemLayoutView.findViewById(R.id.gender);
            relationship = (TextView)itemLayoutView.findViewById(R.id.relationship);

            image2 = (ImageView)itemLayoutView.findViewById(R.id.imageView);
            itemLayoutView.setOnClickListener(this);
            itemLayoutView.setOnLongClickListener(this);





        }

        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(context,PersonDetails.class);
            Intent intent = new Intent(context,PersonExpandedFragment.class);

            Bundle extras = new Bundle();
            extras.putInt("position",getAdapterPosition());
            intent.putExtras(extras);

            context.startActivity(intent);
            //Toast.makeText(RecyclerAdapter.context, "you have clicked Row " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }


        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(RecyclerAdapter.context,
                    "long press detected", Toast.LENGTH_SHORT).show();
            openDialog();

            return false;
        }

        public void openDialog() {
            FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
            final DeleteRowDialog deleteRowDialog = new DeleteRowDialog();
            deleteRowDialog.show(manager, "example");
        }

    }
}

/*
mRecyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "You have pressed it long :)", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
 */