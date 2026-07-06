package org.apache.commons.compress.archivers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class Lister {
    public static final ArchiveStreamFactory factory = new ArchiveStreamFactory();

    public static void main(String[] strArr) throws Exception {
        if (strArr.length == 0) {
            usage();
            return;
        }
        System.out.println("Analysing " + strArr[0]);
        File file = new File(strArr[0]);
        if (!file.isFile()) {
            System.err.println(file + " doesn't exist or is a directory");
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        ArchiveInputStream archiveInputStreamCreateArchiveInputStream = strArr.length > 1 ? factory.createArchiveInputStream(strArr[1], bufferedInputStream) : factory.createArchiveInputStream(bufferedInputStream);
        System.out.println("Created " + archiveInputStreamCreateArchiveInputStream.toString());
        while (true) {
            ArchiveEntry nextEntry = archiveInputStreamCreateArchiveInputStream.getNextEntry();
            if (nextEntry == null) {
                archiveInputStreamCreateArchiveInputStream.close();
                bufferedInputStream.close();
                return;
            }
            System.out.println(nextEntry.getName());
        }
    }

    public static void usage() {
        System.out.println("Parameters: archive-name [archive-type]");
    }
}
