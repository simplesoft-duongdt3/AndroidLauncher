package tinyapps.launcher.common.usecase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.foody.base.data.interactor.DataResult;
import com.foody.base.task.ITaskManager;
import com.foody.pos.common.exception.PosException;
import com.foody.pos.common.listener.OnActionData;
import com.foody.pos.common.task.TaskProcessor;

import hugo.weaving.DebugLog;

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
    public abstract O executeInternal(@NonNull I input) throws PosException;

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
