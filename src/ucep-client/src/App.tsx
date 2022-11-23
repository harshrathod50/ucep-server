import "./App.css";
import "@fontsource/roboto/400.css";
import "@fontsource/roboto-serif/400.css";
import "@fontsource/roboto-mono/400.css";
import "@fontsource/poppins/500.css";

import { BrowserRouter, Route, Routes } from "react-router-dom";

import Home from "./pages/home/Home";
import LandingPage from "./pages/landingPage/LandingPage";
import Login from "./pages/login/Login";
import Root from "./pages/root/Root";
import Success from "./pages/Success";
import UcepFormLoader from "./pages/ucep_form_loader/UcepFormLoader";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/*" element={<Root />} />
        <Route path="/ucep_form_loader" element={<UcepFormLoader />} />
        <Route path="/success" element={<Success />} />
        <Route path="/home" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/lp" element={<LandingPage />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
