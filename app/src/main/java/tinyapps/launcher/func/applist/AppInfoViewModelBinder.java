package tinyapps.launcher.func.applist;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import me.drakeet.multitype.ItemViewBinder;
import tinyapps.launcher.R;
import tinyapps.launcher.common.list.RecylcerButterKnifeViewHolder;

/**
 * Created by duongmatheo on 7/12/17.
 */

public class AppInfoViewModelBinder extends ItemViewBinder<AppInfoViewModel, AppInfoViewModelBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return ViewHolder.newInstance(inflater, parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, @NonNull AppInfoViewModel item) {
        viewHolder.tvAppName.setText("Time: " + System.nanoTime());
    }

    static class ViewHolder extends RecylcerButterKnifeViewHolder {

        @BindView(R.id.tvAppName)
        TextView tvAppName;

        public ViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @LayoutRes int layoutId) {
            super(inflater, parent, layoutId);
        }

        public static ViewHolder newInstance(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
            return new ViewHolder(inflater, parent, R.layout.app_item_view_holder);
        }
    }
}
