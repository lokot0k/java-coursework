package net.enjoy.springboot.registrationlogin.controller;


import net.enjoy.springboot.registrationlogin.global.GlobalData;
import net.enjoy.springboot.registrationlogin.service.ProductService;
import net.enjoy.springboot.registrationlogin.entity.Product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    private ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable long id){
        GlobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String getCart(Model model){
       model.addAttribute("cartCount", GlobalData.cart.size());
       model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
       model.addAttribute("cart", GlobalData.cart);
        return "admin/cart";
    }
    @GetMapping("/cart/removeItem/{index}")
    public String removeItem(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "admin/checkout";
    }
}
