package com.amazon.ignitionshared.pear;

import android.util.Log;
import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import com.amazon.ignitionshared.recommendation.dispacher.RecommendationRequestDispatcher;
import com.amazon.livingroom.auth.AccessTokenProvider;
import com.amazon.livingroom.di.ApplicationScope;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nPearAccessTokenProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PearAccessTokenProvider.kt\ncom/amazon/ignitionshared/pear/PearAccessTokenProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,108:1\n1222#2,2:109\n1252#2,4:111\n*S KotlinDebug\n*F\n+ 1 PearAccessTokenProvider.kt\ncom/amazon/ignitionshared/pear/PearAccessTokenProvider\n*L\n31#1:109,2\n31#1:111,4\n*E\n"})
public final class PearAccessTokenProvider implements AccessTokenProvider {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final String TAG = "PearAccessTokenProvider";

    @Nullable
    public String accessToken;
    public long expiryTimeInMs;

    @NotNull
    public final PearUpdateStructure pearUpdateStructure;

    @NotNull
    public final PearUriBuilder pearUriBuilder;

    @NotNull
    public final RecommendationRequestDispatcher recommendationRequestDispatcher;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Serializable
    public static final class PearAuthenticationResponse {

        @NotNull
        public static final Companion Companion = new Companion();

        @NotNull
        public final String accessToken;
        public final long expiresIn;

        @NotNull
        public final String refreshToken;

        @NotNull
        public final String tokenType;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Companion {
            public Companion() {
            }

            @NotNull
            public final KSerializer<PearAuthenticationResponse> serializer() {
                return PearAccessTokenProvider$PearAuthenticationResponse$$serializer.INSTANCE;
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            }
        }

        public /* synthetic */ PearAuthenticationResponse(int i, String str, long j, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, PearAccessTokenProvider$PearAuthenticationResponse$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
            this.accessToken = str;
            this.expiresIn = j;
            this.refreshToken = str2;
            this.tokenType = str3;
        }

        public static /* synthetic */ PearAuthenticationResponse copy$default(PearAuthenticationResponse pearAuthenticationResponse, String str, long j, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = pearAuthenticationResponse.accessToken;
            }
            if ((i & 2) != 0) {
                j = pearAuthenticationResponse.expiresIn;
            }
            if ((i & 4) != 0) {
                str2 = pearAuthenticationResponse.refreshToken;
            }
            if ((i & 8) != 0) {
                str3 = pearAuthenticationResponse.tokenType;
            }
            return pearAuthenticationResponse.copy(str, j, str2, str3);
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$ignitionshared_release(PearAuthenticationResponse pearAuthenticationResponse, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
            compositeEncoder.encodeStringElement(serialDescriptor, 0, pearAuthenticationResponse.accessToken);
            compositeEncoder.encodeLongElement(serialDescriptor, 1, pearAuthenticationResponse.expiresIn);
            compositeEncoder.encodeStringElement(serialDescriptor, 2, pearAuthenticationResponse.refreshToken);
            compositeEncoder.encodeStringElement(serialDescriptor, 3, pearAuthenticationResponse.tokenType);
        }

        @NotNull
        public final String component1() {
            return this.accessToken;
        }

        public final long component2() {
            return this.expiresIn;
        }

        @NotNull
        public final String component3() {
            return this.refreshToken;
        }

        @NotNull
        public final String component4() {
            return this.tokenType;
        }

