package dao;

import java.util.ArrayList;

public interface DAOInterface<T> {
    int insert(T value);

    int update(T value);

    int delete(T value);

    ArrayList<T> selectAll();

    T selectByID(T value);

    ArrayList<T> selectByCondition(String condition);

    int deleteByCondition(String condition);

    public String findIdCustomer(String condition);
}
