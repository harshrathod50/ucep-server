import ArrowForwardIcon from "@mui/icons-material/ArrowForward";
import { Button, Typography } from "@mui/material";

import Layout from "../../components/layout/Layout";
import Tabs from "../../components/layout/tabs/Tabs";

const Home = () => {
  return (
    <>
      <Layout>
        <Tabs />
        <Typography
          variant="h4"
          sx={{ marginLeft: "15px", marginBottom: "40px" }}
        >
          <h4>welcome citizen</h4>
        </Typography>
        <div className="flex justify-evenly -ml-14 mt-6">
          <Button
            variant="contained"
            className="flex flex-around basis-1/4 grow-1 shrink-0 "
          >
            check what you might get{" "}
            <ArrowForwardIcon className="ml-auto" fontSize="medium" />
          </Button>
          <Button variant="contained" className="basis-1/4 grow-1 shrink-0">
            Apply for benefits{" "}
            <ArrowForwardIcon className="ml-auto" fontSize="medium" />
          </Button>
          <Button variant="contained" className="basis-1/4 grow-1 shrink-0">
            My information{" "}
            <ArrowForwardIcon className="ml-auto" fontSize="medium" />
          </Button>
        </div>
        <div className="flex gap-5 mt-4">
          <div className="basis-1/4 rounded overflow-hidden h-40 shadow-lg bg-neutral-100 ml-8">
            <div className="px-6 py-4">
              <div className="font-bold cardHeader text-xl mb-2 border border-gray-300 shadow text-center">
                EBT Balance
              </div>
              <p className="text-gray-700 text-base text-center">
                no balace information available
              </p>
            </div>
          </div>
          <div className="basis-4/6 rounded overflow-hidden h-40 shadow-lg bg-gray-100 ">
            <div className="px-6 py-4">
              <div className="font-bold cardHeader text-xl mb-2 border border-gray-300 shadow text-center">
                Account Summary
              </div>
              <p className="text-gray-700 text-base text-center">
                no balace information available
              </p>
            </div>
          </div>
        </div>
        <div className="flex gap-5 mt-4">
          <div className="basis-1/4 rounded overflow-hidden h-40 shadow-lg bg-neutral-100 ml-8">
            <div className="px-6 py-4">
              <div className="font-bold  cardHeader text-xl mb-2 border border-gray-300 shadow text-center">
                Recent payments
              </div>
              <p className="text-gray-700 text-base text-center">
                no balace information available
              </p>
            </div>
          </div>
          <div className="basis-4/6 rounded overflow-hidden h-40 shadow-lg bg-gray-100 ">
            <div className="px-6 py-4">
              <div className="font-bold cardHeader text-xl mb-2 border border-gray-300 shadow text-center">
                Application Summary
              </div>
              <p className="text-gray-700 text-base text-center">
                no balace information available
              </p>
            </div>
          </div>
        </div>
      </Layout>
    </>
  );
};

export default Home;
