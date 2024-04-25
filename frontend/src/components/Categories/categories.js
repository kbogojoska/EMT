import React from "react";

const categories = (props) => {
    return (
        <div className="container mt-5">
            <div className="row">
                <div className="table-responsive">
                    <table className="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.categories.map((term, index) => {
                            return (
                                <tr key={index}>
                                    <td>{index + 1}</td>
                                    <td>{term}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default categories;