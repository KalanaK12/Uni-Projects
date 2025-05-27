import { useEffect, useState } from "react";
import { Col, Container, Row, Form, Button, InputGroup } from "react-bootstrap";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import ErrorModal from "../../Components/ErrorModal/ErrorModal";
import { Navigate, isRouteErrorResponse, useNavigate } from "react-router-dom";
import { registerUser } from "../../repository/repository";

export default function SingUp() {
  const [email, setEmail] = useState("");
  const [navigateToSignIn, setNavigateToSignIn] = useState(false);
  const [phoneNumber, setPhoneNumber] = useState("");
  const [firstName, setFirstname] = useState("");
  const [lastName, setLastName] = useState("");
  const [password, setPassword] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [errorTitle, setErrorTitle] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const navigate = useNavigate();

  const handleCloseModal = () => {
    setShowModal(false);

    // If the errorTitle is "Success," set navigateToSignIn to true
    if (errorTitle === "Success") {
      setNavigateToSignIn(true);
    }
  };

  useEffect(() => {
    // Use the `navigate` function to redirect to /sign-in when navigateToSignIn is true
    if (navigateToSignIn) {
      navigate("/sign-in");
    }
  }, [navigateToSignIn]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (
      email === "" ||
      password === "" ||
      firstName === "" ||
      lastName === "" ||
      phoneNumber === ""
    ) {
      setShowModal(true);
      setErrorTitle("Failed Signing up ");
      setErrorMessage(
        "Email, Password, Names, and Phone Number can't be empty"
      );
      return;
    }

    const customerData = {
      firstName: firstName,
      lastName: lastName,
      phoneNumber: phoneNumber,
      userName: email,
      password: password,
    };

    const response = await registerUser(customerData);

    console.log(response);

    if (response == null) {
      setShowModal(true)
      setErrorTitle("Failed Sign up");
      return;
    } else {
      setShowModal(true)
      setErrorTitle("Success");
      setErrorMessage("User has been Signed up");
      return;
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
          <Col className="p-5">
            <div className="mb-5">
              <h3>
                <b>Welcome</b> 👋
              </h3>
              <h5>Create your account</h5>
            </div>
            <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Control
                  type="text"
                  placeholder="First Name"
                  value={firstName}
                  onChange={(e) => setFirstname(e.target.value)}
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Control
                  type="text"
                  placeholder="Last Name"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Control
                  type="text"
                  placeholder="Phone Number"
                  value={phoneNumber}
                  onChange={(e) => setPhoneNumber(e.target.value)}
                />
              </Form.Group>
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
              <div className="d-grid gap-2">
                <Button variant="secondary" type="submit" size="md">
                  Submit
                </Button>
              </div>
            </Form>
          </Col>
        </Row>
        <ErrorModal
          show={showModal}
          onHide={handleCloseModal}
          errorTitle={errorTitle}
          errorBody={errorMessage}
        />
      </Container>
    </>
  );
}
