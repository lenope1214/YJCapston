package com.jumanji.capston.service.interfaces;

import org.springframework.lang.Nullable;


import java.util.List;

public interface BasicService<T, R, PK> {
    public T get(@Nullable String authorization, String... str);
    public List<T> getList(@Nullable String authorization, String... str);
    public T post(@Nullable String authorization, R request);
    public T patch(@Nullable String authorization, R request);
    public void delete(@Nullable String authorization, String... str);



    public T isPresent(PK id);
    public boolean isEmpty(PK id);
}
