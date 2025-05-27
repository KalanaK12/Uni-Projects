import React from "react";
import { Container, Row, Col, Form } from "react-bootstrap";
import "./footer.css";

export default function Footer() {
  return (
    <>
      <div className="d-flex background p-5 gap-5 mt-5">
        <Col className="d-flex">
          <Col className="">
            <Container>
              <Row>
                <span className="heading-footer">HELP</span>
              </Row>
              <Row>
                <span className="text">Help & FAQ</span>
              </Row>
              <Row>
                <span className="text">Shipping & Returns</span>
              </Row>
              <Row>
                <span className="text">Track Order</span>
              </Row>
              <Row>
                <span className="text">Delivery</span>
              </Row>
            </Container>
          </Col>
          <Col>
            <Container>
              <Row>
                <span className="heading-footer">About us</span>
              </Row>
              <Row>
                <span className="text">Why SuperPrice</span>
              </Row>
              <Row>
                <span className="text">Careers</span>
              </Row>
              <Row>
                <span className="text">Our Community</span>
              </Row>
            </Container>
          </Col>
        </Col>
        <Col>
          <Container>
            <Row>
              <span className="heading-footer">Contact</span>
            </Row>
            <Row>
              <span className="text">
                <strong>Phone:</strong> 03 1234 5678
              </span>
            </Row>
            <Row>
              <span className="text">
                <strong>Email:</strong> help@superprice.com
              </span>
            </Row>
          </Container>
        </Col>
        <Col>
          <Col >
            <Container>
              <Row>
                <span className="promotions">Recieve new promotions</span>
              </Row>
              <Row>
                <span className="promotions-text">
                  Sign up to our weekly catalouge
                </span>
              </Row>
              <Row>
                <Form.Control></Form.Control>
              </Row>
            </Container>
          </Col>
        </Col>
      </div>
    </>
  );
}
