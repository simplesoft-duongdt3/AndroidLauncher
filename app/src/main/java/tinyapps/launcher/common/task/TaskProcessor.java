package tinyapps.launcher.common.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.Setter;
import tinyapps.launcher.common.DataResult;
import tinyapps.launcher.common.Util;
import tinyapps.launcher.common.event.EventFireUtil;
import tinyapps.launcher.common.event.OnActionData;
import tinyapps.launcher.common.event.OnWorkingWithException;

/**
 * Created by duongmatheo on 6/9/17.
 */

public class TaskProcessor<O> {
    @NonNull
    private final ITaskManager taskManager;
    @Setter
    private boolean cancelOldTask = true;
    private AsyncTask task;

    public TaskProcessor(@NonNull ITaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public final DataResult<O> execute(@NonNull OnWorkingWithException<O> onWorkingEvent) {
        return DataManagerUtil.request(() -> EventFireUtil.fireEvent(onWorkingEvent));
    }

    public final void executeAsync(@NonNull OnWorkingWithException<O> onWorkingEvent, @Nullable OnActionData<DataResult<O>> onResultEvent) {
        checkAndCancelTaskIfNeed();
        task = DataManagerUtil.requestAsync(taskManager, () -> EventFireUtil.fireEvent(onWorkingEvent), onResultEvent);
    }

    public final void executeAsyncLoading(Activity activity, @NonNull OnWorkingWithException<O> onWorkingEvent, @Nullable OnActionData<DataResult<O>> onResultEvent) {
        checkAndCancelTaskIfNeed();
        task = DataManagerUtil.requestAsyncLoading(activity, taskManager, () -> EventFireUtil.fireEvent(onWorkingEvent), onResultEvent);
    }

    public final void executeAsyncSingle(@NonNull OnWorkingWithException<O> onWorkingEvent, @Nullable OnActionData<DataResult<O>> onResultEvent) {
        checkAndCancelTaskIfNeed();
        task = DataManagerUtil.requestAsync(taskManager, () -> EventFireUtil.fireEvent(onWorkingEvent), onResultEvent, false);
    }

    private void checkAndCancelTaskIfNeed() {
        if (cancelOldTask) {
            Util.checkAndCancelTasks(task);
        }
    }
}
