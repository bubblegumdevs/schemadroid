package com.bubblegum.schemadroid.models;

import com.bubblegum.schemadroid.annotations.AutoInc;
import com.bubblegum.schemadroid.annotations.ColumnName;
import com.bubblegum.schemadroid.annotations.PrimaryKey;
import com.bubblegum.schemadroid.annotations.TableName;
import com.bubblegum.schemadroid.model.Tabbable;

@TableName(TableNames.TABLE_STUDENT)
public class Student implements Tabbable {

    @PrimaryKey @AutoInc
    @ColumnName("_id") private long id;
    @ColumnName("name") private String name;
    @ColumnName("gender") private String gender;
}
