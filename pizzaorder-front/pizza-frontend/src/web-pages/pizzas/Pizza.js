import React from "react";
import axios from "axios";

import "./Pizza.css";

import "./pizza-frame/Framepizza";
import Framepizza from "./pizza-frame/Framepizza";

const baseURL = "http://localhost:8080/api/pizzas";

export default function Pizza() {
  const [post, setPost] = React.useState(null);

  React.useEffect(() => {
    axios.get(baseURL).then((response) => {
      setPost(response.data);
    });
  }, []);

  console.log(post);
  return (
    <div className="container-elements-pizza">
      <div className="banner-oferta">
        <div className="banner-text">
          <i>
            <b>🍕🍕🍕 Profita acum de noile tipuri de pizza 🍕🍕🍕</b>
          </i>
        </div>
      </div>
      <div className="container-space-elements">
        <div className="container-pizza-elements">
          {post != null
            ? post.map((item, index) => {
                return (
                  <Framepizza
                    key={item.id}
                    denumire={item.denumire}
                    pret={item.pret}
                    url={item.url}
                  />
                );
              })
            : null}
        </div>
      </div>
    </div>
  );
}
