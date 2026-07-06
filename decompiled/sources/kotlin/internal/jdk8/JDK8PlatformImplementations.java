package kotlin.internal.jdk8;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.internal.jdk7.JDK7PlatformImplementations;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;
import kotlin.random.jdk8.PlatformThreadLocalRandom;
import kotlin.ranges.IntRange;
import kotlin.text.MatchGroup;
import kotlin.time.Clock;
import kotlin.time.ExperimentalTime;
import kotlin.time.Instant;
import kotlin.time.jdk8.InstantConversionsJDK8Kt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class JDK8PlatformImplementations extends JDK7PlatformImplementations {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nJDK8PlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JDK8PlatformImplementations.kt\nkotlin/internal/jdk8/JDK8PlatformImplementations$ReflectSdkVersion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,76:1\n1#2:77\n*E\n"})
    public static final class ReflectSdkVersion {

        @NotNull
        public static final ReflectSdkVersion INSTANCE = new ReflectSdkVersion();

        @JvmField
        @Nullable
        public static final Integer sdkVersion;

        static {
            Object obj;
            Integer num = null;
            try {
                obj = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Throwable unused) {
            }
            Integer num2 = obj instanceof Integer ? (Integer) obj : null;
            if (num2 != null && num2.intValue() > 0) {
                num = num2;
            }
            sdkVersion = num;
        }
    }

    /* JADX INFO: renamed from: kotlin.internal.jdk8.JDK8PlatformImplementations$getSystemClock$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Clock {
        @Override // kotlin.time.Clock
        public Instant now() {
            j$.time.Instant instantNow = j$.time.Instant.now();
            Intrinsics.checkNotNullExpressionValue(instantNow, "now(...)");
            return InstantConversionsJDK8Kt.toKotlinInstant(instantNow);
        }
    }

    /* JADX INFO: renamed from: kotlin.internal.jdk8.JDK8PlatformImplementations$getSystemClock$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass2 implements Clock {
        @Override // kotlin.time.Clock
        public Instant now() {
            return Instant.Companion.fromEpochMilliseconds(System.currentTimeMillis());
        }
    }

    @Override // kotlin.internal.PlatformImplementations
    @NotNull
    public Random defaultPlatformRandom() {
        return sdkIsNullOrAtLeast(34) ? new PlatformThreadLocalRandom() : new FallbackThreadLocalRandom();
    }

    @Override // kotlin.internal.PlatformImplementations
    @Nullable
    public MatchGroup getMatchResultNamedGroup(@NotNull MatchResult matchResult, @NotNull String name) {
        Intrinsics.checkNotNullParameter(matchResult, "matchResult");
        Intrinsics.checkNotNullParameter(name, "name");
        Matcher matcher = matchResult instanceof Matcher ? (Matcher) matchResult : null;
        if (matcher == null) {
            throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
        }
        int iStart = matcher.start(name);
        IntRange intRange = new IntRange(iStart, matcher.end(name) - 1, 1);
        if (iStart < 0) {
            return null;
        }
        String strGroup = matcher.group(name);
        Intrinsics.checkNotNullExpressionValue(strGroup, "group(...)");
        return new MatchGroup(strGroup, intRange);
    }

    @Override // kotlin.internal.PlatformImplementations
    @ExperimentalTime
    @NotNull
    public Clock getSystemClock() {
        return sdkIsNullOrAtLeast(26) ? new AnonymousClass1() : new AnonymousClass2();
    }

    public final boolean sdkIsNullOrAtLeast(int i) {
        Integer num = ReflectSdkVersion.sdkVersion;
        return num == null || num.intValue() >= i;
    }
}
