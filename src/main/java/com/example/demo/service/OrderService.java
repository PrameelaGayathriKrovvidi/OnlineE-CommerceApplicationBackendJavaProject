package com.example.demo.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImage;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.ProductImageRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	/** 
	 * Fetches all successful orders for a given user and returns the required response format.  
	 * 
	 * @param user the authenticated user object
	 * @return A map containing the user's role ,user name, and ordered products */
	
	public Map<String, Object> getOrdersForUser(User user) {
		//Fetch all successful order items for the user
		List<OrderItem> orderItems = orderItemRepository.findSuccessFulOrderItemsByUserId(user.getUserId());
		
		// prepare the response map
		Map<String, Object> response = new HashMap<>();
		response.put("username",user.getUsername());
		response.put("role", user.getRole()); // Directly use the role as it is an enum mapped to a string
		
		//Transform order items into a list of product details
		List<Map<String, Object>> products = new ArrayList<>();
		for(OrderItem item: orderItems) {
			Product product = productRepository.findById(item.getProductId()).orElse(null);
			if (product == null) {
				continue; // Skip if the product does not exist
			}
			
			// Fetch the product image(if available)
			List<ProductImage> images = productImageRepository.findByProduct_ProductId(product.getProductId());
			String imageUrl = images.isEmpty() ? null : images.get(0).getImageUrl();
			
			// Create a product details map
			Map<String, Object> productDetails = new HashMap<>();
			productDetails.put("order_id", item.getOrder().getOrderId());
			productDetails.put("quantity", item.getQuantity());
			productDetails.put("total_price", item.getTotalPrice());
			productDetails.put("image_url", imageUrl);
			productDetails.put("order_id", product.getProductId());
			productDetails.put("order_id", product.getName());
			productDetails.put("order_id", product.getDescription());
			productDetails.put("order_id", item.getPricePerUnit());
			
			products.add(productDetails);
		}
		
		// Add the products list to the response
		
		return response;
	}
	
	
	
	

}
