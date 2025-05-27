import React, { useState } from 'react';
import { Col, Container, Form, Row} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import { products } from '../../DummyData/data';
import ProductCard from './ProductCard';



export default function ProductList(props) {

    return (
    <>
        <Row>
            <h3>{props.type}</h3>
        </Row>
        <Row>
            {/* {products.map((product, index) => (
                <Col className='mb-4'>
                    <ProductCard product={product}/>
                </Col>
            ))} */}
        </Row>
    </>
    )
}