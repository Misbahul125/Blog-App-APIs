package com.codershub.blog.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codershub.blog.entities.Category;
import com.codershub.blog.exceptions.ResourceNotFoundException;
import com.codershub.blog.payloads.category.CategoryModel;
import com.codershub.blog.repositories.CategoryRepository;
import com.codershub.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryModel createCategory(CategoryModel categoryModel) {
		Category cat = this.modelMapper.map(categoryModel, Category.class);
		Category addedCat = this.categoryRepo.save(cat);

		return this.modelMapper.map(addedCat, CategoryModel.class);
	}

	@Override
	public CategoryModel getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		return this.modelMapper.map(cat, CategoryModel.class);
	}

	@Override
	public List<CategoryModel> getAllCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryModel> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryModel.class))
				.collect(Collectors.toList());
		return catDtos;
	};

	@Override
	public CategoryModel updateCategory(CategoryModel categoryModel) {

		Category cat = this.categoryRepo.findById(categoryModel.getCategoryId()).orElseThrow(
				() -> new ResourceNotFoundException("Category", "Category Id", categoryModel.getCategoryId()));

		cat.setCategoryTitle(categoryModel.getCategoryTitle());
		cat.setCategoryDescription(categoryModel.getCategoryDescription());

		Category updatedCat = this.categoryRepo.save(cat);

		return this.modelMapper.map(updatedCat, CategoryModel.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepo.delete(cat);
	}

}
