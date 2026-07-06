package kotlin.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FileTreeWalk implements Sequence<File> {

    @NotNull
    public final FileWalkDirection direction;
    public final int maxDepth;

    @Nullable
    public final Function1<File, Boolean> onEnter;

    @Nullable
    public final Function2<File, IOException, Unit> onFail;

    @Nullable
    public final Function1<File, Unit> onLeave;

    @NotNull
    public final File start;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nFileTreeWalk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileTreeWalk.kt\nkotlin/io/FileTreeWalk$DirectoryState\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,273:1\n1#2:274\n*E\n"})
    public static abstract class DirectoryState extends WalkState {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DirectoryState(@NotNull File rootDir) {
            super(rootDir);
            Intrinsics.checkNotNullParameter(rootDir, "rootDir");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class FileTreeWalkIterator extends AbstractIterator<File> {

        @NotNull
        public final ArrayDeque<WalkState> state;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final class BottomUpDirectoryState extends DirectoryState {
            public boolean failed;
            public int fileIndex;

            @Nullable
            public File[] fileList;
            public boolean rootVisited;
            public final /* synthetic */ FileTreeWalkIterator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BottomUpDirectoryState(@NotNull FileTreeWalkIterator fileTreeWalkIterator, File rootDir) {
                super(rootDir);
                Intrinsics.checkNotNullParameter(rootDir, "rootDir");
                this.this$0 = fileTreeWalkIterator;
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            @Nullable
            public File step() {
                if (!this.failed && this.fileList == null) {
                    Function1<File, Boolean> function1 = FileTreeWalk.this.onEnter;
                    if (function1 != null && !function1.invoke(this.root).booleanValue()) {
                        return null;
                    }
                    File[] fileArrListFiles = this.root.listFiles();
                    this.fileList = fileArrListFiles;
                    if (fileArrListFiles == null) {
                        Function2<File, IOException, Unit> function2 = FileTreeWalk.this.onFail;
                        if (function2 != null) {
                            function2.invoke(this.root, new AccessDeniedException(this.root, null, "Cannot list files in a directory", 2, null));
                        }
                        this.failed = true;
                    }
                }
                File[] fileArr = this.fileList;
                if (fileArr != null) {
                    int i = this.fileIndex;
                    Intrinsics.checkNotNull(fileArr);
                    if (i < fileArr.length) {
                        File[] fileArr2 = this.fileList;
                        Intrinsics.checkNotNull(fileArr2);
                        int i2 = this.fileIndex;
                        this.fileIndex = i2 + 1;
                        return fileArr2[i2];
                    }
                }
                if (!this.rootVisited) {
                    this.rootVisited = true;
                    return this.root;
                }
                Function1<File, Unit> function12 = FileTreeWalk.this.onLeave;
                if (function12 != null) {
                    function12.invoke(this.root);
                }
                return null;
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @SourceDebugExtension({"SMAP\nFileTreeWalk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileTreeWalk.kt\nkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,273:1\n1#2:274\n*E\n"})
        public final class SingleFileState extends WalkState {
            public final /* synthetic */ FileTreeWalkIterator this$0;
            public boolean visited;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public SingleFileState(@NotNull FileTreeWalkIterator fileTreeWalkIterator, File rootFile) {
                super(rootFile);
                Intrinsics.checkNotNullParameter(rootFile, "rootFile");
                this.this$0 = fileTreeWalkIterator;
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            @Nullable
            public File step() {
                if (this.visited) {
                    return null;
                }
                this.visited = true;
                return this.root;
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final class TopDownDirectoryState extends DirectoryState {
            public int fileIndex;

            @Nullable
            public File[] fileList;
            public boolean rootVisited;
            public final /* synthetic */ FileTreeWalkIterator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public TopDownDirectoryState(@NotNull FileTreeWalkIterator fileTreeWalkIterator, File rootDir) {
                super(rootDir);
                Intrinsics.checkNotNullParameter(rootDir, "rootDir");
                this.this$0 = fileTreeWalkIterator;
            }

            /* JADX WARN: Code restructure failed: missing block: B:30:0x006c, code lost:
            
                if (r0.length == 0) goto L31;
             */
            @Override // kotlin.io.FileTreeWalk.WalkState
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.io.File step() {
                /*
                    r9 = this;
                    boolean r0 = r9.rootVisited
                    r1 = 0
                    if (r0 != 0) goto L22
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r9.this$0
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1<java.io.File, java.lang.Boolean> r0 = r0.onEnter
                    if (r0 == 0) goto L1c
                    java.io.File r2 = r9.root
                    java.lang.Object r0 = r0.invoke(r2)
                    java.lang.Boolean r0 = (java.lang.Boolean) r0
                    boolean r0 = r0.booleanValue()
                    if (r0 != 0) goto L1c
                    return r1
                L1c:
                    r0 = 1
                    r9.rootVisited = r0
                    java.io.File r0 = r9.root
                    return r0
                L22:
                    java.io.File[] r0 = r9.fileList
                    if (r0 == 0) goto L3d
                    int r2 = r9.fileIndex
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                    int r0 = r0.length
                    if (r2 >= r0) goto L2f
                    goto L3d
                L2f:
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r9.this$0
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1<java.io.File, kotlin.Unit> r0 = r0.onLeave
                    if (r0 == 0) goto L3c
                    java.io.File r2 = r9.root
                    r0.invoke(r2)
                L3c:
                    return r1
                L3d:
                    java.io.File[] r0 = r9.fileList
                    if (r0 != 0) goto L7c
                    java.io.File r0 = r9.root
                    java.io.File[] r0 = r0.listFiles()
                    r9.fileList = r0
                    if (r0 != 0) goto L64
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r9.this$0
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function2<java.io.File, java.io.IOException, kotlin.Unit> r0 = r0.onFail
                    if (r0 == 0) goto L64
                    java.io.File r2 = r9.root
                    kotlin.io.AccessDeniedException r3 = new kotlin.io.AccessDeniedException
                    java.io.File r4 = r9.root
                    r7 = 2
                    r8 = 0
                    r5 = 0
                    java.lang.String r6 = "Cannot list files in a directory"
                    r3.<init>(r4, r5, r6, r7, r8)
                    r0.invoke(r2, r3)
                L64:
                    java.io.File[] r0 = r9.fileList
                    if (r0 == 0) goto L6e
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                    int r0 = r0.length
                    if (r0 != 0) goto L7c
                L6e:
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r9.this$0
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1<java.io.File, kotlin.Unit> r0 = r0.onLeave
                    if (r0 == 0) goto L7b
                    java.io.File r2 = r9.root
                    r0.invoke(r2)
                L7b:
                    return r1
                L7c:
                    java.io.File[] r0 = r9.fileList
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                    int r1 = r9.fileIndex
                    int r2 = r1 + 1
                    r9.fileIndex = r2
                    r0 = r0[r1]
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FileTreeWalk.FileTreeWalkIterator.TopDownDirectoryState.step():java.io.File");
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[FileWalkDirection.values().length];
                try {
                    iArr[FileWalkDirection.TOP_DOWN.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[FileWalkDirection.BOTTOM_UP.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public FileTreeWalkIterator() {
            ArrayDeque<WalkState> arrayDeque = new ArrayDeque<>();
            this.state = arrayDeque;
            if (FileTreeWalk.this.start.isDirectory()) {
                arrayDeque.push(directoryState(FileTreeWalk.this.start));
            } else if (FileTreeWalk.this.start.isFile()) {
                arrayDeque.push(new SingleFileState(this, FileTreeWalk.this.start));
            } else {
                super.state = 2;
            }
        }

        @Override // kotlin.collections.AbstractIterator
        public void computeNext() {
            File fileGotoNext = gotoNext();
            if (fileGotoNext != null) {
                setNext(fileGotoNext);
            } else {
                super.state = 2;
            }
        }

        public final DirectoryState directoryState(File file) {
            int i = WhenMappings.$EnumSwitchMapping$0[FileTreeWalk.this.direction.ordinal()];
            if (i == 1) {
                return new TopDownDirectoryState(this, file);
            }
            if (i == 2) {
                return new BottomUpDirectoryState(this, file);
            }
            throw new NoWhenBranchMatchedException();
        }

        public final File gotoNext() {
            File fileStep;
            while (true) {
                WalkState walkStatePeek = this.state.peek();
                if (walkStatePeek == null) {
                    return null;
                }
                fileStep = walkStatePeek.step();
                if (fileStep == null) {
                    this.state.pop();
                } else {
                    if (fileStep.equals(walkStatePeek.root) || !fileStep.isDirectory() || this.state.size() >= FileTreeWalk.this.maxDepth) {
                        break;
                    }
                    this.state.push(directoryState(fileStep));
                }
            }
            return fileStep;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class WalkState {

        @NotNull
        public final File root;

        public WalkState(@NotNull File root) {
            Intrinsics.checkNotNullParameter(root, "root");
            this.root = root;
        }

        @NotNull
        public final File getRoot() {
            return this.root;
        }

        @Nullable
        public abstract File step();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FileTreeWalk(File file, FileWalkDirection fileWalkDirection, Function1<? super File, Boolean> function1, Function1<? super File, Unit> function12, Function2<? super File, ? super IOException, Unit> function2, int i) {
        this.start = file;
        this.direction = fileWalkDirection;
        this.onEnter = function1;
        this.onLeave = function12;
        this.onFail = function2;
        this.maxDepth = i;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<File> iterator() {
        return new FileTreeWalkIterator();
    }

    @NotNull
    public final FileTreeWalk maxDepth(int i) {
        if (i > 0) {
            return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, this.onFail, i);
        }
        throw new IllegalArgumentException("depth must be positive, but was " + i + '.');
    }

    @NotNull
    public final FileTreeWalk onEnter(@NotNull Function1<? super File, Boolean> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new FileTreeWalk(this.start, this.direction, function, this.onLeave, this.onFail, this.maxDepth);
    }

    @NotNull
    public final FileTreeWalk onFail(@NotNull Function2<? super File, ? super IOException, Unit> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, function, this.maxDepth);
    }

    @NotNull
    public final FileTreeWalk onLeave(@NotNull Function1<? super File, Unit> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new FileTreeWalk(this.start, this.direction, this.onEnter, function, this.onFail, this.maxDepth);
    }

    public /* synthetic */ FileTreeWalk(File file, FileWalkDirection fileWalkDirection, Function1 function1, Function1 function12, Function2 function2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i2 & 2) != 0 ? FileWalkDirection.TOP_DOWN : fileWalkDirection, function1, function12, function2, (i2 & 32) != 0 ? Integer.MAX_VALUE : i);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FileTreeWalk(@NotNull File start, @NotNull FileWalkDirection direction) {
        this(start, direction, null, null, null, 0, 32, null);
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(direction, "direction");
    }

    public /* synthetic */ FileTreeWalk(File file, FileWalkDirection fileWalkDirection, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i & 2) != 0 ? FileWalkDirection.TOP_DOWN : fileWalkDirection);
    }
}
