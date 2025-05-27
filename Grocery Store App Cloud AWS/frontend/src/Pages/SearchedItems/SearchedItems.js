import React, { useState,useEffect } from 'react';
import { Col, Container, Form, Row,Dropdown} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import { products } from '../../DummyData/data';
import ProductCard from '../HomePage/ProductCard';
import { formatPrice,fetchProducts,getDefaultFilters } from '../../repository/repository';
import Filters from './Filters';
import ProductsContainer from './ProductsContainer';
import { useLocation } from 'react-router-dom';



export default function SearchedItems(props) {
    const location = useLocation();
    //? location.state should be below the if statement checking if location.state is null or not
    const { searchTerm } = location.state;
    

    const [data, setData] = useState(null);
    //filters is a json object
    const [filters, setFilters] = useState(getDefaultFilters);

    useEffect(() => {
      fetchProducts(searchTerm, setData);
      
    }, [searchTerm]);

    // if (!location.state || !location.state.searchTerm) {
    //     return <div>Loading...</div>;
    // }



    

    return (
    <>
      <Container className='mt-4'>
        <Row className><h2>Products for "{searchTerm}"</h2></Row>

          <Filters setFilters={setFilters} searchTerm={searchTerm} 
          setData={setData}/>

          {data !== null && (
            <ProductsContainer data={data}/>
            )}
      </Container>
    </>
    )
}