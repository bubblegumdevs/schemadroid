package com.bubblegum.schemadroid.migrations;

import android.database.sqlite.SQLiteDatabase;

import com.bubblegum.schemadroid.annotations.AutoInc;
import com.bubblegum.schemadroid.annotations.ColumnName;
import com.bubblegum.schemadroid.annotations.NotNull;
import com.bubblegum.schemadroid.annotations.PrimaryKey;
import com.bubblegum.schemadroid.model.Tabbable;

import java.lang.reflect.Field;
import java.util.List;

public class Migrator {

    public static void migrate(SQLiteDatabase db, int oldVersion, int currentVersion, List<Class<? extends Tabbable>> tabbables) {

    }

    public static void createVersion(SQLiteDatabase db, List<Class<? extends Tabbable>> tabbables) {
        for(Class<? extends Tabbable> tabbableClass : tabbables) {
            process(db, tabbableClass);
        }
    }

    private static void process(SQLiteDatabase db, Class<? extends Tabbable> classOfTabbable) {
        String tableName = Tables.getTableName(classOfTabbable);
        Field []fields = classOfTabbable.getFields();
        createTable(db, tableName, fields);
    }

    private static void createTable(SQLiteDatabase db, String tableName, Field []fields) {
        StringBuilder tableBuilder = new StringBuilder().append("CREATE TABLE ").append(tableName).append(" (");
        boolean isFirst = false;
        for(Field field : fields) {
            processFields(tableBuilder, field, isFirst);
            isFirst = true;
        }
        tableBuilder.append(")");
        db.execSQL(tableBuilder.toString());
    }

    private static void processFields(StringBuilder tableBuilder, Field field, boolean firstField) {
        if(field.isAnnotationPresent(ColumnName.class)) {
            if(!firstField) {
                tableBuilder.append(", ");
            }
            String columnName = field.getAnnotation(ColumnName.class).value();
            String type = getType(field);
            boolean isNotNull = field.isAnnotationPresent(NotNull.class);
            boolean isPrimaryKey = field.isAnnotationPresent(PrimaryKey.class);
            boolean isAutoIncrement = field.isAnnotationPresent(AutoInc.class);
            tableBuilder.append(columnName).append(" ").append(type);
            if(isNotNull) {
                tableBuilder.append(columnName).append(" ").append("NOT NULL");
            }
            if(isPrimaryKey) {
                tableBuilder.append(columnName).append(" ").append("PRIMARY KEY");
            }
            if(isAutoIncrement) {
                tableBuilder.append(columnName).append(" ").append("AUTOINCREMENT");
            }
        }
    }

    private static String getType(Field field) {
        Class<?> type = field.getType();
        if(type.isAssignableFrom(Double.class)) {
            return "DOUBLE";
        }
        if(type.isAssignableFrom(Float.class)) {
            return "FLOAT";
        }
        if(type.isAssignableFrom(Integer.class)) {
            return "INT";
        }
        if(type.isAssignableFrom(String.class) || type.isAssignableFrom(CharSequence.class)) {
            return "TEXT";
        }
        return "TEXT";
    }

}
