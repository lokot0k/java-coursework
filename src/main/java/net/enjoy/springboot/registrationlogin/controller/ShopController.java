package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.entity.Product;
import net.enjoy.springboot.registrationlogin.global.GlobalData;
import net.enjoy.springboot.registrationlogin.service.CategoryService;
import net.enjoy.springboot.registrationlogin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping({"/", "/home"})
    public String home(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(@RequestParam(name = "search", required = false) String search, Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        List<Product> products;
        if (search != null && !search.isEmpty()) {
            products = productService.searchProductsByName(search);
        } else {
            products = productService.getAllProducts();
        }
        model.addAttribute("products", products);
        return "admin/shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(@PathVariable Integer id, Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable Integer id, Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("product", productService.getProductById(id).get());
        return "admin/viewProduct";
    }
}
