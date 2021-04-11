package com.Oguz.engine.Vector;

public interface IVector<T> {
    T add(T one, T two);
    T add(T one, float two);
    T sum(T one, T two);
    T sum(T one, float two);
    T multi(T one, T two);
    T multi(T one, float two);
    T div(T one, T two);
    T div(T one, float two);
}
