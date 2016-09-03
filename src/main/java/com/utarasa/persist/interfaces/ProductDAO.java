package com.utarasa.persist.interfaces;

import com.utarasa.domain.Category;
import com.utarasa.domain.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO {

    public Long addProduct(String name, BigDecimal price, Integer quantity, String description, Long categoryId);

    public boolean addReadyProduct(Product product, int categoryId);

    public Product getProduct(Long productId);

    public Product getProductWithId(Long productId);

    public List<Product> getProductsByCategory(Long categoryId);

    public List<Product> getAllProducts();

    public List<Product> getAllDeletedProducts();

    public List<Product> getAllActiveProducts();

    public List<Product> getAllActiveProductsByCategoryId(int id);

    public List<Product> getAllArchiveProductsByCategoryId(int id);

    public void updateProductName(Long productId, String name);

    public void updateProductIsDeleted(int productId, Integer flag);

    public void updateProductPrice(Long productId, BigDecimal price);

    public void updateProductQty(Long productId, Integer quantity);

    public void updateProductDesc(Long productId, String description);

    public void updateProductCategory(Long productId, Category categoryId);

    public void deleteProduct(int productId);

    public void deleteAllProducts();

}
