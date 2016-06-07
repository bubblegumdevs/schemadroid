package com.bubblegum.schemadroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bubblegum.schemadroid.migrations.Version;
import com.bubblegum.schemadroid.migrations.Versions;
import com.bubblegum.schemadroid.processors.SchemaDroidOpenHelper;

import java.util.List;

public class DemoOpenHelper extends SchemaDroidOpenHelper {

    private static final int CURRENT_DATABASE_VERSION = 1;

    public DemoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected List<Version> getVersions() {
        return Versions.define()
                .add(1, com.bubblegum.schemadroid.models.v1.Student.class,
                        com.bubblegum.schemadroid.models.v1.Course.class,
                        com.bubblegum.schemadroid.models.v1.StudentCourseMap.class)
//                .add(2, com.bubblegum.schemadroid.models.v2.Student.class,
//                        com.bubblegum.schemadroid.models.v2.Course.class,
//                        com.bubblegum.schemadroid.models.v2.StudentCourseMap.class)
                .build();
    }

    @Override
    protected int getCurrentVersionCode() {
        return CURRENT_DATABASE_VERSION;
    }
}
