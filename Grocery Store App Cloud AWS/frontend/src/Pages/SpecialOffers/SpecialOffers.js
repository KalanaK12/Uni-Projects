import { Container, Row, Col } from "react-bootstrap";
import { useEffect, useState } from "react";
import { fetchDiscountProducts } from "../../repository/repository";
import ProductCard from "../HomePage/ProductCard";

export default function SpecialOffers() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetchDiscountProducts()
      .then((response) => {
        if (response != null) {
          setProducts(response.data);
        }
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }, []);

  return (
    <>
      <Container>
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
      </Container>
    </>
  );
}
