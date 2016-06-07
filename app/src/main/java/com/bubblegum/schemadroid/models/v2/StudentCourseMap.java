package com.bubblegum.schemadroid.models.v2;

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
