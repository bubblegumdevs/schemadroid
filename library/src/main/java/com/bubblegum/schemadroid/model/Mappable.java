package com.bubblegum.schemadroid.model;

public interface Mappable<M1 extends Tabbable, M2 extends Tabbable> extends Tabbable {

    int TYPE_ONE_TO_ONE = 1;
    int TYPE_ONE_TO_MANY = 2;
    int TYPE_MANY_TO_MANY = 3;

    Class<M1> getFirstModelClass();

    Class<M2> getSecondModelClass();

    int getMappingType();
}
