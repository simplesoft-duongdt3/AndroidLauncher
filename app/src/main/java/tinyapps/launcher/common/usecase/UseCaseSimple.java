package tinyapps.launcher.common.usecase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import hugo.weaving.DebugLog;
import tinyapps.launcher.task.ITaskManager;

/**
 * Created by duongmatheo on 6/8/17.
 */

public abstract class UseCaseSimple {
    @NonNull
    private final TaskProcessor<Void> taskProcessor;

    public UseCaseSimple(@NonNull ITaskManager taskManager) {
        taskProcessor = new TaskProcessor<>(taskManager);
    }

    public void setCancelOldTask(boolean isCancelOldTask) {
        taskProcessor.setCancelOldTask(isCancelOldTask);
    }

    @DebugLog
    public abstract void executeInternal() throws PosException;

    public final DataResult<Void> execute() {
        return taskProcessor.execute(() -> {
            executeInternal();
            return null;
        });
    }

    public final void executeAsync(@Nullable OnActionData<DataResult<Void>> onResultEvent) {
        taskProcessor.executeAsync(() -> {
            executeInternal();
            return null;
        }, onResultEvent);
    }

    public final void executeAsyncSingle(@Nullable OnActionData<DataResult<Void>> onResultEvent) {
        taskProcessor.executeAsyncSingle(() -> {
            executeInternal();
            return null;
        }, onResultEvent);
    }

    public final void executeAsyncLoading(Activity activity, @Nullable OnActionData<DataResult<Void>> onResultEvent) {
        taskProcessor.executeAsyncLoading(activity, () -> {
            executeInternal();
            return null;
        }, onResultEvent);
    }
}
