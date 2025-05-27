import React, { useEffect, useState } from 'react';
import { Col, Container, Form, Row,Card,Button} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import "./ProductCard.css";
import tempImage from './Images/muesli_product.png'
import { fetchReviews, formatPrice, getReviewStats } from '../../repository/repository';
import { Link } from 'react-router-dom';
import { BsHeartFill,BsCartPlus } from "react-icons/bs";
import ReactStars from 'react-stars';
import LinesEllipsis from 'react-lines-ellipsis';
import { useCart } from '../../Components/Providers/CartContext';


export default function ProductCard(props) {

    const [reviewStats, setReviewStats] = useState({});
    const {addToCart} = useCart();


    //fetch reviews
    useEffect(() => {
        const getReviews = async () => {

            const responseJSON = await fetchReviews(props.product.p_id);
            const reviewStatsTemp = getReviewStats(responseJSON);
            setReviewStats(reviewStatsTemp);
        }

        getReviews();
    }, []);
    


    return (
    <>
    <Card className='card'>
        <div style={{ position: 'relative' }}>
            <Container style={{ minHeight: '200px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            <Card.Img variant="top" src={`http://localhost:8080/images/products/${props.product.p_sub_cat}.jpg`} 
            />
            {(props.product.p_org_price-props.product.p_price)>0&& (
                <div className='discount-text'>Save ${formatPrice(props.product.p_org_price - props.product.p_price)}</div>
            
            )}
            </Container>
        </div>
        <Card.Body>
            <Link
            className='title'
            to={`/product/${props.product.p_id}`}
            state={{
                product: props.product,
            }}
            >   <LinesEllipsis className='title'
                    text={props.product.p_name}
                    maxLine='3'
                    ellipsis='...'
                    trimRight
                    basedOn='letters'
                    />
            </Link>
            <Row>
                <Stack className='mt-3 mb-0' direction="horizontal" gap={1}>
                    <p className='price mb-0'>${formatPrice(props.product.p_price)}</p>
                    {(props.product.p_org_price-props.product.p_price)>0 && (
                        <p className='org-price mb-0'>| WAS ${formatPrice(props.product.p_org_price)}</p>
                
                    )}
                </Stack>
            </Row>
            <p className='ppw mt-0'>${formatPrice(props.product.p_weight)} / 100g - {props.product.p_retailer_name}</p>
            <Row>
                <Stack className='mt-0 mb-2' direction="horizontal" gap={1} align="center">
                    <ReactStars
                        count={5}
                        size={18}
                        edit={false}
                        value={reviewStats.averageRating}
                        color2={'#ffd700'} />
                    <div className='avg-r'>{reviewStats.averageRating}</div>
                    <div className='total-r'>({reviewStats.totalReviews})</div>
                </Stack>
            </Row>
            <Row>
                <div onClick={() => addToCart(props.product,1)} className="ms-auto cart-button">
                    <BsCartPlus size={24} />&nbsp;&nbsp;Add
                </div>
            </Row>
        </Card.Body>
    </Card>
    </>
    )
}