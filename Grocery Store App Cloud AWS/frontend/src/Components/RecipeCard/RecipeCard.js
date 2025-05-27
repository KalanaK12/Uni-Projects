import { Card, Col, Container, Row } from "react-bootstrap";
import "./recipecard.css";

export default function RecipeCard(props) {
  return (
    <>
      <Card style={{ width: "376px" }}>
        <div style={{ position: "relative" }}>
          <Card.Img variant="top" src="https://via.placeholder.com/376x256" />
        </div>
        <Card.Body>
          <Container>
            <Row>
              <span className="recipe-subheading">{props.subheading}</span>
            </Row>
            <Row>
              <span className="recipe-title">{props.title}</span>
            </Row>
            <Row className="mt-4">
              <Col className="">
                <span className="recipe-date">{props.date}</span>
              </Col>
              <Col className="tag justify-content-end">
                <span className="tag-item">{props.time}</span>
              </Col>
            </Row>
          </Container>
        </Card.Body>
      </Card>
    </>
  );
}
