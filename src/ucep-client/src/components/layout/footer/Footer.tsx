const Footer = () => {
  return (
    <div>
      {" "}
      <footer className="absolute bottom-0 w-full">
        <div className="bg-gradient-to-b from-appColor-600 to-gradientColor-600">
          <div className="flex container mx-auto py-2">
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
  );
};

export default Footer;
