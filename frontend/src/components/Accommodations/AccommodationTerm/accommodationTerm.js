import React from "react";
import { Link } from 'react-router-dom';

const accommodationTerm = (props) => {
    return (
        <tr>
            <td scope="col">{props.term.name}</td>
            <td scope="col">{props.term.category}</td>
            <td scope="col">{props.term.host.name}</td>
            <td scope="col">{props.term.numRooms}</td>
            <td scope="col" className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
            </td>
            <td scope="col" className={"text-right"}>
                <Link title={"Edit"} className={"btn btn-info ml-2"}
                   onClick={() => props.onEdit(props.term.id)}
                   to={`/accommodations/edit/${props.term.id}`}>
                    Edit
                </Link>
            </td>
            <td scope="col" className={"text-right"}>
                <a title={"Rent"} className={"btn btn-success ml-2"}
                      onClick={() => props.onRent(props.term.id)}>
                    Rent
                </a>
            </td>
        </tr>
    );
}

export default accommodationTerm;