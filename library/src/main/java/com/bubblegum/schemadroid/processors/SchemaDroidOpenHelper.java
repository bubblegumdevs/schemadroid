/*
 * Copyright 2015 Bubblegum Developers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bubblegum.schemadroid.processors;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bubblegum.schemadroid.migrations.Migrator;
import com.bubblegum.schemadroid.model.Tabbable;

import java.util.List;

public abstract class SchemaDroidOpenHelper extends SQLiteOpenHelper {

    public SchemaDroidOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SchemaDroidOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Migrator.createVersion(db, getTables());
        if(getCurrentVersionCode() != getFirstVersionCode()) {
            Migrator.migrate(db, getFirstVersionCode(), getCurrentVersionCode(), getTables());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Migrator.migrate(db, oldVersion, newVersion, getTables());
    }

    protected abstract List<Class<? extends Tabbable>> getTables();

    protected abstract int getCurrentVersionCode();

    protected abstract int getFirstVersionCode();
}
