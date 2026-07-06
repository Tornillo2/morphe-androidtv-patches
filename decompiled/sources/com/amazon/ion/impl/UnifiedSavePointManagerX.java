package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class UnifiedSavePointManagerX {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int FREE_LIST_LIMIT = 20;
    public UnifiedInputBufferX _buffer;
    public int _free_count;
    public int _open_save_points;
    public UnifiedInputStreamX _stream;
    public SavePoint _inuse = null;
    public SavePoint _free = null;
    public SavePoint _active_stack = null;

    public UnifiedSavePointManagerX(UnifiedInputStreamX unifiedInputStreamX) {
        this._stream = unifiedInputStreamX;
        this._buffer = unifiedInputStreamX._buffer;
    }

    public static /* synthetic */ SavePoint access$700(UnifiedSavePointManagerX unifiedSavePointManagerX, SavePoint savePoint, long j, long j2) {
        unifiedSavePointManagerX.save_point_start(savePoint, j, j2);
        return savePoint;
    }

    public final void end_point_too_far(int i) {
        throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("end point [", i, "] must be within 1 page of current ["), this._buffer._buffer_current, "]"));
    }

    public final boolean isSavePointOpen() {
        return this._open_save_points > 0;
    }

    public final long lengthOf(SavePoint savePoint) {
        int i = savePoint._start_idx;
        int i2 = savePoint._end_idx;
        if (i == -1 || i2 == -1) {
            return 0L;
        }
        if (i == i2) {
            return savePoint._end_pos - savePoint._start_pos;
        }
        UnifiedDataPageX page = this._buffer.getPage(i);
        return (this._buffer.getPage(i2)._file_offset + ((long) savePoint._end_pos)) - (page._file_offset + ((long) savePoint._start_pos));
    }

    public final SavePoint savePointActiveTop() {
        return this._active_stack;
    }

    public final SavePoint savePointAllocate() {
        SavePoint savePoint = this._free;
        if (savePoint != null) {
            this._free = savePoint._next;
            this._free_count--;
            savePoint.clear();
        } else {
            savePoint = new SavePoint(this);
        }
        SavePoint savePoint2 = this._inuse;
        savePoint._next = savePoint2;
        savePoint._prev = null;
        if (savePoint2 != null) {
            savePoint2._prev = savePoint;
            return savePoint;
        }
        this._inuse = savePoint;
        return savePoint;
    }

    public final void savePointFree(SavePoint savePoint) {
        int i = this._free_count;
        if (i >= 20) {
            return;
        }
        if (savePoint._prev == null) {
            savePoint._prev = savePoint._next;
        } else {
            this._inuse = savePoint._next;
        }
        SavePoint savePoint2 = savePoint._next;
        if (savePoint2 != null) {
            savePoint2._prev = savePoint._prev;
        }
        savePoint._next = this._free;
        this._free = savePoint;
        this._free_count = i + 1;
    }

    public final void savePointPopActive(SavePoint savePoint) {
        if (savePoint != this._active_stack) {
            throw new IllegalArgumentException("save point being released isn't currently active");
        }
        this._active_stack = savePoint._next_active;
        savePoint._next_active = null;
        savePoint._state = SavePoint.SavePointState.DEFINED;
        this._stream.save_point_reset_to_prev(savePoint);
    }

    public final void savePointPushActive(SavePoint savePoint, long j, long j2) {
        UnifiedInputBufferX unifiedInputBufferX = this._buffer;
        int i = unifiedInputBufferX._buffer_current;
        UnifiedInputStreamX unifiedInputStreamX = this._stream;
        int i2 = unifiedInputStreamX._pos;
        int i3 = unifiedInputStreamX._limit;
        unifiedInputBufferX.getPage(i);
        savePoint.set_prev_pos(i, i2, i3, j, j2);
        savePoint._next_active = this._active_stack;
        this._active_stack = savePoint;
        savePoint._state = SavePoint.SavePointState.ACTIVE;
        int i4 = savePoint._start_idx;
        int i5 = savePoint._start_pos;
        UnifiedDataPageX page = this._buffer.getPage(i4);
        this._stream.make_page_current(page, i4, i5, savePoint._end_idx != savePoint._start_idx ? page.getBufferLimit() : savePoint._end_pos);
    }

    public final void save_point_clear(SavePoint savePoint) {
        if (savePoint.isClear()) {
            return;
        }
        int i = savePoint._start_idx;
        if ((savePoint._end_idx == -1 && i == -1) || i == -1) {
            return;
        }
        this._open_save_points--;
        save_point_unpin(savePoint);
    }

    public final void save_point_mark_end(SavePoint savePoint, int i) {
        if (savePoint.isActive()) {
            throw new IllegalArgumentException("you can't start an active save point");
        }
        UnifiedDataPageX currentPage = this._buffer.getCurrentPage();
        int i2 = this._buffer._buffer_current;
        int bufferLimit = this._stream._pos + i;
        if (i != 0) {
            if (bufferLimit >= currentPage.getBufferLimit()) {
                bufferLimit -= currentPage.getOriginalStartingOffset();
                i2++;
                currentPage = this._buffer.getPage(i2);
            } else if (bufferLimit < currentPage.getStartingOffset()) {
                int originalStartingOffset = bufferLimit - currentPage.getOriginalStartingOffset();
                i2--;
                currentPage = this._buffer.getPage(i2);
                bufferLimit = currentPage.getBufferLimit() - originalStartingOffset;
            }
            if (currentPage == null || bufferLimit >= currentPage.getBufferLimit() || bufferLimit < currentPage.getStartingOffset()) {
                end_point_too_far(i2);
                throw null;
            }
        }
        savePoint._end_idx = i2;
        savePoint._end_pos = bufferLimit;
    }

    public final SavePoint save_point_start(SavePoint savePoint, long j, long j2) {
        if (savePoint.isDefined()) {
            throw new IllegalArgumentException("you can't start an active save point");
        }
        UnifiedInputBufferX unifiedInputBufferX = this._buffer;
        int i = unifiedInputBufferX._buffer_current;
        unifiedInputBufferX.incLock();
        savePoint.set_start_pos(i, this._stream._pos, j, j2);
        this._open_save_points++;
        return savePoint;
    }

    public final void save_point_unpin(SavePoint savePoint) {
        if (savePoint.isActive()) {
            throw new IllegalArgumentException("you can't release an active save point");
        }
        if (this._buffer.decLock() && this._open_save_points == 0) {
            this._buffer.resetToCurrentPage();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SavePoint {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public int _end_idx;
        public int _end_pos;
        public SavePoint _next;
        public SavePoint _next_active;
        public UnifiedSavePointManagerX _owner;
        public SavePoint _prev;
        public int _prev_idx;
        public int _prev_limit;
        public long _prev_line_count;
        public long _prev_line_start;
        public int _prev_pos;
        public int _start_idx;
        public long _start_line_count;
        public long _start_line_start;
        public int _start_pos;
        public SavePointState _state;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public enum SavePointState {
            CLEAR,
            DEFINED,
            ACTIVE
        }

        public SavePoint(UnifiedSavePointManagerX unifiedSavePointManagerX) {
            clear();
            this._owner = unifiedSavePointManagerX;
        }

        public static void access$500(SavePoint savePoint, int i, int i2) {
            savePoint._end_idx = i;
            savePoint._end_pos = i2;
        }

        public final void clear() {
            if (isDefined()) {
                this._owner.save_point_clear(this);
            }
            this._state = SavePointState.CLEAR;
            this._start_idx = -1;
            this._end_idx = -1;
            this._prev_idx = -1;
        }

        public final void free() {
            this._owner.savePointFree(this);
        }

        public final long getEndFilePosition() {
            int i = this._end_idx;
            if (i == -1) {
                return -1L;
            }
            return this._owner._buffer.getPage(i)._file_offset + ((long) this._end_pos);
        }

        public final int getEndIdx() {
            return this._end_idx;
        }

        public final int getEndPos() {
            return this._end_pos;
        }

        public final int getPrevIdx() {
            return this._prev_idx;
        }

        public final int getPrevLimit() {
            return this._prev_limit;
        }

        public final long getPrevLineNumber() {
            return this._prev_line_count;
        }

        public final long getPrevLineStart() {
            return this._prev_line_start;
        }

        public final int getPrevPos() {
            return this._prev_pos;
        }

        public final long getStartFilePosition() {
            int i = this._start_idx;
            if (i == -1) {
                return -1L;
            }
            return this._owner._buffer.getPage(i)._file_offset + ((long) this._start_pos);
        }

        public final int getStartIdx() {
            return this._start_idx;
        }

        public final long getStartLineNumber() {
            return this._start_line_count;
        }

        public final long getStartLineStart() {
            return this._start_line_start;
        }

        public final int getStartPos() {
            return this._start_pos;
        }

        public final boolean isActive() {
            return this._state == SavePointState.ACTIVE;
        }

        public final boolean isClear() {
            return this._state == SavePointState.CLEAR;
        }

        public final boolean isDefined() {
            SavePointState savePointState = this._state;
            return savePointState == SavePointState.DEFINED || savePointState == SavePointState.ACTIVE;
        }

        public final long length() {
            if (this._start_idx == -1 || this._end_idx == -1) {
                return 0L;
            }
            return this._owner.lengthOf(this);
        }

        public final void markEnd() {
            this._owner.save_point_mark_end(this, 0);
        }

        public final void set_active() {
            this._state = SavePointState.ACTIVE;
        }

        public final void set_end_pos(int i, int i2) {
            this._end_idx = i;
            this._end_pos = i2;
        }

        public final void set_inactive() {
            this._state = SavePointState.DEFINED;
        }

        public final void set_prev_pos(int i, int i2, int i3, long j, long j2) {
            this._prev_idx = i;
            this._prev_pos = i2;
            this._prev_limit = i3;
            this._prev_line_count = j;
            this._prev_line_start = j2;
        }

        public final void set_start_pos(int i, int i2, long j, long j2) {
            this._state = SavePointState.DEFINED;
            this._start_idx = i;
            this._start_pos = i2;
            this._start_line_count = j;
            this._start_line_start = j2;
        }

        public final void start(long j, long j2) {
            this._owner.save_point_start(this, j, j2);
        }

        public final void markEnd(int i) {
            this._owner.save_point_mark_end(this, i);
        }
    }
}
