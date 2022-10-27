import React from "react";

import "./Navigationbar.css";

import logoImage from "../../ui-pngs/logo/pizza-restaurant-logo-nobg.png";

import { Link, useMatch, useResolvedPath } from "react-router-dom";

export default function Navigationbar() {
  return (
    <nav className="navigation-bar">
      <div className="container-logo">
        <img src={logoImage} className="image-logo" alt="logo"></img>
        <h1 className="text-logo">R'Danido</h1>
      </div>
      <div className="container-elements">
        <ul className="container-ui-elements">
          <CostumLink to="/pizza">Pizza</CostumLink>
          <CostumLink to="/bauturi">Bauturi</CostumLink>
          <CostumLink to="/contact">Contact</CostumLink>
          <CostumLink to="/about">About</CostumLink>
        </ul>

        <div className="container-button">
          <button>Log in</button>
        </div>
      </div>
    </nav>
  );
}

function CostumLink({ to, children, ...props }) {
  const resolvedPath = useResolvedPath(to);
  const isActive = useMatch({ path: resolvedPath.pathname, end: true });

  return (
    <li className={isActive ? "active" : ""}>
      <Link to={to} {...props}>
        {children}
      </Link>
    </li>
  );
}
