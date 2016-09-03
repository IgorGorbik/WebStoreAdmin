package com.utarasa.persist.interfaces;

import com.utarasa.domain.Category;
import java.util.List;

public interface CategoryDAO {

    public Long addCategory(String name, String description);

    public Integer countCategoryElements();

    public Category getCategory(Long categoryId);

    public Category getRandomCategory();

    public Category getFirstCategory();

    public Category getCategoryWithId(int categoryId);

    public List<Category> getAllCategories();

    public Long getCategoryIdByName(String name);

    public void updateCategoryName(Long categoryId, String name);

    public void updateCategoryDesc(Long categoryId, String description);

    public void deleteCategory(int categoryId);

    public void deleteAllCategories();

    public boolean addReadyCategory(Category category);
}
