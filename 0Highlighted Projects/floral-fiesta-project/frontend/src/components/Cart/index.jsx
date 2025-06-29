import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { CartContext } from './CartContext';
import Offcanvas from 'react-bootstrap/Offcanvas';
import Button from 'react-bootstrap/Button';
import ListGroup from 'react-bootstrap/ListGroup';
import CartItem from './CartItem';

const Cart = ({ onHide }) => {
  const navigate = useNavigate();

  const { cartState, dispatch } = useContext(CartContext);

  const handleRemoveFromCart = (itemId) => {
    dispatch({ type: 'REMOVE_FROM_CART', payload: itemId });
  };

  const handleIncreaseQuantity = (itemId) => {
    dispatch({ type: 'INCREASE_QUANTITY', payload: itemId });
  };

  const handleDecreaseQuantity = (itemId) => {
    dispatch({ type: 'DECREASE_QUANTITY', payload: itemId });
  };

  const handleClearCart = () => {
    dispatch({ type: 'CLEAR_CART' });
  };

  const formatPrice = (price) => {
    const formattedPrice = new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'EUR',
    }).format(price);
  
    const euroSymbol = '\u20AC'; // Euro symbol
  
    return formattedPrice.replace('€', '') + euroSymbol;
  };
  
  const calculateTotal = () => {
    return cartState.items.reduce(
      (total, item) => total + parseFloat(item.price * item.quantity),
      0
    );
  };

  return (
    <Offcanvas show={true} onHide={onHide} placement="end">
      <Offcanvas.Header closeButton>
        <Offcanvas.Title>Cart</Offcanvas.Title>
      </Offcanvas.Header>
      <Offcanvas.Body>
        {cartState.items.length === 0 ? (
          <p>Your cart is empty.</p>
        ) : (
          <>
            <ListGroup variant="flush">
              {cartState.items.map((item) => (
                <CartItem
                  item={item}
                  handleRemoveFromCart={handleRemoveFromCart}
                  handleIncreaseQuantity={handleIncreaseQuantity}
                  handleDecreaseQuantity={handleDecreaseQuantity}
                  formatPrice={formatPrice}
                />
              ))}
            </ListGroup>
            <div className="mt-3">
              <p>Total: {formatPrice(calculateTotal())}</p>
              <Button variant="secondary" onClick={handleClearCart}>
                Clear Cart
              </Button>
              <Button
                variant="primary"
                className="ms-2"
                onClick={() => {
                  navigate('/purchase');
                  onHide();
                }}
              >
                Checkout
              </Button>
            </div>
          </>
        )}
      </Offcanvas.Body>
    </Offcanvas>
  );
};

export default Cart;
