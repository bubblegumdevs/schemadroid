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

package com.bubblegum.schemadroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bubblegum.schemadroid.migrations.Tables;
import com.bubblegum.schemadroid.model.Tabbable;
import com.bubblegum.schemadroid.models.Course;
import com.bubblegum.schemadroid.models.Student;
import com.bubblegum.schemadroid.models.StudentCourseMap;
import com.bubblegum.schemadroid.processors.SchemaDroidOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DemoOpenHelper extends SchemaDroidOpenHelper {

    private static final int CURRENT_DATABASE_VERSION = 1;
    private static final int FIRST_DATABASE_VERSION = 1;

    public DemoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    protected List<Class<? extends Tabbable>> getTables() {
        return new Tables.Builder()
                .add(Student.class)
                .add(Course.class)
                .add(StudentCourseMap.class)
                .get();
    }

    public List<Student> getStudents() {
        try {
            return Tables.get(getReadableDatabase(), Student.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    protected int getCurrentVersionCode() {
        return CURRENT_DATABASE_VERSION;
    }

    @Override
    protected int getFirstVersionCode() {
        return FIRST_DATABASE_VERSION;
    }
}
