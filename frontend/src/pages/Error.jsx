import React from "react";
import { Link } from "react-router-dom";

export default function ErrorPage() {

  return (
    <div id="error-page">
      <h1>Oops!</h1>
      <p><b>404 Not found</b> - Sorry, an unexpected error has occurred.</p>
      <Link to="/">Back to main page.</Link>
    </div>
  );
}