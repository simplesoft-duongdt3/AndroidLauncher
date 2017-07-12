package tinyapps.launcher.common.usecase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import hugo.weaving.DebugLog;
import tinyapps.launcher.DataResult;
import tinyapps.launcher.common.AppException;
import tinyapps.launcher.common.event.OnActionData;
import tinyapps.launcher.common.task.ITaskManager;
import tinyapps.launcher.common.task.TaskProcessor;

/**
 * Created by duongmatheo on 6/8/17.
 */

public abstract class UseCase<I, O> {
    @NonNull
    protected final ITaskManager taskManager;
    @NonNull
    private final TaskProcessor<O> taskProcessor;

    public UseCase(@NonNull ITaskManager taskManager) {
        taskProcessor = new TaskProcessor<>(taskManager);
        this.taskManager = taskManager;
    }

    public void setCancelOldTask(boolean isCancelOldTask) {
        taskProcessor.setCancelOldTask(isCancelOldTask);
    }

    @DebugLog
    @NonNull
    public abstract O executeInternal(@NonNull I input) throws AppException;

    public final DataResult<O> execute(@NonNull I input) {
        return taskProcessor.execute(() -> executeInternal(input));
    }

    public final void executeAsync(I input, @Nullable OnActionData<DataResult<O>> onResultEvent) {
        taskProcessor.executeAsync(() -> executeInternal(input), onResultEvent);
    }

    public final void executeAsyncLoading(Activity activity, I input, @Nullable OnActionData<DataResult<O>> onResultEvent) {
        taskProcessor.executeAsyncLoading(activity, () -> executeInternal(input), onResultEvent);
    }

    public final void executeAsyncSingle(I input, @Nullable OnActionData<DataResult<O>> onResultEvent) {
        taskProcessor.executeAsyncSingle(() -> executeInternal(input), onResultEvent);
    }
}
