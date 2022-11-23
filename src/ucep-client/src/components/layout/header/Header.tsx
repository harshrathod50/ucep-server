import type { FC, PropsWithChildren } from "react";

const Header: FC<PropsWithChildren> = (props) => {
  return (
    <>
      <header>
        <div className="w-full  bg-gradient-to-b from-appColor-600 to-gradientColor-600 ">
          <div className="flex container mx-auto py-3">
            <h4 className="text-2xl  text-white">ucep</h4>
            <nav className="ml-auto flex flex-row gap-3 flex-end">
              <span className=" flex text-white ">home</span>
              <span className="text-white">Create Account</span>
              <button className="rounded-2xl border border-inherit px-6 py-0.5 bg-white hover:bg-gray-100 ">
                Log out
              </button>
            </nav>
          </div>
        </div>
      </header>
    </>
  );
};

export default Header;
