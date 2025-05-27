import React, { useState,useEffect } from 'react';
import { Col, Container, Form, Row,Dropdown, Card, Button} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import ProductCard from '../HomePage/ProductCard';
import { formatPrice,fetchProducts,getDefaultFilters } from '../../repository/repository';
import { useLocation } from 'react-router-dom';
import DeliveryOptions from './DeliveryOptions';
import { BsPersonCircle } from 'react-icons/bs';
import './CheckoutPage.css'
import { BiLogoMastercard } from "react-icons/bi";
import { TfiReceipt } from "react-icons/tfi";




export default function PaymentSummary(props) {


    return (
    <>
      <Card className='m-1 p-4 summ-card w-100'>
        <Card.Title>Payment Method</Card.Title>
        <p className='mb-1 mt-2' style={{ fontSize: '13px', color:'#00BDD6FF' }}>Change payment method</p>
        <div className='mb-5'>
            <Stack direction='horizontal' className='pay-card'>
                <BiLogoMastercard size={40}/>
                <div >card.name</div>
                <div className="ms-auto">****1235</div>
            </Stack>
        </div>
        <span>
            <TfiReceipt color='red' size={30}/>
            <span className='summ-text'>Summary</span>
        </span>
        <Row className='pt-2'>
            <Col>
                <p className='mb-0 price-cat'>Subtotal</p>
            </Col>
            <Col className='d-flex justify-content-end mb-0'>
                <p className='mb-1 price-amt'>s_total</p>
            </Col>
        </Row>
        <Row>
            <Col>
                <p className='mb-0 price-cat'>Discount</p>
            </Col>
            <Col className='d-flex justify-content-end '>
                <p className='mb-1 price-amt'>discount_price</p>
            </Col>
        </Row>
        <Row>
            <Col>
                <p className='price-cat'>Delivery Fee</p>
            </Col>
            <Col className='d-flex justify-content-end price-amt'>
                <p>delivery_price</p>
            </Col>
        </Row>
        <Row className='mt-3'>
            <Col>
                <p className='price-cat'>Total</p>
            </Col>
            <Col className='d-flex justify-content-end price-total'>
                <p>total_price</p>
            </Col>
        </Row>
        <Button className='payment-btn' variant="primary" size="lg">
          Proceed to Payment
        </Button>


      </Card>
    </>
    )
}