package androidx.core.content;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.IntentSanitizer;
import androidx.core.util.Consumer;
import androidx.core.util.Predicate;
import androidx.core.util.Predicate$$ExternalSyntheticLambda4;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IntentSanitizer {
    public static final String TAG = "IntentSanitizer";
    public boolean mAllowAnyComponent;
    public boolean mAllowClipDataText;
    public boolean mAllowIdentifier;
    public boolean mAllowSelector;
    public boolean mAllowSourceBounds;
    public Predicate<String> mAllowedActions;
    public Predicate<String> mAllowedCategories;
    public Predicate<ClipData> mAllowedClipData;
    public Predicate<Uri> mAllowedClipDataUri;
    public Predicate<ComponentName> mAllowedComponents;
    public Predicate<Uri> mAllowedData;
    public Map<String, Predicate<Object>> mAllowedExtras;
    public int mAllowedFlags;
    public Predicate<String> mAllowedPackages;
    public Predicate<String> mAllowedTypes;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static class Api29Impl {
        public static String getIdentifier(Intent intent) {
            return intent.getIdentifier();
        }

        public static Intent setIdentifier(Intent intent, String str) {
            return intent.setIdentifier(str);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(31)
    public static class Api31Impl {
        public static void checkOtherMembers(int i, ClipData.Item item, Consumer<String> consumer) {
            if (item.getHtmlText() == null && item.getIntent() == null && item.getTextLinks() == null) {
                return;
            }
            consumer.accept("ClipData item at position " + i + " contains htmlText, textLinks or intent: " + item);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public static final int HISTORY_STACK_FLAGS = 2112614400;
        public static final int RECEIVER_FLAGS = 2015363072;
        public boolean mAllowAnyComponent;
        public boolean mAllowIdentifier;
        public boolean mAllowSelector;
        public boolean mAllowSomeComponents;
        public boolean mAllowSourceBounds;
        public int mAllowedFlags;
        public Predicate<String> mAllowedActions = new IntentSanitizer$Builder$$ExternalSyntheticLambda1();
        public Predicate<Uri> mAllowedData = new IntentSanitizer$Builder$$ExternalSyntheticLambda2();
        public Predicate<String> mAllowedTypes = new IntentSanitizer$Builder$$ExternalSyntheticLambda1();
        public Predicate<String> mAllowedCategories = new IntentSanitizer$Builder$$ExternalSyntheticLambda1();
        public Predicate<String> mAllowedPackages = new IntentSanitizer$Builder$$ExternalSyntheticLambda1();
        public Predicate<ComponentName> mAllowedComponents = new IntentSanitizer$Builder$$ExternalSyntheticLambda3();
        public Map<String, Predicate<Object>> mAllowedExtras = new HashMap();
        public boolean mAllowClipDataText = false;
        public Predicate<Uri> mAllowedClipDataUri = new IntentSanitizer$Builder$$ExternalSyntheticLambda2();
        public Predicate<ClipData> mAllowedClipData = new IntentSanitizer$Builder$$ExternalSyntheticLambda4();

        /* JADX INFO: renamed from: $r8$lambda$-r7FER1-hgQtqhYpYZt1Zo4CVuk, reason: not valid java name */
        public static /* synthetic */ boolean m36$r8$lambda$r7FER1hgQtqhYpYZt1Zo4CVuk(ComponentName componentName) {
            return false;
        }

        /* JADX INFO: renamed from: $r8$lambda$37IFovMuSvMwnIfOIV-zxEpNmGs, reason: not valid java name */
        public static /* synthetic */ boolean m37$r8$lambda$37IFovMuSvMwnIfOIVzxEpNmGs(String str) {
            return false;
        }

        public static /* synthetic */ boolean $r8$lambda$D0wyo8AyB0x1f3B6dMcGsvyn1f0(Class cls, Predicate predicate, Object obj) {
            return cls.isInstance(obj) && predicate.test(cls.cast(obj));
        }

        public static /* synthetic */ boolean $r8$lambda$EY5hb1nm591YGL4CC6W6tJ4Cm5s(String str) {
            return false;
        }

        public static /* synthetic */ boolean $r8$lambda$MJeuRW82L9CDj9CGn6f1MYXQMTs(Object obj) {
            return false;
        }

        /* JADX INFO: renamed from: $r8$lambda$O-kTatAuM_XvP8hioAuRF9rjuVI, reason: not valid java name */
        public static /* synthetic */ boolean m39$r8$lambda$OkTatAuM_XvP8hioAuRF9rjuVI(ComponentName componentName) {
            return true;
        }

        /* JADX INFO: renamed from: $r8$lambda$SO-nfSh_sUSikq4sO5Ka-H8atAw, reason: not valid java name */
        public static /* synthetic */ boolean m40$r8$lambda$SOnfSh_sUSikq4sO5KaH8atAw(String str) {
            return false;
        }

        public static /* synthetic */ boolean $r8$lambda$TEEjoLL2e6HTc89VGQsOJmFltuI(Uri uri) {
            return false;
        }

        public static /* synthetic */ boolean $r8$lambda$fr9sw07xL1JZ1PVu7ztjvCR23U4(Uri uri) {
            return false;
        }

        /* JADX INFO: renamed from: $r8$lambda$slA15akG2LFQEqEloDri-OF6AMw, reason: not valid java name */
        public static /* synthetic */ boolean m42$r8$lambda$slA15akG2LFQEqEloDriOF6AMw(Object obj) {
            return true;
        }

        public static /* synthetic */ boolean $r8$lambda$vnfrxbgyX9I6VhGn15JjXEhasvY(String str) {
            return false;
        }

        public static /* synthetic */ boolean $r8$lambda$zq9pLGrLLmWWUPLAfSSS0LeH8ds(ClipData clipData) {
            return false;
        }

        public static /* synthetic */ boolean lambda$allowAnyComponent$10(ComponentName componentName) {
            return true;
        }

        public static /* synthetic */ boolean lambda$allowExtra$12(Object obj) {
            return true;
        }

        public static /* synthetic */ boolean lambda$allowExtra$14(Object obj) {
            return false;
        }

        public static /* synthetic */ boolean lambda$new$0(String str) {
            return false;
        }

        public static /* synthetic */ boolean lambda$new$1(Uri uri) {
            return false;
        }

        public static /* synthetic */ boolean lambda$new$2(String str) {
            return false;
        }

        public static /* synthetic */ boolean lambda$new$3(String str) {
            return false;
        }

        public static /* synthetic */ boolean lambda$new$4(String str) {
            return false;
        }

        public static /* synthetic */ boolean lambda$new$5(ComponentName componentName) {
            return false;
        }

        public static /* synthetic */ boolean lambda$new$6(Uri uri) {
            return false;
        }

        public static /* synthetic */ boolean lambda$new$7(ClipData clipData) {
            return false;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowAction(@NonNull Predicate<String> predicate) {
            predicate.getClass();
            this.mAllowedActions = this.mAllowedActions.or(predicate);
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowAnyComponent() {
            this.mAllowAnyComponent = true;
            this.mAllowedComponents = new IntentSanitizer$Builder$$ExternalSyntheticLambda11();
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowCategory(@NonNull Predicate<String> predicate) {
            predicate.getClass();
            this.mAllowedCategories = this.mAllowedCategories.or(predicate);
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowClipData(@NonNull Predicate<ClipData> predicate) {
            predicate.getClass();
            this.mAllowedClipData = this.mAllowedClipData.or(predicate);
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowClipDataText() {
            this.mAllowClipDataText = true;
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowClipDataUri(@NonNull Predicate<Uri> predicate) {
            predicate.getClass();
            this.mAllowedClipDataUri = this.mAllowedClipDataUri.or(predicate);
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowClipDataUriWithAuthority(@NonNull final String str) {
            str.getClass();
            allowClipDataUri(new Predicate() { // from class: androidx.core.content.IntentSanitizer$Builder$$ExternalSyntheticLambda12
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate.CC.$default$and(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public Predicate negate() {
                    return new Predicate$$ExternalSyntheticLambda4(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate.CC.$default$or(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return str.equals(((Uri) obj).getAuthority());
                }
            });
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowComponent(@NonNull final ComponentName componentName) {
            componentName.getClass();
            allowComponent(new Predicate() { // from class: androidx.core.content.IntentSanitizer$Builder$$ExternalSyntheticLambda10
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate.CC.$default$and(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public Predicate negate() {
                    return new Predicate$$ExternalSyntheticLambda4(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate.CC.$default$or(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return componentName.equals((ComponentName) obj);
                }
            });
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowComponentWithPackage(@NonNull final String str) {
            str.getClass();
            allowComponent(new Predicate() { // from class: androidx.core.content.IntentSanitizer$Builder$$ExternalSyntheticLambda7
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate.CC.$default$and(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public Predicate negate() {
                    return new Predicate$$ExternalSyntheticLambda4(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate.CC.$default$or(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return str.equals(((ComponentName) obj).getPackageName());
                }
            });
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowData(@NonNull Predicate<Uri> predicate) {
            predicate.getClass();
            this.mAllowedData = this.mAllowedData.or(predicate);
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowDataWithAuthority(@NonNull final String str) {
            str.getClass();
            allowData(new Predicate() { // from class: androidx.core.content.IntentSanitizer$Builder$$ExternalSyntheticLambda8
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate.CC.$default$and(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public Predicate negate() {
                    return new Predicate$$ExternalSyntheticLambda4(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate.CC.$default$or(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return str.equals(((Uri) obj).getAuthority());
                }
            });
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowExtra(@NonNull String str, @NonNull Predicate<Object> predicate) {
            str.getClass();
            predicate.getClass();
            Predicate<Object> intentSanitizer$Builder$$ExternalSyntheticLambda13 = this.mAllowedExtras.get(str);
            if (intentSanitizer$Builder$$ExternalSyntheticLambda13 == null) {
                intentSanitizer$Builder$$ExternalSyntheticLambda13 = new IntentSanitizer$Builder$$ExternalSyntheticLambda13();
            }
            this.mAllowedExtras.put(str, intentSanitizer$Builder$$ExternalSyntheticLambda13.or(predicate));
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowExtraOutput(@NonNull final String str) {
            allowExtra("output", Uri.class, new Predicate() { // from class: androidx.core.content.IntentSanitizer$Builder$$ExternalSyntheticLambda0
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate.CC.$default$and(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public Predicate negate() {
                    return new Predicate$$ExternalSyntheticLambda4(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate.CC.$default$or(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return str.equals(((Uri) obj).getAuthority());
                }
            });
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowExtraStream(@NonNull Predicate<Uri> predicate) {
            allowExtra("android.intent.extra.STREAM", Uri.class, predicate);
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowExtraStreamUriWithAuthority(@NonNull final String str) {
            str.getClass();
            allowExtra("android.intent.extra.STREAM", Uri.class, new Predicate() { // from class: androidx.core.content.IntentSanitizer$Builder$$ExternalSyntheticLambda14
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate.CC.$default$and(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public Predicate negate() {
                    return new Predicate$$ExternalSyntheticLambda4(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate.CC.$default$or(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return str.equals(((Uri) obj).getAuthority());
                }
            });
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowFlags(int i) {
            this.mAllowedFlags = i | this.mAllowedFlags;
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowHistoryStackFlags() {
            this.mAllowedFlags |= HISTORY_STACK_FLAGS;
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowIdentifier() {
            this.mAllowIdentifier = true;
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowPackage(@NonNull Predicate<String> predicate) {
            predicate.getClass();
            this.mAllowedPackages = this.mAllowedPackages.or(predicate);
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowReceiverFlags() {
            this.mAllowedFlags |= RECEIVER_FLAGS;
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowSelector() {
            this.mAllowSelector = true;
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowSourceBounds() {
            this.mAllowSourceBounds = true;
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowType(@NonNull Predicate<String> predicate) {
            predicate.getClass();
            this.mAllowedTypes = this.mAllowedTypes.or(predicate);
            return this;
        }

        @NonNull
        public IntentSanitizer build() {
            boolean z = this.mAllowAnyComponent;
            if ((z && this.mAllowSomeComponents) || (!z && !this.mAllowSomeComponents)) {
                throw new SecurityException("You must call either allowAnyComponent or one or more of the allowComponent methods; but not both.");
            }
            IntentSanitizer intentSanitizer = new IntentSanitizer();
            intentSanitizer.mAllowedFlags = this.mAllowedFlags;
            intentSanitizer.mAllowedActions = this.mAllowedActions;
            intentSanitizer.mAllowedData = this.mAllowedData;
            intentSanitizer.mAllowedTypes = this.mAllowedTypes;
            intentSanitizer.mAllowedCategories = this.mAllowedCategories;
            intentSanitizer.mAllowedPackages = this.mAllowedPackages;
            intentSanitizer.mAllowAnyComponent = z;
            intentSanitizer.mAllowedComponents = this.mAllowedComponents;
            intentSanitizer.mAllowedExtras = this.mAllowedExtras;
            intentSanitizer.mAllowClipDataText = this.mAllowClipDataText;
            intentSanitizer.mAllowedClipDataUri = this.mAllowedClipDataUri;
            intentSanitizer.mAllowedClipData = this.mAllowedClipData;
            intentSanitizer.mAllowIdentifier = this.mAllowIdentifier;
            intentSanitizer.mAllowSelector = this.mAllowSelector;
            intentSanitizer.mAllowSourceBounds = this.mAllowSourceBounds;
            return intentSanitizer;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowExtraOutput(@NonNull Predicate<Uri> predicate) {
            allowExtra("output", Uri.class, predicate);
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowAction(@NonNull String str) {
            str.getClass();
            allowAction(new IntentSanitizer$Builder$$ExternalSyntheticLambda6(str));
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowCategory(@NonNull String str) {
            str.getClass();
            allowCategory(new IntentSanitizer$Builder$$ExternalSyntheticLambda6(str));
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowComponent(@NonNull Predicate<ComponentName> predicate) {
            predicate.getClass();
            this.mAllowSomeComponents = true;
            this.mAllowedComponents = this.mAllowedComponents.or(predicate);
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowPackage(@NonNull String str) {
            str.getClass();
            allowPackage(new IntentSanitizer$Builder$$ExternalSyntheticLambda6(str));
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowType(@NonNull String str) {
            str.getClass();
            allowType(new IntentSanitizer$Builder$$ExternalSyntheticLambda6(str));
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public <T> Builder allowExtra(@NonNull String str, @NonNull final Class<T> cls, @NonNull final Predicate<T> predicate) {
            str.getClass();
            cls.getClass();
            predicate.getClass();
            allowExtra(str, new Predicate() { // from class: androidx.core.content.IntentSanitizer$Builder$$ExternalSyntheticLambda9
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate2) {
                    return Predicate.CC.$default$and(this, predicate2);
                }

                @Override // androidx.core.util.Predicate
                public Predicate negate() {
                    return new Predicate$$ExternalSyntheticLambda4(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate2) {
                    return Predicate.CC.$default$or(this, predicate2);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return IntentSanitizer.Builder.$r8$lambda$D0wyo8AyB0x1f3B6dMcGsvyn1f0(cls, predicate, obj);
                }
            });
            return this;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public Builder allowExtra(@NonNull String str, @NonNull Class<?> cls) {
            allowExtra(str, cls, new IntentSanitizer$Builder$$ExternalSyntheticLambda5());
            return this;
        }
    }

    public static /* synthetic */ void $r8$lambda$4RDfDFLlanQyeVRavNeUGzyeHS4(String str) {
        throw new SecurityException(str);
    }

    public IntentSanitizer() {
    }

    public static void checkOtherMembers(int i, ClipData.Item item, Consumer<String> consumer) {
        if (item.getHtmlText() == null && item.getIntent() == null) {
            return;
        }
        consumer.accept("ClipData item at position " + i + " contains htmlText, textLinks or intent: " + item);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void sanitizeClipData(@androidx.annotation.NonNull android.content.Intent r7, android.content.Intent r8, androidx.core.util.Predicate<android.content.ClipData> r9, boolean r10, androidx.core.util.Predicate<android.net.Uri> r11, androidx.core.util.Consumer<java.lang.String> r12) {
        /*
            android.content.ClipData r7 = r7.getClipData()
            if (r7 != 0) goto L8
            goto Lc0
        L8:
            if (r9 == 0) goto L14
            boolean r9 = r9.test(r7)
            if (r9 == 0) goto L14
            r8.setClipData(r7)
            return
        L14:
            r9 = 0
            r0 = 0
            r1 = r9
        L17:
            int r2 = r7.getItemCount()
            if (r0 >= r2) goto Lbb
            android.content.ClipData$Item r2 = r7.getItemAt(r0)
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 31
            if (r3 < r4) goto L2b
            androidx.core.content.IntentSanitizer.Api31Impl.checkOtherMembers(r0, r2, r12)
            goto L2e
        L2b:
            checkOtherMembers(r0, r2, r12)
        L2e:
            if (r10 == 0) goto L35
            java.lang.CharSequence r3 = r2.getText()
            goto L52
        L35:
            java.lang.CharSequence r3 = r2.getText()
            if (r3 == 0) goto L51
            java.lang.String r3 = "Item text cannot contain value. Item position: "
            java.lang.String r4 = ". Text: "
            java.lang.StringBuilder r3 = android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m(r3, r0, r4)
            java.lang.CharSequence r4 = r2.getText()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r12.accept(r3)
        L51:
            r3 = r9
        L52:
            java.lang.String r4 = ". URI: "
            java.lang.String r5 = "Item URI is not allowed. Item position: "
            if (r11 != 0) goto L71
            android.net.Uri r6 = r2.getUri()
            if (r6 == 0) goto L94
            java.lang.StringBuilder r4 = android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m(r5, r0, r4)
            android.net.Uri r2 = r2.getUri()
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r12.accept(r2)
            goto L94
        L71:
            android.net.Uri r6 = r2.getUri()
            if (r6 == 0) goto L96
            android.net.Uri r6 = r2.getUri()
            boolean r6 = r11.test(r6)
            if (r6 == 0) goto L82
            goto L96
        L82:
            java.lang.StringBuilder r4 = android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m(r5, r0, r4)
            android.net.Uri r2 = r2.getUri()
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r12.accept(r2)
        L94:
            r2 = r9
            goto L9a
        L96:
            android.net.Uri r2 = r2.getUri()
        L9a:
            if (r3 != 0) goto L9e
            if (r2 == 0) goto Lb7
        L9e:
            if (r1 != 0) goto Laf
            android.content.ClipData r1 = new android.content.ClipData
            android.content.ClipDescription r4 = r7.getDescription()
            android.content.ClipData$Item r5 = new android.content.ClipData$Item
            r5.<init>(r3, r9, r2)
            r1.<init>(r4, r5)
            goto Lb7
        Laf:
            android.content.ClipData$Item r4 = new android.content.ClipData$Item
            r4.<init>(r3, r9, r2)
            r1.addItem(r4)
        Lb7:
            int r0 = r0 + 1
            goto L17
        Lbb:
            if (r1 == 0) goto Lc0
            r8.setClipData(r1)
        Lc0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.IntentSanitizer.sanitizeClipData(android.content.Intent, android.content.Intent, androidx.core.util.Predicate, boolean, androidx.core.util.Predicate, androidx.core.util.Consumer):void");
    }

    public final void putExtra(Intent intent, String str, Object obj) {
        if (obj == null) {
            intent.getExtras().putString(str, null);
            return;
        }
        if (obj instanceof Parcelable) {
            intent.putExtra(str, (Parcelable) obj);
            return;
        }
        if (obj instanceof Parcelable[]) {
            intent.putExtra(str, (Parcelable[]) obj);
        } else if (obj instanceof Serializable) {
            intent.putExtra(str, (Serializable) obj);
        } else {
            throw new IllegalArgumentException("Unsupported type " + obj.getClass());
        }
    }

    @NonNull
    public Intent sanitize(@NonNull Intent intent, @NonNull Consumer<String> consumer) {
        Intent intent2 = new Intent();
        ComponentName component = intent.getComponent();
        if ((this.mAllowAnyComponent && component == null) || this.mAllowedComponents.test(component)) {
            intent2.setComponent(component);
        } else {
            consumer.accept("Component is not allowed: " + component);
            intent2.setComponent(new ComponentName(ResourceDrawableDecoder.ANDROID_PACKAGE_NAME, "java.lang.Void"));
        }
        String str = intent.getPackage();
        if (str == null || this.mAllowedPackages.test(str)) {
            intent2.setPackage(str);
        } else {
            consumer.accept("Package is not allowed: ".concat(str));
        }
        int flags = this.mAllowedFlags | intent.getFlags();
        int i = this.mAllowedFlags;
        if (flags == i) {
            intent2.setFlags(intent.getFlags());
        } else {
            intent2.setFlags(intent.getFlags() & i);
            consumer.accept("The intent contains flags that are not allowed: 0x" + Integer.toHexString(intent.getFlags() & (~this.mAllowedFlags)));
        }
        String action = intent.getAction();
        if (action == null || this.mAllowedActions.test(action)) {
            intent2.setAction(action);
        } else {
            consumer.accept("Action is not allowed: ".concat(action));
        }
        Uri data = intent.getData();
        if (data == null || this.mAllowedData.test(data)) {
            intent2.setData(data);
        } else {
            consumer.accept("Data is not allowed: " + data);
        }
        String type = intent.getType();
        if (type == null || this.mAllowedTypes.test(type)) {
            intent2.setDataAndType(intent2.getData(), type);
        } else {
            consumer.accept("Type is not allowed: ".concat(type));
        }
        Set<String> categories = intent.getCategories();
        if (categories != null) {
            for (String str2 : categories) {
                if (this.mAllowedCategories.test(str2)) {
                    intent2.addCategory(str2);
                } else {
                    consumer.accept("Category is not allowed: " + str2);
                }
            }
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String str3 : extras.keySet()) {
                if (str3.equals("android.intent.extra.STREAM") && (this.mAllowedFlags & 1) == 0) {
                    consumer.accept("Allowing Extra Stream requires also allowing at least  FLAG_GRANT_READ_URI_PERMISSION Flag.");
                } else if (!str3.equals("output") || ((~this.mAllowedFlags) & 3) == 0) {
                    Object obj = extras.get(str3);
                    Predicate<Object> predicate = this.mAllowedExtras.get(str3);
                    if (predicate == null || !predicate.test(obj)) {
                        consumer.accept("Extra is not allowed. Key: " + str3 + ". Value: " + obj);
                    } else {
                        putExtra(intent2, str3, obj);
                    }
                } else {
                    consumer.accept("Allowing Extra Output requires also allowing FLAG_GRANT_READ_URI_PERMISSION and FLAG_GRANT_WRITE_URI_PERMISSION Flags.");
                }
            }
        }
        sanitizeClipData(intent, intent2, this.mAllowedClipData, this.mAllowClipDataText, this.mAllowedClipDataUri, consumer);
        if (Build.VERSION.SDK_INT >= 29) {
            if (this.mAllowIdentifier) {
                Api29Impl.setIdentifier(intent2, Api29Impl.getIdentifier(intent));
            } else if (Api29Impl.getIdentifier(intent) != null) {
                consumer.accept("Identifier is not allowed: " + Api29Impl.getIdentifier(intent));
            }
        }
        if (this.mAllowSelector) {
            intent2.setSelector(intent.getSelector());
        } else if (intent.getSelector() != null) {
            consumer.accept("Selector is not allowed: " + intent.getSelector());
        }
        if (this.mAllowSourceBounds) {
            intent2.setSourceBounds(intent.getSourceBounds());
            return intent2;
        }
        if (intent.getSourceBounds() != null) {
            consumer.accept("SourceBounds is not allowed: " + intent.getSourceBounds());
        }
        return intent2;
    }

    @NonNull
    public Intent sanitizeByFiltering(@NonNull Intent intent) {
        return sanitize(intent, new IntentSanitizer$$ExternalSyntheticLambda1());
    }

    @NonNull
    public Intent sanitizeByThrowing(@NonNull Intent intent) {
        return sanitize(intent, new IntentSanitizer$$ExternalSyntheticLambda0());
    }

    public IntentSanitizer(AnonymousClass1 anonymousClass1) {
    }

    /* JADX INFO: renamed from: $r8$lambda$S_NlW1ql-j7rOu1sBlB1_SGrq20, reason: not valid java name */
    public static /* synthetic */ void m35$r8$lambda$S_NlW1qlj7rOu1sBlB1_SGrq20(String str) {
    }

    public static /* synthetic */ void lambda$sanitizeByFiltering$0(String str) {
    }
}
