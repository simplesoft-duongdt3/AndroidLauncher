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

public abstract class UseCaseOutput<O> {
    @NonNull
    private final TaskProcessor<O> taskProcessor;

    public UseCaseOutput(@NonNull ITaskManager taskManager) {
        taskProcessor = new TaskProcessor<>(taskManager);
    }

    public void setCancelOldTask(boolean isCancelOldTask) {
        taskProcessor.setCancelOldTask(isCancelOldTask);
    }

    @DebugLog
    @NonNull
    public abstract O executeInternal() throws AppException;

    public final DataResult<O> execute() {
        return taskProcessor.execute(this::executeInternal);
    }

    public final void executeAsync(@Nullable OnActionData<DataResult<O>> onResultEvent) {
        taskProcessor.executeAsync(this::executeInternal, onResultEvent);
    }

    public final void executeAsyncSingle(@Nullable OnActionData<DataResult<O>> onResultEvent) {
        taskProcessor.executeAsyncSingle(this::executeInternal, onResultEvent);
    }

    public final void executeAsyncLoading(Activity activity, @Nullable OnActionData<DataResult<O>> onResultEvent) {
        taskProcessor.executeAsyncLoading(activity, this::executeInternal, onResultEvent);
    }
}
