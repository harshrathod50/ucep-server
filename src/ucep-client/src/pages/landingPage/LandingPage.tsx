import TwitterIcon from "@mui/icons-material/Twitter";

const LandingPage = () => {
  return (
    <>
      <header>
        <div className="fixed w-full opacity-90">
          <div className="flex container mx-auto py-5">
            <h4 className="text-2xl  text-white">
              <img src="uceplogo.png" alt="logo" className="h-8 w-28" />
            </h4>
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
      <div className="landingImage flex ">
        <div className="flex mt-8">
          {/* <Typography className=" flex text-white mt-40">
            dgddhhdhhdsjhdhj
          </Typography> */}
          <div className="basis-1/3 rounded overflow-hidden hover:bg-gray-100 tras h-40 shadow-lg bg-gray-200 ml-8 mt-96">
            <div className="px-6 py-4 ">
              <div className="font-bold cardHeader  text-xl mb-2 border border-gray-300 shadow text-center">
                Find support
              </div>
              <p className="text-gray-700 text-base text-center">
                take a self assesment to find a support
              </p>
            </div>
          </div>

          <div className="basis-1/3 rounded overflow-hidden h-40 shadow-lg bg-gray-200 ml-8 mt-96">
            <div className="px-6 py-4">
              <div className="font-bold cardHeader text-xl mb-2 border border-gray-300 shadow text-center">
                Login
              </div>
              <p className="text-gray-700 text-base text-center">
                login or create an acoount
              </p>
            </div>

            {/* <button className="tect-white">login</button> */}
          </div>
        </div>
      </div>

      <footer className="footer-1 bg-gray-100 py-8 sm:py-12  ml-2 overscroll-auto">
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
                  className="w-8 h-8 border border-2 border-gray-400 rounded-full text-center py-1  text-white bg-slate-800 hover:border-blue-900"
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
          <div className="bg-slate-800">
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

export default LandingPage;
