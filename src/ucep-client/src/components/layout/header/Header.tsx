import PhoneIcon from "@mui/icons-material/Phone";
import type { FC, PropsWithChildren } from "react";

const Header: FC<PropsWithChildren> = (props) => {
  return (
    <header>
      <hr className="border-4 border-blue-600" />
      <div className="flex container mx-auto my-5">
        <h1 className="text-4xl text-blue-600 uppercase">
          Vessel Dues Calculator
        </h1>
        <nav className="ml-auto flex flex-row">
          <PhoneIcon fontSize="large" color="info" />
          <span>+91-3434-34343</span>
        </nav>
      </div>
    </header>
  );
};

export default Header;
