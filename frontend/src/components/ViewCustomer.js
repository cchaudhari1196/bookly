import React from 'react'
import '../compheader.css';
import { Form, Table } from 'react-bootstrap';

export default class ViewCustomer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            to: []
        }
    }
    componentDidMount = () => {
        fetch(process.env.REACT_APP_BASE_URL + "/user/getalluser")
            .then(resp => resp.json())
            .then(data => this.setState({ to: data }));

    }

    
    onChangeAproveStatus = async (e, o) => {
        e.preventDefault()
        let allCustomers = [...this.state.to]
        allCustomers.filter(u => u.u_id === o.u_id).map(async (u) => {

            console.log(u.u_status)

            await fetch(process.env.REACT_APP_BASE_URL + '/user/approve/' + o.u_id + "/" + !u.u_status, {
                method: 'PATCH', // or 'PUT'
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => response.json())
                .then(data => {
                    u.u_status = !u.u_status
                    this.setState({ to: allCustomers })
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        })
    }


    render() {
        const to1 = this.state.to.length;
        return (
            <div>{to1 != 0 ?
                <div className='vhome'>
                    <div className='vhome_container'>
                        <div className=''>
                            {/* <table>
                                <tr>
                                    <th>Customer ID</th>
                                    <th>Customer FirstName</th>
                                    <th>Customer LastName</th>
                                    <th>Customer Email</th>
                                    <th>Customer Address</th>
                                    <th>Customer ContactNumber</th>
                                    <th>Customer Wallet</th>
                                    <th>Customer Approve</th>
                                </tr>
                                {
                                    this.state.to.map(
                                        (o) => {
                                            return (
                                                <tr>
                                                    <td>{o.u_id}</td>
                                                    <td>{o.u_fname}</td>
                                                    <td>{o.u_lname}</td>
                                                    <td>{o.u_email}</td>
                                                    <td>{o.u_address}</td>
                                                    <td>{o.u_phone}</td>
                                                    <td>{o.wallet}</td>
                                                    <td>
                                                        <Form.Check
                                                            type="switch"
                                                            id="custom-switch5"
                                                            checked={(o.u_status === "true" || o.u_status === true) ? true : false}
                                                            onChange={(e) => this.onChangeAproveStatus(e, o)}
                                                            className="mt-2"
                                                        />
                                                    </td>
                                                </tr>
                                            );
                                        }
                                    )
                                }
                            </table> */}
                            <Table striped bordered hover  style={{textAlign: 'center'}}>
                                <thead>
                                    <tr style={{ backgroundColor: "#6e1230", color: "white" }}>
                                        <th>Customer ID</th>
                                        <th>Customer FirstName</th>
                                        <th>Customer LastName</th>
                                        <th>Customer Email</th>
                                        <th>Customer Address</th>
                                        <th>Customer ContactNumber</th>
                                        <th>Customer Wallet</th>
                                        <th>Customer Approve</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        this.state.to.map(
                                            (o) => {
                                                return (
                                                    <tr>
                                                        <td>{o.u_id}</td>
                                                        <td>{o.u_fname}</td>
                                                        <td>{o.u_lname}</td>
                                                        <td>{o.u_email}</td>
                                                        <td>{o.u_address}</td>
                                                        <td>{o.u_phone}</td>
                                                        <td>{o.wallet}</td>
                                                        <td>
                                                            <Form.Check
                                                                type="switch"
                                                                id="custom-switch5"
                                                                checked={(o.u_status === "true" || o.u_status === true) ? true : false}
                                                                onChange={(e) => this.onChangeAproveStatus(e, o)}
                                                                className="mt-2"
                                                            />
                                                        </td>
                                                    </tr>
                                                );
                                            }
                                        )
                                    }
                                </tbody>
                            </Table>
                        </div>
                        <div className='vhome_row'>Total Number Of Customer:<br />{this.state.to.length}</div>
                    </div>
                </div>
                : <div style={{ textAlign: "center", color: "black" }}><h2>No Data</h2></div>
            }
            </div>
        )
    }
}