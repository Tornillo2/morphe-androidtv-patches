package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.StandardSystemProperty;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.io.TempFileCreator;
import j$.util.Objects;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryFlag;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.FileAttribute;
import java.util.EnumSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public abstract class TempFileCreator {
    public static final TempFileCreator INSTANCE = pickSecureCreator();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JavaIoCreator extends TempFileCreator {
        public static final int TEMP_DIR_ATTEMPTS = 10000;

        public JavaIoCreator() {
        }

        @Override // com.google.common.io.TempFileCreator
        public File createTempDir() {
            File file = new File(System.getProperty(StandardSystemProperty.JAVA_IO_TMPDIR.key));
            String str = System.currentTimeMillis() + "-";
            for (int i = 0; i < 10000; i++) {
                File file2 = new File(file, str + i);
                if (file2.mkdir()) {
                    return file2;
                }
            }
            throw new IllegalStateException("Failed to create directory within 10000 attempts (tried " + str + "0 to " + str + "9999)");
        }

        @Override // com.google.common.io.TempFileCreator
        public File createTempFile(String prefix) throws IOException {
            return File.createTempFile(prefix, null, null);
        }

        public JavaIoCreator(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static final class JavaNioCreator extends TempFileCreator {
        public static final PermissionSupplier directoryPermissions;
        public static final PermissionSupplier filePermissions;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @IgnoreJRERequirement
        public interface PermissionSupplier {
            FileAttribute<?> get() throws IOException;
        }

        public static /* synthetic */ FileAttribute $r8$lambda$zMIBKnNU32_2hYUQ1RB0dTuMWHE() throws IOException {
            throw new IOException("unrecognized FileSystem type " + FileSystems.getDefault());
        }

        public static /* synthetic */ FileAttribute $r8$lambda$zVPQICH5lT6vrMqYhoKMRE5aKuQ(IOException iOException) throws IOException {
            throw new IOException("Could not find user", iOException);
        }

        static {
            Set<String> setSupportedFileAttributeViews = FileSystems.getDefault().supportedFileAttributeViews();
            if (setSupportedFileAttributeViews.contains("posix")) {
                filePermissions = new TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda4();
                directoryPermissions = new TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda5();
            } else if (setSupportedFileAttributeViews.contains("acl")) {
                PermissionSupplier permissionSupplierUserPermissions = userPermissions();
                directoryPermissions = permissionSupplierUserPermissions;
                filePermissions = permissionSupplierUserPermissions;
            } else {
                TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda6 tempFileCreator$JavaNioCreator$$ExternalSyntheticLambda6 = new TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda6();
                directoryPermissions = tempFileCreator$JavaNioCreator$$ExternalSyntheticLambda6;
                filePermissions = tempFileCreator$JavaNioCreator$$ExternalSyntheticLambda6;
            }
        }

        public JavaNioCreator() {
        }

        public static String getUsername() {
            String property = System.getProperty(StandardSystemProperty.USER_NAME.key);
            Objects.requireNonNull(property);
            try {
                Class<?> cls = Class.forName("java.lang.ProcessHandle");
                Class<?> cls2 = Class.forName("java.lang.ProcessHandle$Info");
                Class<?> cls3 = Class.forName("j$.util.Optional");
                Method method = cls.getMethod("current", null);
                Method method2 = cls.getMethod("info", null);
                Object objInvoke = cls3.getMethod("orElse", Object.class).invoke(cls2.getMethod("user", null).invoke(method2.invoke(method.invoke(null, null), null), null), property);
                Objects.requireNonNull(objInvoke);
                return (String) objInvoke;
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException unused) {
                return property;
            } catch (InvocationTargetException e) {
                Throwables.throwIfUnchecked(e.getCause());
                return property;
            }
        }

        public static PermissionSupplier userPermissions() {
            try {
                final ImmutableList immutableListOf = ImmutableList.of(AclEntry.newBuilder().setType(AclEntryType.ALLOW).setPrincipal(FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName(getUsername())).setPermissions(EnumSet.allOf(TempFileCreator$JavaNioCreator$$ExternalSyntheticApiModelOutline1.m())).setFlags(AclEntryFlag.DIRECTORY_INHERIT, AclEntryFlag.FILE_INHERIT).build());
                final FileAttribute<ImmutableList<AclEntry>> fileAttribute = new FileAttribute<ImmutableList<AclEntry>>() { // from class: com.google.common.io.TempFileCreator.JavaNioCreator.1
                    @Override // java.nio.file.attribute.FileAttribute
                    public String name() {
                        return "acl:acl";
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.nio.file.attribute.FileAttribute
                    public ImmutableList<AclEntry> value() {
                        return immutableListOf;
                    }
                };
                return new PermissionSupplier() { // from class: com.google.common.io.TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda7
                    @Override // com.google.common.io.TempFileCreator.JavaNioCreator.PermissionSupplier
                    public final FileAttribute get() {
                        FileAttribute fileAttribute2 = fileAttribute;
                        TempFileCreator.JavaNioCreator.m553$r8$lambda$lBeePGvObLwqTe_c7tIZASTFkU(fileAttribute2);
                        return fileAttribute2;
                    }
                };
            } catch (IOException e) {
                return new PermissionSupplier() { // from class: com.google.common.io.TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda8
                    @Override // com.google.common.io.TempFileCreator.JavaNioCreator.PermissionSupplier
                    public final FileAttribute get() throws IOException {
                        TempFileCreator.JavaNioCreator.$r8$lambda$zVPQICH5lT6vrMqYhoKMRE5aKuQ(e);
                        throw null;
                    }
                };
            }
        }

        @Override // com.google.common.io.TempFileCreator
        public File createTempDir() {
            try {
                return java.nio.file.Files.createTempDirectory(Paths.get(System.getProperty(StandardSystemProperty.JAVA_IO_TMPDIR.key), new String[0]), null, directoryPermissions.get()).toFile();
            } catch (IOException e) {
                throw new IllegalStateException("Failed to create directory", e);
            }
        }

        @Override // com.google.common.io.TempFileCreator
        public File createTempFile(String prefix) throws IOException {
            return java.nio.file.Files.createTempFile(Paths.get(System.getProperty(StandardSystemProperty.JAVA_IO_TMPDIR.key), new String[0]), prefix, null, filePermissions.get()).toFile();
        }

        public JavaNioCreator(AnonymousClass1 anonymousClass1) {
        }

        /* JADX INFO: renamed from: $r8$lambda$lBe-ePGvObLwqTe_c7tIZASTFkU, reason: not valid java name */
        public static /* synthetic */ FileAttribute m553$r8$lambda$lBeePGvObLwqTe_c7tIZASTFkU(FileAttribute fileAttribute) {
            return fileAttribute;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ThrowingCreator extends TempFileCreator {
        public static final String MESSAGE = "Guava cannot securely create temporary files or directories under SDK versions before Jelly Bean. You can create one yourself, either in the insecure default directory or in a more secure directory, such as context.getCacheDir(). For more information, see the Javadoc for Files.createTempDir().";

        public ThrowingCreator() {
        }

        @Override // com.google.common.io.TempFileCreator
        public File createTempDir() {
            throw new IllegalStateException(MESSAGE);
        }

        @Override // com.google.common.io.TempFileCreator
        public File createTempFile(String prefix) throws IOException {
            throw new IOException(MESSAGE);
        }

        public ThrowingCreator(AnonymousClass1 anonymousClass1) {
        }
    }

    public TempFileCreator() {
    }

    public static TempFileCreator pickSecureCreator() {
        try {
            try {
                Class.forName("java.nio.file.Path");
                return new JavaNioCreator();
            } catch (ClassNotFoundException unused) {
                return ((Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null)).intValue() < ((Integer) Class.forName("android.os.Build$VERSION_CODES").getField("JELLY_BEAN").get(null)).intValue() ? new ThrowingCreator() : new JavaIoCreator();
            }
        } catch (ClassNotFoundException unused2) {
            return new ThrowingCreator();
        } catch (IllegalAccessException unused3) {
            return new ThrowingCreator();
        } catch (NoSuchFieldException unused4) {
            return new ThrowingCreator();
        }
    }

    @VisibleForTesting
    @IgnoreJRERequirement
    public static void testMakingUserPermissionsFromScratch() throws IOException {
        JavaNioCreator.userPermissions().get();
    }

    public abstract File createTempDir();

    public abstract File createTempFile(String prefix) throws IOException;

    public TempFileCreator(AnonymousClass1 anonymousClass1) {
    }
}
