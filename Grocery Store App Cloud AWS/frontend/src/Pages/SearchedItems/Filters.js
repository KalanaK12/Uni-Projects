import React, { useState,useEffect } from 'react';
import { Col, Container, Form, Row,Dropdown,ToggleButton } from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import { products } from '../../DummyData/data';
import ProductCard from '../HomePage/ProductCard';
import { formatPrice,fetchProducts,getFiltersJSON, getProductsByFilter,getSortOptions,getCategoryOptions, fetchProductsByFilters, getRetailerOptions} from '../../repository/repository';


export default function Filters(props) {
    const [sortType, setsortType] = useState("Name (A-Z)");
    const [categoryFilter, setCategoryFilter] = useState([]);
    const [retailerFilter, setRetailerFilter] = useState([]);
    const [discountFilter, setDiscountFilter] = useState(false);

    const handleCategoryChange = (category) => {
        if (categoryFilter.includes(category)) {
        // If the category is already selected, remove it
        setCategoryFilter(categoryFilter.filter((item) => item !== category));
        } else {
        // If the category is not selected, add it
        setCategoryFilter([...categoryFilter, category]);
        }
    };

    const handleRetailerChange = (retailer) => {
        if (retailerFilter.includes(retailer)) {
        // If the retailer is already selected, remove it
        setRetailerFilter(retailerFilter.filter((item) => item !== retailer));
        } else {
        // If the retailer is not selected, add it
        setRetailerFilter([...retailerFilter, retailer]);
        }
    };

    useEffect(() => {
        let filterJSON = getFiltersJSON(props.searchTerm,categoryFilter,retailerFilter,discountFilter,sortType);

        //request filtered items
        let response = fetchProductsByFilters(filterJSON, props.setData);
    }, [sortType,categoryFilter,discountFilter,retailerFilter]);

    return (
    <>
    <Row className='mt-3'>
    <Col className='sort'>
        <Dropdown data-bs-theme="light">
            <Dropdown.Toggle variant="secondary">
                Sort By: {sortType}
            </Dropdown.Toggle>
            <Dropdown.Menu>
                {getSortOptions().map((sortOption, index) => (
                <Dropdown.Item
                    key={index}
                    onClick={() => setsortType(sortOption)}
                    active={sortType === sortOption}
                    >
                    {sortOption}
                </Dropdown.Item>
                ))}
            </Dropdown.Menu>
        </Dropdown>
    </Col>
    <Col className='category'>
        <Dropdown data-bs-theme="light">
            <Dropdown.Toggle variant="secondary">
                Select Category
            </Dropdown.Toggle>

            <Dropdown.Menu>
                {getCategoryOptions().map((category, index) => (
                <Form.Check
                    key={index}
                    type="checkbox"
                    label={category}
                    checked={categoryFilter.includes(category)}
                    onChange={() => handleCategoryChange(category)}
                />
                ))}
            </Dropdown.Menu>
        </Dropdown>
    </Col>
    <Col className='retailer'>
        <Dropdown data-bs-theme="light">
            <Dropdown.Toggle variant="secondary">
                Select Retailer
            </Dropdown.Toggle>

            <Dropdown.Menu>
                {getRetailerOptions().map((retailer, index) => (
                <Form.Check
                    key={index}
                    type="checkbox"
                    label={retailer}
                    checked={retailerFilter.includes(retailer)}
                    onChange={() => handleRetailerChange(retailer)}
                />
                ))}
            </Dropdown.Menu>
        </Dropdown>
    </Col>
    <Col>
        <ToggleButton
            className="mb-2"
            id="toggle-check"
            type="checkbox"
            variant="outline-primary"
            checked={discountFilter}
            value="1"
            onChange={(e) => setDiscountFilter(e.currentTarget.checked)}
            >
            Specials
      </ToggleButton>
    </Col>
    </Row>
    </>
    )
}