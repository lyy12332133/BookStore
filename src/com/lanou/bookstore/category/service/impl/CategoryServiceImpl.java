package com.lanou.bookstore.category.service.impl;

import com.lanou.bookstore.category.dao.CategoryDao;
import com.lanou.bookstore.category.dao.impl.CategoryDaoImpl;
import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryException;
import com.lanou.bookstore.category.service.CategoryService;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class CategoryServiceImpl implements CategoryService{
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category>  findAll() {
        return categoryDao.findAll();
    }

    @Override
    public boolean login(String adminname, String password) {
       return categoryDao.login(adminname,password);
    }

    @Override
    public void add(Category category) {
        categoryDao.add(category);
    }

    @Override
    public void delete(String cid) throws CategoryException {
        categoryDao.delete(cid);
    }

    @Override
    public Category editPre(String cid) {
       return categoryDao.editPre(cid);
    }

    @Override
    public void edit(Category category) {
        categoryDao.edit(category);
    }

    @Override
    public Admin findSalesByName(String adminname) {

       return categoryDao.findSalesByName(adminname);
    }
}
