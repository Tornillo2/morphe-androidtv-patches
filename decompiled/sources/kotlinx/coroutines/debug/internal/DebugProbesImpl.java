package kotlinx.coroutines.debug.internal;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.KotlinVersion;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.StringsKt__IndentKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DebugProbesImpl {

    @NotNull
    public static final String ARTIFICIAL_FRAME_MESSAGE = "Coroutine creation stacktrace";

    @NotNull
    public static final DebugProbesImpl INSTANCE;

    @NotNull
    public static final ConcurrentWeakMap<CoroutineStackFrame, DebugCoroutineInfoImpl> callerInfoCache;

    @NotNull
    public static final ConcurrentWeakMap<CoroutineOwner<?>, Boolean> capturedCoroutinesMap;

    @NotNull
    public static final ReentrantReadWriteLock coroutineStateLock;

    @NotNull
    public static final SimpleDateFormat dateFormat;

    @NotNull
    public static final /* synthetic */ SequenceNumberRefVolatile debugProbesImpl$SequenceNumberRefVolatile;

    @Nullable
    public static final Function1<Boolean, Unit> dynamicAttach;
    public static boolean enableCreationStackTraces;
    private static volatile int installations;
    public static boolean sanitizeStackTraces;
    public static final /* synthetic */ AtomicLongFieldUpdater sequenceNumber$FU;

    @Nullable
    public static Thread weakRefCleanerThread;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CoroutineOwner<T> implements Continuation<T>, CoroutineStackFrame {

        @JvmField
        @NotNull
        public final Continuation<T> delegate;

        @Nullable
        public final CoroutineStackFrame frame;

        @JvmField
        @NotNull
        public final DebugCoroutineInfoImpl info;

        /* JADX WARN: Multi-variable type inference failed */
        public CoroutineOwner(@NotNull Continuation<? super T> continuation, @NotNull DebugCoroutineInfoImpl debugCoroutineInfoImpl, @Nullable CoroutineStackFrame coroutineStackFrame) {
            this.delegate = continuation;
            this.info = debugCoroutineInfoImpl;
            this.frame = coroutineStackFrame;
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        @Nullable
        public CoroutineStackFrame getCallerFrame() {
            CoroutineStackFrame coroutineStackFrame = this.frame;
            if (coroutineStackFrame != null) {
                return coroutineStackFrame.getCallerFrame();
            }
            return null;
        }

        @Override // kotlin.coroutines.Continuation
        @NotNull
        public CoroutineContext getContext() {
            return this.delegate.getContext();
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        @Nullable
        public StackTraceElement getStackTraceElement() {
            CoroutineStackFrame coroutineStackFrame = this.frame;
            if (coroutineStackFrame != null) {
                return coroutineStackFrame.getStackTraceElement();
            }
            return null;
        }

        @Override // kotlin.coroutines.Continuation
        public void resumeWith(@NotNull Object obj) {
            DebugProbesImpl.INSTANCE.probeCoroutineCompleted(this);
            this.delegate.resumeWith(obj);
        }

        @NotNull
        public String toString() {
            return this.delegate.toString();
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.debug.internal.DebugProbesImpl$startWeakRefCleanerThread$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1(0);

        public AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            DebugProbesImpl.callerInfoCache.runWeakRefQueueCleaningLoopUntilInterrupted();
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.debug.internal.DebugProbesImpl$SequenceNumberRefVolatile] */
    static {
        DebugProbesImpl debugProbesImpl = new DebugProbesImpl();
        INSTANCE = debugProbesImpl;
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        capturedCoroutinesMap = new ConcurrentWeakMap<>(false, 1, null);
        debugProbesImpl$SequenceNumberRefVolatile = new Object(0L) { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl.SequenceNumberRefVolatile
            volatile long sequenceNumber;

            {
                this.sequenceNumber = j;
            }
        };
        coroutineStateLock = new ReentrantReadWriteLock();
        sanitizeStackTraces = true;
        enableCreationStackTraces = true;
        dynamicAttach = debugProbesImpl.getDynamicAttach();
        callerInfoCache = new ConcurrentWeakMap<>(true);
        sequenceNumber$FU = AtomicLongFieldUpdater.newUpdater(SequenceNumberRefVolatile.class, "sequenceNumber");
    }

    public final void build(Job job, Map<Job, DebugCoroutineInfoImpl> map, StringBuilder sb, String str) {
        DebugCoroutineInfoImpl debugCoroutineInfoImpl = map.get(job);
        if (debugCoroutineInfoImpl != null) {
            StackTraceElement stackTraceElement = (StackTraceElement) CollectionsKt___CollectionsKt.firstOrNull((List) debugCoroutineInfoImpl.lastObservedStackTrace());
            String str2 = debugCoroutineInfoImpl._state;
            StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m(str);
            sbM.append(getDebugString(job));
            sbM.append(", continuation is ");
            sbM.append(str2);
            sbM.append(" at line ");
            sbM.append(stackTraceElement);
            sbM.append('\n');
            sb.append(sbM.toString());
            str = str + '\t';
        } else if (!(job instanceof ScopeCoroutine)) {
            StringBuilder sbM2 = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m(str);
            sbM2.append(getDebugString(job));
            sbM2.append('\n');
            sb.append(sbM2.toString());
            str = str + '\t';
        }
        Iterator<Job> it = job.getChildren().iterator();
        while (it.hasNext()) {
            build(it.next(), map, sb, str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> Continuation<T> createOwner(Continuation<? super T> continuation, StackTraceFrame stackTraceFrame) {
        if (!isInstalled$kotlinx_coroutines_core()) {
            return continuation;
        }
        CoroutineOwner<?> coroutineOwner = new CoroutineOwner<>(continuation, new DebugCoroutineInfoImpl(continuation.getContext(), stackTraceFrame, sequenceNumber$FU.incrementAndGet(debugProbesImpl$SequenceNumberRefVolatile)), stackTraceFrame);
        ConcurrentWeakMap<CoroutineOwner<?>, Boolean> concurrentWeakMap = capturedCoroutinesMap;
        concurrentWeakMap.put(coroutineOwner, Boolean.TRUE);
        if (!isInstalled$kotlinx_coroutines_core()) {
            concurrentWeakMap.clear();
        }
        return coroutineOwner;
    }

    public final void dumpCoroutines(@NotNull PrintStream printStream) {
        synchronized (printStream) {
            INSTANCE.dumpCoroutinesSynchronized(printStream);
        }
    }

    @NotNull
    public final List<DebugCoroutineInfo> dumpCoroutinesInfo() {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed");
            }
            debugProbesImpl.getClass();
            return SequencesKt___SequencesKt.toList(SequencesKt___SequencesKt.mapNotNull(SequencesKt___SequencesKt.sortedWith(CollectionsKt___CollectionsKt.asSequence(capturedCoroutinesMap.getKeys()), new DebugProbesImpl$dumpCoroutinesInfoImpl$lambda12$$inlined$sortedBy$1()), new DebugProbesImpl$dumpCoroutinesInfo$$inlined$dumpCoroutinesInfoImpl$1(1)));
        } finally {
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    @NotNull
    public final Object[] dumpCoroutinesInfoAsJsonAndReferences() {
        String str;
        List<DebugCoroutineInfo> listDumpCoroutinesInfo = dumpCoroutinesInfo();
        int size = listDumpCoroutinesInfo.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        ArrayList arrayList3 = new ArrayList(size);
        for (DebugCoroutineInfo debugCoroutineInfo : listDumpCoroutinesInfo) {
            CoroutineContext coroutineContext = debugCoroutineInfo.context;
            CoroutineName coroutineName = (CoroutineName) coroutineContext.get(CoroutineName.Key);
            Long lValueOf = null;
            String stringWithQuotes = (coroutineName == null || (str = coroutineName.name) == null) ? null : toStringWithQuotes(str);
            CoroutineDispatcher coroutineDispatcher = (CoroutineDispatcher) coroutineContext.get(CoroutineDispatcher.Key);
            String stringWithQuotes2 = coroutineDispatcher != null ? toStringWithQuotes(coroutineDispatcher) : null;
            StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("\n                {\n                    \"name\": ", stringWithQuotes, ",\n                    \"id\": ");
            CoroutineId coroutineId = (CoroutineId) coroutineContext.get(CoroutineId.Key);
            if (coroutineId != null) {
                lValueOf = Long.valueOf(coroutineId.id);
            }
            sbM.append(lValueOf);
            sbM.append(",\n                    \"dispatcher\": ");
            sbM.append(stringWithQuotes2);
            sbM.append(",\n                    \"sequenceNumber\": ");
            sbM.append(debugCoroutineInfo.sequenceNumber);
            sbM.append(",\n                    \"state\": \"");
            sbM.append(debugCoroutineInfo.state);
            sbM.append("\"\n                } \n                ");
            arrayList3.add(StringsKt__IndentKt.trimIndent(sbM.toString()));
            arrayList2.add(debugCoroutineInfo.lastObservedFrame);
            arrayList.add(debugCoroutineInfo.lastObservedThread);
        }
        String str2 = "[" + CollectionsKt___CollectionsKt.joinToString$default(arrayList3, null, null, null, 0, null, null, 63, null) + AbstractJsonLexerKt.END_LIST;
        Object[] array = arrayList.toArray(new Thread[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Object[] array2 = arrayList2.toArray(new CoroutineStackFrame[0]);
        if (array2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Object[] array3 = listDumpCoroutinesInfo.toArray(new DebugCoroutineInfo[0]);
        if (array3 != null) {
            return new Object[]{str2, array, array2, array3};
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    public final <R> List<R> dumpCoroutinesInfoImpl(final Function2<? super CoroutineOwner<?>, ? super CoroutineContext, ? extends R> function2) {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed");
            }
            debugProbesImpl.getClass();
            return SequencesKt___SequencesKt.toList(SequencesKt___SequencesKt.mapNotNull(SequencesKt___SequencesKt.sortedWith(CollectionsKt___CollectionsKt.asSequence(capturedCoroutinesMap.getKeys()), new DebugProbesImpl$dumpCoroutinesInfoImpl$lambda12$$inlined$sortedBy$1()), new Function1<CoroutineOwner<?>, R>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesInfoImpl$1$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                @Nullable
                public final R invoke(@NotNull DebugProbesImpl.CoroutineOwner<?> coroutineOwner) {
                    CoroutineContext context;
                    if (DebugProbesImpl.INSTANCE.isFinished(coroutineOwner) || (context = coroutineOwner.info.getContext()) == null) {
                        return null;
                    }
                    return function2.invoke(coroutineOwner, context);
                }
            }));
        } finally {
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final void dumpCoroutinesSynchronized(PrintStream printStream) {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed");
            }
            printStream.print("Coroutines dump " + dateFormat.format(Long.valueOf(System.currentTimeMillis())));
            debugProbesImpl.getClass();
            for (CoroutineOwner coroutineOwner : (SequencesKt___SequencesKt.C00771) SequencesKt___SequencesKt.sortedWith(SequencesKt___SequencesKt.filter(CollectionsKt___CollectionsKt.asSequence(capturedCoroutinesMap.getKeys()), DebugProbesImpl$dumpCoroutinesSynchronized$1$2.INSTANCE), new DebugProbesImpl$dumpCoroutinesSynchronized$lambda19$$inlined$sortedBy$1())) {
                DebugCoroutineInfoImpl debugCoroutineInfoImpl = coroutineOwner.info;
                List<StackTraceElement> listLastObservedStackTrace = debugCoroutineInfoImpl.lastObservedStackTrace();
                DebugProbesImpl debugProbesImpl2 = INSTANCE;
                List<StackTraceElement> listEnhanceStackTraceWithThreadDumpImpl = debugProbesImpl2.enhanceStackTraceWithThreadDumpImpl(debugCoroutineInfoImpl._state, debugCoroutineInfoImpl.lastObservedThread, listLastObservedStackTrace);
                printStream.print("\n\nCoroutine " + coroutineOwner.delegate + ", state: " + ((Intrinsics.areEqual(debugCoroutineInfoImpl._state, DebugCoroutineInfoImplKt.RUNNING) && listEnhanceStackTraceWithThreadDumpImpl == listLastObservedStackTrace) ? debugCoroutineInfoImpl._state + " (Last suspension stacktrace, not an actual stacktrace)" : debugCoroutineInfoImpl._state));
                if (listLastObservedStackTrace.isEmpty()) {
                    printStream.print("\n\tat " + StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE));
                    debugProbesImpl2.printStackTrace(printStream, debugCoroutineInfoImpl.creationStackTrace());
                } else {
                    debugProbesImpl2.printStackTrace(printStream, listEnhanceStackTraceWithThreadDumpImpl);
                }
            }
        } finally {
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    @NotNull
    public final List<DebuggerInfo> dumpDebuggerInfo() {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed");
            }
            debugProbesImpl.getClass();
            return SequencesKt___SequencesKt.toList(SequencesKt___SequencesKt.mapNotNull(SequencesKt___SequencesKt.sortedWith(CollectionsKt___CollectionsKt.asSequence(capturedCoroutinesMap.getKeys()), new DebugProbesImpl$dumpCoroutinesInfoImpl$lambda12$$inlined$sortedBy$1()), new DebugProbesImpl$dumpDebuggerInfo$$inlined$dumpCoroutinesInfoImpl$1(1)));
        } finally {
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    @NotNull
    public final List<StackTraceElement> enhanceStackTraceWithThreadDump(@NotNull DebugCoroutineInfo debugCoroutineInfo, @NotNull List<StackTraceElement> list) {
        return enhanceStackTraceWithThreadDumpImpl(debugCoroutineInfo.state, debugCoroutineInfo.lastObservedThread, list);
    }

    @NotNull
    public final String enhanceStackTraceWithThreadDumpAsJson(@NotNull DebugCoroutineInfo debugCoroutineInfo) {
        List<StackTraceElement> listEnhanceStackTraceWithThreadDumpImpl = enhanceStackTraceWithThreadDumpImpl(debugCoroutineInfo.state, debugCoroutineInfo.lastObservedThread, debugCoroutineInfo.lastObservedStackTrace);
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : listEnhanceStackTraceWithThreadDumpImpl) {
            StringBuilder sb = new StringBuilder("\n                {\n                    \"declaringClass\": \"");
            sb.append(stackTraceElement.getClassName());
            sb.append("\",\n                    \"methodName\": \"");
            sb.append(stackTraceElement.getMethodName());
            sb.append("\",\n                    \"fileName\": ");
            String fileName = stackTraceElement.getFileName();
            sb.append(fileName != null ? toStringWithQuotes(fileName) : null);
            sb.append(",\n                    \"lineNumber\": ");
            sb.append(stackTraceElement.getLineNumber());
            sb.append("\n                }\n                ");
            arrayList.add(StringsKt__IndentKt.trimIndent(sb.toString()));
        }
        return "[" + CollectionsKt___CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null) + AbstractJsonLexerKt.END_LIST;
    }

    public final List<StackTraceElement> enhanceStackTraceWithThreadDumpImpl(String str, Thread thread, List<StackTraceElement> list) {
        Object objCreateFailure;
        if (Intrinsics.areEqual(str, DebugCoroutineInfoImplKt.RUNNING) && thread != null) {
            try {
                objCreateFailure = thread.getStackTrace();
            } catch (Throwable th) {
                objCreateFailure = ResultKt.createFailure(th);
            }
            if (objCreateFailure instanceof Result.Failure) {
                objCreateFailure = null;
            }
            StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) objCreateFailure;
            if (stackTraceElementArr != null) {
                int length = stackTraceElementArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        i = -1;
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTraceElementArr[i];
                    if (Intrinsics.areEqual(stackTraceElement.getClassName(), StackTraceRecoveryKt.baseContinuationImplClass) && Intrinsics.areEqual(stackTraceElement.getMethodName(), "resumeWith") && Intrinsics.areEqual(stackTraceElement.getFileName(), "ContinuationImpl.kt")) {
                        break;
                    }
                    i++;
                }
                Pair<Integer, Integer> pairFindContinuationStartIndex = findContinuationStartIndex(i, stackTraceElementArr, list);
                int iIntValue = pairFindContinuationStartIndex.first.intValue();
                int iIntValue2 = pairFindContinuationStartIndex.second.intValue();
                if (iIntValue != -1) {
                    ArrayList arrayList = new ArrayList((((list.size() + i) - iIntValue) - 1) - iIntValue2);
                    int i2 = i - iIntValue2;
                    for (int i3 = 0; i3 < i2; i3++) {
                        arrayList.add(stackTraceElementArr[i3]);
                    }
                    int size = list.size();
                    for (int i4 = iIntValue + 1; i4 < size; i4++) {
                        arrayList.add(list.get(i4));
                    }
                    return arrayList;
                }
            }
        }
        return list;
    }

    public final Pair<Integer, Integer> findContinuationStartIndex(int i, StackTraceElement[] stackTraceElementArr, List<StackTraceElement> list) {
        for (int i2 = 0; i2 < 3; i2++) {
            int iFindIndexOfFrame = INSTANCE.findIndexOfFrame((i - 1) - i2, stackTraceElementArr, list);
            if (iFindIndexOfFrame != -1) {
                return new Pair<>(Integer.valueOf(iFindIndexOfFrame), Integer.valueOf(i2));
            }
        }
        return new Pair<>(-1, 0);
    }

    public final int findIndexOfFrame(int i, StackTraceElement[] stackTraceElementArr, List<StackTraceElement> list) {
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt___ArraysKt.getOrNull(stackTraceElementArr, i);
        if (stackTraceElement == null) {
            return -1;
        }
        int i2 = 0;
        for (StackTraceElement stackTraceElement2 : list) {
            if (Intrinsics.areEqual(stackTraceElement2.getFileName(), stackTraceElement.getFileName()) && Intrinsics.areEqual(stackTraceElement2.getClassName(), stackTraceElement.getClassName()) && Intrinsics.areEqual(stackTraceElement2.getMethodName(), stackTraceElement.getMethodName())) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public final Set<CoroutineOwner<?>> getCapturedCoroutines() {
        return capturedCoroutinesMap.getKeys();
    }

    public final String getDebugString(Job job) {
        return job instanceof JobSupport ? ((JobSupport) job).toDebugString() : job.toString();
    }

    public final Function1<Boolean, Unit> getDynamicAttach() {
        Object objCreateFailure;
        Object objNewInstance;
        try {
            objNewInstance = Class.forName("kotlinx.coroutines.debug.internal.ByteBuddyDynamicAttach").getConstructors()[0].newInstance(null);
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
        }
        if (objNewInstance == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Function1<kotlin.Boolean, kotlin.Unit>");
        }
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(objNewInstance, 1);
        objCreateFailure = (Function1) objNewInstance;
        return (Function1) (objCreateFailure instanceof Result.Failure ? null : objCreateFailure);
    }

    public final boolean getEnableCreationStackTraces() {
        return enableCreationStackTraces;
    }

    public final boolean getSanitizeStackTraces() {
        return sanitizeStackTraces;
    }

    @NotNull
    public final String hierarchyToString(@NotNull Job job) {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed");
            }
            debugProbesImpl.getClass();
            Set<CoroutineOwner<?>> keys = capturedCoroutinesMap.getKeys();
            ArrayList arrayList = new ArrayList();
            for (Object obj : keys) {
                if (((CoroutineOwner) obj).delegate.getContext().get(Job.Key) != null) {
                    arrayList.add(obj);
                }
            }
            int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            if (iMapCapacity < 16) {
                iMapCapacity = 16;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj2 = arrayList.get(i3);
                i3++;
                linkedHashMap.put(JobKt__JobKt.getJob(((CoroutineOwner) obj2).delegate.getContext()), ((CoroutineOwner) obj2).info);
            }
            StringBuilder sb = new StringBuilder();
            INSTANCE.build(job, linkedHashMap, sb, "");
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            return string;
        } finally {
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final void install() {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            installations++;
            if (installations > 1) {
                return;
            }
            INSTANCE.startWeakRefCleanerThread();
            AgentInstallationType.INSTANCE.getClass();
            if (AgentInstallationType.isInstalledStatically) {
                while (i < readHoldCount) {
                    lock.lock();
                    i++;
                }
                writeLock.unlock();
                return;
            }
            Function1<Boolean, Unit> function1 = dynamicAttach;
            if (function1 != null) {
                function1.invoke(Boolean.TRUE);
            }
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
            writeLock.unlock();
        } finally {
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final boolean isFinished(CoroutineOwner<?> coroutineOwner) {
        Job job;
        CoroutineContext context = coroutineOwner.info.getContext();
        if (context == null || (job = (Job) context.get(Job.Key)) == null || !job.isCompleted()) {
            return false;
        }
        capturedCoroutinesMap.remove(coroutineOwner);
        return true;
    }

    public final boolean isInstalled$kotlinx_coroutines_core() {
        return installations > 0;
    }

    public final boolean isInternalMethod(StackTraceElement stackTraceElement) {
        return StringsKt__StringsJVMKt.startsWith$default(stackTraceElement.getClassName(), "kotlinx.coroutines", false, 2, null);
    }

    public final CoroutineOwner<?> owner(Continuation<?> continuation) {
        CoroutineStackFrame coroutineStackFrame = continuation instanceof CoroutineStackFrame ? (CoroutineStackFrame) continuation : null;
        if (coroutineStackFrame != null) {
            return owner(coroutineStackFrame);
        }
        return null;
    }

    public final void printStackTrace(PrintStream printStream, List<StackTraceElement> list) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            printStream.print("\n\tat " + ((StackTraceElement) it.next()));
        }
    }

    public final void probeCoroutineCompleted(CoroutineOwner<?> coroutineOwner) {
        CoroutineStackFrame coroutineStackFrameRealCaller;
        capturedCoroutinesMap.remove(coroutineOwner);
        CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = coroutineOwner.info.getLastObservedFrame$kotlinx_coroutines_core();
        if (lastObservedFrame$kotlinx_coroutines_core == null || (coroutineStackFrameRealCaller = realCaller(lastObservedFrame$kotlinx_coroutines_core)) == null) {
            return;
        }
        callerInfoCache.remove(coroutineStackFrameRealCaller);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final <T> Continuation<T> probeCoroutineCreated$kotlinx_coroutines_core(@NotNull Continuation<? super T> continuation) {
        if (isInstalled$kotlinx_coroutines_core() && owner(continuation) == null) {
            return createOwner(continuation, enableCreationStackTraces ? toStackTraceFrame(sanitizeStackTrace(new Exception())) : null);
        }
        return continuation;
    }

    public final void probeCoroutineResumed$kotlinx_coroutines_core(@NotNull Continuation<?> continuation) {
        updateState(continuation, DebugCoroutineInfoImplKt.RUNNING);
    }

    public final void probeCoroutineSuspended$kotlinx_coroutines_core(@NotNull Continuation<?> continuation) {
        updateState(continuation, DebugCoroutineInfoImplKt.SUSPENDED);
    }

    public final CoroutineStackFrame realCaller(CoroutineStackFrame coroutineStackFrame) {
        do {
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return null;
            }
        } while (coroutineStackFrame.getStackTraceElement() == null);
        return coroutineStackFrame;
    }

    public final <T extends Throwable> List<StackTraceElement> sanitizeStackTrace(T t) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        int length2 = stackTrace.length - 1;
        if (length2 >= 0) {
            while (true) {
                int i2 = length2 - 1;
                if (Intrinsics.areEqual(stackTrace[length2].getClassName(), "kotlin.coroutines.jvm.internal.DebugProbesKt")) {
                    i = length2;
                    break;
                }
                if (i2 < 0) {
                    break;
                }
                length2 = i2;
            }
        }
        if (!sanitizeStackTraces) {
            int i3 = length - i;
            ArrayList arrayList = new ArrayList(i3);
            int i4 = 0;
            while (i4 < i3) {
                arrayList.add(i4 == 0 ? StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE) : stackTrace[i4 + i]);
                i4++;
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList((length - i) + 1);
        arrayList2.add(StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE));
        while (true) {
            i++;
            while (i < length) {
                if (isInternalMethod(stackTrace[i])) {
                    arrayList2.add(stackTrace[i]);
                    int i5 = i + 1;
                    while (i5 < length && isInternalMethod(stackTrace[i5])) {
                        i5++;
                    }
                    int i6 = i5 - 1;
                    int i7 = i6;
                    while (i7 > i && stackTrace[i7].getFileName() == null) {
                        i7--;
                    }
                    if (i7 > i && i7 < i6) {
                        arrayList2.add(stackTrace[i7]);
                    }
                    arrayList2.add(stackTrace[i6]);
                    i = i5;
                }
            }
            return arrayList2;
            arrayList2.add(stackTrace[i]);
        }
    }

    public final void setEnableCreationStackTraces(boolean z) {
        enableCreationStackTraces = z;
    }

    public final void setSanitizeStackTraces(boolean z) {
        sanitizeStackTraces = z;
    }

    public final void startWeakRefCleanerThread() {
        weakRefCleanerThread = ThreadsKt.thread$default(false, true, null, "Coroutines Debugger Cleaner", 0, AnonymousClass1.INSTANCE, 21, null);
    }

    public final void stopWeakRefCleanerThread() throws InterruptedException {
        Thread thread = weakRefCleanerThread;
        if (thread == null) {
            return;
        }
        weakRefCleanerThread = null;
        thread.interrupt();
        thread.join();
    }

    public final StackTraceFrame toStackTraceFrame(List<StackTraceElement> list) {
        StackTraceFrame stackTraceFrame = null;
        if (!list.isEmpty()) {
            ListIterator<StackTraceElement> listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                stackTraceFrame = new StackTraceFrame(stackTraceFrame, listIterator.previous());
            }
        }
        return stackTraceFrame;
    }

    public final String toStringWithQuotes(Object obj) {
        return ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE + obj + '\"';
    }

    public final void uninstall() {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Agent was not installed");
            }
            installations--;
            if (installations != 0) {
                return;
            }
            debugProbesImpl.stopWeakRefCleanerThread();
            capturedCoroutinesMap.clear();
            callerInfoCache.clear();
            AgentInstallationType.INSTANCE.getClass();
            if (AgentInstallationType.isInstalledStatically) {
                while (i < readHoldCount) {
                    lock.lock();
                    i++;
                }
                writeLock.unlock();
                return;
            }
            Function1<Boolean, Unit> function1 = dynamicAttach;
            if (function1 != null) {
                function1.invoke(Boolean.FALSE);
            }
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
            writeLock.unlock();
        } finally {
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final void updateRunningState(CoroutineStackFrame coroutineStackFrame, String str) {
        ReentrantReadWriteLock.ReadLock lock = coroutineStateLock.readLock();
        lock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                lock.unlock();
                return;
            }
            ConcurrentWeakMap<CoroutineStackFrame, DebugCoroutineInfoImpl> concurrentWeakMap = callerInfoCache;
            DebugCoroutineInfoImpl debugCoroutineInfoImplRemove = concurrentWeakMap.remove(coroutineStackFrame);
            if (debugCoroutineInfoImplRemove == null) {
                CoroutineOwner<?> coroutineOwnerOwner = debugProbesImpl.owner(coroutineStackFrame);
                if (coroutineOwnerOwner != null && (debugCoroutineInfoImplRemove = coroutineOwnerOwner.info) != null) {
                    CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = debugCoroutineInfoImplRemove.getLastObservedFrame$kotlinx_coroutines_core();
                    CoroutineStackFrame coroutineStackFrameRealCaller = lastObservedFrame$kotlinx_coroutines_core != null ? debugProbesImpl.realCaller(lastObservedFrame$kotlinx_coroutines_core) : null;
                    if (coroutineStackFrameRealCaller != null) {
                        concurrentWeakMap.remove(coroutineStackFrameRealCaller);
                    }
                }
                return;
            }
            debugCoroutineInfoImplRemove.updateState$kotlinx_coroutines_core(str, (Continuation) coroutineStackFrame);
            CoroutineStackFrame coroutineStackFrameRealCaller2 = debugProbesImpl.realCaller(coroutineStackFrame);
            if (coroutineStackFrameRealCaller2 == null) {
                lock.unlock();
            } else {
                concurrentWeakMap.put(coroutineStackFrameRealCaller2, debugCoroutineInfoImplRemove);
                lock.unlock();
            }
        } finally {
            lock.unlock();
        }
    }

    public final void updateState(Continuation<?> continuation, String str) {
        if (isInstalled$kotlinx_coroutines_core()) {
            if (Intrinsics.areEqual(str, DebugCoroutineInfoImplKt.RUNNING) && KotlinVersion.CURRENT.isAtLeast(1, 3, 30)) {
                CoroutineStackFrame coroutineStackFrame = continuation instanceof CoroutineStackFrame ? (CoroutineStackFrame) continuation : null;
                if (coroutineStackFrame == null) {
                    return;
                }
                updateRunningState(coroutineStackFrame, str);
                return;
            }
            CoroutineOwner<?> coroutineOwnerOwner = owner(continuation);
            if (coroutineOwnerOwner == null) {
                return;
            }
            updateState(coroutineOwnerOwner, continuation, str);
        }
    }

    public final CoroutineOwner<?> owner(CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof CoroutineOwner)) {
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return null;
            }
        }
        return (CoroutineOwner) coroutineStackFrame;
    }

    public final void updateState(CoroutineOwner<?> coroutineOwner, Continuation<?> continuation, String str) {
        ReentrantReadWriteLock.ReadLock lock = coroutineStateLock.readLock();
        lock.lock();
        try {
            if (INSTANCE.isInstalled$kotlinx_coroutines_core()) {
                coroutineOwner.info.updateState$kotlinx_coroutines_core(str, continuation);
            }
        } finally {
            lock.unlock();
        }
    }

    public static /* synthetic */ void getDebugString$annotations(Job job) {
    }
}
