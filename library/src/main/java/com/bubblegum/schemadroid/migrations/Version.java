package com.bubblegum.schemadroid.migrations;

import com.bubblegum.schemadroid.model.Tabbable;

public final class Version implements Comparable<Version> {

    int versionCode;
    Class<? extends Tabbable> []tabbables;

    protected Version(int versionCode, Class<? extends Tabbable>... tabbables) {
        this.versionCode = versionCode;
        this.tabbables = tabbables;
    }


    @Override
    public int compareTo(Version another) {
        return Integer.valueOf(versionCode).compareTo(another.versionCode);
    }

    public int getVersionCode() {
        return versionCode;
    }

    public Class<? extends Tabbable>[] getTabbables() {
        return tabbables;
    }
}
