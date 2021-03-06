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

package com.bubblegum.schemadroid.model;

public interface Mappable<M1 extends Tabbable, M2 extends Tabbable> extends Tabbable {

    int TYPE_ONE_TO_ONE = 1;
    int TYPE_ONE_TO_MANY = 2;
    int TYPE_MANY_TO_MANY = 3;

    Class<M1> getFirstModelClass();

    Class<M2> getSecondModelClass();

    int getMappingType();
}
