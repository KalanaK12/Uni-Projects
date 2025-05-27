import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import "bootstrap/dist/css/bootstrap.min.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Error from "./Pages/error-page/Error";
import ProductPage from "./Pages/ProductPage/ProductPage";
import Navbar from "./Pages/Navbar";
import SearchedItems from "./Pages/SearchedItems/SearchedItems";
import Signin from "./Pages/sign-in/Signin";
import Home from "./Components/HomeRedirect/Home";
import ShopProducts from "./Pages/Tabs/ShopProducts/ShopProducts";
import SpecialOffers from "./Pages/SpecialOffers/SpecialOffers";
import CheckoutPage from "./Pages/CheckoutPage/CheckoutPage";
import SingUp from "./Pages/SignUp/SignUp";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <Error />,
    children: [
      {
        path: "/product/:id",
        element: <ProductPage />,
        errorElement: <Error />,
      },
      {
        path: "/search/:term",
        element: <SearchedItems />,
        errorElement: <Error />,
      },
      {
        path: "/shop-products",
        element: <ShopProducts />,
        errorElement: <Error />,
      },
      {
        path: "/special-offers",
        element: <SpecialOffers />,
        errorElement: <Error />,
      },
      {
        path: "/checkout",
        element: <CheckoutPage />,
        errorElement: <Error />,
      },
    ],
  },
  {
    path: "/sign-in",
    element: <Signin />,
    errorElement: <Error />
  },
  {
    path: "/sign-up",
    element: <SingUp />,
    errorElement: <Error />
  }
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

const currentPath = window.location.pathname;
if (currentPath === "/") {
  window.location.href = "/shop-products";
}

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
