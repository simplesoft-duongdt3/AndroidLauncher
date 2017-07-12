package tinyapps.launcher.task;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import tinyapps.launcher.DataResult;
import tinyapps.launcher.event.OnWorkingWithException;

/**
 * Created by doanthanhduong on 12/14/16.
 */

public class DataManagerUtil {
    @NonNull
    public static <T> DataResult<T> request(@NonNull OnWorkingWithException<T> request) {
        DataResult<T> dataResult = new DataResult<>(null);
        try {
            T data = request.work();
            dataResult.setData(data);
            dataResult.setSuccess(true);
        } catch (Exception e) {
            Log.d("execute error", Log.getStackTraceString(e));
            dataResult.setSuccess(false);
            dataResult.setErrorMsg(e.getMessage());
            CrashlyticsUtil.logException(e);
            //dataResult.setErrorMsg(Log.getStackTraceString(e));
        }
        return dataResult;
    }


    public static <T> BaseBackgroundAsyncTask requestAsync(ITaskManager taskManager, @NonNull OnWorkingWithException<T> request, OnActionData<DataResult<T>> onDoneEvent) {
        return requestAsync(taskManager, request, onDoneEvent, true);
    }

    public static <T> BaseBackgroundAsyncTask requestAsync(ITaskManager taskManager, @NonNull OnWorkingWithException<T> request, OnActionData<DataResult<T>> onDoneEvent, boolean executeMulti) {
        OnAsyncTaskCallBack<DataResult<T>> callback = new OnAsyncTaskCallBack<DataResult<T>>() {

            @Override
            public void onPostExecute(DataResult<T> dataResult) {
                EventFireUtil.fireEvent(onDoneEvent, dataResult);
            }
        };

        BaseBackgroundAsyncTask<Void, Void, DataResult<T>> task = new BaseBackgroundAsyncTask<Void, Void, DataResult<T>>(callback) {

            @Override
            protected DataResult<T> doInBackgroundOverride(Void... voids) {
                return DataManagerUtil.request(request);
            }
        };

        if (executeMulti) {
            taskManager.executeTaskMultiMode(task);
        } else {
            taskManager.executeTaskSingleMode(task);
        }
        return task;
    }

    public static <T> BaseBackgroundAsyncTask requestAsyncLoading(Activity activity, ITaskManager taskManager, @NonNull OnWorkingWithException<T> request, OnActionData<DataResult<T>> onDoneEvent) {
        return requestAsyncLoading(activity, taskManager, request, onDoneEvent, true);
    }

    public static <T> BaseBackgroundAsyncTask requestAsyncLoading(Activity activity, ITaskManager taskManager, @NonNull OnWorkingWithException<T> request, OnActionData<DataResult<T>> onDoneEvent, boolean executeMulti) {
        OnAsyncTaskCallBack<DataResult<T>> callback = new OnAsyncTaskCallBack<DataResult<T>>() {

            @Override
            public void onPostExecute(DataResult<T> dataResult) {
                EventFireUtil.fireEvent(onDoneEvent, dataResult);
            }
        };

        BaseBackgroundAsyncTask<Void, Void, DataResult<T>> task = new BaseLoadingAsyncTask<Void, Void, DataResult<T>>(activity, callback) {

            @Override
            protected DataResult<T> doInBackgroundOverride(Void... voids) {
                return DataManagerUtil.request(request);
            }
        };

        if (executeMulti) {
            taskManager.executeTaskMultiMode(task);
        } else {
            taskManager.executeTaskSingleMode(task);
        }
        return task;
    }
}
