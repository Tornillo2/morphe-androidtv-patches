package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.Json;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nComposers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Composers.kt\nkotlinx/serialization/json/internal/ComposerWithPrettyPrint\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,104:1\n1#2:105\n*E\n"})
public final class ComposerWithPrettyPrint extends Composer {

    @NotNull
    public final Json json;
    public int level;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerWithPrettyPrint(@NotNull InternalJsonWriter writer, @NotNull Json json) {
        super(writer);
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void indent() {
        this.writingFirst = true;
        this.level++;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void nextItem() {
        this.writingFirst = false;
        print(StringUtils.LF);
        int i = this.level;
        for (int i2 = 0; i2 < i; i2++) {
            print(this.json.configuration.prettyPrintIndent);
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void nextItemIfNotFirst() {
        if (this.writingFirst) {
            this.writingFirst = false;
        } else {
            nextItem();
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void space() {
        print(' ');
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void unIndent() {
        this.level--;
    }
}
