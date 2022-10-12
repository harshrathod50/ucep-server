import "./Root.css";

import { Link } from "react-router-dom";

import Button from "../../components/Button";

const App = () => {
  return (
    <>
      <h1 className="h-36 text-6xl text-center">UCEP</h1>
      <div className="flex flex-row justify-center">
        <Button>
          <Link to="/ucep_form_loader">Start Application</Link>
        </Button>
      </div>
    </>
  );
};

export default App;
