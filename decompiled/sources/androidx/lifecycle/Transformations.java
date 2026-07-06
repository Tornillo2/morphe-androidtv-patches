package androidx.lifecycle;

import androidx.annotation.CheckResult;
import androidx.annotation.MainThread;
import androidx.arch.core.util.Function;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@JvmName(name = "Transformations")
public final class Transformations {
    @JvmName(name = "distinctUntilChanged")
    @NotNull
    @CheckResult
    @MainThread
    public static final <X> LiveData<X> distinctUntilChanged(@NotNull LiveData<X> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        if (liveData.isInitialized()) {
            mediatorLiveData.setValue(liveData.getValue());
            booleanRef.element = false;
        }
        mediatorLiveData.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1<X, Unit>() { // from class: androidx.lifecycle.Transformations.distinctUntilChanged.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(X x) {
                X value = mediatorLiveData.getValue();
                if (booleanRef.element || ((value == null && x != null) || !(value == null || value.equals(x)))) {
                    booleanRef.element = false;
                    mediatorLiveData.setValue(x);
                }
            }
        }));
        return mediatorLiveData;
    }

    @JvmName(name = "map")
    @NotNull
    @CheckResult
    @MainThread
    public static final <X, Y> LiveData<Y> map(@NotNull LiveData<X> liveData, @NotNull final Function1<X, Y> transform) {
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1<X, Unit>() { // from class: androidx.lifecycle.Transformations.map.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference incomplete: some casts might be missing */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(X x) {
                mediatorLiveData.setValue((Y) transform.invoke(x));
            }
        }));
        return mediatorLiveData;
    }

    @JvmName(name = "switchMap")
    @NotNull
    @CheckResult
    @MainThread
    public static final <X, Y> LiveData<Y> switchMap(@NotNull LiveData<X> liveData, @NotNull final Function1<X, LiveData<Y>> transform) {
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<X>() { // from class: androidx.lifecycle.Transformations.switchMap.1

            @Nullable
            public LiveData<Y> liveData;

            @Nullable
            public final LiveData<Y> getLiveData() {
                return this.liveData;
            }

            /* JADX WARN: Multi-variable type inference failed */
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
            @Override // androidx.lifecycle.Observer
            public void onChanged(X x) {
                LiveData<Y> liveData2 = (LiveData) transform.invoke(x);
                Object obj = this.liveData;
                if (obj == liveData2) {
                    return;
                }
                if (obj != null) {
                    mediatorLiveData.removeSource(obj);
                }
                this.liveData = liveData2;
                if (liveData2 != 0) {
                    final MediatorLiveData<Y> mediatorLiveData2 = mediatorLiveData;
                    mediatorLiveData2.addSource(liveData2, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1<Y, Unit>() { // from class: androidx.lifecycle.Transformations$switchMap$1$onChanged$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Object obj2) {
                            invoke2(obj2);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Y y) {
                            mediatorLiveData2.setValue(y);
                        }
                    }));
                }
            }

            public final void setLiveData(@Nullable LiveData<Y> liveData2) {
                this.liveData = liveData2;
            }
        });
        return mediatorLiveData;
    }

    @JvmName(name = "map")
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use kotlin functions, instead of outdated arch core Functions")
    @CheckResult
    @MainThread
    public static final /* synthetic */ LiveData map(LiveData liveData, final Function mapFunction) {
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        Intrinsics.checkNotNullParameter(mapFunction, "mapFunction");
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1<Object, Unit>() { // from class: androidx.lifecycle.Transformations.map.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object obj) {
                mediatorLiveData.setValue(mapFunction.apply(obj));
            }
        }));
        return mediatorLiveData;
    }

    @JvmName(name = "switchMap")
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use kotlin functions, instead of outdated arch core Functions")
    @CheckResult
    @MainThread
    public static final /* synthetic */ LiveData switchMap(LiveData liveData, final Function switchMapFunction) {
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        Intrinsics.checkNotNullParameter(switchMapFunction, "switchMapFunction");
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<Object>() { // from class: androidx.lifecycle.Transformations.switchMap.2

            @Nullable
            public LiveData<Object> liveData;

            @Nullable
            public final LiveData<Object> getLiveData() {
                return this.liveData;
            }

            @Override // androidx.lifecycle.Observer
            public void onChanged(Object obj) {
                LiveData<Object> liveDataApply = switchMapFunction.apply(obj);
                LiveData<Object> liveData2 = this.liveData;
                if (liveData2 == liveDataApply) {
                    return;
                }
                if (liveData2 != null) {
                    mediatorLiveData.removeSource(liveData2);
                }
                this.liveData = liveDataApply;
                if (liveDataApply != null) {
                    final MediatorLiveData<Object> mediatorLiveData2 = mediatorLiveData;
                    mediatorLiveData2.addSource(liveDataApply, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1<Object, Unit>() { // from class: androidx.lifecycle.Transformations$switchMap$2$onChanged$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Object obj2) {
                            invoke2(obj2);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Object obj2) {
                            mediatorLiveData2.setValue(obj2);
                        }
                    }));
                }
            }

            public final void setLiveData(@Nullable LiveData<Object> liveData2) {
                this.liveData = liveData2;
            }
        });
        return mediatorLiveData;
    }
}
