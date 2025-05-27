import { useState } from "react";
import { Col, Container, Row, Form, Button, InputGroup } from "react-bootstrap";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import ErrorModal from "../../Components/ErrorModal/ErrorModal";
import { Navigate, useNavigate } from "react-router-dom";
import { addUserDataToLocalStorage, loginUser } from "../../repository/repository";
import axios from "axios";

export default function Signin() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [errorTitle, setErrorTitle] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (email === "" || password === "") {
      setShowModal(true);
      setErrorTitle("Failed Logging in");
      setErrorMessage("Email or password can't be empty");
      return;
    }

    const data = {
      userName: email,
      password: password
    }

    try {
      const response = await loginUser(data);
      addUserDataToLocalStorage(response.data.accessToken, response.data.customerUsername,response.data.customerID);

      // Reset the form fields 
      setEmail('');
      setPassword('');
    } catch (error) {
      console.log('Login error:', error);
    }
    
  };

  

  return (
    <>
      <Container>
        <Row className="d-flex p-3">
          <Col>
            <div>
              <a href="/">Logo</a>
            </div>
          </Col>
        </Row>
        <Row className="mt-5">
          <Col className="d-flex justify-content-center">
            <div>
              <img
                src="https://via.placeholder.com/400x400"
                alt="placeholder"
              />
            </div>
          </Col>
          <Col>
            <div className="mb-5">
              <h3>
                <b>Welcome Back</b> 👋
              </h3>
              <h5>Log in your account</h5>
            </div>
            <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Control
                  type="email"
                  placeholder="Email Adress"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </Form.Group>
              <InputGroup className="mb-3" controlId="formBasicPassword">
                <Form.Control
                  type={showPassword ? "text" : "password"}
                  placeholder="Password"
                  value={password}
                  onChange={(e) => {
                    setPassword(e.target.value);
                  }}
                />
                <Button
                  variant="secondary"
                  onClick={() => setShowPassword(!showPassword)}
                >
                  {showPassword ? <FaEyeSlash /> : <FaEye />}
                </Button>
              </InputGroup>
              <div className="d-flex justify-content-between">
                <Form.Group className="mb-3" controlId="formBasicCheckbox">
                  <Form.Check type="checkbox" label="Remember me" />
                </Form.Group>
                <a href="/forgot-password">Forgot Password</a>
              </div>
              <div className="d-grid gap-2">
                <Button variant="secondary" type="submit" size="md">
                  Submit
                </Button>
              </div>
            </Form>
            <div className="d-flex justify-content-center m-5">
              <span>
                Don't have an account? <a href="/sign-up"><b>Sign up</b></a>
              </span>
            </div>
          </Col>
        </Row>
        <ErrorModal
          show={showModal}
          onHide={() => setShowModal(false)}
          errorTitle={errorTitle}
          errorBody={errorMessage}
        />
      </Container>
    </>
  );
}
