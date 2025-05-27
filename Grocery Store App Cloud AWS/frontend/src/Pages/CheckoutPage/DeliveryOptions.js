import React, { useState,useEffect } from 'react';
import { Col, Container, Form, Row,Dropdown} from "react-bootstrap";
import Stack from 'react-bootstrap/Stack';
import ProductCard from '../HomePage/ProductCard';
import { formatPrice,fetchProducts,getDefaultFilters, getDeliveryTimeSlots } from '../../repository/repository';
import { useLocation } from 'react-router-dom';
import { BsTruck } from 'react-icons/bs';
import DatePicker from 'react-date-picker';
import 'react-date-picker/dist/DatePicker.css';
import 'react-calendar/dist/Calendar.css';



export default function DeliveryOptions(props) {

    const [deliveryDate, setDeliveryDate] = useState(new Date());
    const [selectedTimeSlot, setSelectedTimeSlot] = useState('8:00 AM - 9:00 AM');


    const handleTimeSlotChange = (event) => {
        setSelectedTimeSlot(event.target.value);
    };

    return (
    <>
    <Container className="mt-1">
        <span className=''><BsTruck size={25}/></span>
        <span className='m-3'><b>Delivery Options</b></span> 
        <Row className='mt-2'>
            <p className='mb-0'><b>Time Slot</b></p>
            
            <Stack className='mt-0 mb-0' direction="horizontal" gap={4}>
                <div className='mt-2' style={{ display: 'flex', justifyContent: 'top', alignItems: 'top' }}>
                    <DatePicker 
                        clearIcon = {null}
                        minDate = {new Date()}
                        onChange={setDeliveryDate} 
                        value={deliveryDate} />
                </div>
                <div style={{ display: 'flex', justifyContent: 'top', alignItems: 'top' }}>
                    <select id="timeSlot" value={selectedTimeSlot} 
                    onChange={handleTimeSlotChange}>
                        {getDeliveryTimeSlots().map((slot) => (
                        <option key={slot} value={slot}>
                            {slot}
                        </option>
                        ))}
                    </select>
                </div>
            </Stack>

        </Row>
        <Row className='mt-4'>
            <p><b>Operator</b></p>
            <Form>
                <Form.Check
                    inline
                    isValid
                    label="AusPost"
                    name="group1"
                    type='radio'
                    checked={props.deliveryOperator === "AusPost"}
                    onChange={() => props.setDeliveryOperator("AusPost")}
                />
                <Form.Check
                    inline
                    isValid
                    label="FedEx"
                    name="group1"
                    type='radio'
                    checked={props.deliveryOperator === "FedEx"}
                    onChange={() => props.setDeliveryOperator("FedEx")}
                />
                <Form.Check
                    inline
                    isValid
                    label="DHL"
                    name="group1"
                    type='radio'
                    checked={props.deliveryOperator === "DHL"}
                    onChange={() => props.setDeliveryOperator("DHL")}
                />
            </Form>
        </Row>
        <Row>
        
        </Row>


    </Container>
    </>
    )
}