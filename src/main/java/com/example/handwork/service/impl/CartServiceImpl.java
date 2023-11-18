package com.example.handwork.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.handwork.dto.CartDto;
import com.example.handwork.dto.ProductDto;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.entity.Cart;
import com.example.handwork.entity.Product;
import com.example.handwork.entity.Users;
import com.example.handwork.repository.CartRepository;
import com.example.handwork.repository.ProductRepository;
import com.example.handwork.repository.UsersRepository;
import com.example.handwork.service.CartService;
import com.example.handwork.service.mapper.CartMapper;
import com.example.handwork.service.mapper.ProductMapper;

import java.util.Optional;
import java.util.List;

import static com.example.handwork.status.AppStatusMessage.DATABASE_ERROR;
import static com.example.handwork.status.AppStatusMessage.OK;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UsersRepository usersRepository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    @Override
    public Boolean createCart(Users user) {
        try {
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public ResponseDto<CartDto> addToCart(Integer id, Integer productId) {
        try {
            Optional<Cart> byId = cartRepository.findById(id);
            Optional<Product> byId1 = productRepository.findById(productId);

            Cart cart = byId.get();
            cart.getProducts().add(byId1.get());
            cart.setTotalPrice((int) (cart.getTotalPrice() + byId1.get().getPrice()));
            Cart save = cartRepository.save(cart);
            return ResponseDto.<CartDto>builder()
                    .success(true)
                    .message(OK)
                    .data(cartMapper.toDto(save))
                    .build();
        }catch (Exception e){
            return ResponseDto.<CartDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public void removeFromCart(Cart cart, Product product) {

    }

    @Override
    public void clearCart(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public ResponseDto<List<ProductDto>> getUserCart(Integer userId) {
        Optional<Users> byId = usersRepository.findById(userId);
        if(byId.isEmpty()){
            return ResponseDto.<List<ProductDto>>builder()
                    .build();
        }
        Optional<Cart> byUserId = cartRepository.findByUserId(userId);
        return ResponseDto.<List<ProductDto>>builder()
                .data(byUserId.get().getProducts().stream().map(pr->productMapper.toDto(pr)).toList())
                .build();
    }
}