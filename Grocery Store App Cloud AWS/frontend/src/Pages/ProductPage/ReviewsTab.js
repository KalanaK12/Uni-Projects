import React, { useEffect, useState } from 'react';
import { Col, Container, Form, ProgressBar, Row} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import starIcon from './icons/star-fill.svg';
import personCircle from './icons/person-circle.svg';
import { tempReviews } from '../../DummyData/data';
import { getReviewStats } from '../../repository/repository';




export default function ReviewsTab(props) {

    const [reviews, setReviews] = useState(tempReviews);
    const [reviewStats, setReviewStats] = useState(getReviewStats(reviews));

    return (
    <>
        <div class="row">
            <div class="col-4">
                <h2 class="text-center">{reviewStats.averageRating.toFixed(1)}</h2>
                <div class="row">
                    <ReviewBar star="5" value={reviewStats.starRatings.starFive_p}/>
                </div>
                <div class="row">
                    <ReviewBar star="4" value={reviewStats.starRatings.starFour_p}/>
                </div>
                <div class="row">
                    <ReviewBar star="3" value={reviewStats.starRatings.starThree_p}/>
                </div>
                <div class="row">
                    <ReviewBar star="2" value={reviewStats.starRatings.starTwo_p}/>
                </div>
                <div class="row">
                    <ReviewBar star="1" value={reviewStats.starRatings.starOne_p}/>
                </div>
                <div class="d-grid gap-2 col-4 mx-auto">
                    <button type="button" class="btn btn-primary">Leave Review</button>
                </div>
                
            </div>
            <div class="col-8">
                    {reviews.map((review, index) => (
                        <Row>
                            <ReviewCard review={review}/>    
                        </Row>
                    ))}
                    {reviews.length === 0 && (
                        <p>No Reviews</p>

                    )}   
            </div>
        </div>
    </>
    )
}


function ReviewBar(props) {
    return (
        <>
            <div class="col-1">
                <p class="align-middle">{props.star}</p>
            </div>
            <div class="col-1">
                <img src={starIcon}  class="align-middle"/>
            </div>
            <div class="col w-auto">
                <ProgressBar now={props.value}/>
            </div>
            <div class="col">
                <p class="align-middle">{props.value.toFixed(0)}%</p>
            </div>
        </>
    )
}

function ReviewCard(props) {
    return (
        <>
            <div class="row row-cols-auto">
                <Col xs>
                    <img src={personCircle} />
                </Col>
                <Col>
                    <Row>
                        <Col>
                            <b>c_id: {props.review.c_id}</b>
                        </Col>
                        <Col>
                            <i>3 days ago</i>
                        </Col>
                    </Row>
                    <Row>
                        <i>rating: {props.review.review_rat}</i>
                    </Row>
                </Col>
                
            </div>
            <Row>
                <p>{props.review.review_det}</p>
            </Row>
        </>
    )
}