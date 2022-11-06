import "./App.css";
import "@fontsource/roboto/400.css";
import "@fontsource/roboto-serif/400.css";
import "@fontsource/roboto-mono/400.css";

import { BrowserRouter, Route, Routes } from "react-router-dom";

import Root from "./pages/root/Root";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/*" element={<Root />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
