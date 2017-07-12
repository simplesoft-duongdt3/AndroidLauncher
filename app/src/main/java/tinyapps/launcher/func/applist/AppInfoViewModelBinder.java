package tinyapps.launcher.func.applist;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import info.awesomedevelopment.tvgrid.library.TVGridView;
import me.drakeet.multitype.ItemViewBinder;
import tinyapps.launcher.R;
import tinyapps.launcher.common.list.RecylcerButterKnifeViewHolder;

/**
 * Created by duongmatheo on 7/12/17.
 */

public class AppInfoViewModelBinder extends ItemViewBinder<AppInfoViewModel, AppInfoViewModelBinder.ViewHolder> {
    private final TVGridView tvGridView;

    public AppInfoViewModelBinder(@NonNull TVGridView tvGridView) {
        this.tvGridView = tvGridView;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.newInstance(inflater, parent);
        viewHolder.itemView.setFocusable(true);
        viewHolder.itemView.setOnFocusChangeListener(tvGridView::selectView);
        return viewHolder;
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
            return new ViewHolder(inflater, parent, R.layout.item);
        }
    }
}
