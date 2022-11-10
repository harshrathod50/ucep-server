import type { FC, PropsWithChildren } from "react";

import Footer from "./footer/Footer";
import Header from "./header/Header";

const Layout: FC<PropsWithChildren> = (props) => {
  return (
    <>
      <Header />
      {props.children}
      <Footer />
    </>
  );
};

export default Layout;
