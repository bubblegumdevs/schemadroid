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
