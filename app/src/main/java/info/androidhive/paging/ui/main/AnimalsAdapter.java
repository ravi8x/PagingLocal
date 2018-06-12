package info.androidhive.paging.ui.main;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.androidhive.paging.R;
import info.androidhive.paging.db.entity.AnimalEntity;

public class AnimalsAdapter extends PagedListAdapter<AnimalEntity, AnimalsAdapter.MyViewHolder> {

    private static final String TAG = AnimalsAdapter.class.getSimpleName();

    private AnimalsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView note;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAnimalClick(getAnimal(getLayoutPosition()).getId());
                }
            });
        }
    }

    public AnimalsAdapter(AnimalsAdapterListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_animal, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AnimalEntity animal = getAnimal(position);
        if (animal != null) {
            String name = animal.getName().substring(0, 1).toUpperCase() + animal.getName().substring(1);
            holder.note.setText(name);
        }
    }

    public AnimalEntity getAnimal(int position) {
        return getItem(position);
    }

    private static final DiffUtil.ItemCallback<AnimalEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<AnimalEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull AnimalEntity oldItem, @NonNull AnimalEntity newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull AnimalEntity oldItem, @NonNull AnimalEntity newItem) {
                    return oldItem.getId() == newItem.getId() && oldItem.getName().equalsIgnoreCase(newItem.getName());
                }
            };

    public interface AnimalsAdapterListener {
        void onAnimalClick(int id);
    }
}
