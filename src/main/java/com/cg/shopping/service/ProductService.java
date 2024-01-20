package com.cg.shopping.service;

import com.cg.shopping.exception.ResourceNotFoundException;
import com.cg.shopping.model.Category;
import com.cg.shopping.model.Product;
import com.cg.shopping.model.dto.req.ProductReqDto;
import com.cg.shopping.model.dto.res.ProductResDto;
import com.cg.shopping.repository.CategoryRepository;
import com.cg.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    public List<ProductResDto> getProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::toProductResDto).collect(Collectors.toList());
    }
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ProductResDto save(ProductReqDto productReqDto) {
        Category category = categoryRepository.findById(productReqDto.getCategoryId()).get();

        Product product = productRepository
                .save(new Product(productReqDto.getName(), productReqDto.getPrice(), category));
        return product.toProductResDto();
    }

    public ProductResDto update(ProductReqDto productReqDto){
        Category category = categoryRepository.findById(productReqDto.getCategoryId()).get();
        Product product = productRepository.findById(productReqDto.getId()).orElseThrow();

        product.setName(productReqDto.getName());
        product.setCategory(category);
        product.setPrice(product.getPrice());

        productRepository.save(product);

        return product.toProductResDto();
    }

    public void delete(Long id) {
        Product p = productRepository.findById(id).get();
        productRepository.delete(p);

    }
}
