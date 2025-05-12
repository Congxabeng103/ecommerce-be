package org.example.ecommerce_be.service;

import org.example.ecommerce_be.entity.Cart;
import org.example.ecommerce_be.entity.CartItem;
import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.repository.CartItemRepository;
import org.example.ecommerce_be.repository.CartRepository;
import org.example.ecommerce_be.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    // Lấy giỏ hàng của user (nếu chưa có thì tạo mới)
    @Transactional
    public Cart getCartByEmail(String userEmail) {
        return cartRepository.findByUserEmail(userEmail)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserEmail(userEmail);
                    newCart.setTotalPrice(BigDecimal.ZERO);
                    newCart.setCartItems(new ArrayList<>()); // 🔥 FIX: Khởi tạo danh sách rỗng
                    return cartRepository.save(newCart);
                });
    }

    public List<CartItem> getCartItemsByEmail(String email) {
        return cartItemRepository.findByUserEmail(email);
    }

    // Thêm sản phẩm vào giỏ hàng
    @Transactional
    public Cart addToCart(String userEmail, Long productId, int quantity) {
        Cart cart = getCartByEmail(userEmail);
        // 🔥 FIX: Đảm bảo danh sách không null
        if (cart.getCartItems() == null) {
            cart.setCartItems(new ArrayList<>());
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            // Nếu sản phẩm đã có trong giỏ, cập nhật số lượng
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.updateTotalPrice();
        } else {
            // Nếu sản phẩm chưa có, thêm mới
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setPrice(product.getPrice());
            newItem.updateTotalPrice();
            cart.getCartItems().add(newItem);
        }

        cart.updateTotalPrice();
        return cartRepository.save(cart);
    }

    // Xóa một sản phẩm khỏi giỏ hàng
    @Transactional
    public Cart removeFromCart(String userEmail, Long cartItemId) {
        Cart cart = getCartByEmail(userEmail);
        cart.getCartItems().removeIf(item -> item.getId().equals(cartItemId));
        cartItemRepository.deleteById(cartItemId);
        cart.updateTotalPrice();
        return cartRepository.save(cart);
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    @Transactional
    public Cart updateCartItemQuantity(String userEmail, Long cartItemId, int quantity) {
        Cart cart = getCartByEmail(userEmail);
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        cartItem.setQuantity(quantity);
        cartItem.updateTotalPrice();

        cart.updateTotalPrice();
        return cartRepository.save(cart);
    }

    // Xóa toàn bộ giỏ hàng
    @Transactional
    public void clearCart(String userEmail) {
        Cart cart = getCartByEmail(userEmail);
        cartItemRepository.deleteAll(cart.getCartItems());
        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    // Thanh toán giỏ hàng
    @Transactional
    public Cart checkout(String userEmail) {
        Cart cart = getCartByEmail(userEmail);
        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot checkout.");
        }

        cart.setStatus(Cart.CartStatus.ORDERED);
        return cartRepository.save(cart);
    }


}
