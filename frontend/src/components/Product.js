import React from 'react'
import '../Product.css'
import { useStateValue } from './Stateprovider'
import { Row, Card, Col } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { Rating } from 'react-simple-star-rating'

function Product({
  id,
  title,
  describe,
  price,
  image,
  rating,
  categories,
  authors,
  p_qty,
  imageUrl,
  language,
  noOfPages,
  publisher,
}) {
  const [{ basket }, dispatch] = useStateValue()
  console.log('this is basket', basket)
  //let x=10;

  const addToBasket = () => {
    //dispatch item in data layer
    dispatch({
      type: 'ADD_TO_BASKET',
      item: {
        pid: id,
        pname: title,
        pdesc: describe,
        pimage: image,
        pprice: price,
        prating: rating,
        p_qty: p_qty,
        categories: categories,
        authors: authors,
        quantity: 1,
        imageUrl: imageUrl,
      },
    })
  }
  const checkProductInCart = () => {
    console.log(basket)
    let c = basket.filter((b) => b.pid === id)
    if (c.length > 0) {
      return true
    } else {
      return false
    }
  }
  return (
    <Col>
      <Card
        style={{
          padding: '20px',
          boxShadow:
            'rgba(0, 0, 0, 0.1) 0px 1px 3px 0px, rgba(0, 0, 0, 0.06) 0px 1px 2px 0px',
        }}
      >
        <div className="productImageDiv">
          <Card.Img
            variant="top"
            src={imageUrl ? imageUrl : image}
            style={{
              borderRadius: '4px',
              boxShadow: 'rgba(0, 0, 0, 0.15) 2.4px 2.4px 3.2px',
            }}
          />
        </div>
        <Card.Body>
          <Card.Title>{title}</Card.Title>
          <Card.Text style={{ marginBottom: '0px' }}>
            <button className="mb-1" style={{border: '1px solid #6e1230',backgroundColor:"transparent",color:"#6e1230",fontSize:"14px"}}>QUICK PREVIEW</button>
          </Card.Text>
          <Card.Text style={{ marginBottom: '0px' }}>
            {
              authors.map(a => (
                <div>{a.a_name}</div>
              ))
            }
          </Card.Text>
          <Card.Text>
            {/* <small>Rs </small> */}
            <strong>₹{price}</strong>
          </Card.Text>
          <Card.Text style={{ marginBottom: '0px' }}>
            'Available Stocks :
            <strong>
              {p_qty}
            </strong>
          </Card.Text>
          <Card.Text className="product_rating">
            {/* {Array(rating)
              .fill()
              .map((_, i) => (
                <p key={i}>⭐</p>
              ))} */}
            <Rating
              ratingValue={rating}
              allowHalfIcon={true}
              allowHover={false}
              readonly={true}
            />
          </Card.Text>
          {checkProductInCart() ? (
            <Link to="/checkout">
              <button className="addToCartBtn"> Go to cart</button>
            </Link>
          ) : (
            <button onClick={addToBasket} className="addToCartBtn">
              + Add to Basket
            </button>
          )}
        </Card.Body>
      </Card>
    </Col>
  )
}

export default Product
