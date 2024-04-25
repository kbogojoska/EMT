import axios from '../custom-axios/axios';
import accommodations from "../components/Accommodations/AccommodationList/accommodations";

const airbnbService = {
    fetchAccommodations : () => {
        return axios.get("/accommodations")
    },
    fetchCategories : () => {
        return axios.get("/accommodations/categories")
    },
    fetchHosts: () => {
        return axios.get("/hosts");
    },
    deleteAccommodation: (id) => {
        return axios.delete(`/accommodations/delete/${id}`) // or delete;
    },
    addAccommodation: (name, category, hostId, availableNights) => {
        return axios.post("/accommodations/add", {
            "name": name,
            "category": category,
            "hostId": hostId,
            "availableNights": availableNights
        })
    },
    editAccommodation: (id, name, category, hostId, availableNights) => {
        return axios.post(`/accommodations/edit/${id}`, {
            "name": name,
            "category": category,
            "hostId": hostId,
            "availableNights": availableNights
        })
    },
    getAccommodation: (id) => {
        return axios.get(`/accommodations/${id}`);
    },
    lowerAvailableNights: (id) => {
        return axios.post(`/accommodations/occupyRoom/${id}`);
    }
}

export default airbnbService;