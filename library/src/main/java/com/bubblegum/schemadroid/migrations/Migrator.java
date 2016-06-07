package com.bubblegum.schemadroid.migrations;

import android.database.sqlite.SQLiteDatabase;

import com.bubblegum.schemadroid.annotations.TableName;
import com.bubblegum.schemadroid.model.Tabbable;

import java.lang.reflect.Field;
import java.util.List;

public class Migrator {

    public static void migrate(SQLiteDatabase db, int oldVersion, int currentVersion, List<Version> versions) {

    }

    public static void createVersion(SQLiteDatabase db, Version version) {
        for(Class<? extends Tabbable> tabbableClass : version.getTabbables()) {
            process(db, tabbableClass);
        }
    }

    private static void process(SQLiteDatabase db, Class<? extends Tabbable> classOfTabbable) {
        String tableName;
        if(classOfTabbable.isAnnotationPresent(TableName.class)) {
            tableName = classOfTabbable.getAnnotation(TableName.class).value();
        } else {
            tableName = classOfTabbable.getSimpleName();
        }
        Field []fields = classOfTabbable.getFields();

    }

//    private static void createTable()

}
