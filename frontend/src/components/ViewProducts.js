import React from 'react'
import '../compheader.css'
import { Table } from 'react-bootstrap'

export default class ViewProducts extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      to: [],
    }
  }
  componentDidMount = () => {
    fetch(process.env.REACT_APP_BASE_URL + '/product/getallproducts')
      .then((resp) => resp.json())
      .then((data) => this.setState({ to: data }))
  }
  render() {
    const to1 = this.state.to.length
    return (
      <div>
        {to1 != 0 ? (
          <div className="vhome">
            <div className="vhome_container">
              <div className="vhome_row">
                <Table striped bordered hover style={{ textAlign: 'center' }}>
                  <thead>
                    <tr style={{ backgroundColor: '#6e1230', color: 'white' }}>
                      <th>Product ID</th>
                      <th>Image</th>
                      <th>Vendor Name</th>
                      <th>Product Title</th>
                      <th>Product Describe</th>
                      <th>Product Price</th>
                      <th>Product Rating</th>
                      <th>Product Quantity(units)</th>
                      <th>Categories</th>
                      <th>Authors</th>
                      <th>Publisher</th>
                    </tr>
                  </thead>
                  <tbody>
                    {this.state.to.map((o) => {
                      return (
                        <tr key={o.p_id}>
                          <td>{o.p_id}</td>
                          <td>
                            <img src={o.imageUrl} style={{ height: '110px' }} />
                          </td>
                          <td>{o.vdr?.v_name}</td>
                          <td>{o.pname}</td>
                          <td>{o.pdesc}</td>
                          <td>{o.pprice}</td>
                          <td>{o.prating}</td>
                          <td>{o.pqty}</td>
                          <td>
                            {o.categories.map((c) => {
                              return <div key={c.c_id}>{c.c_name}</div>
                            })}
                          </td>
                          <td>
                            {o.authors.map((a) => {
                              return <div key={a.a_id}>{a.a_name}</div>
                            })}
                          </td>
                          <td>{o?.publisher?.p_name}</td>
                        </tr>
                      )
                    })}
                  </tbody>
                </Table>
              </div>
              <div className="">
                Total Number Of Products:
                <br />
                {this.state.to.length}
              </div>
            </div>
          </div>
        ) : (
          <div style={{ textAlign: 'center', color: 'black' }}>
            <h2>No Data</h2>
          </div>
        )}
      </div>
    )
  }
}
