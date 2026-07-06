package com.amazon.ignitionshared;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Version implements Comparable<Version> {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String TAG = "Version";

    @NotNull
    public final List<Integer> parts;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nVersion.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Version.kt\ncom/amazon/ignitionshared/Version$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,46:1\n1#2:47\n739#3,9:48\n1563#3:57\n1634#3,3:58\n*S KotlinDebug\n*F\n+ 1 Version.kt\ncom/amazon/ignitionshared/Version$Companion\n*L\n17#1:48,9\n17#1:57\n17#1:58,3\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0063 A[Catch: Exception -> 0x004a, LOOP:1: B:18:0x005d->B:20:0x0063, LOOP_END, TryCatch #0 {Exception -> 0x004a, blocks: (B:3:0x0005, B:5:0x0012, B:7:0x0024, B:8:0x002c, B:10:0x0032, B:13:0x003f, B:17:0x004e, B:18:0x005d, B:20:0x0063, B:16:0x004c, B:21:0x0075, B:22:0x007c), top: B:26:0x0005 }] */
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.amazon.ignitionshared.Version parse(@org.jetbrains.annotations.NotNull java.lang.String r3) {
            /*
                r2 = this;
                java.lang.String r0 = "version"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                kotlin.text.Regex r0 = new kotlin.text.Regex     // Catch: java.lang.Exception -> L4a
                java.lang.String r1 = "\\d+(\\.\\d+)*"
                r0.<init>(r1)     // Catch: java.lang.Exception -> L4a
                boolean r0 = r0.matches(r3)     // Catch: java.lang.Exception -> L4a
                if (r0 == 0) goto L75
                kotlin.text.Regex r0 = new kotlin.text.Regex     // Catch: java.lang.Exception -> L4a
                java.lang.String r1 = "\\."
                r0.<init>(r1)     // Catch: java.lang.Exception -> L4a
                r1 = 0
                java.util.List r3 = r0.split(r3, r1)     // Catch: java.lang.Exception -> L4a
                boolean r0 = r3.isEmpty()     // Catch: java.lang.Exception -> L4a
                if (r0 != 0) goto L4c
                int r0 = r3.size()     // Catch: java.lang.Exception -> L4a
                java.util.ListIterator r0 = r3.listIterator(r0)     // Catch: java.lang.Exception -> L4a
            L2c:
                boolean r1 = r0.hasPrevious()     // Catch: java.lang.Exception -> L4a
                if (r1 == 0) goto L4c
                java.lang.Object r1 = r0.previous()     // Catch: java.lang.Exception -> L4a
                java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L4a
                int r1 = r1.length()     // Catch: java.lang.Exception -> L4a
                if (r1 != 0) goto L3f
                goto L2c
            L3f:
                int r0 = r0.nextIndex()     // Catch: java.lang.Exception -> L4a
                int r0 = r0 + 1
                java.util.List r3 = kotlin.collections.CollectionsKt___CollectionsKt.take(r3, r0)     // Catch: java.lang.Exception -> L4a
                goto L4e
            L4a:
                r3 = move-exception
                goto L7d
            L4c:
                kotlin.collections.EmptyList r3 = kotlin.collections.EmptyList.INSTANCE     // Catch: java.lang.Exception -> L4a
            L4e:
                java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Exception -> L4a
                r1 = 10
                int r1 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r3, r1)     // Catch: java.lang.Exception -> L4a
                r0.<init>(r1)     // Catch: java.lang.Exception -> L4a
                java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Exception -> L4a
            L5d:
                boolean r1 = r3.hasNext()     // Catch: java.lang.Exception -> L4a
                if (r1 == 0) goto L86
                java.lang.Object r1 = r3.next()     // Catch: java.lang.Exception -> L4a
                java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L4a
                int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Exception -> L4a
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Exception -> L4a
                r0.add(r1)     // Catch: java.lang.Exception -> L4a
                goto L5d
            L75:
                java.lang.String r3 = "Invalid version format"
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch: java.lang.Exception -> L4a
                r0.<init>(r3)     // Catch: java.lang.Exception -> L4a
                throw r0     // Catch: java.lang.Exception -> L4a
            L7d:
                java.lang.String r0 = "Version"
                java.lang.String r1 = "Error in Parsing the version String"
                com.amazon.reporting.Log.e(r0, r1, r3)
                kotlin.collections.EmptyList r0 = kotlin.collections.EmptyList.INSTANCE
            L86:
                com.amazon.ignitionshared.Version r3 = new com.amazon.ignitionshared.Version
                r3.<init>(r0)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.ignitionshared.Version.Companion.parse(java.lang.String):com.amazon.ignitionshared.Version");
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ Version(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Version) && Version.class == obj.getClass() && compareTo((Version) obj) == 0;
    }

    @NotNull
    public final List<Integer> getParts() {
        return this.parts;
    }

    public int hashCode() {
        return this.parts.hashCode();
    }

    public Version(List<Integer> list) {
        this.parts = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(@Nullable Version version) {
        if (version == null) {
            return 1;
        }
        int iMax = Math.max(this.parts.size(), version.parts.size());
        int i = 0;
        while (i < iMax) {
            int iIntValue = i < this.parts.size() ? this.parts.get(i).intValue() : 0;
            int iIntValue2 = i < version.parts.size() ? version.parts.get(i).intValue() : 0;
            if (iIntValue < iIntValue2) {
                return -1;
            }
            if (iIntValue > iIntValue2) {
                return 1;
            }
            i++;
        }
        return 0;
    }
}
