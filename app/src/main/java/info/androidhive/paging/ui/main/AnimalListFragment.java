package info.androidhive.paging.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.androidhive.paging.R;
import info.androidhive.paging.db.DataGenerator;
import info.androidhive.paging.db.entity.AnimalEntity;
import info.androidhive.paging.utils.MyDividerItemDecoration;

public class AnimalListFragment extends Fragment implements AnimalsAdapter.AnimalsAdapterListener {

    private static final String TAG = AnimalListFragment.class.getSimpleName();

    private AnimalListViewModel mViewModel;
    private Unbinder unbinder;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private AnimalsAdapter mAdapter;

    public static AnimalListFragment newInstance() {
        return new AnimalListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.animal_list_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new AnimalsAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AnimalListViewModel.class);

        mViewModel.getAnimals().observe(this, new Observer<PagedList<AnimalEntity>>() {
            @Override
            public void onChanged(@Nullable PagedList<AnimalEntity> animals) {
                if (animals == null || animals.size() == 0) {
                    // add data when data is empty
                    mViewModel.insertSampleData();
                }

                mAdapter.submitList(animals);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onAnimalClick(int id) {
        // no-op
    }
}
