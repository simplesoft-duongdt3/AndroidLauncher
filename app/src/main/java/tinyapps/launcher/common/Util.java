package tinyapps.launcher.common;

import android.os.AsyncTask;

/**
 * Created by duongmatheo on 7/12/17.
 */

public class Util {
    public static void checkAndCancelTasks(AsyncTask task) {
        if (task != null && task.getStatus() != AsyncTask.Status.FINISHED) {
            task.cancel(true);
        }
    }
}
