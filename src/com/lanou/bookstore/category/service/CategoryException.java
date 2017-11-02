package com.lanou.bookstore.category.service;

/**
 * Created by dllo on 17/9/22.
 */
public class CategoryException extends Exception{
    public CategoryException() {
        super();
    }

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryException(Throwable cause) {
        super(cause);
    }

    protected CategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
