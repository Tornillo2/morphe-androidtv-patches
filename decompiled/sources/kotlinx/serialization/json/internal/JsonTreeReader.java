package kotlinx.serialization.json.internal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.DeepRecursiveFunction;
import kotlin.DeepRecursiveKt;
import kotlin.DeepRecursiveScope;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nJsonTreeReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonTreeReader.kt\nkotlinx/serialization/json/internal/JsonTreeReader\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n*L\n1#1,121:1\n27#1,25:122\n27#1,25:147\n517#2,3:172\n*S KotlinDebug\n*F\n+ 1 JsonTreeReader.kt\nkotlinx/serialization/json/internal/JsonTreeReader\n*L\n19#1:122,25\n24#1:147,25\n64#1:172,3\n*E\n"})
public final class JsonTreeReader {
    public final boolean isLenient;

    @NotNull
    public final AbstractJsonLexer lexer;
    public int stackDepth;
    public final boolean trailingCommaAllowed;

    /* JADX INFO: renamed from: kotlinx.serialization.json.internal.JsonTreeReader$readDeepRecursive$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.serialization.json.internal.JsonTreeReader$readDeepRecursive$1", f = "JsonTreeReader.kt", i = {}, l = {115}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function3<DeepRecursiveScope<Unit, JsonElement>, Unit, Continuation<? super JsonElement>, Object> {
        public /* synthetic */ Object L$0;
        public int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DeepRecursiveScope<Unit, JsonElement> deepRecursiveScope = (DeepRecursiveScope) this.L$0;
                byte bPeekNextToken = JsonTreeReader.this.lexer.peekNextToken();
                if (bPeekNextToken == 1) {
                    return JsonTreeReader.this.readValue(true);
                }
                if (bPeekNextToken == 0) {
                    return JsonTreeReader.this.readValue(false);
                }
                if (bPeekNextToken != 6) {
                    if (bPeekNextToken == 8) {
                        return JsonTreeReader.this.readArray();
                    }
                    AbstractJsonLexer.fail$default(JsonTreeReader.this.lexer, "Can't begin reading element, unexpected token", 0, null, 6, null);
                    throw null;
                }
                JsonTreeReader jsonTreeReader = JsonTreeReader.this;
                this.label = 1;
                obj = jsonTreeReader.readObject(deepRecursiveScope, this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return (JsonElement) obj;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(DeepRecursiveScope<Unit, JsonElement> deepRecursiveScope, Unit unit, Continuation<? super JsonElement> continuation) {
            AnonymousClass1 anonymousClass1 = JsonTreeReader.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = deepRecursiveScope;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.serialization.json.internal.JsonTreeReader$readObject$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.serialization.json.internal.JsonTreeReader", f = "JsonTreeReader.kt", i = {0, 0, 0, 0}, l = {24}, m = "readObject", n = {"$this$readObject", "this_$iv", "result$iv", "key$iv"}, s = {"L$0", "L$1", "L$2", "L$3"})
    public static final class AnonymousClass2 extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public /* synthetic */ Object result;

        public AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return JsonTreeReader.this.readObject(null, this);
        }
    }

