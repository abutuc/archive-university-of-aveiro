import React from 'react';

// Bootstrap
import Modal from 'react-bootstrap/Modal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleCheck } from '@fortawesome/free-solid-svg-icons';

export default function ConfirmPurchaseModal({ show, handleClose }) {
    return(
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Thank you for your puchase</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p>
                    <FontAwesomeIcon icon={faCircleCheck} /> Your order has been placed successfully.
                </p>
            </Modal.Body>
        </Modal>
    )
}