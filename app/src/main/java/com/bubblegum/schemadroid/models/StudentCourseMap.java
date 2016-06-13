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

package com.bubblegum.schemadroid.models;

import com.bubblegum.schemadroid.annotations.ColumnName;
import com.bubblegum.schemadroid.annotations.ForeignKey;
import com.bubblegum.schemadroid.annotations.TableName;
import com.bubblegum.schemadroid.model.Mappable;

@TableName("student_course_map")
public class StudentCourseMap implements Mappable<Student, Course> {

    @ForeignKey(references = Student.class)
    @ColumnName("studentId") private long studentId;
    @ForeignKey(references = Course.class)
    @ColumnName("courseId") private long courseId;

    @Override
    public Class<Student> getFirstModelClass() {
        return Student.class;
    }

    @Override
    public Class<Course> getSecondModelClass() {
        return Course.class;
    }

    @Override
    public int getMappingType() {
        return TYPE_MANY_TO_MANY;
    }
}
