package androidx.core.app;

import android.app.RemoteInput;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RemoteInput {
    public static final int EDIT_CHOICES_BEFORE_SENDING_AUTO = 0;
    public static final int EDIT_CHOICES_BEFORE_SENDING_DISABLED = 1;
    public static final int EDIT_CHOICES_BEFORE_SENDING_ENABLED = 2;
    public static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    public static final String EXTRA_RESULTS_SOURCE = "android.remoteinput.resultsSource";
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    public static final int SOURCE_CHOICE = 1;
    public static final int SOURCE_FREE_FORM_INPUT = 0;
    public final boolean mAllowFreeFormTextInput;
    public final Set<String> mAllowedDataTypes;
    public final CharSequence[] mChoices;
    public final int mEditChoicesBeforeSending;
    public final Bundle mExtras;
    public final CharSequence mLabel;
    public final String mResultKey;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(20)
    public static class Api20Impl {
        public static void addResultsToIntent(Object obj, Intent intent, Bundle bundle) {
            android.app.RemoteInput.addResultsToIntent((android.app.RemoteInput[]) obj, intent, bundle);
        }

        public static android.app.RemoteInput fromCompat(RemoteInput remoteInput) {
            Set<String> set;
            RemoteInput.Builder builderAddExtras = new RemoteInput.Builder(remoteInput.mResultKey).setLabel(remoteInput.mLabel).setChoices(remoteInput.mChoices).setAllowFreeFormInput(remoteInput.mAllowFreeFormTextInput).addExtras(remoteInput.mExtras);
            if (Build.VERSION.SDK_INT >= 26 && (set = remoteInput.mAllowedDataTypes) != null) {
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    Api26Impl.setAllowDataType(builderAddExtras, it.next(), true);
                }
            }
            if (Build.VERSION.SDK_INT >= 29) {
                Api29Impl.setEditChoicesBeforeSending(builderAddExtras, remoteInput.mEditChoicesBeforeSending);
            }
            return builderAddExtras.build();
        }

        public static RemoteInput fromPlatform(Object obj) {
            Set<String> allowedDataTypes;
            android.app.RemoteInput remoteInput = (android.app.RemoteInput) obj;
            Builder builder = new Builder(remoteInput.getResultKey());
            builder.mLabel = remoteInput.getLabel();
            builder.mChoices = remoteInput.getChoices();
            builder.mAllowFreeFormTextInput = remoteInput.getAllowFreeFormInput();
            builder.addExtras(remoteInput.getExtras());
            if (Build.VERSION.SDK_INT >= 26 && (allowedDataTypes = Api26Impl.getAllowedDataTypes(remoteInput)) != null) {
                Iterator<String> it = allowedDataTypes.iterator();
                while (it.hasNext()) {
                    builder.setAllowDataType(it.next(), true);
                }
            }
            if (Build.VERSION.SDK_INT >= 29) {
                builder.mEditChoicesBeforeSending = Api29Impl.getEditChoicesBeforeSending(remoteInput);
            }
            return builder.build();
        }

        public static Bundle getResultsFromIntent(Intent intent) {
            return android.app.RemoteInput.getResultsFromIntent(intent);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(26)
    public static class Api26Impl {
        public static void addDataResultToIntent(RemoteInput remoteInput, Intent intent, Map<String, Uri> map) {
            android.app.RemoteInput.addDataResultToIntent(Api20Impl.fromCompat(remoteInput), intent, map);
        }

        public static Set<String> getAllowedDataTypes(Object obj) {
            return ((android.app.RemoteInput) obj).getAllowedDataTypes();
        }

        public static Map<String, Uri> getDataResultsFromIntent(Intent intent, String str) {
            return android.app.RemoteInput.getDataResultsFromIntent(intent, str);
        }

        public static RemoteInput.Builder setAllowDataType(RemoteInput.Builder builder, String str, boolean z) {
            return builder.setAllowDataType(str, z);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(28)
    public static class Api28Impl {
        public static int getResultsSource(Intent intent) {
            return android.app.RemoteInput.getResultsSource(intent);
        }

        public static void setResultsSource(Intent intent, int i) {
            android.app.RemoteInput.setResultsSource(intent, i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static class Api29Impl {
        public static int getEditChoicesBeforeSending(Object obj) {
            return ((android.app.RemoteInput) obj).getEditChoicesBeforeSending();
        }

        public static RemoteInput.Builder setEditChoicesBeforeSending(RemoteInput.Builder builder, int i) {
            return builder.setEditChoicesBeforeSending(i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public CharSequence[] mChoices;
        public CharSequence mLabel;
        public final String mResultKey;
        public final Set<String> mAllowedDataTypes = new HashSet();
        public final Bundle mExtras = new Bundle();
        public boolean mAllowFreeFormTextInput = true;
        public int mEditChoicesBeforeSending = 0;

        public Builder(@NonNull String str) {
            if (str == null) {
                throw new IllegalArgumentException("Result key can't be null");
            }
            this.mResultKey = str;
        }

        @NonNull
        public Builder addExtras(@NonNull Bundle bundle) {
            if (bundle != null) {
                this.mExtras.putAll(bundle);
            }
            return this;
        }

        @NonNull
        public RemoteInput build() {
            return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mEditChoicesBeforeSending, this.mExtras, this.mAllowedDataTypes);
        }

        @NonNull
        public Bundle getExtras() {
            return this.mExtras;
        }

        @NonNull
        public Builder setAllowDataType(@NonNull String str, boolean z) {
            if (z) {
                this.mAllowedDataTypes.add(str);
                return this;
            }
            this.mAllowedDataTypes.remove(str);
            return this;
        }

        @NonNull
        public Builder setAllowFreeFormInput(boolean z) {
            this.mAllowFreeFormTextInput = z;
            return this;
        }

        @NonNull
        public Builder setChoices(@Nullable CharSequence[] charSequenceArr) {
            this.mChoices = charSequenceArr;
            return this;
        }

        @NonNull
        public Builder setEditChoicesBeforeSending(int i) {
            this.mEditChoicesBeforeSending = i;
            return this;
        }

        @NonNull
        public Builder setLabel(@Nullable CharSequence charSequence) {
            this.mLabel = charSequence;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface EditChoicesBeforeSending {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface Source {
    }

    public RemoteInput(String str, CharSequence charSequence, CharSequence[] charSequenceArr, boolean z, int i, Bundle bundle, Set<String> set) {
        this.mResultKey = str;
        this.mLabel = charSequence;
        this.mChoices = charSequenceArr;
        this.mAllowFreeFormTextInput = z;
        this.mEditChoicesBeforeSending = i;
        this.mExtras = bundle;
        this.mAllowedDataTypes = set;
        if (i == 2 && !z) {
            throw new IllegalArgumentException("setEditChoicesBeforeSending requires setAllowFreeFormInput");
        }
    }

    public static void addDataResultToIntent(@NonNull RemoteInput remoteInput, @NonNull Intent intent, @NonNull Map<String, Uri> map) {
        if (Build.VERSION.SDK_INT >= 26) {
            Api26Impl.addDataResultToIntent(remoteInput, intent, map);
            return;
        }
        Intent clipDataIntentFromIntent = getClipDataIntentFromIntent(intent);
        if (clipDataIntentFromIntent == null) {
            clipDataIntentFromIntent = new Intent();
        }
        for (Map.Entry<String, Uri> entry : map.entrySet()) {
            String key = entry.getKey();
            Uri value = entry.getValue();
            if (key != null) {
                Bundle bundleExtra = clipDataIntentFromIntent.getBundleExtra(getExtraResultsKeyForData(key));
                if (bundleExtra == null) {
                    bundleExtra = new Bundle();
                }
                bundleExtra.putString(remoteInput.mResultKey, value.toString());
                clipDataIntentFromIntent.putExtra(getExtraResultsKeyForData(key), bundleExtra);
            }
        }
        intent.setClipData(ClipData.newIntent(RESULTS_CLIP_LABEL, clipDataIntentFromIntent));
    }

    public static void addResultsToIntent(@NonNull RemoteInput[] remoteInputArr, @NonNull Intent intent, @NonNull Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 26) {
            android.app.RemoteInput.addResultsToIntent(fromCompat(remoteInputArr), intent, bundle);
            return;
        }
        Bundle resultsFromIntent = android.app.RemoteInput.getResultsFromIntent(intent);
        int resultsSource = getResultsSource(intent);
        if (resultsFromIntent != null) {
            resultsFromIntent.putAll(bundle);
            bundle = resultsFromIntent;
        }
        for (RemoteInput remoteInput : remoteInputArr) {
            Map<String, Uri> dataResultsFromIntent = getDataResultsFromIntent(intent, remoteInput.mResultKey);
            android.app.RemoteInput.addResultsToIntent(fromCompat(new RemoteInput[]{remoteInput}), intent, bundle);
            if (dataResultsFromIntent != null) {
                addDataResultToIntent(remoteInput, intent, dataResultsFromIntent);
            }
        }
        setResultsSource(intent, resultsSource);
    }

    @RequiresApi(20)
    public static android.app.RemoteInput[] fromCompat(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        android.app.RemoteInput[] remoteInputArr2 = new android.app.RemoteInput[remoteInputArr.length];
        for (int i = 0; i < remoteInputArr.length; i++) {
            remoteInputArr2[i] = Api20Impl.fromCompat(remoteInputArr[i]);
        }
        return remoteInputArr2;
    }

    @RequiresApi(20)
    public static RemoteInput fromPlatform(android.app.RemoteInput remoteInput) {
        return Api20Impl.fromPlatform(remoteInput);
    }

    public static Intent getClipDataIntentFromIntent(Intent intent) {
        ClipData clipData = intent.getClipData();
        if (clipData == null) {
            return null;
        }
        ClipDescription description = clipData.getDescription();
        if (description.hasMimeType("text/vnd.android.intent") && description.getLabel().toString().contentEquals(RESULTS_CLIP_LABEL)) {
            return clipData.getItemAt(0).getIntent();
        }
        return null;
    }

    @Nullable
    public static Map<String, Uri> getDataResultsFromIntent(@NonNull Intent intent, @NonNull String str) {
        String string;
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.getDataResultsFromIntent(intent, str);
        }
        Intent clipDataIntentFromIntent = getClipDataIntentFromIntent(intent);
        if (clipDataIntentFromIntent == null) {
            return null;
        }
        HashMap map = new HashMap();
        for (String str2 : clipDataIntentFromIntent.getExtras().keySet()) {
            if (str2.startsWith(EXTRA_DATA_TYPE_RESULTS_DATA)) {
                String strSubstring = str2.substring(39);
                if (!strSubstring.isEmpty() && (string = clipDataIntentFromIntent.getBundleExtra(str2).getString(str)) != null && !string.isEmpty()) {
                    map.put(strSubstring, Uri.parse(string));
                }
            }
        }
        if (map.isEmpty()) {
            return null;
        }
        return map;
    }

    public static String getExtraResultsKeyForData(String str) {
        return RemoteInput$$ExternalSyntheticOutline0.m(EXTRA_DATA_TYPE_RESULTS_DATA, str);
    }

    @Nullable
    public static Bundle getResultsFromIntent(@NonNull Intent intent) {
        return android.app.RemoteInput.getResultsFromIntent(intent);
    }

    public static int getResultsSource(@NonNull Intent intent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getResultsSource(intent);
        }
        Intent clipDataIntentFromIntent = getClipDataIntentFromIntent(intent);
        if (clipDataIntentFromIntent == null) {
            return 0;
        }
        return clipDataIntentFromIntent.getExtras().getInt(EXTRA_RESULTS_SOURCE, 0);
    }

    public static void setResultsSource(@NonNull Intent intent, int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            Api28Impl.setResultsSource(intent, i);
            return;
        }
        Intent clipDataIntentFromIntent = getClipDataIntentFromIntent(intent);
        if (clipDataIntentFromIntent == null) {
            clipDataIntentFromIntent = new Intent();
        }
        clipDataIntentFromIntent.putExtra(EXTRA_RESULTS_SOURCE, i);
        intent.setClipData(ClipData.newIntent(RESULTS_CLIP_LABEL, clipDataIntentFromIntent));
    }

    public boolean getAllowFreeFormInput() {
        return this.mAllowFreeFormTextInput;
    }

    @Nullable
    public Set<String> getAllowedDataTypes() {
        return this.mAllowedDataTypes;
    }

    @Nullable
    public CharSequence[] getChoices() {
        return this.mChoices;
    }

    public int getEditChoicesBeforeSending() {
        return this.mEditChoicesBeforeSending;
    }

    @NonNull
    public Bundle getExtras() {
        return this.mExtras;
    }

    @Nullable
    public CharSequence getLabel() {
        return this.mLabel;
    }

    @NonNull
    public String getResultKey() {
        return this.mResultKey;
    }

    public boolean isDataOnly() {
        Set<String> set;
        if (this.mAllowFreeFormTextInput) {
            return false;
        }
        CharSequence[] charSequenceArr = this.mChoices;
        return ((charSequenceArr != null && charSequenceArr.length != 0) || (set = this.mAllowedDataTypes) == null || set.isEmpty()) ? false : true;
    }

    @RequiresApi(20)
    public static android.app.RemoteInput fromCompat(RemoteInput remoteInput) {
        return Api20Impl.fromCompat(remoteInput);
    }
}
