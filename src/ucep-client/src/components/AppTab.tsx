import "./AppTab.css";

import type { FC } from "react";
const AppTab: FC<{ activeTabIndex: number; tabs: { title: string }[] }> = (
  props
) => {
  const something = { color: "white" };
  return (
    <div className="flex ml-8 mb-4">
      {props.tabs.map((tab, index) => (
        <div
          className={`arrow inline cursor-pointer leading-8 h-8 ml-8 mr-6 pl-16 pr-16 relative bg-gray-300
          
                     font-semibold  shadow-md -4 
                     ${index === props.activeTabIndex ? something : ""}`}
        >
          {tab.title}
        </div>
      ))}
    </div>
  );
};

export default AppTab;
