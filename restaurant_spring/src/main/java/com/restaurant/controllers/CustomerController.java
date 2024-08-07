package com.restaurant.controllers;


import com.restaurant.dtos.CategoryDto;
import com.restaurant.dtos.ProductDto;
import com.restaurant.dtos.ReservationDto;
import com.restaurant.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList = customerService.getAllCategories();
        if (categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByName(@PathVariable String title){
        List<CategoryDto> categoryDtoList = customerService.getCategoriesByName(title);
        if (categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    // Product operations

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId){
        List<ProductDto> productDtoList = customerService.getProductsByCategory(categoryId);
        if (productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title){
        List<ProductDto> productDtoList = customerService.getProductsByCategoryAndTitle(categoryId,title);
        if (productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @PostMapping("/reservation")
    public ResponseEntity<?> postReservation(@RequestBody ReservationDto reservationDto) throws IOException {
        ReservationDto postedReservationDto = customerService.postReservation(reservationDto);
        if(postedReservationDto ==  null) return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.CREATED).body(postedReservationDto);
    }

    @GetMapping("/reservations/{customerId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByUser(@PathVariable Long customerId){
        List<ReservationDto> reservationDtoList = customerService.getReservationsByUser(customerId);
        if (reservationDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservationDtoList);
    }
}

