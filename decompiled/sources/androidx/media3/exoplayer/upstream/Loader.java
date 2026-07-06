package androidx.media3.exoplayer.upstream;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class Loader implements LoaderErrorThrower {
    public static final int ACTION_TYPE_DONT_RETRY = 2;
    public static final int ACTION_TYPE_DONT_RETRY_FATAL = 3;
    public static final int ACTION_TYPE_RETRY = 0;
    public static final int ACTION_TYPE_RETRY_AND_RESET_ERROR_COUNT = 1;
    public static final String THREAD_NAME_PREFIX = "ExoPlayer:Loader:";

    @Nullable
    public LoadTask<? extends Loadable> currentTask;
    public final ExecutorService downloadExecutorService;

    @Nullable
    public IOException fatalError;
    public static final LoadErrorAction RETRY = new LoadErrorAction(0, -9223372036854775807L);
    public static final LoadErrorAction RETRY_RESET_ERROR_COUNT = new LoadErrorAction(1, -9223372036854775807L);
    public static final LoadErrorAction DONT_RETRY = new LoadErrorAction(2, -9223372036854775807L);
    public static final LoadErrorAction DONT_RETRY_FATAL = new LoadErrorAction(3, -9223372036854775807L);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Callback<T extends Loadable> {
        void onLoadCanceled(T t, long j, long j2, boolean z);

        void onLoadCompleted(T t, long j, long j2);

        LoadErrorAction onLoadError(T t, long j, long j2, IOException iOException, int i);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LoadErrorAction {
        public final long retryDelayMillis;
        public final int type;

        public boolean isRetry() {
            int i = this.type;
            return i == 0 || i == 1;
        }

        public LoadErrorAction(int i, long j) {
            this.type = i;
            this.retryDelayMillis = j;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SuppressLint({"HandlerLeak"})
    public final class LoadTask<T extends Loadable> extends Handler implements Runnable {
        public static final int MSG_FATAL_ERROR = 3;
        public static final int MSG_FINISH = 1;
        public static final int MSG_IO_EXCEPTION = 2;
        public static final int MSG_START = 0;
        public static final String TAG = "LoadTask";

        @Nullable
        public Callback<T> callback;
        public boolean canceled;

        @Nullable
        public IOException currentError;
        public final int defaultMinRetryCount;
        public int errorCount;

        @Nullable
        public Thread executorThread;
        public final T loadable;
        public volatile boolean released;
        public final long startTimeMs;

        public LoadTask(Looper looper, T t, Callback<T> callback, int i, long j) {
            super(looper);
            this.loadable = t;
            this.callback = callback;
            this.defaultMinRetryCount = i;
            this.startTimeMs = j;
        }

        public void cancel(boolean z) {
            this.released = z;
            this.currentError = null;
            if (hasMessages(0)) {
                this.canceled = true;
                removeMessages(0);
                if (!z) {
                    sendEmptyMessage(1);
                }
            } else {
                synchronized (this) {
                    try {
                        this.canceled = true;
                        this.loadable.cancelLoad();
                        Thread thread = this.executorThread;
                        if (thread != null) {
                            thread.interrupt();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            if (z) {
                finish();
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                Callback<T> callback = this.callback;
                callback.getClass();
                callback.onLoadCanceled(this.loadable, jElapsedRealtime, jElapsedRealtime - this.startTimeMs, true);
                this.callback = null;
            }
        }

        public final void execute() {
            this.currentError = null;
            Loader loader = Loader.this;
            ExecutorService executorService = loader.downloadExecutorService;
            LoadTask<? extends Loadable> loadTask = loader.currentTask;
            loadTask.getClass();
            executorService.execute(loadTask);
        }

        public final void finish() {
            Loader.this.currentTask = null;
        }

        public final long getRetryDelayMillis() {
            return Math.min((this.errorCount - 1) * 1000, 5000);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.released) {
                return;
            }
            int i = message.what;
            if (i == 0) {
                execute();
                return;
            }
            if (i == 3) {
                throw ((Error) message.obj);
            }
            finish();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j = jElapsedRealtime - this.startTimeMs;
            Callback<T> callback = this.callback;
            callback.getClass();
            if (this.canceled) {
                callback.onLoadCanceled(this.loadable, jElapsedRealtime, j, false);
                return;
            }
            int i2 = message.what;
            if (i2 == 1) {
                try {
                    callback.onLoadCompleted(this.loadable, jElapsedRealtime, j);
                    return;
                } catch (RuntimeException e) {
                    Log.e("LoadTask", "Unexpected exception handling load completed", e);
                    Loader.this.fatalError = new UnexpectedLoaderException(e);
                    return;
                }
            }
            if (i2 != 2) {
                return;
            }
            IOException iOException = (IOException) message.obj;
            this.currentError = iOException;
            int i3 = this.errorCount + 1;
            this.errorCount = i3;
            LoadErrorAction loadErrorActionOnLoadError = callback.onLoadError(this.loadable, jElapsedRealtime, j, iOException, i3);
            int i4 = loadErrorActionOnLoadError.type;
            if (i4 == 3) {
                Loader.this.fatalError = this.currentError;
            } else if (i4 != 2) {
                if (i4 == 1) {
                    this.errorCount = 1;
                }
                long retryDelayMillis = loadErrorActionOnLoadError.retryDelayMillis;
                if (retryDelayMillis == -9223372036854775807L) {
                    retryDelayMillis = getRetryDelayMillis();
                }
                start(retryDelayMillis);
            }
        }

        public void maybeThrowError(int i) throws IOException {
            IOException iOException = this.currentError;
            if (iOException != null && this.errorCount > i) {
                throw iOException;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            try {
                synchronized (this) {
                    z = this.canceled;
                    this.executorThread = Thread.currentThread();
                }
                if (!z) {
                    TraceUtil.beginSection("load:".concat(this.loadable.getClass().getSimpleName()));
                    try {
                        this.loadable.load();
                        TraceUtil.endSection();
                    } catch (Throwable th) {
                        TraceUtil.endSection();
                        throw th;
                    }
                }
                synchronized (this) {
                    this.executorThread = null;
                    Thread.interrupted();
                }
                if (this.released) {
                    return;
                }
                sendEmptyMessage(1);
            } catch (IOException e) {
                if (this.released) {
                    return;
                }
                obtainMessage(2, e).sendToTarget();
            } catch (Exception e2) {
                if (this.released) {
                    return;
                }
                Log.e("LoadTask", "Unexpected exception loading stream", e2);
                obtainMessage(2, new UnexpectedLoaderException(e2)).sendToTarget();
            } catch (OutOfMemoryError e3) {
                if (this.released) {
                    return;
                }
                Log.e("LoadTask", "OutOfMemory error loading stream", e3);
                obtainMessage(2, new UnexpectedLoaderException(e3)).sendToTarget();
            } catch (Error e4) {
                if (!this.released) {
                    Log.e("LoadTask", "Unexpected error loading stream", e4);
                    obtainMessage(3, e4).sendToTarget();
                }
                throw e4;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void start(long j) {
            Assertions.checkState(Loader.this.currentTask == null);
            Loader.this.currentTask = this;
            if (j > 0) {
                sendEmptyMessageDelayed(0, j);
            } else {
                execute();
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Loadable {
        void cancelLoad();

        void load() throws IOException;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ReleaseCallback {
        void onLoaderReleased();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ReleaseTask implements Runnable {
        public final ReleaseCallback callback;

        public ReleaseTask(ReleaseCallback releaseCallback) {
            this.callback = releaseCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.callback.onLoaderReleased();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnexpectedLoaderException extends IOException {
        public UnexpectedLoaderException(Throwable th) {
            String str;
            StringBuilder sb = new StringBuilder("Unexpected ");
            sb.append(th.getClass().getSimpleName());
            if (th.getMessage() != null) {
                str = ": " + th.getMessage();
            } else {
                str = "";
            }
            sb.append(str);
            super(sb.toString(), th);
        }
    }

    public Loader(String str) {
        this.downloadExecutorService = Util.newSingleThreadExecutor("ExoPlayer:Loader:" + str);
    }

    public static LoadErrorAction createRetryAction(boolean z, long j) {
        return new LoadErrorAction(z ? 1 : 0, j);
    }

    public void cancelLoading() {
        LoadTask<? extends Loadable> loadTask = this.currentTask;
        Assertions.checkStateNotNull(loadTask);
        loadTask.cancel(false);
    }

    public void clearFatalError() {
        this.fatalError = null;
    }

    public boolean hasFatalError() {
        return this.fatalError != null;
    }

    public boolean isLoading() {
        return this.currentTask != null;
    }

    @Override // androidx.media3.exoplayer.upstream.LoaderErrorThrower
    public void maybeThrowError() throws IOException {
        maybeThrowError(Integer.MIN_VALUE);
    }

    public void release() {
        release(null);
    }

    public <T extends Loadable> long startLoading(T t, Callback<T> callback, int i) {
        Looper looperMyLooper = Looper.myLooper();
        Assertions.checkStateNotNull(looperMyLooper);
        this.fatalError = null;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        new LoadTask(looperMyLooper, t, callback, i, jElapsedRealtime).start(0L);
        return jElapsedRealtime;
    }

    @Override // androidx.media3.exoplayer.upstream.LoaderErrorThrower
    public void maybeThrowError(int i) throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
        LoadTask<? extends Loadable> loadTask = this.currentTask;
        if (loadTask != null) {
            if (i == Integer.MIN_VALUE) {
                i = loadTask.defaultMinRetryCount;
            }
            loadTask.maybeThrowError(i);
        }
    }

    public void release(@Nullable ReleaseCallback releaseCallback) {
        LoadTask<? extends Loadable> loadTask = this.currentTask;
        if (loadTask != null) {
            loadTask.cancel(true);
        }
        if (releaseCallback != null) {
            this.downloadExecutorService.execute(new ReleaseTask(releaseCallback));
        }
        this.downloadExecutorService.shutdown();
    }
}
