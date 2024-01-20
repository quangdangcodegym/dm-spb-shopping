package com.cg.shopping.restcontroller;

import com.cg.shopping.exception.ResourceNotFoundException;
import com.cg.shopping.model.dto.req.ProductReqDto;
import com.cg.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> showProducts() {

        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('MODIFIER')")
    public ResponseEntity<?> saveProduct(@RequestBody ProductReqDto productReqDto) {
        return new ResponseEntity<>(productService.save(productReqDto), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MODIFIER')")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductReqDto productReqDto) {
        productReqDto.setId(id);
        return new ResponseEntity<>(productService.update(productReqDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MODIFIER', 'ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
