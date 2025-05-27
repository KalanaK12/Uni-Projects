import React, { useState,useEffect } from 'react';
import { Col, Container, Form, Row,Dropdown} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import { products } from '../../DummyData/data';
import ProductCard from '../HomePage/ProductCard';
import { formatPrice,fetchProducts } from '../../repository/repository';
import { useLocation } from 'react-router-dom';



export default function ProductsContainer(props) {
    // const location = useLocation();

    // if (!location.state || !location.state.product) {
    //     return <div>Loading...</div>;
    // }
    // const { product } = location.state;

    return (
    <>
    <Row className='justify-content-left mt-4'>
        
        {props.data.map((product, index) => (
            <Col className='m-2' xs='auto' key={index}>
            <ProductCard product={product} />
            </Col>
        ))}
        {props.data.length === 0 && (
            <p>No items</p>

        )}
    </Row>
    </>
    )
}