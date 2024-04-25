import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Accommodations from "../Accommodations/AccommodationList/accommodations";
import Categories from "../Categories/categories";
import Hosts from "../Hosts/hosts";
import AccommodationAdd from "../Accommodations/AccommodationAdd/AccommodationAdd";
import airbnbService from "../../repository/airbnbRepository";
import ReactDOM from "react-dom/client";
import Header from "../Header/header";
import AccommodationEdit from "../Accommodations/AccommodationEdit/AccommodationEdit";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            accommodations: [],
            hosts: [],
            categories: [],
            selectedAccommodation: {} // ??
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                            <Route path={"/accommodations/add"}
                                   element={<AccommodationAdd
                                       categories={this.state.categories}
                                       hosts={this.state.hosts}
                                       onAddAccommodation={this.addAccommodation}
                                   />}/>
                            <Route path={"/accommodations/edit/:id"}
                                   element={<AccommodationEdit
                                       categories={this.state.categories}
                                       hosts={this.state.hosts}
                                       onEditAccommodation={this.editAccommodation}
                                       accommodation={this.state.selectedAccommodation}
                                   />}/>
                            <Route path={"/accommodations"}
                                   element={<Accommodations
                                       accommodations={this.state.accommodations}
                                       onDelete={this.deleteAccommodation}
                                       onEdit={this.getAccommodation}
                                       onRent={this.lowerAvailableNights}
                                   />}/>
                            <Route path={"/categories"}
                                   element={<Categories
                                       categories={this.state.categories}
                                   />}/>
                            <Route path={"/hosts"} element={<Hosts
                                hosts={this.state.hosts}
                                    />}/>
                            <Route path={"/"}
                                   element={<Accommodations
                                       accommodations={this.state.accommodations}
                                       onDelete={this.deleteAccommodation}
                                       onEdit={this.getAccommodation}
                                       onRent={this.lowerAvailableNights}
                                   />}/>
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () => {
        this.loadCategories();
        this.loadAccommodations();
        this.loadHosts();
    }

    loadAccommodations = () => {
        airbnbService.fetchAccommodations()
            .then((data) => {
                this.setState({
                    accommodations: data.data
                })
            });
    }

    loadCategories = () => {
        airbnbService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    loadHosts = () => {
        airbnbService.fetchHosts()
            .then((data) => {
                this.setState({
                    hosts: data.data
                })
            });
    }

    deleteAccommodation = (id) => {
        airbnbService.deleteAccommodation(id)
            .then(() => {
                this.loadAccommodations();
            });
    }

    addAccommodation = (name, category, hostId, availableNights) => {
        airbnbService.addAccommodation(name, category, hostId, availableNights)
            .then(() => {
                this.loadAccommodations();
            });
    }

    getAccommodation = (id) => {
        airbnbService.getAccommodation(id)
            .then((data) => {
                this.setState({
                    selectedAccommodation: data.data
                })
            });
    }

    editAccommodation = (id, name, category, hostId, availableNights) => {
        airbnbService.editAccommodation(id, name, category, hostId, availableNights)
            .then(() => {
                this.loadAccommodations();
            });
    }

    lowerAvailableNights = (id) => {
        airbnbService.lowerAvailableNights(id)
            .then(() => {
                this.loadAccommodations();
            });
    }
}

export default App;
