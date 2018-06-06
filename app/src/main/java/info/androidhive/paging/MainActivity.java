package info.androidhive.paging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.androidhive.paging.ui.main.AnimalListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AnimalListFragment.newInstance())
                    .commitNow();
        }
    }
}
