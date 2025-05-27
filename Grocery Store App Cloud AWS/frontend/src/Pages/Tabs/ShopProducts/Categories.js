import React, { useEffect, useState } from "react";
import { Col, Container, Form, Row, Image } from "react-bootstrap";
import { PiFishSimpleBold } from "react-icons/pi";
import Stack from "react-bootstrap/Stack";
import meat_seafood_icon from "./Images/meat_seafood.jpg";
import "./Categories.css";
import { fetchAllCategories } from "../../../repository/repository";
import { Link } from "react-router-dom";
import CategoryIcon from "../../../Components/CategoryIcon/CategoryIcon";

export default function Categories(props) {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    fetchAllCategories().then((response) => {
      if (response != null) {
        setCategories(response.data);
      }
    });
  }, []);


  return (
    <>
      <Stack direction="horizontal" gap={2} className="custom-stack mt-3 justify-content-between p-5">
        {categories != null &&
          categories.map((category, index) => (
            <CategoryIcon icon={category.cat_name}/>
          ))}
      </Stack>
    </>
  );
}
