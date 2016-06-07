package com.bubblegum.schemadroid.migrations;

import com.bubblegum.schemadroid.model.Tabbable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Versions {

    public static final class Builder {

        List<Version> versions = new ArrayList<>();

        public Builder add(int versionCode, Class<? extends Tabbable>... tabbables) {
            versions.add(new Version(versionCode, tabbables));
            return this;
        }

        public List<Version> build() {
            Collections.sort(versions);
            return versions;
        }

    }

    public static Builder define() {
        return new Builder();
    }

}
