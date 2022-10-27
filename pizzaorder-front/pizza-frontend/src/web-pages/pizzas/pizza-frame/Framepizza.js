import React from "react";

import "./Framepizza.css";

export default function Framepizza(props) {
  return (
    <div className="pizza-frame">
      <div className="pizza-title">
        <i>{props.denumire}</i>
      </div>
      <div className="pizza-image">
        <center>
          <img src={props.url} width="180" height="180"></img>
        </center>
      </div>
      <div className="pizza-pret">
        <i>{props.pret} lei</i>
      </div>
    </div>
  );
}
