package org.example.ecommerce_be.controller;
import org.example.ecommerce_be.entity.Cart;
import org.example.ecommerce_be.entity.CartItem;
import org.example.ecommerce_be.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173") // Điều chỉnh nếu frontend có domain khác
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Lấy email từ token
    private String getEmailFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt)) {
            throw new RuntimeException("Unauthorized: Không lấy được email từ token");
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();
        return jwt.getClaim("email"); // Lấy email từ JWT
    }

    // Lấy giỏ hàng của user
    @GetMapping
    public ResponseEntity<Cart> getCart() {
        String userEmail = getEmailFromToken();
        return ResponseEntity.ok(cartService.getCartByEmail(userEmail));
    }
    @GetMapping("/{userEmail}")
    public ResponseEntity<List<CartItem>> getCartItemsByEmail(@PathVariable String userEmail) {
        List<CartItem> cartItems = cartService.getCartItemsByEmail(userEmail);
        return ResponseEntity.ok(cartItems);
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        String userEmail = getEmailFromToken();
        return ResponseEntity.ok(cartService.addToCart(userEmail, productId, quantity));
    }

    // Xóa một sản phẩm khỏi giỏ hàng
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long cartItemId) {
        String userEmail = getEmailFromToken();
        return ResponseEntity.ok(cartService.removeFromCart(userEmail, cartItemId));
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    @PutMapping("/update")
    public ResponseEntity<Cart> updateCartItemQuantity(
            @RequestParam Long cartItemId,
            @RequestParam int quantity) {
        String userEmail = getEmailFromToken();
        return ResponseEntity.ok(cartService.updateCartItemQuantity(userEmail, cartItemId, quantity));
    }

    // Xóa toàn bộ giỏ hàng
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart() {
        String userEmail = getEmailFromToken();
        cartService.clearCart(userEmail);
        return ResponseEntity.noContent().build();
    }

    // Thanh toán giỏ hàng
    @PostMapping("/checkout")
    public ResponseEntity<Cart> checkout() {
        String userEmail = getEmailFromToken();
        return ResponseEntity.ok(cartService.checkout(userEmail));
    }
}
