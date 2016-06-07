package com.bubblegum.schemadroid.processors;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bubblegum.schemadroid.migrations.Migrator;
import com.bubblegum.schemadroid.migrations.Version;

import java.util.List;

public abstract class SchemaDroidOpenHelper extends SQLiteOpenHelper {

    public SchemaDroidOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SchemaDroidOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public final void onCreate(SQLiteDatabase db) {
        Version firstVersion = getFirstVersion();
        Migrator.createVersion(db, firstVersion);
        if(getCurrentVersionCode() != firstVersion.getVersionCode()) {
            Migrator.migrate(db, firstVersion.getVersionCode(), getCurrentVersionCode(), getVersions());
        }
    }

    @Override
    public final void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Migrator.migrate(db, oldVersion, newVersion, getVersions());
    }

    protected abstract List<Version> getVersions();

    protected abstract int getCurrentVersionCode();

    private Version getFirstVersion() {
        List<Version> versions = getVersions();
        if(versions.size() > 0) {
            return versions.get(0);
        } else {
            throw new IllegalArgumentException("There needs to be at least one version.");
        }
    }
}
