import "./Sections.css";

import type { FC } from "react";

const SectionsPanel: FC<{
  list?: {
    label: string;
    icon: any;
  }[];
}> = (props) => {
  return (
    <div className="bg-white rounded-3xl  px-2 py-5 mt-8 overflow-auto hide-scrollbar flex flex-row neu-box">
      {props?.list?.map((section, index, list) => {
        if (index === 0) {
          return (
            <div
              key={Math.random()}
              className={`basis-1/5 flex flex-row gap-2 bg-gradient-to-b from-appColor-600  to-gradientColor-600
              text-white justify-center px-4 py-2 text-center
              shadow shadow-inherit arrow-left`}
            >
              <section.icon />
              {section.label}
            </div>
          );
        } else if (index === list.length - 1) {
          return (
            <div
              key={Math.random()}
              className={`basis-1/5 flex flex-row gap-2 bg-gray-300
            text-white justify-center px-4 py-2 text-center
            shadow shadow-inherit arrow-right`}
            >
              <section.icon />
              {section.label}
            </div>
          );
        }
        return (
          <div
            key={Math.random()}
            className={`basis-1/5 flex flex-row gap-2 bg-gray-300
          text-white justify-center px-4 py-2 text-center
          shadow shadow-inherit arrow`}
          >
            <section.icon />
            {section.label}
          </div>
        );
      })}
    </div>
  );
};

export default SectionsPanel;
