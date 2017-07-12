package tinyapps.launcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import tinyapps.launcher.func.applist.AppInfoViewModel;
import tinyapps.launcher.func.applist.AppInfoViewModelBinder;
import tinyapps.launcher.func.applist.FixAppInfoViewModel;
import tinyapps.launcher.func.applist.FixAppInfoViewModelBinder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvApp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
        multiTypeAdapter.register(AppInfoViewModel.class, new AppInfoViewModelBinder());
        multiTypeAdapter.register(FixAppInfoViewModel.class, new FixAppInfoViewModelBinder());
        recyclerView.setAdapter(multiTypeAdapter);

        Items items = new Items();
        for (int i = 0; i < 1000; i++) {
            items.add(new AppInfoViewModel());
            items.add(new FixAppInfoViewModel());
        }

        multiTypeAdapter.setItems(items);
        multiTypeAdapter.notifyDataSetChanged();
    }
}
