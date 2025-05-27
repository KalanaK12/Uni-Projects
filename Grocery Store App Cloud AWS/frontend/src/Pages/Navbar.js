import React, { useState, useEffect, useRef } from "react";
import {
  Col,
  Container,
  Form,
  Row,
  Button,
  Popover,
  Overlay,
} from "react-bootstrap";
import Stack from "react-bootstrap/Stack";
import {
  fetchSearchSuggestions,
  getDefaultFilters,
} from "../repository/repository";
import "./Navbar.css";
import Select from "react-select";
import CustomTabs from "./CustomTabs";
import { Link } from "react-router-dom";
import { useCart } from "../Components/Providers/CartContext";

export default function Navbar(props) {
  const [searchValue, setSearchValue] = useState("");
  //results are product objects
  const [results, setResults] = useState([]);
  //for live results
  const [show, setShow] = useState(false);
  const [target, setTarget] = useState(null);
  const [focusChange, setFocusChange] = useState(false);
  const [searched, setSearched] = useState(false);
  //keep track if submit in clicked
  const [submitted, setSubmitted] = useState(false);
  const [submitClicked, setSubmitClicked] = useState(0);

  const {cartSize} = useCart();


  useEffect(() => {
    fetchSearchSuggestions(searchValue, setResults);
    if (searchValue === "") {
      setShow(false);
    }
    if (results.length === 0) {
      setShow(false);
    } else {
      setShow(true);
    }
    if (!searched) {
      setShow(true);
    }
  }, [searchValue, focusChange]);

  const handleInputChange = (e) => {
    setSearchValue(e.target.value);
    setTarget(e.target);

    setShow(true);
  };

  const handleSubmit = (e) => {
    if (show) {
      setShow(false);
    }
    setShow(false);
    if (searchValue !== "") {
      setSubmitted(true);
      setSubmitClicked(submitClicked + 1);
    } else {
      return;
    }
  };

  return (
    <>
      <Stack direction="horizontal" gap={2} className="p-2">
        <div className="p-3">logo</div>
        <div className="flex-fill">
          <Form.Control
            className="me-auto flex-fill"
            type="text"
            placeholder="Search Item"
            value={searchValue}
            onChange={handleInputChange}
            onBlur={() => setShow(false)}
            onFocus={() => setFocusChange(!focusChange)}
          />
          <Overlay
            show={show}
            target={target}
            placement="bottom"
            containerPadding={20}
          >
            <Popover id="popover-contained">
              <Popover.Body>
                <div className="search-results">
                  {results.map((result,index) => (
                    <a
                      className="mb-3 search-item"
                      key={index}
                      onClick={(e) => {
                        setSearchValue(result.p_name);
                      }}
                    >
                      {result.p_name}
                    </a>
                  ))}
                </div>
              </Popover.Body>
            </Popover>
          </Overlay>
        </div>
        <Link
          to={`/search/${encodeURIComponent(searchValue)}`}
          state={{
            searchTerm: searchValue,
          }}
        >
          <Button
            className="ml-0"
            variant="primary"
            onClick={(e) => {
              // e.preventDefault();
              handleSubmit();
            }}
          >
            Search
          </Button>
        </Link>

        <div className="vr " />
        <div className="p-3 ms-auto">
          <a href="/checkout">Cart ({cartSize})</a>
        </div>
        <div className="p-3">
          <a href="/sign-in">Sign in</a>
        </div>
        <div className="p-3">Info</div>
      </Stack>
      <CustomTabs submitClicked={submitClicked} />
    </>
  );
}
