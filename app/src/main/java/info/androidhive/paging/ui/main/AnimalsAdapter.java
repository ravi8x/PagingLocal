package info.androidhive.paging.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.androidhive.paging.R;
import info.androidhive.paging.db.entity.AnimalEntity;

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.MyViewHolder> {

    private static final String TAG = AnimalsAdapter.class.getSimpleName();

    private Context context;
    private List<AnimalEntity> mAnimalsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView note;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public AnimalsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_animal, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mAnimalsList != null) {
            AnimalEntity note = mAnimalsList.get(position);

            String name = note.getName().substring(0, 1).toUpperCase() + note.getName().substring(1);
            holder.note.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return mAnimalsList == null ? 0 : mAnimalsList.size();
    }

    public void setData(final List<AnimalEntity> notes) {
        mAnimalsList = notes;
        notifyDataSetChanged();
    }
}
