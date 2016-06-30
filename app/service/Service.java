package service;


import mysql.dao.ScopedContext;

import java.util.function.Supplier;

/**
 * Created by zg on 2016/6/30.
 */
public class Service {


    public <T> T excutor(Supplier<T> supplier, ScopedContext context) {
        try {
            return supplier.get();
        } finally {
            try {
                context.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    protected ScopedContext newContext() {
        return new ScopedContext();
    }
}
