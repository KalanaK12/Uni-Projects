import React, { useState } from 'react';
import { Col, Container, Form, Row} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import Header from './Header';
import Categories from './Categories';
import ProductList from './ProductList';



export default function ShopProducts(props) {
    return (
    <>
        <Header/>
        <Row>
            <Categories/>
        </Row>
        <Row className='products p-2'>
            <ProductList type="Special Products"/>
        </Row>
        <Row className='products'>
            <Categories/>
        </Row>
    </>
    )
}