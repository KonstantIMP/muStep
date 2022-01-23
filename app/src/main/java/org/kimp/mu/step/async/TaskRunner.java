package org.kimp.mu.step.async;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Class for running tasks in different thread (For HTTPS requests)
 */
public class TaskRunner {
    /** Object for running tasks in other thread */
    private final Executor executor = Executors.newSingleThreadExecutor();
    /** Object for handling tasks' finish */
    private final Handler handler = new Handler(Looper.getMainLooper());

    /** Interface for Callbacks */
    public interface Callback<R> {
        void onComplete(R result);
    }

    /**
     * Run task in different thread
     * @param callable Task for executing
     * @param callback Callback function for result return
     * @param <R> Type for result
     */
    public <R> void executeAsync(Callable<R> callable, Callback<R> callback) {
        executor.execute(() -> {
            try {
                final R result = callable.call();
                handler.post(() -> { callback.onComplete(result); });
            }
            catch (Exception e) {
                handler.post(() -> { callback.onComplete(null); });
            }
        });
    }
}