/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Product;

import Models.Users;

import java.math.BigDecimal;
import java.util.Date;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tien Phat
 */
@Local
public interface ProductFacadeLocal {

    void create(Product product);

    void edit(Product product);

    void remove(Product product);

    Product find(Object id);

    List<Product> findAll();

    List<Product> findRange(int[] range);

    int count();

    public BigDecimal getHighestProductPrice();

    public List<Product> getListExistingProduct();

    public List<Product> getListApprovingProduct();

    public List<Product> getProductbyCategoryStatictiscal(int proCateId,Date fromDate, Date toDate);

    public List<Product> getProductbyCate(int proCateId);

    public List<Product> getListProductSortedDesc();

    public List<Product> searchProductByName(String name);

    public List<Product> searchProductByUserPhoneNumber(String phoneNumber);

    public int createNewProduct(Product newProduct);

    public List<Product> searchProduct(String productName, Models.Anime animeId, Models.Category categoryId, BigDecimal minPrice, BigDecimal maxPrice);

    public List<Product> getRandomProductSameAnime(Product product);

}
