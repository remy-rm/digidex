package com.remyrm.digidex.service;

public interface ApiService<T> {
    T fetchById(long id);
    void saveFromApi(long id);
}
