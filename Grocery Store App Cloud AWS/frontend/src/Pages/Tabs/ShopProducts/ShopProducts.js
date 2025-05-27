import React, { useState } from 'react';
import { Col, Container, Form, Row} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import Header from './Header';
import Categories from './Categories';
import Footer from '../../../Components/Footer/Footer';
import HomeDiscount from './HomeDiscount';
import Recipes from './Recipes';



export default function ShopProducts(props) {
    return (
    <>
        <Header/>
        <Row>
            <Categories/>
        </Row>
        <Row className='products p-2'>
            <HomeDiscount/>
        </Row>
        <Row>
        </Row>
        <Row className='products'>
            {/* <Categories/> */}
        </Row>
        <Row className='m-1'>
            <Recipes />
        </Row>
    </>
    )
}