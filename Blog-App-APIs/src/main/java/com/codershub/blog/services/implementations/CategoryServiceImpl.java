package com.codershub.blog.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codershub.blog.entities.Category;
import com.codershub.blog.entities.Post;
import com.codershub.blog.exceptions.ResourceNotFoundException;
import com.codershub.blog.payloads.category.ApiResponseCategoryModels;
import com.codershub.blog.payloads.category.CategoryModel;
import com.codershub.blog.payloads.post.ApiResponsePostModels;
import com.codershub.blog.payloads.post.PostModel;
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
	public ApiResponseCategoryModels getAllCategories(Integer pageNumber, Integer pageSize, String sortBy,
			Integer sortMode) {

		// sorting format
		Sort sort = (sortMode == 0) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		// paging format
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		// retrieving paged data items
		Page<Category> pageCategories = this.categoryRepo.findAll(pageable);

		List<Category> allCategories = pageCategories.getContent();

		List<CategoryModel> categoryModels = allCategories.stream()
				.map((category) -> this.modelMapper.map(category, CategoryModel.class)).collect(Collectors.toList());

		ApiResponseCategoryModels apiResponseCategoryModels = new ApiResponseCategoryModels(true, HttpStatus.OK.value(),
				"Categories Fetched Successfully", pageCategories.getNumber(), pageCategories.getSize(),
				pageCategories.getTotalElements(), pageCategories.getTotalPages(), pageCategories.isLast(),
				categoryModels);

		if (pageCategories.getNumber() >= pageCategories.getTotalPages()) {

			apiResponseCategoryModels.setMessage("No more category(s) found");
			apiResponseCategoryModels.setCategoryModels(null);

		}

		return apiResponseCategoryModels;

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
