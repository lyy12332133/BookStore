package com.lanou.bookstore.category.admin.web.controller;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.bookstore.category.service.impl.CategoryServiceImpl;
import com.lanou.commons.CommonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {
    // 添加图书
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        // 创建 Book 对象
        Book book = new Book();
        try {
            List<FileItem> fileItems = sfu.parseRequest(request);
            // 解析request
            String bname = new String(fileItems.get(0).getString().getBytes("iso-8859-1"), "utf-8");
            String price = fileItems.get(2).getString();
            String author = new String(fileItems.get(3).getString().getBytes("iso-8859-1"), "utf-8");
            String content = new String(fileItems.get(4).getString().getBytes("iso-8859-1"), "utf-8");
            String cid = fileItems.get(5).getString();

            String image = fileItems.get(1).getString();
            if (bname.trim().length()==0||author.trim().length()==0||price.trim().length()==0||image.trim().length()==0) {
                request.setAttribute("msg","上传的数据不能为空");
                book.setBname(bname);
                book.setPrice(price);
                book.setAuthor(author);
                book.setContent(content);
                book.setCid(cid);
                CategoryService categoryService = new CategoryServiceImpl();
                List<Category> all = categoryService.findAll();
                request.setAttribute("allCTG",all);
                request.setAttribute("book",book);
                request.getRequestDispatcher("adminjsps/admin/book/add.jsp").forward(request, response);
            }else {
                try {
                    Double d = Double.valueOf(price);
                } catch (Exception e) {
                    request.setAttribute("msg", "请输入正确的价钱格式");
                    request.getRequestDispatcher("adminjsps/admin/book/add.jsp").forward(request, response);
                }
                book.setBid(CommonUtils.uuid());
                book.setBname(bname);
                book.setPrice(price);
                book.setAuthor(author);
                book.setContent(content);
                book.setCid(cid);
                book.setDel(false);
                book.setSales(0);

                // 上传图片
                FileItem fi = fileItems.get(1);
                String root = this.getServletContext().getRealPath("/book_img/");
                String filename = fi.getName();

                int index = filename.lastIndexOf("/");
                if (index != -1) {
                    filename = filename.substring(index + 1);
                }
                // 图片名
                String saveName = CommonUtils.uuid() + "_" + filename;
                File dirFile = new File(root);
                dirFile.mkdirs();
                File destFile = new File(dirFile, saveName);
                fi.write(destFile);
                // 保存图片地址
                book.setImage("book_img/" + saveName);
                request.setAttribute("book", book);
                BookService bookService = new BookServiceImpl();
                bookService.add(book);
                request.setAttribute("msg","添加图书成功");
                request.getRequestDispatcher("adminjsps/msg.jsp").forward(request,response);
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
    }
}