package org.apache.commons.compress.archivers.dump;

import java.util.Date;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class DumpArchiveSummary {
    public String devname;
    public long dumpDate;
    public String filesys;
    public int firstrec;
    public int flags;
    public String hostname;
    public String label;
    public int level;
    public int ntrec;
    public long previousDumpDate;
    public int volume;

    public DumpArchiveSummary(byte[] bArr) {
        this.dumpDate = ((long) DumpArchiveUtil.convert32(bArr, 4)) * 1000;
        this.previousDumpDate = ((long) DumpArchiveUtil.convert32(bArr, 8)) * 1000;
        this.volume = DumpArchiveUtil.convert32(bArr, 12);
        this.label = new String(bArr, 676, 16).trim();
        this.level = DumpArchiveUtil.convert32(bArr, 692);
        this.filesys = new String(bArr, 696, 64).trim();
        this.devname = new String(bArr, 760, 64).trim();
        this.hostname = new String(bArr, 824, 64).trim();
        this.flags = DumpArchiveUtil.convert32(bArr, 888);
        this.firstrec = DumpArchiveUtil.convert32(bArr, 892);
        this.ntrec = DumpArchiveUtil.convert32(bArr, 896);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        DumpArchiveSummary dumpArchiveSummary = (DumpArchiveSummary) obj;
        return this.dumpDate == dumpArchiveSummary.dumpDate && getHostname() != null && getHostname().equals(dumpArchiveSummary.getHostname()) && getDevname() != null && getDevname().equals(dumpArchiveSummary.getDevname());
    }

    public String getDevname() {
        return this.devname;
    }

    public Date getDumpDate() {
        return new Date(this.dumpDate);
    }

    public String getFilesystem() {
        return this.filesys;
    }

    public int getFirstRecord() {
        return this.firstrec;
    }

    public int getFlags() {
        return this.flags;
    }

    public String getHostname() {
        return this.hostname;
    }

    public String getLabel() {
        return this.label;
    }

    public int getLevel() {
        return this.level;
    }

    public int getNTRec() {
        return this.ntrec;
    }

    public Date getPreviousDumpDate() {
        return new Date(this.previousDumpDate);
    }

    public int getVolume() {
        return this.volume;
    }

    public int hashCode() {
        String str = this.label;
        int iHashCode = (int) ((this.dumpDate * 31) + ((long) (str != null ? str.hashCode() : 17)));
        String str2 = this.hostname;
        if (str2 != null) {
            iHashCode = (str2.hashCode() * 31) + 17;
        }
        String str3 = this.devname;
        return str3 != null ? (str3.hashCode() * 31) + 17 : iHashCode;
    }

    public boolean isCompressed() {
        return (this.flags & 128) == 128;
    }

    public boolean isExtendedAttributes() {
        return (this.flags & 32768) == 32768;
    }

    public boolean isMetaDataOnly() {
        return (this.flags & 256) == 256;
    }

    public boolean isNewHeader() {
        return (this.flags & 1) == 1;
    }

    public boolean isNewInode() {
        return (this.flags & 2) == 2;
    }

    public void setDevname(String str) {
        this.devname = str;
    }

    public void setDumpDate(Date date) {
        this.dumpDate = date.getTime();
    }

    public void setFilesystem(String str) {
        this.filesys = str;
    }

    public void setFirstRecord(int i) {
        this.firstrec = i;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public void setHostname(String str) {
        this.hostname = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public void setNTRec(int i) {
        this.ntrec = i;
    }

    public void setPreviousDumpDate(Date date) {
        this.previousDumpDate = date.getTime();
    }

    public void setVolume(int i) {
        this.volume = i;
    }
}
