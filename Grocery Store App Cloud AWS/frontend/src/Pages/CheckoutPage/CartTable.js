import React, { useState,useEffect } from 'react';
import { Col, Container, Form, Row,Dropdown} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import ProductCard from '../HomePage/ProductCard';
import { formatPrice,fetchProducts,getDefaultFilters } from '../../repository/repository';
import { useLocation } from 'react-router-dom';
import DeliveryOptions from './DeliveryOptions';
import { BsPersonCircle } from 'react-icons/bs';
import CartItem from './CartItem';



export default function CartTable(props) {


    // useEffect(() => {
    //     this.forceUpdate();
    // }, [props.cart]);

    return (
    <>
      <h1 className='mb-0 mt-5' style={{ color:'#171A1FFF', fontSize:'20px', fontWeight:700 }}>Order Summary</h1>
        <Row>
            <Col md={8}>Name</Col>
            <Col md={1}>Price</Col>
            <Col md={1}>Qty</Col>
            <Col md={1}>Total</Col>
            <Col md={1}></Col>
        </Row>
        {props.cart.map((entry, index) => (
            <CartItem key={index} entry={entry}/>
        ))}
    </>
    )
}