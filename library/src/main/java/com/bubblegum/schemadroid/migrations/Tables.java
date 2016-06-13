package com.bubblegum.schemadroid.migrations;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bubblegum.schemadroid.annotations.ColumnName;
import com.bubblegum.schemadroid.annotations.TableName;
import com.bubblegum.schemadroid.model.Tabbable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Tables {

    public static class Builder {

        List<Class<? extends Tabbable>> tables = new ArrayList<>();

        public Builder add(Class<? extends Tabbable> tabbableClass) {
            tables.add(tabbableClass);
            return this;
        }

        public List<Class<? extends Tabbable>> get() {
            return tables;
        }

    }

    public static <T extends Tabbable> List<T> get(SQLiteDatabase db, Class<T> classOfT) throws InstantiationException, IllegalAccessException {
        return get(db, classOfT, null, null);
    }

    public static <T extends Tabbable> List<T> get(SQLiteDatabase db, Class<T> classOfT, String selection, String[] selectionArgs) throws IllegalAccessException, InstantiationException {
        return get(db, classOfT, false, selection, selectionArgs, null, null, null, null);
    }

    public static <T extends Tabbable> List<T> get(SQLiteDatabase db, Class<T> classOfT, boolean distinct, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) throws InstantiationException, IllegalAccessException {
        String table = getTableName(classOfT);
        List<T> values = new ArrayList<>();
        Cursor cursor = db.query(distinct, table, null, selection, selectionArgs, groupBy, having, orderBy, limit);
        if(cursor.moveToFirst()) {
            do {
                values.add(getObjectFromCursor(classOfT, cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return values;
    }

    private static <T extends Tabbable> T getObjectFromCursor(Class<T> classOfT, Cursor cursor) throws IllegalAccessException, InstantiationException {
        Field []fields = classOfT.getFields();
        T object = classOfT.newInstance();
        for(Field field : fields) {
            if(field.isAnnotationPresent(ColumnName.class)) {
                Object value = getFieldValueFromCursor(field, cursor, cursor.getColumnIndex(field.getAnnotation(ColumnName.class).value()));
                field.set(object, value);
            }
        }
        return object;
    }

    private static Object getFieldValueFromCursor(Field field, Cursor cursor, int index) {
        Class<?> type = field.getType();
        if(type.isAssignableFrom(Double.class)) {
            return cursor.getDouble(index);
        }
        if(type.isAssignableFrom(Float.class)) {
            return cursor.getFloat(index);
        }
        if(type.isAssignableFrom(Integer.class)) {
            return cursor.getInt(index);
        }
        if(type.isAssignableFrom(String.class) || type.isAssignableFrom(CharSequence.class)) {
            return cursor.getString(index);
        }
        return cursor.getDouble(index);
    }

    protected static String getTableName(Class<? extends Tabbable> classOfTabbable) {
        String tableName;
        if(classOfTabbable.isAnnotationPresent(TableName.class)) {
            tableName = classOfTabbable.getAnnotation(TableName.class).value();
        } else {
            tableName = classOfTabbable.getSimpleName();
        }
        return tableName;
    }

}
