package com.lanou.bookstore.category.service;


import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.domain.Category;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface CategoryService {
    List<Category>  findAll();

    boolean login(String adminname, String password);

    void add(Category category);

    void delete(String cid) throws CategoryException;

    Category editPre(String cid);

    void edit(Category category);

    Admin findSalesByName(String adminname);
}
