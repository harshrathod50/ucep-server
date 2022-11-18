import PhoneIcon from "@mui/icons-material/Phone";
import { grey } from "@mui/material/colors";
import type { FC, PropsWithChildren } from "react";

const Header: FC<PropsWithChildren> = (props) => {
  return (
    <header>
      <div className=" bg-gradient-to-b from-appColor-600 to-gradientColor-600">
        <div className="flex container mx-auto py-3">
          <h4 className="text-2xl text-white ">UCEP</h4>
          <nav className="ml-auto flex flex-row">
            <PhoneIcon fontSize="large" sx={{ color: grey[100] }} />
            <span className="text-white">+91-3434-34343</span>
          </nav>
        </div>
      </div>
    </header>
  );
};

export default Header;
