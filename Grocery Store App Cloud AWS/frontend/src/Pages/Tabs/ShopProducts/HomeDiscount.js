import React, { useEffect, useState } from "react";
import { Col, Container, Form, Row } from "react-bootstrap";
import { products } from "../../../DummyData/data";
import ProductCard from "../../HomePage/ProductCard";
import { fetchDiscountProducts } from "../../../repository/repository";
import "./shopproduct.css";

export default function HomeDiscount() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetchDiscountProducts()
      .then((response) => {
        if (response != null) {
          setProducts(response.data.slice(0,5));
        }
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }, []);

  return (
    <>
      <Row>
        <span className="heading">Special Products</span>
      </Row>
      <Row>
        {products != null &&
          products.map((product, index) => (
            <Col className="mb-4">
              <ProductCard product={product} />
            </Col>
          ))}
      </Row>
    </>
  );
}
