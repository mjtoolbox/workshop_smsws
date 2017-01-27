package com.workshop.dao;

/**
 * Created by mijo on 2017-01-05.
 */
public interface Sequence {
    int getNextSequenceId(String key) throws RuntimeException;
}
