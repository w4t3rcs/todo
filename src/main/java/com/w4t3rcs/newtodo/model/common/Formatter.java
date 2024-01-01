package com.w4t3rcs.newtodo.model.common;

public interface Formatter<K, V> {
    K format(V v);
}
