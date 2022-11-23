import { Link } from "react-router-dom";

const Tabs = () => {
  return (
    <div className="bg-blue-700 pl-2 pr-2 py-1">
      <ul className="flex ">
        <li className="">
          <Link
            className=" px-6
          
          my-4 text-md active:text-black text-white 
               hover:text-black "
            to={"/home"}
          >
            Dashboard
          </Link>
        </li>
        <li>
          <Link
            className=" px-6
      py-3
      my-4 text-md text-white"
            to={"/home"}
          >
            application
          </Link>
        </li>
        <li>
          <Link
            className=" px-6
      py-3
      my-4 text-md text-white"
            to={"/home"}
          >
            Verification
          </Link>
        </li>
        <li>
          <Link
            className=" px-6
      py-3
      my-4 text-md text-white"
            to={"/home"}
          >
            My information
          </Link>
        </li>
        <li>
          <Link
            className=" px-6
      py-3
      my-4 text-md text-white"
            to={"/home"}
          >
            Notices
          </Link>
        </li>
        <li>
          <Link
            className=" px-6
      py-3
      my-4 text-md text-white"
            to={"/home"}
          >
            cases
          </Link>
        </li>
        <li>
          <Link
            className=" px-6
      py-3
      my-4 text-md text-white"
            to={"/home"}
          >
            payments
          </Link>
        </li>
        <li></li>
      </ul>
    </div>
  );
};

export default Tabs;
