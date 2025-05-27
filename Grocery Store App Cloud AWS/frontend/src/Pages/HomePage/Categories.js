import React, { useState } from 'react';
import { Col, Container, Form, Row,Image} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import Header from './Header';
import meat_seafood_icon from "./Images/meat_seafood.jpg";
import "./Categories.css";




export default function Categories(props) {

    const categoryData = [
    { name: 'Meat and Seafood', icon: meat_seafood_icon, link:'/'},
    { name: 'Fresh Produce', icon: meat_seafood_icon, link:'/' },
    { name: 'Dairy', icon: meat_seafood_icon, link:'/' },
    { name: 'Bakery', icon: meat_seafood_icon, link:'/' },
    { name: 'Deli', icon: meat_seafood_icon, link:'/' },
    { name: 'Frozen', icon: meat_seafood_icon, link:'/' },
    { name: 'Household', icon: meat_seafood_icon, link:'/' },
    { name: 'Drinks', icon: meat_seafood_icon, link:'/' },
  ];

    return (
    <>
        <h7 className='custom-see-all'> See all</h7>
        <Stack direction="horizontal" gap={2} className='custom-stack' >
            {categoryData.map((category, index) => (
                <Container key={index} className='category-container'>
                    <Row className="d-inline-block">
                        <Image className='category-icon' src={category.icon} roundedCircle />
                    </Row>
                    <Row>
                        <p>{category.name}</p>
                    </Row>
                </Container>
            ))}
            
        </Stack>
    </>
    )
}