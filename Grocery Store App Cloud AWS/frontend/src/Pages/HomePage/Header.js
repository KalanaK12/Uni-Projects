import React, { useState } from 'react';
import { 
  Col, Container, Form, Row,Carousel,Button,Figure
} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import logo from '../../logo.svg'



export default function Header(props) {
    return (
    <>
        <Row style={{ backgroundColor: 'grey' }}>
          <Col className='p-4 m-5' >
            <h1>Featured Products</h1>
            <Button variant="secondary">Shop</Button>
          </Col>
          <Col>
            <Figure className="d-flex justify-content-center align-items-center slideshow-cursor m-0">
                <Figure.Image
                  src={logo}
                  className='m-0'
                />
              </Figure>
          </Col>
        </Row>
    </>
    )
}