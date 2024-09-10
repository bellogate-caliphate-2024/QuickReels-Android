package com.bellogatecaliphate.create_post.util.video_trimer.utils;

public class TrimVideoOptions {

    public String fileName;

    public TrimType trimType = TrimType.DEFAULT;

    public long minDuration, fixedDuration;

    public boolean hideSeekBar;

    public boolean accurateCut;

    public boolean showFileLocationAlert;

    public long[] minToMax;

    public String title;

    public String local;

    public CompressOption compressOption;

    public TrimVideoOptions() {
    }

}
