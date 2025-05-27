import { Col, Row } from "react-bootstrap";
import RecipeCard from "../../../Components/RecipeCard/RecipeCard";
import "./recipe.css";

export default function Recipes() {
  const recipes = [
    {
      subheading: "lorem",
      title: "ipsum",
      date: "July 12, 2021",
      time: "5 mins read",
    },
    {
      subheading: "lorem",
      title: "ipsum",
      date: "July 12, 2021",
      time: "5 mins read",
    },
    {
      subheading: "lorem",
      title: "ipsum",
      date: "July 12, 2021",
      time: "5 mins read",
    },
  ];
  return (
    <>
      <Row className="text-center mt-3">
        <span className="heading-recipes">Recipes..?</span>
      </Row>{" "}
      <Row className="text-center mb-5">
        <span className="sub-heading">Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </span>
      </Row>
      <Row className="justify-content-center">
        {recipes.map((recipe, index) => {
          return (
            <Col key={index} className="d-flex justify-content-center">
              <RecipeCard
                title={recipe.title}
                subheading={recipe.subheading}
                date={recipe.date}
                time={recipe.time}
              ></RecipeCard>
            </Col>
          );
        })}
      </Row>
    </>
  );
}
