import React from 'react';

// Bootstrap
import Modal from 'react-bootstrap/Modal';

export default function ErrorModal({ error, show, handleClose, title }) {

    const titleText = title ? title : 'Error';

    return (
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>{titleText}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p>{error}</p>
            </Modal.Body>
        </Modal>
    )
}