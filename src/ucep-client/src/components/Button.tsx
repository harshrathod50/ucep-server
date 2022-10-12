import type { ButtonHTMLAttributes, FC, PropsWithChildren } from "react";

/**
 * `Button` component.
 * @returns `ReactElement`
 */
const Button: FC<PropsWithChildren<ButtonHTMLAttributes<HTMLButtonElement>>> = (
  props
) => {
  const { children, className, type, ...rest } = props;
  return (
    <button
      type={type ? type : "button"}
      className={
        className
          ? className
          : `inline px-7 py-3 bg-blue-600 text-white 
            font-medium text-sm leading-snug uppercase rounded shadow-md 
          hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 
            focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 
            active:shadow-lg transition duration-150 ease-in-out`
      }
      {...rest}
    >
      {children ? children : "Button"}
    </button>
  );
};

export default Button;
