package kotlinx.coroutines.selects;

import java.util.ArrayList;
import java.util.Collections;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class UnbiasedSelectBuilderImpl<R> implements SelectBuilder<R> {

    @NotNull
    public final ArrayList<Function0<Unit>> clauses = new ArrayList<>();

    @NotNull
    public final SelectBuilderImpl<R> instance;

    public UnbiasedSelectBuilderImpl(@NotNull Continuation<? super R> continuation) {
        this.instance = new SelectBuilderImpl<>(continuation);
    }

    @NotNull
    public final ArrayList<Function0<Unit>> getClauses() {
        return this.clauses;
    }

    @NotNull
    public final SelectBuilderImpl<R> getInstance() {
        return this.instance;
    }

    @PublishedApi
    public final void handleBuilderException(@NotNull Throwable th) throws Throwable {
        this.instance.handleBuilderException(th);
    }

    @PublishedApi
    @Nullable
    public final Object initSelectResult() throws Throwable {
        if (!this.instance.isSelected()) {
            try {
                Collections.shuffle(this.clauses);
                ArrayList<Function0<Unit>> arrayList = this.clauses;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Function0<Unit> function0 = arrayList.get(i);
                    i++;
                    function0.invoke();
                }
            } catch (Throwable th) {
                this.instance.handleBuilderException(th);
            }
        }
        return this.instance.getResult();
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        invoke(selectClause2, null, function2);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void onTimeout(final long j, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> function1) {
        this.clauses.add(new Function0<Unit>(this) { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.onTimeout.1
            public final /* synthetic */ UnbiasedSelectBuilderImpl<R> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.instance.onTimeout(j, function1);
            }
        });
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(@NotNull final SelectClause0 selectClause0, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> function1) {
        this.clauses.add(new Function0<Unit>() { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.invoke.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                selectClause0.registerSelectClause0(this.instance, function1);
            }
        });
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <Q> void invoke(@NotNull final SelectClause1<? extends Q> selectClause1, @NotNull final Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        this.clauses.add(new Function0<Unit>() { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.invoke.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r1v2 boolean
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                	at jadx.core.dex.visitors.typeinference.FinishTypeInference.visit(FinishTypeInference.java:22)
                */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                /*
                    r3 = this;
                    kotlinx.coroutines.selects.SelectClause1<Q> r0 = r1
                    kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl<R> r1 = r2
                    kotlinx.coroutines.selects.SelectBuilderImpl<R> r1 = r1.instance
                    kotlin.jvm.functions.Function2<Q, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r2 = r3
                    r0.registerSelectClause1(r1, r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.AnonymousClass2.invoke2():void");
            }
        });
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull final SelectClause2<? super P, ? extends Q> selectClause2, final P p, @NotNull final Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        this.clauses.add(new Function0<Unit>() { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.invoke.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r1v2 boolean
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                	at jadx.core.dex.visitors.typeinference.FinishTypeInference.visit(FinishTypeInference.java:22)
                */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                /*
                    r4 = this;
                    kotlinx.coroutines.selects.SelectClause2<P, Q> r0 = r1
                    kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl<R> r1 = r2
                    kotlinx.coroutines.selects.SelectBuilderImpl<R> r1 = r1.instance
                    P r2 = r3
                    kotlin.jvm.functions.Function2<Q, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r3 = r4
                    r0.registerSelectClause2(r1, r2, r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.AnonymousClass3.invoke2():void");
            }
        });
    }
}
