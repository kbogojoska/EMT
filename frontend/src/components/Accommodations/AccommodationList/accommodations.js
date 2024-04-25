import React from "react";
import AccommodationTerm from "../AccommodationTerm/accommodationTerm";
import { Link } from 'react-router-dom';

const accommodations = (props) => {
    return (
        <div className="container mt-5">
            <div className="row">
                <div className="table-responsive">
                    <table className="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Category</th>
                            <th scope="col">Host</th>
                            <th scope="col">Available Nights</th>
                        </tr>
                        </thead>
                        <tbody>
                            {props.accommodations.map((term) => {
                                return (
                                  <AccommodationTerm term={term}
                                                     onDelete={props.onDelete}
                                                     onEdit={props.onEdit}
                                                     onRent={props.onRent}/>
                                );
                            })}
                        </tbody>
                    </table>
                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className="btn btn-dark btn-block" to="/accommodations/add">Add New Accommodation</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default accommodations;