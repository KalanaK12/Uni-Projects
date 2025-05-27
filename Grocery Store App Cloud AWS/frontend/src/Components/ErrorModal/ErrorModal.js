import { Modal, Button } from "react-bootstrap";
import { useState, useEffect} from "react";

export default function ErrorModal({ show, onHide, errorTitle, errorBody}) {
  return (
    <>
      <Modal
        show={show}
        onHide={onHide}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>{errorTitle}</Modal.Title>
        </Modal.Header>
        <Modal.Body>{errorBody}</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={onHide}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
