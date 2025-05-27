import { Row, Col, Container, Image, Stack } from "react-bootstrap";
import { BsTrash } from "react-icons/bs";
import './CheckoutPage.css'
import { useCart } from "../../Components/Providers/CartContext";


export default function CartItem(props) {

    const {removeFromCart} = useCart();

    return (
    <>
    <Container className="mt-1">
        <Row className="mb-3">
            <Col md={8}>
                <Row>
                    <Stack direction="horizontal" gap={4}>
                        <Image width={80} src={`http://localhost:8080/images/products/${props.entry.product.p_sub_cat}.jpg`}/>
                        <p>{props.entry.product.p_name}</p>
                    </Stack>
                </Row>
            </Col>
            
            <Col md={1}>
                <p>${props.entry.product.p_price}</p>
            </Col>
            <Col md={1}>
                <p>{props.entry.quantity}</p>
            </Col>
            <Col md={1}>
                <p>${props.entry.totalcost}</p>
            </Col>
            <Col md={1}>
                <BsTrash className="bin-btn" size={18} onClick={()=>removeFromCart(props.entry.product.p_id)}/>
            </Col>
        </Row>
    </Container>
    </>
    )
}