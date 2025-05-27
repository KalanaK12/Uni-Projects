package com.septp0402.orchestratorservice.service;

import com.septp0402.orchestratorservice.proxy.CustomerProxy;
import com.septp0402.orchestratorservice.proxy.OrderProxy;
import com.septp0402.orchestratorservice.proxy.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class OrderService {
    private OrderProxy orderProxy;
    private CustomerProxy customerProxy;
    private ProductProxy productProxy;

    @Autowired
    public OrderService(OrderProxy orderProxy, CustomerProxy customerProxy, ProductProxy productProxy) {
        this.orderProxy = orderProxy;
        this.customerProxy = customerProxy;
        this.productProxy = productProxy;
    }

    public String getShoppingCart(String displayCartStr) {
        String status = customerProxy.authenticate(displayCartStr);

        if (status.equals("Authenticated")) {
            String orderItemsStr = orderProxy.getShoppingCart(displayCartStr);

            try {
                // Convert the order items string to JSON
                JSONArray orderItems = new JSONArray(orderItemsStr);

                // For each order item, retrieve product details and add them to the object
                for (int i = 0; i < orderItems.length(); i++) {
                    JSONObject orderItem = orderItems.getJSONObject(i);
                    int pid = orderItem.getInt("pid");

                    // Retrieve product details as a string
                    String productStr = productProxy.getProductByID(pid);
                    JSONObject product = new JSONObject(productStr);

                    // Remove the 'pid' field
                    orderItem.remove("pid");

                    // Replace it with the product details
                    orderItem.put("product", product);
                }

                // get the json object from displayCartStr
                JSONObject customerID = new JSONObject(displayCartStr);

                // get the customer id from json object
                int cID = customerID.getInt("cID");

                // retrieve customer information as a string
                String customerInformationStr = customerProxy.getCustomerInformation(cID);

                // convert customer information string to a json object
                JSONObject customerInformation = new JSONObject(customerInformationStr);

                // Create a new JSONArray and insert the customer information first
                JSONArray cartDisplay = new JSONArray();
                cartDisplay.put(customerInformation);

                // Append the rest of the items from the original orderItems JSONArray
                for (int i = 0; i < orderItems.length(); i++) {
                    cartDisplay.put(orderItems.getJSONObject(i));
                }

                // Convert the updated order items back to a string
                return cartDisplay.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "Error processing order items";
            }
        }
        else {
            return "Not logged in";
        }
    }

    public String addShoppingItem(@RequestBody String addOrderItemDTOStr) {
        String status = customerProxy.authenticate(addOrderItemDTOStr);

        if (status.equals("Authenticated")) {
            return orderProxy.addShoppingItem(addOrderItemDTOStr);
        }
        else {
            return "Not logged in";
        }
    }

    public String deleteShoppingItem(String deleteOrderItemStr) {
        String status = customerProxy.authenticate(deleteOrderItemStr);

        if (status.equals("Authenticated")) {
            return orderProxy.deleteShoppingItem(deleteOrderItemStr);
        }
        else {
            return "Not logged in";
        }
    }

    @DeleteMapping("/deleteCart")
    public String deleteShoppingCart(String deleteCartDTOStr) {
        String status = customerProxy.authenticate(deleteCartDTOStr);

        if (status.equals("Authenticated")) {
            return orderProxy.deleteShoppingCart(deleteCartDTOStr);
        }
        else {
            return "Not logged in";
        }
    }

}