package androidx.emoji2.text;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Trace;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.os.TraceCompat;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class FontRequestEmojiCompatConfig extends EmojiCompat.Config {
    public static final FontProviderHelper DEFAULT_FONTS_CONTRACT = new FontProviderHelper();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ExponentialBackoffRetryPolicy extends RetryPolicy {
        public long mRetryOrigin;
        public final long mTotalMs;

        public ExponentialBackoffRetryPolicy(long j) {
            this.mTotalMs = j;
        }

        @Override // androidx.emoji2.text.FontRequestEmojiCompatConfig.RetryPolicy
        public long getRetryDelay() {
            if (this.mRetryOrigin == 0) {
                this.mRetryOrigin = SystemClock.uptimeMillis();
                return 0L;
            }
            long jUptimeMillis = SystemClock.uptimeMillis() - this.mRetryOrigin;
            if (jUptimeMillis > this.mTotalMs) {
                return -1L;
            }
            return Math.min(Math.max(jUptimeMillis, 1000L), this.mTotalMs - jUptimeMillis);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static class FontProviderHelper {
        @Nullable
        public Typeface buildTypeface(@NonNull Context context, @NonNull FontsContractCompat.FontInfo fontInfo) throws PackageManager.NameNotFoundException {
            return TypefaceCompat.createFromFontInfo(context, null, new FontsContractCompat.FontInfo[]{fontInfo}, 0);
        }

        @NonNull
        public FontsContractCompat.FontFamilyResult fetchFonts(@NonNull Context context, @NonNull FontRequest fontRequest) throws PackageManager.NameNotFoundException {
            return FontsContractCompat.fetchFonts(context, null, fontRequest);
        }

        public void registerObserver(@NonNull Context context, @NonNull Uri uri, @NonNull ContentObserver contentObserver) {
            context.getContentResolver().registerContentObserver(uri, false, contentObserver);
        }

        public void unregisterObserver(@NonNull Context context, @NonNull ContentObserver contentObserver) {
            context.getContentResolver().unregisterContentObserver(contentObserver);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FontRequestMetadataLoader implements EmojiCompat.MetadataRepoLoader {
        public static final String S_TRACE_BUILD_TYPEFACE = "EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface";

        @Nullable
        @GuardedBy("mLock")
        public EmojiCompat.MetadataRepoLoaderCallback mCallback;

        @NonNull
        public final Context mContext;

        @Nullable
        @GuardedBy("mLock")
        public Executor mExecutor;

        @NonNull
        public final FontProviderHelper mFontProviderHelper;

        @NonNull
        public final Object mLock = new Object();

        @Nullable
        @GuardedBy("mLock")
        public Handler mMainHandler;

        @Nullable
        @GuardedBy("mLock")
        public Runnable mMainHandlerLoadCallback;

        @Nullable
        @GuardedBy("mLock")
        public ThreadPoolExecutor mMyThreadPoolExecutor;

        @Nullable
        @GuardedBy("mLock")
        public ContentObserver mObserver;

        @NonNull
        public final FontRequest mRequest;

        @Nullable
        @GuardedBy("mLock")
        public RetryPolicy mRetryPolicy;

        public FontRequestMetadataLoader(@NonNull Context context, @NonNull FontRequest fontRequest, @NonNull FontProviderHelper fontProviderHelper) {
            Preconditions.checkNotNull(context, "Context cannot be null");
            Preconditions.checkNotNull(fontRequest, "FontRequest cannot be null");
            this.mContext = context.getApplicationContext();
            this.mRequest = fontRequest;
            this.mFontProviderHelper = fontProviderHelper;
        }

        public final void cleanUp() {
            synchronized (this.mLock) {
                try {
                    this.mCallback = null;
                    ContentObserver contentObserver = this.mObserver;
                    if (contentObserver != null) {
                        this.mFontProviderHelper.unregisterObserver(this.mContext, contentObserver);
                        this.mObserver = null;
                    }
                    Handler handler = this.mMainHandler;
                    if (handler != null) {
                        handler.removeCallbacks(this.mMainHandlerLoadCallback);
                    }
                    this.mMainHandler = null;
                    ThreadPoolExecutor threadPoolExecutor = this.mMyThreadPoolExecutor;
                    if (threadPoolExecutor != null) {
                        threadPoolExecutor.shutdown();
                    }
                    this.mExecutor = null;
                    this.mMyThreadPoolExecutor = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @RequiresApi(19)
        @WorkerThread
        public void createMetadata() {
            synchronized (this.mLock) {
                try {
                    if (this.mCallback == null) {
                        return;
                    }
                    try {
                        FontsContractCompat.FontInfo fontInfoRetrieveFontInfo = retrieveFontInfo();
                        int resultCode = fontInfoRetrieveFontInfo.getResultCode();
                        if (resultCode == 2) {
                            synchronized (this.mLock) {
                                try {
                                    RetryPolicy retryPolicy = this.mRetryPolicy;
                                    if (retryPolicy != null) {
                                        long retryDelay = retryPolicy.getRetryDelay();
                                        if (retryDelay >= 0) {
                                            scheduleRetry(fontInfoRetrieveFontInfo.getUri(), retryDelay);
                                            return;
                                        }
                                    }
                                } finally {
                                }
                            }
                        }
                        if (resultCode != 0) {
                            throw new RuntimeException("fetchFonts result is not OK. (" + resultCode + ")");
                        }
                        try {
                            TraceCompat.beginSection(S_TRACE_BUILD_TYPEFACE);
                            Typeface typefaceBuildTypeface = this.mFontProviderHelper.buildTypeface(this.mContext, fontInfoRetrieveFontInfo);
                            ByteBuffer byteBufferMmap = TypefaceCompatUtil.mmap(this.mContext, null, fontInfoRetrieveFontInfo.getUri());
                            if (byteBufferMmap == null || typefaceBuildTypeface == null) {
                                throw new RuntimeException("Unable to open file.");
                            }
                            MetadataRepo metadataRepoCreate = MetadataRepo.create(typefaceBuildTypeface, byteBufferMmap);
                            Trace.endSection();
                            synchronized (this.mLock) {
                                try {
                                    EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback = this.mCallback;
                                    if (metadataRepoLoaderCallback != null) {
                                        metadataRepoLoaderCallback.onLoaded(metadataRepoCreate);
                                    }
                                } finally {
                                }
                            }
                            cleanUp();
                        } catch (Throwable th) {
                            TraceCompat.endSection();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        synchronized (this.mLock) {
                            try {
                                EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback2 = this.mCallback;
                                if (metadataRepoLoaderCallback2 != null) {
                                    metadataRepoLoaderCallback2.onFailed(th2);
                                }
                                cleanUp();
                            } finally {
                            }
                        }
                    }
                } finally {
                }
            }
        }

        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoader
        @RequiresApi(19)
        public void load(@NonNull EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback) {
            Preconditions.checkNotNull(metadataRepoLoaderCallback, "LoaderCallback cannot be null");
            synchronized (this.mLock) {
                this.mCallback = metadataRepoLoaderCallback;
            }
            loadInternal();
        }

        @RequiresApi(19)
        public void loadInternal() {
            synchronized (this.mLock) {
                try {
                    if (this.mCallback == null) {
                        return;
                    }
                    if (this.mExecutor == null) {
                        ThreadPoolExecutor threadPoolExecutorCreateBackgroundPriorityExecutor = ConcurrencyHelpers.createBackgroundPriorityExecutor("emojiCompat");
                        this.mMyThreadPoolExecutor = threadPoolExecutorCreateBackgroundPriorityExecutor;
                        this.mExecutor = threadPoolExecutorCreateBackgroundPriorityExecutor;
                    }
                    this.mExecutor.execute(new Runnable() { // from class: androidx.emoji2.text.FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.createMetadata();
                        }
                    });
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @WorkerThread
        public final FontsContractCompat.FontInfo retrieveFontInfo() {
            try {
                FontsContractCompat.FontFamilyResult fontFamilyResultFetchFonts = this.mFontProviderHelper.fetchFonts(this.mContext, this.mRequest);
                if (fontFamilyResultFetchFonts.mStatusCode != 0) {
                    throw new RuntimeException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(new StringBuilder("fetchFonts failed ("), fontFamilyResultFetchFonts.mStatusCode, ")"));
                }
                FontsContractCompat.FontInfo[] fonts = fontFamilyResultFetchFonts.getFonts();
                if (fonts == null || fonts.length == 0) {
                    throw new RuntimeException("fetchFonts failed (empty result)");
                }
                return fonts[0];
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException("provider not found", e);
            }
        }

        @RequiresApi(19)
        @WorkerThread
        public final void scheduleRetry(Uri uri, long j) {
            synchronized (this.mLock) {
                try {
                    Handler handlerMainHandlerAsync = this.mMainHandler;
                    if (handlerMainHandlerAsync == null) {
                        handlerMainHandlerAsync = ConcurrencyHelpers.mainHandlerAsync();
                        this.mMainHandler = handlerMainHandlerAsync;
                    }
                    if (this.mObserver == null) {
                        ContentObserver contentObserver = new ContentObserver(handlerMainHandlerAsync) { // from class: androidx.emoji2.text.FontRequestEmojiCompatConfig.FontRequestMetadataLoader.1
                            @Override // android.database.ContentObserver
                            public void onChange(boolean z, Uri uri2) {
                                FontRequestMetadataLoader.this.loadInternal();
                            }
                        };
                        this.mObserver = contentObserver;
                        this.mFontProviderHelper.registerObserver(this.mContext, uri, contentObserver);
                    }
                    if (this.mMainHandlerLoadCallback == null) {
                        this.mMainHandlerLoadCallback = new Runnable() { // from class: androidx.emoji2.text.FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.loadInternal();
                            }
                        };
                    }
                    handlerMainHandlerAsync.postDelayed(this.mMainHandlerLoadCallback, j);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void setExecutor(@NonNull Executor executor) {
            synchronized (this.mLock) {
                this.mExecutor = executor;
            }
        }

        public void setRetryPolicy(@Nullable RetryPolicy retryPolicy) {
            synchronized (this.mLock) {
                this.mRetryPolicy = retryPolicy;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class RetryPolicy {
        public abstract long getRetryDelay();
    }

    public FontRequestEmojiCompatConfig(@NonNull Context context, @NonNull FontRequest fontRequest) {
        super(new FontRequestMetadataLoader(context, fontRequest, DEFAULT_FONTS_CONTRACT));
    }

    @NonNull
    @Deprecated
    public FontRequestEmojiCompatConfig setHandler(@Nullable Handler handler) {
        if (handler == null) {
            return this;
        }
        setLoadingExecutor(new ConcurrencyHelpers$$ExternalSyntheticLambda0(handler));
        return this;
    }

    @NonNull
    public FontRequestEmojiCompatConfig setLoadingExecutor(@NonNull Executor executor) {
        ((FontRequestMetadataLoader) this.mMetadataLoader).setExecutor(executor);
        return this;
    }

    @NonNull
    public FontRequestEmojiCompatConfig setRetryPolicy(@Nullable RetryPolicy retryPolicy) {
        ((FontRequestMetadataLoader) this.mMetadataLoader).setRetryPolicy(retryPolicy);
        return this;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public FontRequestEmojiCompatConfig(@NonNull Context context, @NonNull FontRequest fontRequest, @NonNull FontProviderHelper fontProviderHelper) {
        super(new FontRequestMetadataLoader(context, fontRequest, fontProviderHelper));
    }
}