    public JsonTreeReader(@NotNull JsonConfiguration configuration, @NotNull AbstractJsonLexer lexer) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        this.lexer = lexer;
        this.isLenient = configuration.isLenient;
        this.trailingCommaAllowed = configuration.allowTrailingComma;
    }

    @NotNull
    public final JsonElement read() {
        byte bPeekNextToken = this.lexer.peekNextToken();
        if (bPeekNextToken == 1) {
            return readValue(true);
        }
        if (bPeekNextToken == 0) {
            return readValue(false);
        }
        if (bPeekNextToken != 6) {
            if (bPeekNextToken == 8) {
                return readArray();
            }
            AbstractJsonLexer.fail$default(this.lexer, "Cannot read Json element because of unexpected ".concat(AbstractJsonLexerKt.tokenDescription(bPeekNextToken)), 0, null, 6, null);
            throw null;
        }
        int i = this.stackDepth + 1;
        this.stackDepth = i;
        this.stackDepth--;
        return i == 200 ? readDeepRecursive() : readObject();
    }

    public final JsonElement readArray() {
        byte bConsumeNextToken = this.lexer.consumeNextToken();
        if (this.lexer.peekNextToken() == 4) {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
            throw null;
        }
        ArrayList arrayList = new ArrayList();
        while (this.lexer.canConsumeValue()) {
            arrayList.add(read());
            bConsumeNextToken = this.lexer.consumeNextToken();
            if (bConsumeNextToken != 4) {
                AbstractJsonLexer abstractJsonLexer = this.lexer;
                boolean z = bConsumeNextToken == 9;
                int i = abstractJsonLexer.currentPosition;
                if (!z) {
                    AbstractJsonLexer.fail$default(abstractJsonLexer, "Expected end of the array or comma", i, null, 4, null);
                    throw null;
                }
            }
        }
        if (bConsumeNextToken == 8) {
            this.lexer.consumeNextToken((byte) 9);
        } else if (bConsumeNextToken == 4) {
            if (!this.trailingCommaAllowed) {
                JsonExceptionsKt.invalidTrailingComma(this.lexer, "array");
                throw null;
            }
            this.lexer.consumeNextToken((byte) 9);
        }
        return new JsonArray(arrayList);
    }

    public final JsonElement readDeepRecursive() {
        return (JsonElement) DeepRecursiveKt.invoke(new DeepRecursiveFunction(new AnonymousClass1(null)), Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0095 -> B:27:0x009f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object readObject(kotlin.DeepRecursiveScope<kotlin.Unit, kotlinx.serialization.json.JsonElement> r21, kotlin.coroutines.Continuation<? super kotlinx.serialization.json.JsonElement> r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.JsonTreeReader.readObject(kotlin.DeepRecursiveScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final JsonObject readObjectImpl(Function0<? extends JsonElement> function0) {
        byte bConsumeNextToken = this.lexer.consumeNextToken((byte) 6);
        if (this.lexer.peekNextToken() == 4) {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
            throw null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (true) {
            if (!this.lexer.canConsumeValue()) {
                break;
            }
            String strConsumeStringLenient = this.isLenient ? this.lexer.consumeStringLenient() : this.lexer.consumeString();
            this.lexer.consumeNextToken((byte) 5);
            linkedHashMap.put(strConsumeStringLenient, function0.invoke());
            bConsumeNextToken = this.lexer.consumeNextToken();
            if (bConsumeNextToken != 4) {
                if (bConsumeNextToken != 7) {
                    AbstractJsonLexer.fail$default(this.lexer, "Expected end of the object or comma", 0, null, 6, null);
                    throw null;
                }
            }
        }
        if (bConsumeNextToken == 6) {
            this.lexer.consumeNextToken((byte) 7);
        } else if (bConsumeNextToken == 4) {
            if (!this.trailingCommaAllowed) {
                JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
                throw null;
            }
            this.lexer.consumeNextToken((byte) 7);
        }
        return new JsonObject(linkedHashMap);
    }

    public final JsonPrimitive readValue(boolean z) {
        String strConsumeStringLenient = (this.isLenient || !z) ? this.lexer.consumeStringLenient() : this.lexer.consumeString();
        return (z || !Intrinsics.areEqual(strConsumeStringLenient, AbstractJsonLexerKt.NULL)) ? new JsonLiteral(strConsumeStringLenient, z, null, 4, null) : JsonNull.INSTANCE;
    }

    public final JsonElement readObject() {
        byte bConsumeNextToken = this.lexer.consumeNextToken((byte) 6);
        if (this.lexer.peekNextToken() != 4) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            while (true) {
                if (!this.lexer.canConsumeValue()) {
                    break;
                }
                String strConsumeStringLenient = this.isLenient ? this.lexer.consumeStringLenient() : this.lexer.consumeString();
                this.lexer.consumeNextToken((byte) 5);
                linkedHashMap.put(strConsumeStringLenient, read());
                bConsumeNextToken = this.lexer.consumeNextToken();
                if (bConsumeNextToken != 4) {
                    if (bConsumeNextToken != 7) {
                        AbstractJsonLexer.fail$default(this.lexer, "Expected end of the object or comma", 0, null, 6, null);
                        throw null;
                    }
                }
            }
            if (bConsumeNextToken == 6) {
                this.lexer.consumeNextToken((byte) 7);
            } else if (bConsumeNextToken == 4) {
                if (this.trailingCommaAllowed) {
                    this.lexer.consumeNextToken((byte) 7);
                } else {
                    JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
                    throw null;
                }
            }
            return new JsonObject(linkedHashMap);
        }
        AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
        throw null;
    }
}
