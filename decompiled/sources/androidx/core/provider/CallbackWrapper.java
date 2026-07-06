package androidx.core.provider;

import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.core.provider.FontRequestWorker;
import androidx.core.provider.FontsContractCompat;
import androidx.core.provider.RequestExecutor;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class CallbackWrapper {

    @NonNull
    public final FontsContractCompat.FontRequestCallback mCallback;

    @NonNull
    public final Executor mExecutor;

    public CallbackWrapper(@NonNull FontsContractCompat.FontRequestCallback fontRequestCallback, @NonNull Executor executor) {
        this.mCallback = fontRequestCallback;
        this.mExecutor = executor;
    }

    public final void onTypefaceRequestFailed(final int i) {
        final FontsContractCompat.FontRequestCallback fontRequestCallback = this.mCallback;
        this.mExecutor.execute(new Runnable() { // from class: androidx.core.provider.CallbackWrapper.2
            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRequestFailed(i);
            }
        });
    }

    public void onTypefaceResult(@NonNull FontRequestWorker.TypefaceResult typefaceResult) {
        if (typefaceResult.isSuccess()) {
            onTypefaceRetrieved(typefaceResult.mTypeface);
        } else {
            onTypefaceRequestFailed(typefaceResult.mResult);
        }
    }

    public final void onTypefaceRetrieved(@NonNull final Typeface typeface) {
        final FontsContractCompat.FontRequestCallback fontRequestCallback = this.mCallback;
        this.mExecutor.execute(new Runnable() { // from class: androidx.core.provider.CallbackWrapper.1
            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRetrieved(typeface);
            }
        });
    }

    public CallbackWrapper(@NonNull FontsContractCompat.FontRequestCallback fontRequestCallback) {
        this(fontRequestCallback, new RequestExecutor.HandlerExecutor(CalleeHandler.create()));
    }
}
