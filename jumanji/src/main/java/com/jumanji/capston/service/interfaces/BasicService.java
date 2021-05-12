package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Employee;
import com.jumanji.capston.data.User;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.List;

public interface BasicService<T, R> {
    public T get(@Nullable String authorization, String... str);
    public List<T> getList(@Nullable String authorization, String... str);
    public T post(@Nullable String authorization, R request);
    public T patch(@Nullable String authorization, R request);
    public void delete(@Nullable String authorization, String... str);



    public T isPresent(String id);
    public boolean isEmpty(String id);
}
