import type { FC, PropsWithChildren } from "react";

import Header from "./header/Header";

const Layout: FC<PropsWithChildren> = (props) => {
  return (
    <>
      <Header />
      {props.children}
    </>
  );
};

export default Layout;
