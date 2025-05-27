import React, { useState,useEffect } from 'react';
import { Col, Container, Form, Row,Dropdown} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import ProductCard from '../HomePage/ProductCard';
import { formatPrice,fetchProducts,getDefaultFilters } from '../../repository/repository';
import { useLocation } from 'react-router-dom';
import DeliveryOptions from './DeliveryOptions';
import { BsPersonCircle } from 'react-icons/bs';



export default function CustomerInformation(props) {



    

    return (
    <>
      <Container className='mt-4'>
        <span className=''><BsPersonCircle size={25}/></span>
        <span className='m-3'><b>Customer Information</b></span> 
        <Row className='mt-3'>
            <Col>
                <p>Full Name</p>
                <p>user.name</p>
            </Col>
            <Col>
                <p>Phone Number</p>
                <p>user.phNum</p>
            </Col>
        </Row>
        <Row className='mt-2'>
            <Col>
                <p>Adress</p>
                <p>user.adress</p>
            </Col>
        </Row>
        
      </Container>
    </>
    )
}