        @NotNull
        public final PearAuthenticationResponse copy(@NotNull String accessToken, long j, @NotNull String refreshToken, @NotNull String tokenType) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            Intrinsics.checkNotNullParameter(tokenType, "tokenType");
            return new PearAuthenticationResponse(accessToken, j, refreshToken, tokenType);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PearAuthenticationResponse)) {
                return false;
            }
            PearAuthenticationResponse pearAuthenticationResponse = (PearAuthenticationResponse) obj;
            return Intrinsics.areEqual(this.accessToken, pearAuthenticationResponse.accessToken) && this.expiresIn == pearAuthenticationResponse.expiresIn && Intrinsics.areEqual(this.refreshToken, pearAuthenticationResponse.refreshToken) && Intrinsics.areEqual(this.tokenType, pearAuthenticationResponse.tokenType);
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        public final long getExpiresIn() {
            return this.expiresIn;
        }

        @NotNull
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        @NotNull
        public final String getTokenType() {
            return this.tokenType;
        }

        public int hashCode() {
            return this.tokenType.hashCode() + DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.refreshToken, (FloatFloatPair$$ExternalSyntheticBackport0.m(this.expiresIn) + (this.accessToken.hashCode() * 31)) * 31, 31);
        }

        @NotNull
        public String toString() {
            return "PearAuthenticationResponse(accessToken=" + this.accessToken + ", expiresIn=" + this.expiresIn + ", refreshToken=" + this.refreshToken + ", tokenType=" + this.tokenType + ")";
        }

        public PearAuthenticationResponse(@NotNull String accessToken, long j, @NotNull String refreshToken, @NotNull String tokenType) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            Intrinsics.checkNotNullParameter(tokenType, "tokenType");
            this.accessToken = accessToken;
            this.expiresIn = j;
            this.refreshToken = refreshToken;
            this.tokenType = tokenType;
        }

        @SerialName("access_token")
        public static /* synthetic */ void getAccessToken$annotations() {
        }

        @SerialName("expires_in")
        public static /* synthetic */ void getExpiresIn$annotations() {
        }

        @SerialName("refresh_token")
        public static /* synthetic */ void getRefreshToken$annotations() {
        }

        @SerialName("token_type")
        public static /* synthetic */ void getTokenType$annotations() {
        }
    }

    @Inject
    public PearAccessTokenProvider(@NotNull PearUpdateStructure pearUpdateStructure, @NotNull RecommendationRequestDispatcher recommendationRequestDispatcher, @NotNull PearUriBuilder pearUriBuilder) {
        Intrinsics.checkNotNullParameter(pearUpdateStructure, "pearUpdateStructure");
        Intrinsics.checkNotNullParameter(recommendationRequestDispatcher, "recommendationRequestDispatcher");
        Intrinsics.checkNotNullParameter(pearUriBuilder, "pearUriBuilder");
        this.pearUpdateStructure = pearUpdateStructure;
        this.recommendationRequestDispatcher = recommendationRequestDispatcher;
        this.pearUriBuilder = pearUriBuilder;
        this.expiryTimeInMs = Long.MIN_VALUE;
    }

    public final synchronized void clearAccessToken() {
        this.accessToken = null;
        this.expiryTimeInMs = Long.MIN_VALUE;
    }

    @Override // com.amazon.livingroom.auth.AccessTokenProvider
    @Nullable
    public synchronized String getAccessToken() {
        String str;
        if (isExpired()) {
            str = null;
            try {
                try {
                    Oauth20V1 credentials = this.pearUpdateStructure.getCredentials();
                    if (credentials == null) {
                        return null;
                    }
                    HttpRequestV1 httpRequestV1 = credentials.refreshRequest.requestV1;
                    Set<Map.Entry<String, JsonElement>> setEntrySet = httpRequestV1.headers.content.entrySet();
                    int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(setEntrySet, 10));
                    if (iMapCapacity < 16) {
                        iMapCapacity = 16;
                    }
                    LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
                    for (Object obj : setEntrySet) {
                        String str2 = (String) ((Map.Entry) obj).getKey();
                        Object value = ((Map.Entry) obj).getValue();
                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlinx.serialization.json.JsonPrimitive");
                        linkedHashMap.put(str2, ((JsonPrimitive) value).getContent());
                    }
                    JsonObject jsonObject = this.recommendationRequestDispatcher.dispatch(this.pearUriBuilder.buildPearCredentialsUri(credentials), linkedHashMap, httpRequestV1.body, httpRequestV1.getVerb()).get();
                    Json.Default r2 = Json.Default;
                    KSerializer<PearAuthenticationResponse> kSerializerSerializer = PearAuthenticationResponse.Companion.serializer();
                    Intrinsics.checkNotNull(jsonObject);
                    PearAuthenticationResponse pearAuthenticationResponse = (PearAuthenticationResponse) r2.decodeFromJsonElement(kSerializerSerializer, jsonObject);
                    setExpiryTime(pearAuthenticationResponse.expiresIn);
                    this.accessToken = pearAuthenticationResponse.accessToken;
                    this.pearUpdateStructure.tryUpdateRefreshToken(pearAuthenticationResponse.refreshToken);
                    str = this.accessToken;
                } catch (InterruptedException e) {
                    Log.e(TAG, "Interrupted while fetching new access token. No access token will be provided.", e);
                    clearAccessToken();
                }
            } catch (TimeoutException e2) {
                Log.e(TAG, "Waiting for new access token timed out. No access token will be provided.", e2);
                clearAccessToken();
            } catch (Exception e3) {
                Log.e(TAG, "Exception while fetching access token : " + e3.getMessage(), e3);
                clearAccessToken();
            }
        } else {
            str = this.accessToken;
        }
        return str;
    }

    public final synchronized boolean isExpired() {
        return System.currentTimeMillis() > this.expiryTimeInMs;
    }

    public final synchronized void setExpiryTime(long j) {
        this.expiryTimeInMs = (j * ((long) 1000)) + System.currentTimeMillis();
    }
}
