import React, { useEffect, useState } from 'react';
import { Col, Container, Form, Image, ProgressBar, Row,Tab,Tabs} from "react-bootstrap";
import ProductCard from '../HomePage/ProductCard';
import { Breadcrumb } from 'react-bootstrap';
import Stack from 'react-bootstrap/Stack';
import Button from 'react-bootstrap/Button';
import temp_image from "../HomePage/Images/muesli_product.png";
import { Link, useLocation, useParams } from 'react-router-dom';
import { fetchCatImage, fetchRetailer, fetchReviews, fetchSimilarProducts, formatPrice, getReviewStats } from '../../repository/repository';
import "./ProductPage.css";
import ReviewsTab from './ReviewsTab';
import { BsCartPlus } from 'react-icons/bs';
import { useCart } from '../../Components/Providers/CartContext';



export default function ProductPage(props) {
    const location = useLocation();
    const [key, setKey] = useState('details');
    const [quantity, setQuantity] = useState(1);
    const [simProducts, setSimProducts] = useState([]);
    const [reviewStats, setReviewStats] = useState({});
    const [retailer, setRetailer] = useState({});
    const {cart,addToCart} = useCart();

    
    // const style = {
    //     fontSize: '16px',
    //     fontWeight: 'bold',
    //     lineHeight: '28px',
    //     color: '#000000FF'
    // };
    //? Could result in potential issues of accessing location.state when location is NULL
    // if (!location.state || !location.state.product) {
    //     return <div>Loading...</div>;
    // }
    
    const { product } = location.state;

    useEffect(() => {
        const getSimilarProducts = async () => {

            const responseJSON = await fetchSimilarProducts(product.p_id,product.p_sub_cat);
            setSimProducts(responseJSON);
        }
        const getReviews = async () => {

            const responseJSON = await fetchReviews(product.p_id);
            const reviewStatsTemp = getReviewStats(responseJSON);
            setReviewStats(reviewStatsTemp);
        }
        const getRetailer = async () => {

            const responseJSON = await fetchRetailer(product.p_id);
            setRetailer(responseJSON);
        }


        getSimilarProducts();
        getReviews();
        getRetailer();
    }, [location.state]);
    

    return (
    <>
        {/*Breadcrumb and Share/Save Row*/}
    <Container>
    
        <Row className='mt-2 mb-0 breadcrumb'>
            <Breadcrumb>
            
                <Breadcrumb.Item>
                <Link to={`/search/${encodeURIComponent(product.p_cat_name)}`}
                    state={{
                    searchTerm: product.p_cat_name,
                }}
                >
                    <div className='breadcrumb'>{product.p_cat_name}</div>
                </Link>
                </Breadcrumb.Item>
                
                <Breadcrumb.Item>
                <Link to={`/search/${encodeURIComponent(product.p_sub_cat_name)}`}
                    state={{
                    searchTerm: product.p_sub_cat_name,
                }}
                >
                    <p className='breadcrumb'>{product.p_sub_cat_name}</p>
                </Link>
                </Breadcrumb.Item>
            </Breadcrumb>
        </Row>
        
        {/* Main Product Info */}
        <Row className="mb-4">
            <Col md={6}>
                <Image width={400} src={`http://localhost:8080/images/products/${product.p_sub_cat}.jpg`}/>
            </Col>
            <Col md={6} className='p-details'>
                <h1 className='p-details-heading'>{product.p_name}</h1>
                <Stack className='mt-3 mb-3' direction="horizontal" gap={1}>
                    <div className='review-text'>{reviewStats.averageRating}</div>
                    <div style={{ marginRight: '1rem' }}>starts here</div>
                    <div className='review-text'>{reviewStats.totalReviews}</div>
                    <div style={{ color:'#9095A0FF' }}>reviews</div>

                </Stack>

                <Stack className='' direction="horizontal" gap={3}>
                    <h2 style={{ fontSize: '48px', fontWeight: 700 }} className='mb-1'>${formatPrice(product.p_price)}</h2>
                    
                    {product.p_org_price && (
                        <div className='discounttext d-inline-block'>
                            Save ${formatPrice(product.p_org_price - product.p_price)}
                        </div>
                    )}
                </Stack>
                
                <Stack style={{ fontSize: '16px', fontWeight: 400 }} className='mb-4' direction="horizontal" gap={1}>

                    <p className='' >${formatPrice(product.p_weight)} / 100g</p>
                    {product.p_org_price && (
                        <p>
                            | Was ${formatPrice(product.p_org_price)}
                        </p>
                    )}

                </Stack>
                

                <Row className="mt-5 mb-1">
                <b style={{ color:'#424955FF' }}>Quantity</b>
                </Row>
                
                <Row className="row row-cols-auto">
                    <div class="col">
                        <Button className='quantity-btn'
                        onClick={() => setQuantity(quantity > 1 ? quantity - 1 : 1)}
                        ><b>-</b></Button>        
                    </div>
                    <div class="col">
                        <Form.Control placeholder={quantity} readOnly
                        style ={{width: '50px'}} />
                    </div>
                    <div class="col">
                        <Button className='quantity-btn' onClick={()=> setQuantity(quantity+1)}><b>+</b></Button>        
                    </div>
                </Row>
                <Stack className='mt-4' direction="horizontal" gap={2}>

                    <div className="cart-btn" onClick={() => addToCart(product,quantity)}>
                        <BsCartPlus size={20}/>
                        &nbsp;&nbsp;Add To Cart
                    </div>
                    <Button className='checkout-btn' variant="dark">Checkout</Button>
                </Stack>
                


            </Col>
        </Row>

        {/* Compare Prices */}

            <h1 className='mb-0 mt-5' style={{ color:'#171A1FFF', fontSize:'20px', fontWeight:700 }}>Compare Prices</h1>
            <Row className='justify-content-left mt-4'>
                
                {simProducts.map((product, index) => (
                    <Col className='m-2' xs='auto' key={index}>
                    <ProductCard product={product} />
                    </Col>
                ))}
                {simProducts.length === 0 && (
                    <p>No Similar Items...</p>

                )}
            </Row>

        {/* Product Detail/Reviews/About Store */}

            <Tabs 
            activeKey={key}
            onSelect={(k) => setKey(k)}
            className="mb-0 mt-5 bg-light"
            >
                <Tab eventKey="details" title="Product Detail">
                    <p>{product.p_desc}</p>
                </Tab>

                <Tab
                eventKey="reviews" title={`Reviews (${reviewStats.totalReviews})`}>
                    <ReviewsTab reviewStats={reviewStats}/>
                </Tab>

                <Tab eventKey="storeAbout" title="About Store">
                    <Row>
                        <Col className='d-inline-block' md={4}>
                            <Image width={100} src={`http://localhost:8080/images/retailer/${product.p_retailer_id}_small.jpg`} roundedCircle />
                            <h4>{retailer.ret_name}</h4>
                            <h7>{retailer.ret_web}</h7>
                        </Col>
                        <Col>
                            <h7>{retailer.ret_det}</h7>

                        </Col>
                    </Row>
                </Tab>
            </Tabs>
                    
    </Container>

    </>
    )
}   

