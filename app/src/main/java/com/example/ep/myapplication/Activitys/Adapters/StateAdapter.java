package com.example.ep.myapplication.Activitys.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.ep.myapplication.Activitys.Activitys.MainActivity;
import com.example.ep.myapplication.Activitys.Model.State;
import com.example.ep.myapplication.R;


import java.util.ArrayList;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<State> allstates;
    private boolean first;

    public StateAdapter(Context context, ArrayList<State> allstates , boolean provide) {

        this.context = context;
        this.allstates = allstates;
        this.first = provide;

        Log.d("nathan", "StateAdapter: "+ provide + " num states " + allstates.size());

    }

    public ArrayList<State> custumeFilter(ArrayList<State> allstates, String key) {
        ArrayList<State> states = new ArrayList<>();

        for (State s : allstates) {
            if(s.getName().toLowerCase().startsWith(key.toLowerCase())){
                states.add(s);
            }
        }
        return states;
    }

    @NonNull
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rowlayout, parent, false);
        return  new StateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.ViewHolder holder, int position) {
        final State temp = allstates.get(position);
        if (holder != null) {
            holder.name_LBL.setText(temp.getName());
            holder.nativeName_LBL.setText(temp.getNativeName());
        if(first){
            holder.country_LAY_crd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.shake);
                    view.startAnimation(hyperspaceJumpAnimation);

                    MainActivity ma =(MainActivity) context;
                    ma.LoadSecFragment(temp);
                }
            });
        }

        }

    }

    @Override
    public int getItemCount() {
       if (allstates == null){
           return 0;
       }
        return allstates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name_LBL , nativeName_LBL;
        CardView country_LAY_crd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init();
        }

        private void init() {
            country_LAY_crd = itemView.findViewById(R.id.country_LAY_crd);
            name_LBL = itemView.findViewById(R.id.name_LBL);
            nativeName_LBL = itemView.findViewById(R.id.nativeName_LBL);
        }

    }
}
