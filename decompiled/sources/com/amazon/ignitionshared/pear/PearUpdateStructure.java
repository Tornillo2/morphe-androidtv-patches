package com.amazon.ignitionshared.pear;

import android.content.SharedPreferences;
import com.amazon.ignitionshared.pear.PearParameterUpdateMessage;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nPearUpdateStructure.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PearUpdateStructure.kt\ncom/amazon/ignitionshared/pear/PearUpdateStructure\n+ 2 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n+ 3 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,62:1\n41#2,12:63\n41#2,6:75\n47#2,6:82\n205#3:81\n*S KotlinDebug\n*F\n+ 1 PearUpdateStructure.kt\ncom/amazon/ignitionshared/pear/PearUpdateStructure\n*L\n42#1:63,12\n52#1:75,6\n52#1:82,6\n55#1:81\n*E\n"})
public final class PearUpdateStructure {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String RECOMMENDATION_PARAMETERS_SHARED_PREFERENCE_KEY = "RECOMMENDATION_PARAMETERS";

    @NotNull
    public final SharedPreferences sharedPreferences;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public PearUpdateStructure(@Named(Names.RECOMMENDATION_PARAMETERS) @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        this.sharedPreferences = sharedPreferences;
    }

    @Nullable
    public final Oauth20V1 getCredentials() {
        Credentials credentials;
        String string = this.sharedPreferences.getString(RECOMMENDATION_PARAMETERS_SHARED_PREFERENCE_KEY, null);
        if (string == null || (credentials = PearParameterUpdateMessage.Companion.of(string).update.recsV1.credentials) == null) {
            return null;
        }
        return credentials.oauth20V1;
    }

    @Nullable
    public final String getRawStoredUpdateMessage() {
        return this.sharedPreferences.getString(RECOMMENDATION_PARAMETERS_SHARED_PREFERENCE_KEY, null);
    }

    @Nullable
    public final PearParameterUpdateMessage getStoredUpdateMessage() {
        String rawStoredUpdateMessage = getRawStoredUpdateMessage();
        if (rawStoredUpdateMessage != null) {
            return PearParameterUpdateMessage.Companion.of(rawStoredUpdateMessage);
        }
        return null;
    }

    public final synchronized void tryUpdateRefreshToken(@NotNull String newRefreshToken) {
        Oauth20V1 oauth20V1;
        Oauth20V1 oauth20V12;
        try {
            Intrinsics.checkNotNullParameter(newRefreshToken, "newRefreshToken");
            String rawStoredUpdateMessage = getRawStoredUpdateMessage();
            if (rawStoredUpdateMessage != null) {
                PearParameterUpdateMessage.Companion companion = PearParameterUpdateMessage.Companion;
                PearParameterUpdateMessage pearParameterUpdateMessageOf = companion.of(rawStoredUpdateMessage);
                Credentials credentials = pearParameterUpdateMessageOf.update.recsV1.credentials;
                if (!Intrinsics.areEqual((credentials == null || (oauth20V12 = credentials.oauth20V1) == null) ? null : oauth20V12.refreshToken, newRefreshToken)) {
                    Credentials credentials2 = pearParameterUpdateMessageOf.update.recsV1.credentials;
                    if (credentials2 != null && (oauth20V1 = credentials2.oauth20V1) != null) {
                        oauth20V1.refreshToken = newRefreshToken;
                    }
                    SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
                    Json.Default r3 = Json.Default;
                    r3.getClass();
                    editorEdit.putString(RECOMMENDATION_PARAMETERS_SHARED_PREFERENCE_KEY, r3.encodeToString(companion.serializer(), pearParameterUpdateMessageOf));
                    editorEdit.apply();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void updateStoredPearMessage(@NotNull String newUpdateMessage) {
        Intrinsics.checkNotNullParameter(newUpdateMessage, "newUpdateMessage");
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putString(RECOMMENDATION_PARAMETERS_SHARED_PREFERENCE_KEY, newUpdateMessage);
        editorEdit.apply();
    }
}
