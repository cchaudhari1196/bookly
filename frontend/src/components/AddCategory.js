import React from 'react'
import '../register.css'
import { Link } from 'react-router-dom'
import 'react-dropdown/style.css'
import { Form } from 'react-bootstrap'

export default class AddProduct extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      cname: '',
    }
  }

  handleChange = (a) => {
    this.setState({ cname: a.target.value })
    console.log(this.state.cname)
  }
  submitForm = async (e) => {
    e.preventDefault()
    const reqData = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        c_name: this.state.cname,
      }),
    }
    await fetch(process.env.REACT_APP_BASE_URL + '/category', reqData)
      .then((resp) => resp.json())
      .then((data) => this.setState({ st: data, success: true }))
    window.location.href = '/viewcategory'
  }
  render() {
    return (
      <div className="register">
        <div className="register_container">
          <form>
            <Form.Group className="mb-2">
              <Form.Label>Category Name</Form.Label>
              <Form.Control
                type="text"
                name="cname"
                value={this.state.cname}
                onChange={this.handleChange}
              />
            </Form.Group>

            <Link to="/viewproducts">
              {' '}
              <button
                className="innerbutton mt-3"
                type="submit"
                value="Submit"
                onClick={this.submitForm}
              >
                Add Category
              </button>
            </Link>
            <br />
          </form>
        </div>
      </div>
    )
  }
}
