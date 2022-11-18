import "./Root.css";

import { Link } from "react-router-dom";

import Button from "../../components/Button";
import Header from "../../components/Header";

const App = () => {
  return (
    <>
      <Header />
      <h1 className="h-36 text-6xl text-center">UCEP</h1>
      <div className="flex flex-row justify-center">
        <Button
          className={`inline px-7 py-3 bg-appColor-600 text-white 
        font-medium text-sm leading-snug uppercase rounded shadow-md 
      hover:bg-appColor-900 hover:shadow-lg focus:bg-appColor-700 
        focus:shadow-lg focus:outline-none focus:ring-0 active:bg-appColor-700 
        active:shadow-lg transition duration-150 ease-in-out`}
        >
          <Link to="/ucep_form_loader">Start Application</Link>
        </Button>
      </div>
    </>
  );
};

export default App;
