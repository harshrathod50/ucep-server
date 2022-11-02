import "./App.css";
import "@fontsource/roboto/400.css";
import "@fontsource/roboto-serif/400.css";
import "@fontsource/roboto-mono/400.css";

import { BrowserRouter, Route, Routes } from "react-router-dom";

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
      </Routes>
    </BrowserRouter>
  );
};

export default App;
