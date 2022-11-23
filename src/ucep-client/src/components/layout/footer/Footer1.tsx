import TwitterIcon from "@mui/icons-material/Twitter";

const Footer1 = () => {
  return (
    // <div>
    //   {" "}
    //   <footer className="absolute bottom-0 w-full">
    //     <div className="bg-gradient-to-b from-appColor-600 to-gradientColor-600">
    //       <div className="flex container mx-auto py-2">
    //         <h6 className="text-white">
    //           © 2022 Gigatorb | a Gigatorb Group Ltd. company. All Rights
    //           Reserved.
    //         </h6>
    //         <nav className="ml-auto flex flex-row">
    //           <span className="text-white">
    //             Terms of Use | Security | Accessibility | Privacy
    //           </span>
    //         </nav>
    //       </div>
    //     </div>
    //   </footer>
    // </div>
    <>
      <footer className="footer-1 bg-gray-100 py-8 sm:py-12 mt-20 ml-2 overscroll-auto">
        <div className="container mx-auto px-4">
          <div className="sm:flex sm:flex-wrap sm:-mx-4 md:py-4">
            <div className="px-4 s xl:w-1/5">
              <h5 className="text-xl font-bold  mb-6">FAQ</h5>
              <ul className="list-none footer-links">
                <li className="mb-2">
                  <a
                    href="#"
                    className="border-b border-solid border-transparent hover:border-purple-800 hover:text-purple-800"
                  >
                    Contact us
                  </a>
                </li>
                <li className="mb-2">
                  <a
                    href="#"
                    className="border-b border-solid border-transparent hover:border-purple-800 hover:text-purple-800"
                  >
                    Find a service center
                  </a>
                </li>
                <li className="mb-2">
                  <a
                    href="#"
                    className="border-b border-solid border-transparent hover:border-purple-800 hover:text-purple-800"
                  >
                    District direct mobile
                  </a>
                </li>
                <li className="mb-2">
                  <a
                    href="#"
                    className="border-b border-solid border-transparent hover:border-purple-800 hover:text-purple-800"
                  >
                    <b> App</b>
                  </a>
                </li>
                <li className="mb-2">
                  <a
                    href="#"
                    className="border-b border-solid border-transparent hover:border-purple-800 hover:text-purple-800"
                  >
                    Leave feedback
                  </a>
                </li>
              </ul>
            </div>
            <div className="px-4  w-1/5  mt-8 sm:mt-0">
              <h5 className="text-xl font-bold mb-6">
                Health Benifit exchange authority
              </h5>
              <ul className="list-none footer-links">
                <li className="mb-2">
                  <a
                    href="#"
                    className="border-b border-solid border-transparent hover:border-purple-800 hover:text-purple-800"
                  >
                    Privacy Mobile
                  </a>
                </li>
                <li className="mb-2">
                  <a
                    href="#"
                    className="border-b border-solid border-transparent hover:border-purple-800 hover:text-purple-800"
                  >
                    Non-discrimination
                  </a>
                </li>
              </ul>
            </div>
            <div className="px-4 sm:w-1/2 md:w-1/4  w-1/5 mt-8 md:mt-0">
              <h5 className="text-xl font-bold mb-6">
                Department of Health care finance
              </h5>
              <ul className="list-none footer-links">
                <li className="mb-2">
                  <a
                    href="#"
                    className="border-b border-solid border-transparent hover:border-purple-800 hover:text-purple-800"
                  >
                    Privacy policy
                  </a>
                </li>
                <li className="mb-2">
                  <a
                    href="#"
                    className="border-b border-solid border-transparent hover:border-purple-800 hover:text-purple-800"
                  >
                    non-discrimination
                  </a>
                </li>
              </ul>
            </div>
            <div className="px-4 sm:w-1/2 md:w-1/4 xl:w-1/5 mt-8 md:mt-0">
              <h5 className="text-xl font-bold mb-6">
                Department of human service
              </h5>
              <ul className="list-none footer-links">
                <li className="mb-2">
                  <a
                    href="#"
                    className="border-b border-solid border-transparent hover:border-purple-800 hover:text-purple-800"
                  >
                    Support
                  </a>
                </li>
                <li className="mb-2">
                  <a
                    href="#"
                    className="border-b border-solid border-transparent hover:border-purple-800 hover:text-purple-800"
                  >
                    Help Center
                  </a>
                </li>
              </ul>
            </div>
            <div className="px-4 mt-4 sm:mx-auto xl:mt-0 xl:ml-auto">
              <h5 className="text-xl font-bold mb-6 sm:text-center xl:text-left">
                Stay connected
              </h5>
              <div className="flex sm:justify-center xl:justify-start">
                <a
                  href=""
                  className="w-8 h-8 border border-2 border-gray-400 rounded-full text-center py-1  text-white bg-blue-600 hover:border-blue-900"
                >
                  <i className="fab fa-facebook"></i>
                  <TwitterIcon />
                </a>
              </div>
            </div>
          </div>
        </div>
      </footer>
      <div>
        {" "}
        <footer className="">
          <div className="bg-gradient-to-b from-appColor-600 to-gradientColor-600">
            <div className="flex container mx-auto py-1">
              <h6 className="text-white">
                © 2022 Gigatorb | a Gigatorb Group Ltd. company. All Rights
                Reserved.
              </h6>
              <nav className="ml-auto flex flex-row">
                <span className="text-white">
                  Terms of Use | Security | Accessibility | Privacy
                </span>
              </nav>
            </div>
          </div>
        </footer>
      </div>
    </>
  );
};

export default Footer1;
