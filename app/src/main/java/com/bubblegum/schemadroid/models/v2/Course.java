package com.bubblegum.schemadroid.models.v2;

import com.bubblegum.schemadroid.annotations.ColumnName;
import com.bubblegum.schemadroid.annotations.NotNull;
import com.bubblegum.schemadroid.annotations.PrimaryKey;
import com.bubblegum.schemadroid.annotations.TableName;
import com.bubblegum.schemadroid.model.Tabbable;

@TableName(Tables.TABLE_COURSE)
public class Course implements Tabbable {

    @PrimaryKey @NotNull
    @ColumnName("_id") private long id;
    @ColumnName("name") private String name;
}
