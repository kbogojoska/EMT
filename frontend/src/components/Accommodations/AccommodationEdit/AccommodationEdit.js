import React from "react";
import {useNavigate} from 'react-router-dom'

const AccommodationEdit = (props) => {

    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "ROOM",
        hostId: 0,
        availableNights: 0
    });

    const handleChange = (e) => {
        updateFormData(({
            ...formData,
            [e.target.name] : e.target.value.trim()
        }))
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.accommodation.name;
        const hostId = formData.hostId !== 0 ? formData.hostId : props.accommodation.hostId;
        const category = formData.category !== "" ? formData.category : props.accommodation.category;
        const availableNights = formData.availableNights !== 0 ? formData.availableNights : props.accommodation.availableNights;

        props.onEditAccommodation(props.accommodation.id, name, category, hostId, availableNights);
        navigate("/accommodations");
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Accommodation name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder={props.accommodation.name}
                               onChange={handleChange}
                        />
                    </div>
                    {/*name, category, hostId, availableNights*/}
                    <div className="form-group">
                        <label htmlFor="price">Category</label>
                        <select name="category" className="form-select" onChange={handleChange} value={formData.category}>
                            {props.categories.map((term) =>
                                {
                                    if(props.accommodation.category !== undefined &&
                                        props.accommodation.category === term)
                                        return <option selected={props.accommodation.category} value={term}>{term}</option>
                                    else return <option value={term}>{term}</option>
                                }
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label className="form-label">Host</label>
                        <select name="hostId" className="form-select" onChange={handleChange} value={formData.hostId}>
                            {props.hosts.map(host => (
                                <option key={host.id} value={host.id}>{host.name}</option>
                            ))}
                        </select>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="availableNights" className="form-label">Available Nights</label>
                        <input
                            type="number"
                            className="form-control"
                            id="availableNights"
                            name="availableNights"
                            required
                            placeholder={props.accommodation ? props.accommodation.availableNights : ''}
                            onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>

    );
}

export default AccommodationEdit;