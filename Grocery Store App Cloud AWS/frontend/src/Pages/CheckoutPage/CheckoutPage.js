import React, { useState,useEffect } from 'react';
import { Col, Container, Form, Row,Dropdown} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import ProductCard from '../HomePage/ProductCard';
import { formatPrice,fetchProducts,getDefaultFilters,fetchSimilarProducts,fetchCart} from '../../repository/repository';
import { useLocation } from 'react-router-dom';
import DeliveryOptions from './DeliveryOptions';
import CustomerInformation from './CustomerInformation';
import PaymentSummary from './PaymentSummary';
import CartItem from './CartItem';
import { useCart } from '../../Components/Providers/CartContext';
import CartTable from './CartTable';



export default function CheckoutPage(props) {

    const [deliveryOperator, setDeliveryOperator] = useState("AusPost");
    const [deliveryOption, setDeliveryOption] = useState("AusPost");
    const [updateTrigger, setUpdateTrigger] = useState(true);

    const {cart,cartSize} = useCart();

    useEffect(() => {
        console.log("useEffect[cart] in CheckoutPage, "+updateTrigger);
        setUpdateTrigger(!updateTrigger);
    }, [cart]);
   

    return (
    <>
      <Container className='mt-4'>
        <Row>
            <h1 style={{ textAlign: 'center' }}>My Shopping Bag ({cartSize})</h1>
        </Row>
        <Row>
            <Col md={8}>
                <Row id='order-summary' className="border">
                    <CartTable key={updateTrigger} cart={cart}/>
                    
                </Row>
                <Row id='delivery-options'className="border">
                    <DeliveryOptions deliveryOperator={deliveryOperator} setDeliveryOperator={setDeliveryOperator}/>
                </Row>
                <Row id='customer-info' className="border">
                    <CustomerInformation />
                </Row>
            </Col>
            <Col md={4}>
                <PaymentSummary/>
            </Col>
        </Row>
        <h1 className='mb-0 mt-5' style={{ color:'#171A1FFF', fontSize:'20px', fontWeight:700 }}>Missing Something?</h1>
        {/* <Row className='justify-content-left mt-4'>
            {simProducts.map((product, index) => (
                <Col className='m-2' xs='auto' key={index}>
                <ProductCard product={product} />
                </Col>
            ))}
            {simProducts.length === 0 && (
                <p>No Similar Items...</p>

            )}
        </Row> */}
        
      </Container>
    </>
    )
}