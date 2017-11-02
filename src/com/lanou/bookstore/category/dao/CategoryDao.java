package com.lanou.bookstore.category.dao;


import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryException;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface CategoryDao {
    List<Category>  findAll();

    boolean login(String adminname, String password);

    void add(Category category);

    void delete(String cid) throws CategoryException;

    Category editPre(String cid);

    void edit(Category category);

    Admin findSalesByName(String adminname);
}
