package androidx.core.view.contentcapture;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewStructureCompat;
import androidx.core.view.ViewStructureCompat$$ExternalSyntheticApiModelOutline0;
import androidx.core.view.autofill.AutofillIdCompat;
import j$.util.Objects;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ContentCaptureSessionCompat {
    public static final String KEY_VIEW_TREE_APPEARED = "TREAT_AS_VIEW_TREE_APPEARED";
    public static final String KEY_VIEW_TREE_APPEARING = "TREAT_AS_VIEW_TREE_APPEARING";
    public final View mView;
    public final Object mWrappedObj;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public static class Api23Impl {
        public static Bundle getExtras(ViewStructure viewStructure) {
            return viewStructure.getExtras();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static class Api29Impl {
        public static AutofillId newAutofillId(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j) {
            return contentCaptureSession.newAutofillId(autofillId, j);
        }

        public static ViewStructure newViewStructure(ContentCaptureSession contentCaptureSession, View view) {
            return contentCaptureSession.newViewStructure(view);
        }

        public static ViewStructure newVirtualViewStructure(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j) {
            return contentCaptureSession.newVirtualViewStructure(autofillId, j);
        }

        public static void notifyViewAppeared(ContentCaptureSession contentCaptureSession, ViewStructure viewStructure) {
            contentCaptureSession.notifyViewAppeared(viewStructure);
        }

        public static void notifyViewTextChanged(ContentCaptureSession contentCaptureSession, AutofillId autofillId, CharSequence charSequence) {
            contentCaptureSession.notifyViewTextChanged(autofillId, charSequence);
        }

        public static void notifyViewsDisappeared(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long[] jArr) {
            contentCaptureSession.notifyViewsDisappeared(autofillId, jArr);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(34)
    public static class Api34Impl {
        public static void notifyViewsAppeared(ContentCaptureSession contentCaptureSession, List<ViewStructure> list) {
            contentCaptureSession.notifyViewsAppeared(list);
        }
    }

    @RequiresApi(29)
    public ContentCaptureSessionCompat(@NonNull ContentCaptureSession contentCaptureSession, @NonNull View view) {
        this.mWrappedObj = contentCaptureSession;
        this.mView = view;
    }

    @NonNull
    @RequiresApi(29)
    public static ContentCaptureSessionCompat toContentCaptureSessionCompat(@NonNull ContentCaptureSession contentCaptureSession, @NonNull View view) {
        return new ContentCaptureSessionCompat(contentCaptureSession, view);
    }

    @Nullable
    public AutofillId newAutofillId(long j) {
        if (Build.VERSION.SDK_INT < 29) {
            return null;
        }
        ContentCaptureSession contentCaptureSessionM = ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj);
        AutofillIdCompat autofillId = ViewCompat.getAutofillId(this.mView);
        Objects.requireNonNull(autofillId);
        return contentCaptureSessionM.newAutofillId(autofillId.toAutofillId(), j);
    }

    @Nullable
    public ViewStructureCompat newVirtualViewStructure(@NonNull AutofillId autofillId, long j) {
        if (Build.VERSION.SDK_INT >= 29) {
            return new ViewStructureCompat(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).newVirtualViewStructure(autofillId, j));
        }
        return null;
    }

    public void notifyViewTextChanged(@NonNull AutofillId autofillId, @Nullable CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 29) {
            ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).notifyViewTextChanged(autofillId, charSequence);
        }
    }

    public void notifyViewsAppeared(@NonNull List<ViewStructure> list) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 34) {
            Api34Impl.notifyViewsAppeared(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), list);
            return;
        }
        if (i >= 29) {
            ViewStructure viewStructureNewViewStructure = ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).newViewStructure(this.mView);
            viewStructureNewViewStructure.getExtras().putBoolean(KEY_VIEW_TREE_APPEARING, true);
            ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).notifyViewAppeared(viewStructureNewViewStructure);
            for (int i2 = 0; i2 < list.size(); i2++) {
                ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).notifyViewAppeared(ViewStructureCompat$$ExternalSyntheticApiModelOutline0.m(list.get(i2)));
            }
            ViewStructure viewStructureNewViewStructure2 = ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).newViewStructure(this.mView);
            viewStructureNewViewStructure2.getExtras().putBoolean(KEY_VIEW_TREE_APPEARED, true);
            ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).notifyViewAppeared(viewStructureNewViewStructure2);
        }
    }

    public void notifyViewsDisappeared(@NonNull long[] jArr) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 34) {
            ContentCaptureSession contentCaptureSessionM = ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj);
            AutofillIdCompat autofillId = ViewCompat.getAutofillId(this.mView);
            Objects.requireNonNull(autofillId);
            contentCaptureSessionM.notifyViewsDisappeared(autofillId.toAutofillId(), jArr);
            return;
        }
        if (i >= 29) {
            ViewStructure viewStructureNewViewStructure = ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).newViewStructure(this.mView);
            viewStructureNewViewStructure.getExtras().putBoolean(KEY_VIEW_TREE_APPEARING, true);
            ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).notifyViewAppeared(viewStructureNewViewStructure);
            ContentCaptureSession contentCaptureSessionM2 = ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj);
            AutofillIdCompat autofillId2 = ViewCompat.getAutofillId(this.mView);
            Objects.requireNonNull(autofillId2);
            contentCaptureSessionM2.notifyViewsDisappeared(autofillId2.toAutofillId(), jArr);
            ViewStructure viewStructureNewViewStructure2 = ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).newViewStructure(this.mView);
            viewStructureNewViewStructure2.getExtras().putBoolean(KEY_VIEW_TREE_APPEARED, true);
            ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).notifyViewAppeared(viewStructureNewViewStructure2);
        }
    }

    @NonNull
    @RequiresApi(29)
    public ContentCaptureSession toContentCaptureSession() {
        return ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj);
    }
}
