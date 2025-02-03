package com.elyashevich.ecommerce.controller;

import com.elyashevich.ecommerce.dto.CategoryDto;
import com.elyashevich.ecommerce.dto.ProductDto;
import com.elyashevich.ecommerce.mapper.CategoryMapper;
import com.elyashevich.ecommerce.mapper.ProductMapper;
import com.elyashevich.ecommerce.mapper.UserMapper;
import com.elyashevich.ecommerce.service.CategoryService;
import com.elyashevich.ecommerce.service.ProductService;
import com.elyashevich.ecommerce.service.UserService;
import com.elyashevich.ecommerce.util.ValidationHandlerUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.elyashevich.ecommerce.util.ConstantUtil.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    @GetMapping("/categories")
    public String createCategory(
            final Model model,
            final @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            final @RequestParam(value = "sortField", defaultValue = "name") String sortField,
            final @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        var paginated = this.categoryService.findAllPaginated(pageNo, PAGE_SIZE, sortField, sortDir);
        model.addAttribute("categories", paginated.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", paginated.getTotalPages());
        model.addAttribute("totalItems", paginated.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals(ORDER_ASC) ? ORDER_DESC : ORDER_ASC);
        return "admin/categories";
    }

    @GetMapping("/users")
    public String createUser(final Model model) {
        var users = this.userService.findAll();
        model.addAttribute("users", this.userMapper.toDto(users));
        return "admin/users";
    }

    @GetMapping("/products/create")
    public String createProduct(final Model model) {
        var categories = this.categoryService.findAll();
        model.addAttribute("categories", this.categoryMapper.toDto(categories));
        return "admin/create-product";
    }

    @GetMapping("/categories/edit/{id}")
    public String editProduct(final Model model, @PathVariable("id") final Long id) {
        var category = this.categoryService.findById(id);
        model.addAttribute("category", this.categoryMapper.toDto(category));
        return "admin/edit-category";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(final @PathVariable("id") Long id, final Model model) {
        var product = this.productService.findById(id);
        var categories = this.categoryService.findAll().stream()
                .filter(category -> !product.getCategory().getId().equals(category.getId()))
                .toList();
        model.addAttribute("categories", this.categoryMapper.toDto(categories));
        model.addAttribute("product", this.productMapper.toDto(product));
        return "admin/edit-product";
    }

    @PostMapping("/categories/create")
    public String createCategoryAction(
            final @Valid @ModelAttribute("categoryDto") CategoryDto categoryDto,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            ValidationHandlerUtil.handleValidation(model, bindingResult);
            return "redirect:/admin/categories";
        }
        var category = this.categoryMapper.toEntity(categoryDto);
        this.categoryService.create(category);
        return "redirect:/admin/categories";
    }

    @PostMapping("/products/create")
    public String createProductAction(
            final @Valid @ModelAttribute("productDto") ProductDto productDto,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            ValidationHandlerUtil.handleValidation(model, bindingResult);
            return "redirect:/products";
        }
        var product = this.productService.create(productMapper.toEntity(productDto));
        return "redirect:/products/%d".formatted(product.getId());
    }

    @PostMapping("/categories/edit/{id}")
    public String editCategoryAction(
            final @PathVariable("id") Long id,
            final @Valid @ModelAttribute("categoryDto") CategoryDto categoryDto,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            ValidationHandlerUtil.handleValidation(model, bindingResult);
            return "redirect:/admin/categories";
        }
        var category = this.categoryMapper.toEntity(categoryDto);
        this.categoryService.update(id, category);
        return "redirect:/admin/categories";
    }

    @PostMapping("/products/edit/{id}")
    public String editProductAction(
            final @PathVariable("id") Long id,
            final @Valid @ModelAttribute("productDto") ProductDto productDto,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            ValidationHandlerUtil.handleValidation(model, bindingResult);
            return "redirect:/products";
        }
        this.productService.update(id, this.productMapper.toEntity(productDto));
        return "redirect:/products/%d".formatted(id);
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(final @PathVariable("id") Long id) {
        this.productService.delete(id);
        return "redirect:/products";
    }

    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(final @PathVariable("id") Long id) {
        this.categoryService.delete(id);
        return "redirect:/admin/categories";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(final @PathVariable("id") Long id) {
        this.userService.delete(id);
        return "redirect:/admin/users";
    }
}
