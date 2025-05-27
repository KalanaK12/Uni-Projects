import "./App.css";
import React, { useState, useEffect } from "react";

import HomePage from "./Pages/HomePage/HomePage";
import Navbar from "./Pages/Navbar";
import SearchedItems from "./Pages/SearchedItems/SearchedItems";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link,
  Outlet,
} from "react-router-dom";

import { useNavigate } from "react-router-dom";
import Footer from "./Components/Footer/Footer";
import { CartProvider } from "./Components/Providers/CartContext";

function App() {
  const [itemSearched, setItemSearched] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");


  return (
    <>
      <CartProvider>
        <Navbar setItemSearched={setItemSearched} setSearchTerm={setSearchTerm} />

        {/* remove HomePage */}
        {/* <HomePage itemSearched={itemSearched} setItemSearched={setItemSearched} searchTerm={searchTerm}/> */}
        <Outlet />
      </CartProvider>
      <Footer />
    </>
  );
}

export default App;
