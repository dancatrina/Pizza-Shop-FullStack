import React from "react";
import { Route, Routes } from "react-router-dom";

import Navigationbar from "../../components/navigation-bar/Navigationbar";
import Bautura from "../bauturi/Bautura";
import Contact from "../contact/Contact";
import About from "../about/About";
import Pizza from "../pizzas/Pizza";

import "./Home.css";

export default function Home() {
  return (
    <div className="container-home">
      <Navigationbar />
      <div className="container-content">
        <Routes>
          <Route path="/pizza" element={<Pizza />} />
          <Route path="/bauturi" element={<Bautura />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/about" element={<About />} />
        </Routes>
      </div>
      <div className="footer-container">
        <div className="info-text">📞- 0733 021 041</div>
        <div className="crediatls">Site powered by Catrina Dan - 👷</div>
      </div>
    </div>
  );
}
