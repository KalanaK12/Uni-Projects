// CartContext.js

import React, { createContext, useContext, useEffect, useState } from 'react';
import { deleteItemFromCart, fetchCart, postItemToCart } from '../../repository/repository';

const CartContext = createContext();

export function useCart() {
  return useContext(CartContext);
}

export function CartProvider({ children }) {
  const [cart, setCart] = useState([]);
  const [cartSize, setCartSize] = useState(0);
  const [user, setUser] = useState({});

  //get customers cart
    useEffect(() => {
        getCart();

    }, []);

    useEffect(() => {
        if(cart){
            setCartSize(cart.length);
        }else{
            setCartSize(0);
        }
        console.log("useEffect[cart]")
    }, [cart]);
    

    const getCart = async () => {

        const {user,products} = await fetchCart();

        setCart(products);
        setUser(user);
    }

  const addToCart = async (product, quantity) => {
    console.log(product.p_id,quantity);
    await postItemToCart(product,quantity);

    getCart();
  };

  const removeFromCart = async (p_id) => {
    const response = await deleteItemFromCart(p_id);


    getCart();
  };
  



  return (
    <CartContext.Provider value={{user,cart,addToCart,removeFromCart,cartSize}}>
      {children}
    </CartContext.Provider>
  );
}
