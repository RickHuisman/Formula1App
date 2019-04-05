package com.rickhuisman.formula1app.ui.driverdetail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Team;
import com.rickhuisman.formula1app.ui.constructor.ConstructorActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamHolder> {

    private Context mContext;
    private List<Team> mTeams = new ArrayList<>();

    public TeamAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public TeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new TeamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamHolder holder, int position) {
        final Team team = mTeams.get(position);

        holder.team.setText(team.getName());
        holder.date.setText(team.getYearStart() + " • " + team.getYearEnd());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int constructorId = team.getConstructor().getConstructorId();
//
//                Intent intent = new Intent(mContext, ConstructorActivity.class);
//                intent.putExtra("constructorId", constructorId);
//                mContext.startActivity(intent);
//            }
//        });
    }

    public void setTeams(List<Team> teams) {
        this.mTeams = teams;
    }

    @Override
    public int getItemCount() {
        return mTeams.size();
    }

    public class TeamHolder extends RecyclerView.ViewHolder {

        private TextView team, date;

        public TeamHolder(@NonNull View itemView) {
            super(itemView);
            team = itemView.findViewById(R.id.team);
            date = itemView.findViewById(R.id.date);
        }
    }
}
