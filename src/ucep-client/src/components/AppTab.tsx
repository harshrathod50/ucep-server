import "./AppTab.css";

import type { FC } from "react";
const AppTab: FC<{ activeTabIndex: number; tabs: { title: string }[] }> = (
  props
) => {
  const something = { backgroundColor: "black" };
  const useStyles = {
    "&.active": {
      backgroundColor: "lightgray",
    },
  };
  return (
    <div className="flex ml-32 mb-4">
      {props.tabs.map((tab, index) => (
        <button
          type="button"
          className={`flex bg-violet-900 active:bg-gray-300 text-white justify-center cp px-32 py-2  text-center shadow shadow-inherit "
                            
                     ${index === props.activeTabIndex ? useStyles : ""}`}
        >
          {tab.title}
        </button>
      ))}
    </div>
  );
};

export default AppTab;
